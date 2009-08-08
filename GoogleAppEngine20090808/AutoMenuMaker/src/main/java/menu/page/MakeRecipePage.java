package menu.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import menu.dao.RecipeDao;
import menu.dto.Recipe;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;


public class MakeRecipePage extends WebPage{
	
	private transient RecipeDao dao = new RecipeDao();
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
        		@Override
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
        			
        			Recipe makeRecipe = new Recipe(categoryData,titleData,cookData, materialData, null);
        			System.out.println("test");
        			dao.insert(makeRecipe);
        			
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
	
	public class ChoiceElement implements Serializable {

		private String id;
		private String name;
		private String listStr;
		
		public ChoiceElement(String id,String name){
			this.id=id;
			this.name=name;
		}
		
		public String getId(){
			return id;
		}
		
		public void setId(String id){
			this.id=id;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String name){
			this.name=name;
		}
		
		public String getListStr() {
		    return listStr;
		  }

		  public void setListStr(String listStr) {
		    this.listStr = listStr;
		  }
	}
}

