package com.Liferoid.android;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Liferoid.android.R.id;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Output extends Activity {
	/** Called when the activity is first created. */

	// private ProgressDialog dialog;
	
	Liferoid obj = new Liferoid();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output);

		// 電力使用量取得

		// URI文字列を生成
		String scheme = "http";
		String authority = "tepco-usage-api.appspot.com";
		String path = "/latest.json";

		Uri.Builder uriBuilder = new Uri.Builder();

		uriBuilder.scheme(scheme);
		uriBuilder.authority(authority);
		uriBuilder.path(path);

		String uri = uriBuilder.toString();

		// 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
		// Toast.makeText(this, uri, Toast.LENGTH_LONG).show();

		// ProgressDialogを作成
		// dialog = new ProgressDialog(this);
		// dialog.setTitle("Wonderflに接続中");
		// dialog.setMessage("Wonderflからsakefのデータを取得しています。");
		// dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// dialog.setCancelable(true);
		// dialog.show();

		// dialog.dismiss();

		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 1000);
		HttpConnectionParams.setSoTimeout(params, 1000);

		HttpUriRequest httpRequest = new HttpGet(uri);

		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			// 例外処理
		} catch (IOException e) {
			// 例外処理
		}

		String json = null;

		if (httpResponse != null
				&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				json = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				// 例外処理
			} catch (IOException e) {
				// 例外処理
			} finally {
				try {
					httpEntity.consumeContent();
				} catch (IOException e) {
					// 例外処理
				}
			}
		}

		httpClient.getConnectionManager().shutdown();

		try {
			JSONObject rootObject = new JSONObject(json);

			// 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
			// Toast.makeText(this, rootObject.getString("usage"),
			// Toast.LENGTH_LONG).show();

			TextView textView = (TextView) findViewById(id.textView1);
			// テキストビューのテキストを設定します
			textView.setText(rootObject.getString("usage") + ","
					+ rootObject.getString("forecast_peak_usage") + ","
					+ rootObject.getString("capacity"));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		SharedPreferences pref1 = getSharedPreferences("pref_location",  MODE_PRIVATE);
			String str_location = pref1.getString("Location", "");
		
		
		
		// 放射線量取得
		
		// URI文字列を生成
		String scheme2 = "http";
		String authority2 = "dokusyodebokin.com";
		String path2 = "/earthquake/now/";
		String appendQueryParameter1 = "prefecture";
		String appendQueryParameter2 = obj.getY();

		Uri.Builder uriBuilder2 = new Uri.Builder();

		uriBuilder2.scheme(scheme2);
		uriBuilder2.authority(authority2);
		uriBuilder2.path(path2);
		uriBuilder2.appendQueryParameter(appendQueryParameter1, appendQueryParameter2);

		String uri2 = uriBuilder2.toString();

		// ProgressDialogを作成
		// dialog = new ProgressDialog(this);
		// dialog.setTitle("Wonderflに接続中");
		// dialog.setMessage("Wonderflからsakefのデータを取得しています。");
		// dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// dialog.setCancelable(true);
		// dialog.show();

		// dialog.dismiss();

		HttpClient httpClient1 = new DefaultHttpClient();
		HttpParams params1 = httpClient1.getParams();
		HttpConnectionParams.setConnectionTimeout(params1, 1000);
		HttpConnectionParams.setSoTimeout(params1, 1000);

		HttpUriRequest httpRequest1 = new HttpGet(uri2);

		HttpResponse httpResponse1 = null;

		try {
			httpResponse1 = httpClient1.execute(httpRequest1);
		} catch (ClientProtocolException e) {
			// 例外処理
		} catch (IOException e) {
			// 例外処理
		}

		String json1 = null;

		if (httpResponse1 != null
				&& httpResponse1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity1 = httpResponse1.getEntity();
			try {
				json1 = EntityUtils.toString(httpEntity1);
			} catch (ParseException e) {
				// 例外処理
			} catch (IOException e) {
				// 例外処理
			} finally {
				try {
					httpEntity1.consumeContent();
				} catch (IOException e) {
					// 例外処理
				}
			}
		}

		httpClient1.getConnectionManager().shutdown();
		
		
		String test = "";

		try {
			JSONArray jsons = new JSONArray(json1);
			for (int i = 0; i < jsons.length(); i++) {
			    JSONObject jsonObj = jsons.getJSONObject(i);

				// 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
				//Toast.makeText(this, jsonObj.getString("doserate"),
				//		Toast.LENGTH_LONG).show();
			    
			    test += jsonObj.getString("doserate") + ",";
			}
			
			TextView textView = (TextView) findViewById(id.textView2);
			// テキストビューのテキストを設定します
			textView.setText(test);


		} catch (JSONException e) {
			// 例外処理
		}

	}
}