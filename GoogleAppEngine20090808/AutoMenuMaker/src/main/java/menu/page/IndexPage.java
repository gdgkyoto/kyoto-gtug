package menu.page;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.ExternalLink;


import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public class IndexPage extends WebPage {
	
	public IndexPage(final PageParameters parameters) {
		
		UserService userService = UserServiceFactory.getUserService();
		
		HttpServletRequest request = getWebRequestCycle().getWebRequest().getHttpServletRequest();
		
		String thisURL = request.getRequestURI();
		
		Principal principal = request.getUserPrincipal();
		
		if (principal != null) {            
			setResponsePage(new RecipeListPage(null));
		}
		else {
			// ログイン画面へのリンクを生成
			String url = userService.createLoginURL(thisURL);
			ExternalLink link = new ExternalLink("login", url);
			add(link);
		}		
	}

}
