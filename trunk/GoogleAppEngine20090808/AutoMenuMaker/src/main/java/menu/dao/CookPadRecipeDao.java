package menu.dao;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import menu.dto.Recipe;
import menu.util.XmlUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class CookPadRecipeDao {

	XPathFactory xPathFactory;

	public List<Recipe> findList(String condition, int page) throws DataAccessException {
		try {
			Document dom = callCookPadAPI(condition, page);

			XPath xpath = getXPathFactory().newXPath();
			XPathExpression expr = xpath.compile("/result/recipes/recipe");
			NodeList recipeNodes = (NodeList) expr.evaluate(dom, XPathConstants.NODESET);
			List<Recipe> recipeList = new ArrayList<Recipe>();
			for (int i = 0; i < recipeNodes.getLength(); i++) {
				Element element = (Element) recipeNodes.item(i);
				Element recipeTitle = (Element) element.getElementsByTagName("recipeTitle").item(0);
				Element image = (Element) element.getElementsByTagName("image").item(0);
				Element description = (Element) element.getElementsByTagName("description").item(0);
				Element material = (Element) element.getElementsByTagName("material").item(0);
				Recipe recipe = new Recipe();
				recipe.setTitle(recipeTitle.hasChildNodes() ? recipeTitle.getFirstChild().getNodeValue() : null);
				recipe.setImgPath(image.hasChildNodes() ? image.getFirstChild().getNodeValue() : null);
				recipe.setDescription(description.hasChildNodes() ? description.getFirstChild().getNodeValue() : null);
				recipe.setMaterial(material.hasChildNodes() ? material.getFirstChild().getNodeValue() : null);
				recipeList.add(recipe);
			}

			return recipeList;
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public int count(String condition) throws DataAccessException {
		try {
			Document dom = callCookPadAPI(condition, 1);

			XPath xpath = getXPathFactory().newXPath();
			XPathExpression expr = xpath.compile("/result/query/recipeCount");
			Node countNode = (Node) expr.evaluate(dom, XPathConstants.NODE);
			//XmlUtils.dumpNode(dom);
			return Integer.parseInt(countNode.getFirstChild().getNodeValue());
		} catch (DataAccessException e) {
			throw e;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	protected Document callCookPadAPI(String condition, int page) throws DataAccessException {
		try {
			String encodeCondition = URLEncoder.encode(condition, "UTF-8");

			// URLFetch
			String url = "http://www.daisukeuchida.com/services/cookpadxml.php?keyword=" + encodeCondition + "&page=" + page;
			URL urlPathtraq = new URL(url);
			InputStream stream = urlPathtraq.openStream();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(stream);

			//XmlUtils.dumpNode(dom);
		} catch (Exception e) {
			throw new DataAccessException("CookPad���V�s�̎擾�Ɏ��s���܂����B", e);
		}
	}
	
	protected XPathFactory getXPathFactory() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (xPathFactory == null) {
			// xPathFactory = XPathFactory.newInstance();
			xPathFactory = (XPathFactory) Class.forName("org.apache.xpath.jaxp.XPathFactoryImpl").newInstance();
		}
		return xPathFactory;
	}

}
