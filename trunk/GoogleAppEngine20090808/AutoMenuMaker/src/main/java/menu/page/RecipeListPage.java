package menu.page;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;


import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class RecipeListPage extends WebPage {
        
        private String input="";
        private String searchword="";
        
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
	    		}
	    		else {
	    			setResponsePage(new IndexPage(null));
	    		}
	    		
	    		Link<String> importRecipe = new Link<String>("importRecipe"){
	    			@Override
	    			public void onClick() {
	    				setResponsePage(new CookPadRecipeListPage(null));
	    			}
	    		};
	    		add(importRecipe);

                
                //***献立表示ページへのリンク***//
                Form<Void> menuForm = new Form<Void>("menuForm");
                        menuForm.add(new Button("generateButton"){
                                //ボタンを押すと献立表示ページに遷移
                                @Override
                                public void onSubmit() {
//                                      setResponsePage(<T>.class);
                                }
                        });
                add(menuForm);
                
                //***レシピ作成へのリンク***//
                Form<Void> recipeForm = new Form<Void>("recipeForm");
                        recipeForm.add(new Button("makeButton"){
                                //ボタンを押すとレシピ作成のページに遷移
                                @Override
                                public void onSubmit() {
                                        setResponsePage(MakeRecipePage.class);
                                }
                        });
                add(recipeForm);
                
                //***検索処理***//
                final TextField<String> text=new TextField<String>("search",new PropertyModel<String>(this,"input"));
                Form<Void> searchForm = new Form<Void>("searchForm"){
                	protected void onSubmit(){
                			String inputData=text.getModelObject();
                			searchword=input;
	                        //***検索ワードでDBにアクセスして検索を行う***//
	                        //***検索結果を画面に反映させる***//
	                        //***遷移したあとはテキストフィールドを消去しておく***//
	                        System.out.println("デバッグ中:"+searchword);
                        
	                        //***テキストフィールドの消去***//
	                        input="";
	                        setResponsePage(RecipeListPage.class);
	                }
                };
                searchForm.add(text);
                add(searchForm);
        }
}
