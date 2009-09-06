package com.hkmosh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.maps.GeoPoint;

public class Flickr {
	
	class Entry {
		private String id;
		private String owner;
		private String secret;
		private String server;
		private String title;
		private String latitude;
		private String longitude;
		private String accurary;
		public String getId() {
			return id;
		}
		public String getOwner() {
			return owner;
		}
		public String getSecret() {
			return secret;
		}
		public String getServer() {
			return server;
		}
		public String getTitle() {
			return title;
		}
		public String getLatitude() {
			return latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public String getAccurary() {
			return accurary;
		}
	}
	
	private static final String LOG_TAG = "Flickr";

	private static final String API_URI = "http://api.flickr.com/services/rest/"; // API
																					// URL
	
	private static final String API_KEY = "60b83b7f78f604d47bbc0843550303b9"; // Flickr
																				// API
																				// KEY
	private static final String PRM_METHOD = "flickr.photos.search"; // or
																		// "flickr.photos.getRecent"
																		// //
																		// 要求メソッド
	private static final String PRM_PER_PAGE = "10"; // １ページあたりの枚数（一回の要求で取得できる検索結果上限）
	private static final String PRM_SORT = "date-posted-desc"; // Sort
	private static final String PRM_FORMAT = "json"; // 返答型
	private static final String PRM_EXTRAS = "geo"; // 追加情報（緯度経度）
	private static final String PRM_NOJSONCALLBACK = "1"; // = 1 コールバック関数を呼び出さない
	// private static final String PRM_JSONCALLBACK = 'jsonFlickrApi';

	// for Return JSON Analysis
	private static final String PHOTOS_TAG = "photos";
	private static final String PHOTO_TAG = "photo";

	private GeoPoint mCurrentGPoint;
	private JSONObject mRetJson; // 返答Json文字列
	private JSONArray mRetPhotos; // 取得写真データ
	private double mLat;
	private double mLng;

	// For Debug
	private static final double SEARCH_AREA_SIZE = 0.5; // 指定された緯度・経度を中心にどれくらいの範囲を検索するか
														// ※ToDo※パラメータ化

	public Flickr() {
		Log.i(LOG_TAG, "constructor LightFlickr");
	}

	public List<Entry> getPhotosByLoation(final double latitude, final double longitude) {
		String params = getQueryString(geoPointToBoxStr(latitude, longitude));
		Log.d(LOG_TAG, "Params / " + params);
		
		HttpClient http = new HttpClient();
		String response = http.getStringOnWeb(API_URI + "?" + params);
		Log.d(LOG_TAG, response);
		
		
		List <Entry> result = new LinkedList<Entry>();
		try {
			JSONObject object = new JSONObject(response);
			JSONArray photos = object.getJSONObject("photos").getJSONArray("photo");
			
			for(int i = 0; i < photos.length(); i++) {
				Entry entry = JsonPhotoToEntry(photos.getJSONObject(i));
				result.add(entry);
			}

			photos.length();
			Log.d(LOG_TAG, "length: " + photos.length());
			
			int total = object.getJSONObject("photos").getInt("total");
			Log.d(LOG_TAG, "total: " + total);
			
		} catch (JSONException e) {
			Log.e(LOG_TAG, "JSON Parse Error" + e.getMessage());
		}

		return result;
	}

	private String getQueryString(final String boxParam) {
		StringBuilder sb = new StringBuilder();
		sb.append("method=").append(PRM_METHOD).append("&");
		sb.append("api_key=").append(API_KEY).append("&");
		sb.append("per_page=").append(PRM_PER_PAGE).append("&");
		sb.append("sort=").append(PRM_SORT).append("&");
		sb.append("extras=").append(PRM_EXTRAS).append("&");
		sb.append("nojsoncallback=").append(PRM_NOJSONCALLBACK).append("&");
		sb.append("format=").append(PRM_FORMAT).append("&");
		sb.append("bbox=").append(boxParam);
		return sb.toString();
	}

	private String geoPointToBoxStr(final double latitude, final double longitude) {
		String bbox_str = "";

		double search_lat_min = latitude - SEARCH_AREA_SIZE;
		double search_lot_min = longitude - SEARCH_AREA_SIZE;
		double search_lat_max = latitude + SEARCH_AREA_SIZE;
		double search_lot_max = longitude + SEARCH_AREA_SIZE;
		bbox_str = search_lot_min + "," + search_lat_min + "," + search_lot_max	+ "," + search_lat_max;

		return bbox_str;
	}
	
	private Entry JsonPhotoToEntry(JSONObject obj) throws JSONException {
		Entry entry = new Entry();
		entry.accurary = obj.getString("accurary");
		entry.id = obj.getString("id");
		entry.latitude = obj.getString("latitude");
		entry.longitude = obj.getString("longitude");
		entry.owner = obj.getString("owner");
		entry.secret = obj.getString("secret");
		entry.server = obj.getString("server");
		entry.title = obj.getString("title");
		return entry;
	}

	
	class HttpClient extends DefaultHttpClient {

		private HttpGet httpGet;

		public HttpClient() {
			Log.i(LOG_TAG, "constructor MyHttpClient");
			httpGet = new HttpGet();
		}

		public InputStream getInputStreamOnWeb(String uri) {
			Log.i(LOG_TAG, "getInputStreamOnWeb");

			try {
				httpGet.setURI(new URI(uri));
			} catch (URISyntaxException e1) {
				Log.e(LOG_TAG, "URISyntaxException");
				e1.printStackTrace();
				return null;
			}

			HttpResponse response = null;
			try {
				response = execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();
				Log.i(LOG_TAG, "Status Code: " + statusCode);
				if (200 <= statusCode && statusCode < 300) {
					// success!!
					return response.getEntity().getContent();
				}
			} catch (ClientProtocolException e) {
				Log.e(LOG_TAG, "ClientProtocolException");
				e.printStackTrace();
			} catch (IllegalStateException e) {
				Log.e(LOG_TAG, "IllegalStateException");
				e.printStackTrace();
			} catch (IOException e) {
				Log.e(LOG_TAG, "IOException");
				e.printStackTrace();
			} catch (Exception e) {
				Log.e(LOG_TAG, "Exception");
				e.printStackTrace();
			}

			return null;
		}

		public String getStringOnWeb(String uri) {
			Log.i(LOG_TAG, "getStringOnWeb");
			InputStream is = getInputStreamOnWeb(uri);
			if (is == null) {
				return null;
			}

			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();
			try {
				String line;
				while ((line = br.readLine()) != null) {
					buffer.append(line);
				}
			} catch (IOException e) {
				Log.e(LOG_TAG, "IOException");
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return buffer.toString();
		}
	}
}
