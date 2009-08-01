package gtug.menu;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class IndexPage extends WebPage {
	
	private String input="";
	private String searchword="";
	
	public IndexPage(final PageParameters parameters) {
		
		//***献立表示ページへのリンク***//
		Form<Void> menuForm = new Form<Void>("menuForm");
			menuForm.add(new Button("generateButton"){
				//ボタンを押すと献立表示ページに遷移
				@Override
				public void onSubmit() {
//					setResponsePage(<T>.class);
				}
			});
		add(menuForm);
		
		//***レシピ作成へのリンク***//
		Form<Void> recipeForm = new Form<Void>("recipeForm");
			recipeForm.add(new Button("makeButton"){
				//ボタンを押すとレシピ作成のページに遷移
				@Override
				public void onSubmit() {
					setResponsePage(MakeRecipe.class);
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
        		setResponsePage(IndexPage.class);
        	}
       };
       searchForm.add(text);
       add(searchForm);
       
	}
}
