package menu.page;

import java.io.Serializable;

public  class Data implements Serializable{
//	private String id;
//	private String cluster;
//	private Boolean checkBool;
//	private String strUrl;
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
