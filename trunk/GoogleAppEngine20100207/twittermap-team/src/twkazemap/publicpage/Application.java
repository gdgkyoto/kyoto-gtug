/*
 * Application.java
 *
 * Created on 2010/02/07, 11:10
 */
 
package twkazemap.publicpage;

import appengine.wicket.AppEngineSessionStore;
import appengine.wicket.AppEngineWebApplicationHelper;
import javax.servlet.http.HttpServletRequest;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.session.ISessionStore;
import org.apache.wicket.util.tester.WicketTester;
/** 
 *
 * @author masanao
 * @version 
 */

public class Application extends WebApplication {

    private static final String GOOGLE_MAPS_API_KEY_PARAM = "GoogleMapsAPIkey";

    /** 色々と処理を行うためのヘルパのインスタンス。thisを渡す。 */
	AppEngineWebApplicationHelper helper = new AppEngineWebApplicationHelper(this);

    public Application() {
    }

    public Class getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
		super.init();
		// ヘルパーに最低限必要な設定処理をしてもらう。
		// ResourceWatcherの設定とFileUploadの上限の設定とか。
		helper.init();
        //getResourceSettings().setResourcePollFrequency(Duration.seconds(10));

        getMarkupSettings().setStripWicketTags(true);
	}

    /**
	 * 設定ファイルからConfigrationTypeを設定するなら実装する必要は無い。
	 */
	@Override
	public String getConfigurationType() {
		// ソースコードから設定をするならヘルパに処理を委譲する。
		// AppEngine上ではDEPLOYMENTを返し、ローカルではDEVELOPMENTを返す。
		return helper.getConfigurationType();
	}

	/**
	 * 実行環境に合わせた{@link ISessionStore}を返す。
	 * <ul>
	 * <li>AppEngine上での実行、時は{@link AppEngineSessionStore}を返す。</li>
	 * <li>ローカルでのSDKによるWebコンテナ経由での実行時も{@link AppEngineSessionStore}を返す。</li>
	 * <li>{@link WicketTester}からの実行時は{@link HttpSessionStore}を返す。</li>
	 * </ul>
	 */
	@Override
	protected ISessionStore newSessionStore() {
		// ヘルパに処理を委譲する。
		return helper.newSessionStore();
	}

	/**
	 *　Wicket標準のFileUploadでは問題が出るのでAppEngine用の{@link WebRequest}を返す。
	 */
	@Override
	protected WebRequest newWebRequest(HttpServletRequest servletRequest) {
		// ヘルパに処理を委譲する。
		return helper.newWebRequest(servletRequest);
	}

    public String getGoogleMapsAPIkey() {
        String googleMapsAPIkey = getInitParameter(GOOGLE_MAPS_API_KEY_PARAM);
        if (googleMapsAPIkey == null) {
            throw new WicketRuntimeException("There is no Google Maps API key configured in the "
                + "deployment descriptor of this application.");
        }
        return googleMapsAPIkey;
    }
}
