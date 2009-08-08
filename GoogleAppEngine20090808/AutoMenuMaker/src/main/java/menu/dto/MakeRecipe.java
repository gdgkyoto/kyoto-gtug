package menu.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MakeRecipe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	String category="";
	@Persistent
	String title="";
	@Persistent
	String material="";
	@Persistent
	String cook="";
	
	
	public MakeRecipe() {
	}
	
	public MakeRecipe(String category,String title,String material,String cook){
		this.category=category;
		this.title=title;
		this.material=material;
		this.cook=cook;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setCategory(String category){
		this.category= category;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getMaterial() {
		return material;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getCook() {
		return cook;
	}
	
	public void setCook(String cook) {
		this.cook = cook;
	}
	
	
}
