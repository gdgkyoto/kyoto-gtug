package menu.page;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import menu.dto.Recipe;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.UrlResourceStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class RecipeListPage extends WebPage {
	
	protected static Logger log = LoggerFactory.getLogger(CookPadRecipeListPage.class);

	public RecipeListPage(final PageParameters parameters) {

		UserService userService = UserServiceFactory.getUserService();
		HttpServletRequest request = getWebRequestCycle().getWebRequest().getHttpServletRequest();
		String thisURL = request.getRequestURI();
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			User user = userService.getCurrentUser();
			Label nicknameLabel = new Label("nickname", user.getNickname());
			add(nicknameLabel);

			String url = userService.createLogoutURL(thisURL);
			ExternalLink link = new ExternalLink("logout", url);
			add(link);
		} else {
			setResponsePage(new IndexPage(null));
		}

		Link<String> importRecipe = new Link<String>("importRecipe") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new CookPadRecipeListPage(null));
			}
		};
		add(importRecipe);

		//***献立表示ページへのリンク***//
		Form<Void> menuForm = new Form<Void>("menuForm");
		menuForm.add(new Button("generateButton") {
			private static final long serialVersionUID = 1L;
			//ボタンを押すと献立表示ページに遷移
			@Override
			public void onSubmit() {
				setResponsePage(AutoMenuPage.class);
			}
		});
		add(menuForm);

		//***レシピ作成へのリンク***//
		Form<Void> recipeForm = new Form<Void>("recipeForm");
		recipeForm.add(new Button("makeButton") {
			private static final long serialVersionUID = 1L;
			//ボタンを押すとレシピ作成のページに遷移
			@Override
			public void onSubmit() {
				setResponsePage(MakeRecipePage.class);
			}
		});
		add(recipeForm);

		//***検索処理***//
		RecipeDataProvider dataProvider = new RecipeDataProvider();
		final DataView<Recipe> recipeListView = new DataView<Recipe>("recipeList", dataProvider) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(Item<Recipe> item) {
				final Recipe recipe = item.getModelObject();
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
						}
					}
				}));
			}
		};
		
		PagingNavigator pagingNavigator = new PagingNavigator("paging", recipeListView);
		
		final TextField<String> conditionField = new TextField<String>("search", new Model<String>(""));
		Form<Void> searchForm = new Form<Void>("searchForm") {
			private static final long serialVersionUID = 1L;
			protected void onSubmit() {
				RecipeDataProvider provider = (RecipeDataProvider) recipeListView.getDataProvider();
				provider.setCondition(conditionField.getModelObject());
				recipeListView.setCurrentPage(0);
			}
		};
		searchForm.add(conditionField);
		add(searchForm);
		add(recipeListView);
		add(pagingNavigator);

	}
}
