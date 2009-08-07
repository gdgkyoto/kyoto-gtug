package menu.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import menu.page.ChoiceElement;

public class MakeRecipePage extends WebPage{
	
	private String inputTitle="";
	private String inputMaterial="";
	private String inputCook="";
	private Collection<ChoiceElement> selection;
	
	public MakeRecipePage(final PageParameters parameters) {
	     	List<ChoiceElement> choices =Arrays.asList(
	     		new ChoiceElement("Meal","ご飯"),
	     		new ChoiceElement("Appetizer","前菜"),
	     		new ChoiceElement("Main","メイン"),
	     		new ChoiceElement("Soup","スープ"));
     
        	
			//***カテゴリ選択***//
	     	final ListMultipleChoice<ChoiceElement> select = new ListMultipleChoice<ChoiceElement>(
     			"category",
     			new PropertyModel<Collection<ChoiceElement>>(MakeRecipePage.this,"selection"),
     			choices,
     			new ChoiceRenderer<ChoiceElement>("name","id"));
			

	     	
			//***タイトル入力***//
        	final TextField<String> title = new TextField<String>("title",new PropertyModel<String>(this,"inputTitle"));
        	
        	//***材料を入力***//
        	final TextArea<String> Material = new TextArea<String>("Material",new PropertyModel<String>(this,"inputMaterial"));
        	
        	//***作り方を入力***//
        	final TextArea<String> Cook = new TextArea<String>("Cook",new PropertyModel<String>(this,"inputCook"));
        	
        	//***新規レシピ入力フォーム***//
        	Form<Void> recipeForm = new Form<Void>("recipeForm"){
        		protected void onSubmit(){
        			
        			//***入力データの取得***//
        			String categoryData="";
        			
        			if(selection!=null){
	         			List<String>labelValue=new ArrayList<String>();
	         			for(ChoiceElement elem:selection){
	         				labelValue.add(elem.getName());
	         			}
	         			categoryData=labelValue.toString();
        			}else{
        				
        			}
	         		
        			String titleData=title.getModelObject();
        			String materialData=Material.getModelObject();
        			String cookData=Material.getModelObject();
        			
        			System.out.println("デバッグ中:"+categoryData);
        			System.out.println("デバッグ中:"+titleData);
        			System.out.println("デバッグ中:"+materialData);
        			System.out.println("デバッグ中:"+cookData);
        			
        			//***次のページに遷移***//
        			setResponsePage(RecipeListPage.class);
            
        		}
        	};
        	recipeForm.add(select);
        	recipeForm.add(title);
        	recipeForm.add(Material);
        	recipeForm.add(Cook);
        	add(recipeForm);
	}
}

