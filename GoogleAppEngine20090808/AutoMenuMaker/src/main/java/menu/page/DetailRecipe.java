package menu.page;



import java.util.List;

import menu.dao.RecipeDao;
import menu.dto.Recipe;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.wicket.PageParameters;

public class DetailRecipe {

	
	
	public DetailRecipe(final PageParameters parameters){

		RecipeDao dao=new RecipeDao();
		List<Recipe> mainList= dao.findListByCategory("メイン");
		List<Recipe> mealList= dao.findListByCategory("ご飯");
		List<Recipe> appetizerList= dao.findListByCategory("前菜");
		List<Recipe> soupList= dao.findListByCategory("スープ");
		
		int monMainIndex = RandomUtils.nextInt(mainList.size());
		mainList.get(monMainIndex);
		
		
	}
}
