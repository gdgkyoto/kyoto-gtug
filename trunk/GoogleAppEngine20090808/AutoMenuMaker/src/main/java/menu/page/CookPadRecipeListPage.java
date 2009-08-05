package menu.page;

import java.io.InputStream;
import java.net.MalformedURLException;
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

import menu.dao.RecipeDao;
import menu.dto.Recipe;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.UrlResourceStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class CookPadRecipeListPage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	protected static Logger log = LoggerFactory.getLogger(CookPadRecipeListPage.class);
	
	//private RecipeDao recipeDao = new RecipeDao();
	
	public CookPadRecipeListPage(final PageParameters parameters) {
		
		final TextField<String> conditionField = new TextField<String>("condition", new Model<String>(""));
		
		final ListView<Recipe> recipeListView = new ListView<Recipe>("recipeList") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<Recipe> item) {
				final Recipe recipe = item.getModelObject();
				item.add(new CheckBox("updateFlag", new Model<Boolean>()));
				item.add(new Label("no", new Model<Integer>(item.getIndex())));
				item.add(new Label("title", new PropertyModel<String>(recipe, "title")));
				item.add(new Label("description", new PropertyModel<String>(recipe, "description")));
				item.add(new Label("material", new PropertyModel<String>(recipe, "material")));
				item.add(new Image("img", new WebResource() {
					private static final long serialVersionUID = 1L;
					@Override
					public IResourceStream getResourceStream() {
						try {
							return new UrlResourceStream(new URL(recipe.getImgPath()));
						}
						catch (MalformedURLException e) {
							log.error("ひとまずもみ消します", e);
							return null;
						}
					}
				}));
			}
		};

		final Button searchButton = new Button("searchButton") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				try {
					String condition = URLEncoder.encode(conditionField.getModelObject(), "UTF-8");
					
//					// URLFetch
//					URLFetchService ufs = URLFetchServiceFactory.getURLFetchService();
					String url = "http://www.daisukeuchida.com/services/cookpadxml.php?keyword=" + condition + "&page=1";
//					HTTPRequest httpRequest = new HTTPRequest(new URL(url));
//					HTTPResponse httpResponse = ufs.fetch(httpRequest);
//					
//					String s = new String(httpResponse.getContent(), "UTF-8");
//					log.info(s);
					
//					InputStream stream = new ByteArrayInputStream(httpResponse.getContent());
					
					
					
			        URL urlPathtraq = new URL(url);
			        InputStream stream = urlPathtraq.openStream();
			        
			        
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document dom = db.parse(stream);
					
					//XmlUtils.dumpNode(dom);
					
					//XPathFactory factory = XPathFactory.newInstance();
					XPathFactory factory = (XPathFactory) Class.forName("org.apache.xpath.jaxp.XPathFactoryImpl").newInstance();
					XPath xpath = factory.newXPath();
					XPathExpression expr = xpath.compile("/result/recipes/recipe");
					NodeList recipeNodes = (NodeList) expr.evaluate(dom, XPathConstants.NODESET);
					List<Recipe> recipeList = new ArrayList<Recipe>();
					for (int i = 0; i < recipeNodes.getLength(); i++) {
					    Element element = (Element)recipeNodes.item(i);
					    Element recipeTitle = (Element)element.getElementsByTagName("recipeTitle").item(0);
					    Element image = (Element)element.getElementsByTagName("image").item(0);
					    Element description = (Element)element.getElementsByTagName("description").item(0);
					    Element material = (Element)element.getElementsByTagName("material").item(0);
					    Recipe recipe = new Recipe();
					    recipe.setTitle(recipeTitle.hasChildNodes() ? recipeTitle.getFirstChild().getNodeValue() : null);
					    recipe.setImgPath(image.hasChildNodes() ? image.getFirstChild().getNodeValue() : null);
					    recipe.setDescription(description.hasChildNodes() ? description.getFirstChild().getNodeValue() : null);
					    recipe.setMaterial(material.hasChildNodes() ? material.getFirstChild().getNodeValue() : null);
					    recipeList.add(recipe);
					    
//					    System.out.println("title:" + recipe.getTitle());
//					    System.out.println("imgPath:" + recipe.getImgPath());
//					    System.out.println("description" + recipe.getDescription());
//					    System.out.println("material:" + recipe.getMaterial());
					}
					
					recipeListView.setList(recipeList);
				}
				catch (Exception e) {
					log.error("kitamura faild", e);
					throw new IllegalStateException(e);
				}
				
				//throw new IllegalStateException("hoge");
			}
		};
		
		final Button importButton = new Button("importButton") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				List<Recipe> recipeList = recipeListView.getModelObject();
				RecipeDao dao = new RecipeDao();
				for (int i = 0; i < recipeList.size(); i++) {
					ListItem<Recipe> item = (ListItem<Recipe>)recipeListView.get(i);
					CheckBox checkBox = (CheckBox)item.get("updateFlag");
					if (checkBox.getModelObject()) {
						dao.insert(recipeList.get(i));
					}
				}
			}
		};
		
		Form<Void> submitForm = new Form<Void>("submitForm");
		submitForm.add(conditionField);
		submitForm.add(recipeListView);
		submitForm.add(searchButton);
		submitForm.add(importButton);
		add(submitForm);
	}

}
