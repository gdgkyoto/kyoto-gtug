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

		// �d�͎g�p�ʎ擾

		// URI������𐶐�
		String scheme = "http";
		String authority = "tepco-usage-api.appspot.com";
		String path = "/latest.json";

		Uri.Builder uriBuilder = new Uri.Builder();

		uriBuilder.scheme(scheme);
		uriBuilder.authority(authority);
		uriBuilder.path(path);

		String uri = uriBuilder.toString();

		// ��3�����́A�\�����ԁiLENGTH_SHORT�A�܂��́ALENGTH_LONG�j
		// Toast.makeText(this, uri, Toast.LENGTH_LONG).show();

		// ProgressDialog���쐬
		// dialog = new ProgressDialog(this);
		// dialog.setTitle("Wonderfl�ɐڑ���");
		// dialog.setMessage("Wonderfl����sakef�̃f�[�^���擾���Ă��܂��B");
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
			// ��O����
		} catch (IOException e) {
			// ��O����
		}

		String json = null;

		if (httpResponse != null
				&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				json = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				// ��O����
			} catch (IOException e) {
				// ��O����
			} finally {
				try {
					httpEntity.consumeContent();
				} catch (IOException e) {
					// ��O����
				}
			}
		}

		httpClient.getConnectionManager().shutdown();

		try {
			JSONObject rootObject = new JSONObject(json);

			// ��3�����́A�\�����ԁiLENGTH_SHORT�A�܂��́ALENGTH_LONG�j
			// Toast.makeText(this, rootObject.getString("usage"),
			// Toast.LENGTH_LONG).show();

			TextView textView = (TextView) findViewById(id.textView1);
			// �e�L�X�g�r���[�̃e�L�X�g��ݒ肵�܂�
			textView.setText(rootObject.getString("usage") + ","
					+ rootObject.getString("forecast_peak_usage") + ","
					+ rootObject.getString("capacity"));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		SharedPreferences pref1 = getSharedPreferences("pref_location",  MODE_PRIVATE);
			String str_location = pref1.getString("Location", "");
		
		
		
		// ���ː��ʎ擾
		
		// URI������𐶐�
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

		// ProgressDialog���쐬
		// dialog = new ProgressDialog(this);
		// dialog.setTitle("Wonderfl�ɐڑ���");
		// dialog.setMessage("Wonderfl����sakef�̃f�[�^���擾���Ă��܂��B");
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
			// ��O����
		} catch (IOException e) {
			// ��O����
		}

		String json1 = null;

		if (httpResponse1 != null
				&& httpResponse1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity1 = httpResponse1.getEntity();
			try {
				json1 = EntityUtils.toString(httpEntity1);
			} catch (ParseException e) {
				// ��O����
			} catch (IOException e) {
				// ��O����
			} finally {
				try {
					httpEntity1.consumeContent();
				} catch (IOException e) {
					// ��O����
				}
			}
		}

		httpClient1.getConnectionManager().shutdown();
		
		
		String test = "";

		try {
			JSONArray jsons = new JSONArray(json1);
			for (int i = 0; i < jsons.length(); i++) {
			    JSONObject jsonObj = jsons.getJSONObject(i);

				// ��3�����́A�\�����ԁiLENGTH_SHORT�A�܂��́ALENGTH_LONG�j
				//Toast.makeText(this, jsonObj.getString("doserate"),
				//		Toast.LENGTH_LONG).show();
			    
			    test += jsonObj.getString("doserate") + ",";
			}
			
			TextView textView = (TextView) findViewById(id.textView2);
			// �e�L�X�g�r���[�̃e�L�X�g��ݒ肵�܂�
			textView.setText(test);


		} catch (JSONException e) {
			// ��O����
		}

	}
}