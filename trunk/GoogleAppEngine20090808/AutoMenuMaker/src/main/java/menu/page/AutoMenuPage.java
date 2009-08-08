package menu.page;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

public class AutoMenuPage extends WebPage {
	
	// String[] week = {"月曜日","火曜日","水曜日","木曜日","金曜日","土曜日","日曜日"};
	 
	public AutoMenuPage(final PageParameters parameters) {
		
		
		
		String meal="";
		String apptizer="";
		String main="";
		String soup="";
		
		
		
		//for(int i=0; i<week.length;i++){
			final List weekList = Arrays.asList(
				new Data("月曜日",meal,apptizer,main,soup),
				new Data("火曜日",meal,apptizer,main,soup),
				new Data("水曜日",meal,apptizer,main,soup),
				new Data("木曜日",meal,apptizer,main,soup),
				new Data("金曜日",meal,apptizer,main,soup),
				new Data("土曜日",meal,apptizer,main,soup),
				new Data("日曜日",meal,apptizer,main,soup)
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
	
}