/**
 * 
 */
package com.appspot.eitan.search.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.appspot.eitan.model.beans.Meaning;


/**
 * DOMを使うパーサ。
 * @author kazu
 */
class DOMStrategy implements ParsingStrategy {

	/* (non-Javadoc)
	 * @see search.parsers.ParsingStrategy#parse(java.lang.String)
	 */
	@Override
	public List<Meaning> parse(InputStream xml) {
		try {
			Document doc = getDocument(xml);
			return parseDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Meaning>();
		}
	}
	
	private Document getDocument(InputStream xml) 
		throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(xml);
	}
	
	private List<Meaning> parseDocument(Document doc) {
		Element vocabularyNode = doc.getDocumentElement();
		NodeList itemsNodes = vocabularyNode.getElementsByTagName("items");
		List<Meaning> result = new ArrayList<Meaning>();
		for (int i = 0; i != itemsNodes.getLength(); ++i)
			parseItemsNode(result, itemsNodes.item(i));
		return result;
	}
	
	private void parseItemsNode(List<Meaning> result, Node itemsNode) {
		NodeList itemNodes = itemsNode.getChildNodes();
		for (int i = 0; i != itemNodes.getLength(); ++i)
			if (itemNodes.item(i).getNodeName().equals("item"))
				parseItemNode(result, itemNodes.item(i));
	}
	
	private void parseItemNode(List<Meaning> result, Node itemNode) {
		try {
			Meaning.WordCategory category = parseForWordCategory(itemNode);
			String meaning = parseForMeaning(itemNode);
			result.add(new Meaning(meaning, category));
		} catch (Exception e) {
			// パース失敗したら追加しない
			e.printStackTrace();
		}
	}
	
	private Meaning.WordCategory parseForWordCategory(Node itemNode) {
		NodeList nodes = itemNode.getChildNodes();
		for (int i = 0; i != nodes.getLength(); ++i) {
			if (nodes.item(i).getNodeName().equals("cue")) {
				NamedNodeMap attrs = nodes.item(i).getAttributes();
				String value = attrs.getNamedItem("part_of_speech").getNodeValue();
				return Meaning.WordCategory.fromString(value);
			}
		}
		throw new RuntimeException("ここには来ない");
	}
	
	private String parseForMeaning(Node itemNode) {
		NodeList nodes = itemNode.getChildNodes();
		for (int i = 0; i != nodes.getLength(); ++i) {
			if (nodes.item(i).getNodeName().equals("responses"))
				return parseResponsesNode(nodes.item(i));
		}
		throw new RuntimeException("ここには来ない");
	}
	
	private String parseResponsesNode(Node responsesNode) {
		NodeList nodes = responsesNode.getChildNodes();
		for (int i = 0; i != nodes.getLength(); ++i) {
			if (nodes.item(i).getNodeName().equals("response"))
				return parseResponseNode(nodes.item(i));			
		}
		throw new RuntimeException("ここには来ない");
	}
	
	private String parseResponseNode(Node responseNode) {
		NodeList nodes = responseNode.getChildNodes();
		for (int i = 0; i != nodes.getLength(); ++i) {
			if (nodes.item(i).getNodeName().equals("text"))
				return parseTextNode(nodes.item(i));
		}
		throw new RuntimeException("ここには来ない");
	}
	
	private String parseTextNode(Node textNode) {
		return textNode.getChildNodes().item(0).getNodeValue();		
	}
}
