package twkazemap.cron.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import twkazemap.PMF;
import twkazemap.entity.Twitter;

import com.e.tobitobi.GaeClientConnectionManager;

public class TwitterCollectingService {
	private static final Logger LOG = Logger.getLogger(TwitterCollectingService.class.getName());

	public void collect() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		HttpClient httpClient = new DefaultHttpClient(new GaeClientConnectionManager(), new BasicHttpParams());
		InputStream is = null;
		Document doc = null;
		Twitter lastTweet = null;
		try {
			Query q = pm.newQuery(Twitter.class);
			q.setOrdering("statusId desc");
			q.setRange(0, 1);
			try {
				@SuppressWarnings("unchecked")
				List<Twitter> results = (List<Twitter>) q.execute();
				if (results.iterator().hasNext()) {
					for (Twitter e : results) {
						lastTweet = e;
					}
				}
			} finally {
				q.closeAll();
			}

			final String lang = "ja";
			final String query = URLEncoder.encode("風邪 OR インフル", "utf-8");
			final int rpp = 100;
			final String geoCode = URLEncoder.encode("35.0,135.0,1500km", "utf-8");
			StringBuilder sb = new StringBuilder();
			sb.append("lang=");
			sb.append(lang);
			sb.append("&q=");
			sb.append(query);
			sb.append("&rpp=");
			sb.append(rpp);
			sb.append("&geocode=");
			sb.append(geoCode);
			if (null != lastTweet) {
				sb.append("&since_id=");
				sb.append(lastTweet.getStatusId());
			}
			HttpGet httpMethod = new HttpGet(String.format("http://search.twitter.com/search.atom?%s", sb.toString()));
			LOG.config(String.format("GET\tURL:%s", httpMethod.getURI().toString()));

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpClient.execute(httpMethod, responseHandler);
			// LOG.config(responseBody);

			is = new ByteArrayInputStream(responseBody.getBytes("utf-8"));
			doc = this.load(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		httpClient.getConnectionManager().shutdown();

		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			@Override
			public String getNamespaceURI(String prefix) {
				if (prefix == null) {
					throw new NullPointerException("Null prefix");
				} else if ("google".equals(prefix)) {
					return "http://base.google.com/ns/1.0";
				} else if ("twitter".equals(prefix)) {
					return "http://api.twitter.com/";
				} else if ("atom".equals(prefix)) {
					return "http://www.w3.org/2005/Atom";
				} else if ("georss".equals(prefix)) {
					return "http://www.georss.org/georss";
				}
				return XMLConstants.NULL_NS_URI;
			}

			@Override
			public String getPrefix(String namespaceURI) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Iterator<String> getPrefixes(String namespaceURI) {
				throw new UnsupportedOperationException();
			}
		});
		try {
			NodeList list = (NodeList) xpath.evaluate("/atom:feed/atom:entry", doc, XPathConstants.NODESET);
			for (int i = 0; i < list.getLength(); ++i) {
				Node n = list.item(i);
				String id = (String) xpath.evaluate(String.format("atom:id/text()", i), n, XPathConstants.STRING);
				id = id.substring(1 + id.lastIndexOf(":"));
				String text = (String) xpath.evaluate(String.format("atom:title/text()", i), n, XPathConstants.STRING);
				String update = (String) xpath.evaluate(String.format("atom:published/text()", i), n,
						XPathConstants.STRING);
				Date theUpdate = null;
				String updateStr = "";
				if (null != update && !"".equals(update)) {
					theUpdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(update);
					updateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(theUpdate);
				}
				String name = (String) xpath.evaluate(String.format("atom:author/atom:name/text()", i), n,
						XPathConstants.STRING);
				String nameId = name.substring(0, name.indexOf(" "));
				String showName = name.substring(1 + name.indexOf("("), name.indexOf(")"));
				String geo = (String) xpath.evaluate(String.format("twitter:geo/georss:point/text()", i), n,
						XPathConstants.STRING);
				boolean geoEnabled = null != geo && !"".equals(geo);
				String location = (String) xpath
						.evaluate(String.format("google:location", i), n, XPathConstants.STRING);

				LOG.info(String.format("%d:%s, %s, %s, [%s], %s, %s, %b, %s", i, id, updateStr, nameId, showName, text,
						geo, geoEnabled, location));

				Twitter t = new Twitter();
				t.setCreateDatetime(theUpdate);
				t.setStatusId(Long.parseLong(id));
				t.setText(text);
				t.setUserName(nameId);
				t.setUserShowName(showName);
				t.setGeoEnabled(geoEnabled);
				t.setGeoPosition(geo);

				// ユーザId
				// 場所
				// プロファイル画像URL

				// responseBody = StringEscapeUtils.escapeXml(responseBody);

				// Query query = pm.newQuery(Twitter.class);
				pm.makePersistent(t);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			pm.close();
		}
	}

	public Document load(InputStream is) {
		Document doc = null;
		try {
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			// fac.setValidating(true);
			fac.setNamespaceAware(true);
			DocumentBuilder db = fac.newDocumentBuilder();
			db.setErrorHandler(new ErrorHandler() {
				public void warning(SAXParseException exception) throws SAXException {
					throw exception;
				}

				public void fatalError(SAXParseException exception) throws SAXException {
					throw exception;
				}

				public void error(SAXParseException exception) throws SAXException {
					throw exception;
				}
			});
			doc = db.parse(is);
			if (null == doc) {
				throw new RuntimeException("null.");
			}
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
		}
		return doc;
	}
}
