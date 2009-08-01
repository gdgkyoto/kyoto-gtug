package gtug.menu.logic;

import java.util.Date;

import gtug.menu.entity.Entity;

import com.google.appengine.api.users.User;

public class EntityLogic extends AbstractLogic<Entity> {
	public static Entity create(User user, String content) {
		Entity entity = new Entity();
		entity.setUser(user);
		entity.setContent(content);
		entity.setCreated(new Date(System.currentTimeMillis()));
		return entity;
	}
}
