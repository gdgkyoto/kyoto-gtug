package gtug.menu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Entity implements Serializable {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String content;

	@Persistent
	private User user;

	@Persistent
	private Date created;

	public Key getKey() { return key; }
	public void setKey(Key key) { this.key = key; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public Date getCreated() { return created; }
	public void setCreated(Date created) { this.created = created; }

	public Long getId() { return this.key.getId(); }
}
