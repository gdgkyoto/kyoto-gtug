package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class TestWebView extends Activity implements OnClickListener{

    //private final int FP = ViewGroup.LayoutParams.FILL_PARENT; 
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 

    private Button buttonInfo;
    private WebView webview;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        buttonInfo = new Button(this);
        buttonInfo.setText("Info");
        buttonInfo.setOnClickListener(this);

        webview = new WebView(this);
        webview.setWebViewClient(new HelloWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://www.google.co.jp/"); 

        linearLayout.addView(buttonInfo, createParam(WC, WC));
        linearLayout.addView(webview, createParam(WC, WC));
    }

    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    
    public void onClick(View v) {
        String title = webview.getTitle();
        String url = webview.getUrl();
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        //ad.setTitle(title);
        ad.setMessage("title: " + title + "\nurl: " + url);
        ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
                setResult(RESULT_OK);
            }
        });
        ad.create();
        ad.show();
    }
    
    @Override	
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
