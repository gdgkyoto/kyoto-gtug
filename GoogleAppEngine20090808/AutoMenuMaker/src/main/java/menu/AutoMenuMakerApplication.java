package menu;

import menu.page.ErrorPage;
import menu.page.IndexPage;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.session.ISessionStore;
import org.apache.wicket.settings.IExceptionSettings;

public class AutoMenuMakerApplication extends WebApplication {
	public AutoMenuMakerApplication() {
    }

	@Override
	public String getConfigurationType() {
		return Application.DEPLOYMENT;
	}

	@Override
	protected ISessionStore newSessionStore() {
		return new HttpSessionStore(this);
	}

	// ***初期化***//
	@Override
	protected void init() {
		super.init();

		// ***文字コードの設定***//
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

		getApplicationSettings().setInternalErrorPage(ErrorPage.class);
		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

		// ***ページ設定***//
		mountBookmarkablePage("/MakeRecipe", MakeRecipe.class);
	}

	@Override
	public RequestCycle newRequestCycle(Request request, Response response) {
		return new ErrorRequestCycle(this, (WebRequest)request, (WebResponse)response);
	}

	// ***初期ページ設定***//
	@Override
	public Class<IndexPage> getHomePage() {
		return IndexPage.class;
	}
}
