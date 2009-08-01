package gtug.menu;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.ISessionStore;

public class WicketApplication extends WebApplication {
	public WicketApplication() {
	}

	@Override
	public String getConfigurationType() {
		return Application.DEPLOYMENT;
	}

	@Override
	protected ISessionStore newSessionStore() {
		return new HttpSessionStore(this);
	}
	
	//***初期ページ設定***//
	public Class<IndexPage> getHomePage() {
		return IndexPage.class;
	}
}
