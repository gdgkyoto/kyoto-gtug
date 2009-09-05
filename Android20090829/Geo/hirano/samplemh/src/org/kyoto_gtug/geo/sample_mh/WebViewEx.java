package org.kyoto_gtug.geo.sample_mh;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class WebViewEx extends Activity {
	private WebView webView;
	private Handler handler;
	
	public  void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// ハンドラの生成
		handler = new Handler();
		
		// WEBビュー作成
		webView = new WebView(this);
		WebSettings settings = webView.getSettings();
		//settings.setJavaScriptEnabled(true);
		settings.setJavaScriptEnabled(false);
		settings.setSavePassword(false);
		settings.setSaveFormData(false);
		settings.setSupportZoom(false);
		
		webView.addJavascriptInterface(new JSInterface(), "demo");
		
		webView.setWebChromeClient(new ChromeClient());
		
		webView.loadUrl("http://www.miosys.co.jp/");
		setContentView(webView);		
	}

	public final class JSInterface {

	}

	public final class ChromeClient extends WebChromeClient {
		public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
			android.util.Log.e("ChromeClinet", message);
			result.confirm();
			return true;
		}
		
	}

}
