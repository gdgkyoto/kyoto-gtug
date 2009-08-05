package menu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class XmlUtils {

	private XmlUtils() {
	}

	public static Document createDocument(String xml) throws SAXException {
		ByteArrayInputStream is = null;
		try {
			is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
			// キャラクターセットが固定値なので例外は発生しません
			e.printStackTrace();
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			// JDK標準の実装を使用するので例外は発生しません
			e.printStackTrace();
		}

		Document doc = null;
		try {
			doc = db.parse(is);
		}
		catch (IOException e) {
			// ストリームをStringから作っているので発生しません
			e.printStackTrace();
		}
		return doc;
	}

	public static String getValue(Element element, String expression) throws XPathExpressionException {
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xp = xpf.newXPath();

		return xp.evaluate(expression, element);
	}

	public static void dumpNode(Node node) {
		TransformerFactory tff = TransformerFactory.newInstance();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Transformer tf;
		try {
			tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "Shift_JIS");
			tf.transform(new DOMSource(node), new StreamResult(out));

			System.out.println(new String(out.toByteArray()));

		}
		catch (TransformerConfigurationException e) {
			System.out.println("XML DocumentのDumpに失敗");
			e.printStackTrace();
		}
		catch (TransformerException e) {
			System.out.println("XML DocumentのDumpに失敗");
			e.printStackTrace();
		}
	}
}
