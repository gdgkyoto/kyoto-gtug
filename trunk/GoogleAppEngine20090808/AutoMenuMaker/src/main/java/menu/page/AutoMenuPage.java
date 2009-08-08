package menu.page;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import menu.dao.RecipeDao;
import menu.dto.Recipe;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

public class AutoMenuPage extends WebPage {
	
	// String[] week = {"月曜日","火曜日","水曜日","木曜日","金曜日","土曜日","日曜日"};
	 
	public AutoMenuPage(final PageParameters parameters) {
		
		RecipeDao dao=new RecipeDao();
		List<Recipe> mainList= dao.findListByCategory("[メイン]");
		List<Recipe> mealList= dao.findListByCategory("[ご飯]");
		List<Recipe> appetizerList= dao.findListByCategory("[前菜]");
		List<Recipe> soupList= dao.findListByCategory("[スープ]");
		
		
		
		int monMainIndex = RandomUtils.nextInt(mainList.size());
		Recipe monmain = mainList.get(monMainIndex);
		
		int monMealIndex = RandomUtils.nextInt(mealList.size());
		Recipe monmeal = mealList.get(monMealIndex);
		
		int monApptizerIndex = RandomUtils.nextInt(appetizerList.size());
		Recipe monapptizer = appetizerList.get(monApptizerIndex);
		
		int monSoupIndex = RandomUtils.nextInt(soupList.size());
		Recipe monsoup = soupList.get(monSoupIndex);
		

		int tueMainIndex = RandomUtils.nextInt(mainList.size());
		Recipe tuemain = mainList.get(tueMainIndex);
		int tueMealIndex = RandomUtils.nextInt(mealList.size());
		Recipe tuemeal = mealList.get(tueMealIndex);
		int tueApptizerIndex = RandomUtils.nextInt(appetizerList.size());
		Recipe tueapptizer = appetizerList.get(tueApptizerIndex);
		int tueSoupIndex = RandomUtils.nextInt(soupList.size());
		Recipe tuesoup = soupList.get(tueSoupIndex);
		
		
		int wedMainIndex = RandomUtils.nextInt(mainList.size());
		Recipe wedmain = mainList.get(wedMainIndex);
		int wedMealIndex = RandomUtils.nextInt(mealList.size());
		Recipe wedmeal = mealList.get(wedMealIndex);
		int wedApptizerIndex = RandomUtils.nextInt(appetizerList.size());
		Recipe wedapptizer = appetizerList.get(wedApptizerIndex);
		int wedSoupIndex = RandomUtils.nextInt(soupList.size());
		Recipe wedsoup = soupList.get(wedSoupIndex);
		
		int thuMainIndex = RandomUtils.nextInt(mainList.size());
		Recipe thumain = mainList.get(thuMainIndex);
		int thuMealIndex = RandomUtils.nextInt(mealList.size());
		Recipe thumeal = mealList.get(thuMealIndex);
		int thuApptizerIndex = RandomUtils.nextInt(appetizerList.size());
		Recipe thuapptizer = appetizerList.get(thuApptizerIndex);
		int thuSoupIndex = RandomUtils.nextInt(soupList.size());
		Recipe thusoup = soupList.get(thuSoupIndex);
		
		int friMainIndex = RandomUtils.nextInt(mainList.size());
		Recipe frimain = mainList.get(friMainIndex);
		int friMealIndex = RandomUtils.nextInt(mealList.size());
		Recipe frimeal = mealList.get(friMealIndex);
		int friApptizerIndex = RandomUtils.nextInt(appetizerList.size());
		Recipe friapptizer = appetizerList.get(friApptizerIndex);
		int friSoupIndex = RandomUtils.nextInt(soupList.size());
		Recipe frisoup = soupList.get(friSoupIndex);
		
		int satMainIndex = RandomUtils.nextInt(mainList.size());
		Recipe satmain = mainList.get(satMainIndex);
		int satMealIndex = RandomUtils.nextInt(mealList.size());
		Recipe satmeal = mealList.get(satMealIndex);
		int satApptizerIndex = RandomUtils.nextInt(appetizerList.size());
		Recipe satapptizer = appetizerList.get(satApptizerIndex);
		int satSoupIndex = RandomUtils.nextInt(soupList.size());
		Recipe satsoup = soupList.get(satSoupIndex);
		
		int sunMainIndex = RandomUtils.nextInt(mainList.size());
		Recipe sunmain = mainList.get(sunMainIndex);
		int sunMealIndex = RandomUtils.nextInt(mealList.size());
		Recipe sunmeal = mealList.get(sunMealIndex);
		int sunApptizerIndex = RandomUtils.nextInt(appetizerList.size());
		Recipe sunapptizer = appetizerList.get(sunApptizerIndex);
		int sunSoupIndex = RandomUtils.nextInt(soupList.size());
		Recipe sunsoup = soupList.get(sunSoupIndex);
		
		//for(int i=0; i<week.length;i++){
			final List weekList = Arrays.asList(
				new Data("月曜日",monmeal.getTitle(),monapptizer.getTitle(),monmain.getTitle(),monsoup.getTitle()),
				new Data("火曜日",tuemeal.getTitle(),tueapptizer.getTitle(),tuemain.getTitle(),tuesoup.getTitle()),
				new Data("水曜日",wedmeal.getTitle(),wedapptizer.getTitle(),wedmain.getTitle(),wedsoup.getTitle()),
				new Data("木曜日",thumeal.getTitle(),thuapptizer.getTitle(),thumain.getTitle(),thusoup.getTitle()),
				new Data("金曜日",frimeal.getTitle(),friapptizer.getTitle(),frimain.getTitle(),frisoup.getTitle()),
				new Data("土曜日",satmeal.getTitle(),satapptizer.getTitle(),satmain.getTitle(),satsoup.getTitle()),
				new Data("日曜日",sunmeal.getTitle(),sunapptizer.getTitle(),sunmain.getTitle(),sunsoup.getTitle())
			);	
		//}
		
		ListView myfavo = new ListView("weekMenu",weekList){
			
			@Override
			protected void populateItem(ListItem menu){
				
				menu.add(new Label("day",new PropertyModel(menu.getModel(),"day")));
				menu.add(new Label("appetizer",new PropertyModel(menu.getModel(),"appetizer")));
				menu.add(new Label("meal",new PropertyModel(menu.getModel(),"meal")));
				menu.add(new Label("main",new PropertyModel(menu.getModel(),"main")));
				menu.add(new Label("soup",new PropertyModel(menu.getModel(),"soup")));
			
			}
		};
		
		add(myfavo);
	}
	
	public  class Data implements Serializable{
//		private String id;
//		private String cluster;
//		private Boolean checkBool;
//		private String strUrl;
	//	
		String day="";
		String meal="";
		String appetizer="";
		String main="";
		String soup="";
		
		public Data(){
		}
		
		public Data(String day,String meal,String appetizer, String main, String soup){
			this.day=day;
			this.meal=meal;
			this.appetizer=appetizer;
			this.main=main;
			this.soup = soup;
		}
		  

	}
	
}