package gtug.menu.logic;

import java.util.Date;

import gtug.menu.entity.SampleEntity;

import com.google.appengine.api.users.User;

public class SampleEntityLogic extends AbstractLogic<SampleEntity> {
	public static SampleEntity create(User user, String content) {
		SampleEntity entity = new SampleEntity();
		entity.setUser(user);
		entity.setContent(content);
		entity.setCreated(new Date(System.currentTimeMillis()));
		return entity;
	}
}
