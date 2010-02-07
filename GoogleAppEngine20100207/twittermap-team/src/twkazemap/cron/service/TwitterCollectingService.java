package twkazemap.cron.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
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
		try {
			Long lastStatusId = 0L;
			Query q = pm.newQuery(Twitter.class);
			q.setOrdering("statusId desc");
			q.setRange(0, 1);
			try {
				Twitter lastTweet = null;
				@SuppressWarnings("unchecked")
				List<Twitter> results = (List<Twitter>) q.execute();
				if (results.iterator().hasNext()) {
					for (Twitter e : results) {
						lastTweet = e;
					}
				}
				if (null != lastTweet) {
					lastStatusId = lastTweet.getStatusId();
				}
			} finally {
				q.closeAll();
			}

			final String lang = "ja";
			final String query = URLEncoder.encode("風邪 OR インフル", "utf-8");
			final int rpp = 100;
			HttpGet httpMethod = new HttpGet(String.format(
					"http://search.twitter.com/search.atom?show_user=true&lang=%s&q=%s&rpp=%d&since_id=%d", lang,
					query, rpp, lastStatusId.longValue()));
			LOG.fine(String.format("GET\tURL:%s", httpMethod.getURI().toString()));

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
		try {
			NodeList list = (NodeList) xpath.evaluate("/feed/entry", doc, XPathConstants.NODESET);
			for (int i = 1; i <= list.getLength(); ++i) {
				String id = (String) xpath.evaluate(String.format("/feed/entry[%d]/id/text()", i), doc,
						XPathConstants.STRING);
				id = id.substring(1 + id.lastIndexOf(":"));
				String text = (String) xpath.evaluate(String.format("/feed/entry[%d]/title/text()", i), doc,
						XPathConstants.STRING);
				String update = (String) xpath.evaluate(String.format("/feed/entry[%d]/published/text()", i), doc,
						XPathConstants.STRING);
				Date theUpdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(update);
				String name = (String) xpath.evaluate(String.format("/feed/entry[%d]/author/name/text()", i), doc,
						XPathConstants.STRING);
				String nameId = name.substring(0, name.indexOf(" "));
				String showName = name.substring(1 + name.indexOf("("), name.indexOf(")"));
				String geo = (String) xpath.evaluate(String.format("/feed/entry[%d]/twitter:geo/text()", i), doc,
						XPathConstants.STRING);
				boolean geoEnabled = null == geo || "".equals(geo) ? false : true;
				LOG.info(String.format("%d:%s, %s, %s, [%s], %s", i, id, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
						.format(theUpdate), nameId, showName, text));

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
