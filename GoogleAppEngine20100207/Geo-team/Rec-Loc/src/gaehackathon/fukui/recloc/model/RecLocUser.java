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
	private String password;

	public RecLocUser(String name) {
		this(name, "Unknown User");
	}
	public RecLocUser(String name, String displayName) {
		this.name = name;
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
	public void setPassword(String password) {
		if (password == null) {
			throw new NullPointerException("Password must be not null");
		} else if (password.trim().length() == 0) {
			throw new IllegalArgumentException("Password must be set some alphabets or numbers.");
		}
		this.password = password;
	}
	
	public boolean isPass(String checkPass) {
		return password.equals(checkPass);
	}
	
}
