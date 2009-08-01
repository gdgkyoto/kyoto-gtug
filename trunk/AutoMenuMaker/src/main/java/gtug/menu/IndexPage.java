package gtug.menu;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.jdo.Query;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import gtug.menu.entity.SampleEntity;
import gtug.menu.logic.SampleEntityLogic;

public class IndexPage extends WebPage {
	static final Logger logger = Logger.getLogger(IndexPage.class.getName());

	public IndexPage() {
		final User user = UserServiceFactory.getUserService().getCurrentUser();
		createAccountPane(user);
		createListPane();
		createFormPane(user);
	}

	@SuppressWarnings("serial")
	private void createFormPane(final User user) {
		add(new StatelessForm<Void>("form") {
			RequiredTextField<String> contentTextField = new RequiredTextField<String>(
					"content", new Model<String>(""));
			{
				add(contentTextField);
			}

			@Override
			protected void onSubmit() {
				SampleEntity entity = SampleEntityLogic.create(user,
						contentTextField.getModelObject());
				new SampleEntityLogic().save(entity);
				removeCache();
				contentTextField.setModelObject("");
				entities.clear();
				entities.addAll(getEntities());
			}
		});
	}

	@SuppressWarnings("serial")
	private void createListPane() {
		entities = getEntities();
		PageableListView<SampleEntity> listView = new PageableListView<SampleEntity>(
				"list", entities, 10) {
			@Override
			protected void populateItem(ListItem<SampleEntity> item) {
				SampleEntity entity = item.getModelObject();
				item.add(new Label("created", new Model<Date>(entity
						.getCreated())));
				item.add(new Label("content", new Model<String>(entity
						.getContent())));
				item.add(new Label("user", new Model<String>(
						entity.getUser() != null ? entity.getUser()
								.getNickname() : "anonymous")));
			}
		};
		add(listView);
		add(new PagingNavigator("navi", listView));
	}

	@SuppressWarnings("serial")
	private void createAccountPane(User user) {
		if (user == null) {
			add(new Label("loginUser", "anonymous"));
			add(new Label("loginOrLogoutPrefixMessage", ""));
			add(new ExternalLink("loginOrLogoutUrl", UserServiceFactory
					.getUserService().createLoginURL("/")) {
				@Override
				protected void onComponentTagBody(MarkupStream markupStream,
						ComponentTag openTag) {
					replaceComponentTagBody(markupStream, openTag, "Sign in");
				}
			});
			add(new Label("loginOrLogoutPostfixMessage",
					" to include your name with you post."));
		} else {
			add(new Label("loginUser", user.getNickname()));
			add(new Label("loginOrLogoutPrefixMessage", "You can "));
			add(new ExternalLink("loginOrLogoutUrl", UserServiceFactory
					.getUserService().createLogoutURL("/")) {
				@Override
				protected void onComponentTagBody(MarkupStream markupStream,
						ComponentTag openTag) {
					replaceComponentTagBody(markupStream, openTag, "sign out");
				}
			});
			add(new Label("loginOrLogoutPostfixMessage", "."));
		}
	}

	private List<SampleEntity> entities;

	@SuppressWarnings("unchecked")
	private List<SampleEntity> getEntities() {
		String key = SampleEntity.class.getName();
		Cache cache = null;
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					Collections.emptyMap());
			if (cache.containsKey(key)) {
				return (List<SampleEntity>) cache.get(key);
			}
		} catch (CacheException e) {
			//
		}
		SampleEntityLogic logic = new SampleEntityLogic();
		Query query = logic.newQuery();
		query.setOrdering("created desc");
		List<SampleEntity> list = logic.list(query);
		if (cache != null) {
			cache.put(key, list);
		}
		return list;
	}

	private void removeCache() {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();
			Cache cache = cacheFactory.createCache(Collections.emptyMap());
			String key = SampleEntity.class.getName();
			if (cache.containsKey(key)) {
				cache.remove(key);
			}
		} catch (CacheException e) {
			//
		}
	}
}
