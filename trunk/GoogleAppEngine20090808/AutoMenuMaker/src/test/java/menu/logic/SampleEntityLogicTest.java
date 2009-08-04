package menu.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.jdo.Query;

import menu.entity.Entity;
import org.junit.Test;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

public class SampleEntityLogicTest extends AbstractLogicTest {
	EntityLogic logic = new EntityLogic();

	@Test
	public void save() {
		User user = UserServiceFactory.getUserService().getCurrentUser();
		Entity entity1 = EntityLogic.create(user, "テストデータ");
		Entity save1 = logic.save(entity1);
		Key key1 = save1.getKey();

		Query query1 = logic.newQuery();
		@SuppressWarnings("unchecked")
		List<Entity> list1 = (List<Entity>) query1.execute();
		query1.closeAll();
		assertThat(list1.size(), is(1));

		Query query2 = logic.newQuery();
		query2.setFilter("key == pKey");
		query2.declareParameters("java.lang.String pKey");
		@SuppressWarnings("unchecked")
		List<Entity> list2 = (List<Entity>) query2
				.execute(KeyFactory.keyToString(key1));
		assertThat(list2.size(), is(1));
		assertThat(list2.get(0).getContent(), is("テストデータ"));
	}

	@Test
	public void delete() {
		User user = UserServiceFactory.getUserService().getCurrentUser();
		Entity entity1 = EntityLogic.create(user, "テストデータ1");
		Entity save1 = logic.save(entity1);
		Entity entity2 = EntityLogic.create(user, "テストデータ2");
		Entity save2 = logic.save(entity2);

		Query query1 = logic.newQuery();
		@SuppressWarnings("unchecked")
		List<Entity> beforeDelete = (List<Entity>) query1.execute();
		query1.closeAll();
		assertThat(beforeDelete.size(), is(2));

		logic.delete(save2, save2.getKey());

		@SuppressWarnings("unchecked")
		List<Entity> afterDelete = (List<Entity>) query1.execute();
		query1.closeAll();
		assertThat(afterDelete.size(), is(1));
	}
}
