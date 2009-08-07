package menu.page;

import java.io.Serializable;

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
