package gaehackathon.fukui.recloc.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class RecLocUser {
	@PrimaryKey
	@Persistent
	private String name;
	
	@Persistent
	private String displayName;
	
	@Persistent
	private String hashCode;

	public RecLocUser(String name) {
		this(name, null);
	}
	public RecLocUser(String name, String code) {
		this(name, code, "Unknown User");
	}
	public RecLocUser(String name, String code, String displayName) {
		this.name = name;
		this.hashCode = code;
		this.displayName = displayName;
	}
	
	//=================
	public String getName() {
		return name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String newName) {
		this.displayName = newName;
	}
	
}
