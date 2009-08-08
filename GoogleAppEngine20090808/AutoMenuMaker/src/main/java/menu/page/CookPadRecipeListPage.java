package menu.page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

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
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.UrlResourceStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookPadRecipeListPage extends WebPage {

	private static final long serialVersionUID = 1L;

	protected static Logger log = LoggerFactory.getLogger(CookPadRecipeListPage.class);
	private transient RecipeDao recipeDao = new RecipeDao();

	public CookPadRecipeListPage(final PageParameters parameters) {

		final TextField<String> conditionField = new TextField<String>("condition", new Model<String>(""));
		
		final Form<Void> submitForm = new Form<Void>("submitForm");

		CookPadRecipeDataProvider dataProvider = new CookPadRecipeDataProvider();
		final DataView<Recipe> recipeListView = new DataView<Recipe>("recipeList", dataProvider) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Recipe> item) {
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
						} catch (MalformedURLException e) {
							log.error("エラー", e);
							throw new IllegalStateException(e);
//							try {
//								return new UrlResourceStream(new URL("http://img7.cookpad.com/recipe/t/1198/755/F64EE27AF4008851823E14378E3C6543.jpg?1248713164"));
//							}
//							catch (Exception ee) {
//								return null;
//							}
						}
					}
				}));
			}
		};

		PagingNavigator pagingNavigator = new PagingNavigator("paging", recipeListView);

		final Button searchButton = new Button("searchButton") {

			private static final long serialVersionUID = 1L;

			/* (non-Javadoc)
			 * @see org.apache.wicket.markup.html.form.Button#onSubmit()
			 */
			@Override
			public void onSubmit() {
				CookPadRecipeDataProvider provider = (CookPadRecipeDataProvider) recipeListView.getDataProvider();
				provider.setCondition(conditionField.getModelObject());
				recipeListView.setCurrentPage(0);
				
//				CookPadRecipeDataProvider dataProvider2 = new CookPadRecipeDataProvider();
//				dataProvider2.setCondition(conditionField.getModelObject());
//				final DataView<Recipe> recipeListView2 = new DataView<Recipe>("recipeList", dataProvider2) {
//
//					private static final long serialVersionUID = 1L;
//
//					@Override
//					protected void populateItem(Item<Recipe> item) {
//						final Recipe recipe = item.getModelObject();
//						item.add(new CheckBox("updateFlag", new Model<Boolean>()));
//						item.add(new Label("no", new Model<Integer>(item.getIndex())));
//						item.add(new Label("title", new PropertyModel<String>(recipe, "title")));
//						item.add(new Label("description", new PropertyModel<String>(recipe, "description")));
//						item.add(new Label("material", new PropertyModel<String>(recipe, "material")));
//						item.add(new Image("img", new WebResource() {
//
//							private static final long serialVersionUID = 1L;
//
//							@Override
//							public IResourceStream getResourceStream() {
//								try {
//									return new UrlResourceStream(new URL(recipe.getImgPath()));
//								} catch (MalformedURLException e) {
//									log.error("エラー", e);
//									throw new IllegalStateException(e);
//								}
//							}
//						}));
//					}
//				};
//
//				PagingNavigator pagingNavigator2 = new PagingNavigator("paging", recipeListView2);
//				
//				submitForm.replace(recipeListView2);
//				submitForm.replace(pagingNavigator2);
			}
		};

		final Button importButton = new Button("importButton") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				Iterator<Item<Recipe>> recipes = recipeListView.getItems();
				while (recipes.hasNext()) {
					Item<Recipe> item = recipes.next();
					CheckBox checkBox = (CheckBox) item.get("updateFlag");
					if (checkBox.getModelObject()) {
						recipeDao.insert(item.getModelObject());
					}
				}
			}
		};

		
		submitForm.add(conditionField);
		submitForm.add(recipeListView);
		submitForm.add(searchButton);
		submitForm.add(importButton);
		submitForm.add(pagingNavigator);
		add(submitForm);
	}

}
