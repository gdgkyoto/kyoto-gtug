package menu;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class MakeRecipe extends WebPage{
	
	private String inputTitle="";
	private String inputMaterial="";
	private String inputCook="";
	
	public MakeRecipe(final PageParameters parameters) {
        	
			//***タイトル入力***//
        	final TextField<String> title=new TextField<String>("title",new PropertyModel<String>(this,"inputTitle"));
        	
        	//***材料を入力***//
        	final TextArea<String> Material = new TextArea<String>("Material",new PropertyModel<String>(this,"inputMaterial"));
        	
        	//***材料を入力***//
        	final TextArea<String> Cook = new TextArea<String>("Cook",new PropertyModel<String>(this,"inputCook"));
        	
        	//***新規レシピ入力フォーム***//
        	Form<Void> recipeForm = new Form<Void>("recipeForm"){
        		protected void onSubmit(){
        			
        			//***入力データの取得***//
        			String titleData=title.getModelObject();
        			String materialData=Material.getModelObject();
        			String cookData=Material.getModelObject();
        			
        			System.out.println("デバッグ中:"+titleData);
        			System.out.println("デバッグ中:"+materialData);
        			System.out.println("デバッグ中:"+cookData);
        			
        			//***次のページに遷移***//
        			//setResponsePage(MakeRecipe.class);
            
        		}
        	};
        	recipeForm.add(title);
        	recipeForm.add(Material);
        	recipeForm.add(Cook);
        	add(recipeForm);
	}
}

