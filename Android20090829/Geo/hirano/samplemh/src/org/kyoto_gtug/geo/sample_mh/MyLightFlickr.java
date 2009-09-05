
package org.kyoto_gtug.geo.sample_mh;

//import android.location.Location;
import android.util.Log;
import com.google.android.maps.GeoPoint;
import static org.kyoto_gtug.geo.sample_mh.GeoSampleMHActivity.LOG_TAG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*
 * Flickrアクセスクラス簡易版
 * 2009/09 Ver.0.02 M.H 
 */
public class MyLightFlickr {
	// for Flickr API Access
    private static final String API_URI      = "http://api.flickr.com/services/rest/?"; // API URL
    private static final String API_KEY      = "60b83b7f78f604d47bbc0843550303b9";		// Flickr API KEY
    private static final String PRM_METHOD   = "flickr.photos.search";  // or "flickr.photos.getRecent" // 要求メソッド
    private static final String PRM_PER_PAGE = "10";					// １ページあたりの枚数（一回の要求で取得できる検索結果上限）
    private static final String PRM_SORT     = "date-posted-desc";		// Sort
    private static final String PRM_FORMAT   = "json";					// 返答型
    private static final String PRM_EXTRAS   = "geo";					// 追加情報（緯度経度）
    private static final String PRM_NOJSONCALLBACK = "1"; 				// = 1 コールバック関数を呼び出さない	
    //private static final String PRM_JSONCALLBACK = 'jsonFlickrApi';

    // for Return JSON Analysis
    private static final String PHOTOS_TAG = "photos";
    private static final String PHOTO_TAG = "photo";
    
    private MyHttpClient mHttpClient;
    private GeoPoint mCurrentGPoint;
    private JSONObject mRetJson;		// 返答Json文字列
    private JSONArray mRetPhotos;		// 取得写真データ
    private double mLat;
    private double mLng;
    
    // For Debug
    private static final double SEARCH_AREA_SIZE = 0.5; // 指定された緯度・経度を中心にどれくらいの範囲を検索するか ※ToDo※パラメータ化

    MyLightFlickr() {
        Log.i(LOG_TAG, "constructor LightFlickr");

        // Webアクセス用クライアント
        mHttpClient = new MyHttpClient();
    }

    // 現在地を設定
    void setCurrentGPoint(GeoPoint currentGPoint) {
        this.mCurrentGPoint = currentGPoint;
        this.mLat = (double) currentGPoint.getLatitudeE6() / (double) 1E6;
        this.mLng = (double) currentGPoint.getLongitudeE6() / (double) 1E6;
    }

    // 設定された現在地を返す
    GeoPoint getCurrentGPoint() {
        return mCurrentGPoint;
    }

    // 条件に合った写真をFlickrから取得（※現在は位置検索のみ）
    public void GetPhotos() {
        Log.i( LOG_TAG, "GetPhotos / lat:" + mCurrentGPoint.getLatitudeE6() + ", lng:" + mCurrentGPoint.getLongitudeE6() );
        
        // 検索条件文字列作成
        String search_prm = ""; 
        if ( mCurrentGPoint != null ) { // 現在地が設置されていれば緯度・経度の検索BOXを設定
            double search_lat_min = mLat - SEARCH_AREA_SIZE;
            double search_lot_min = mLng - SEARCH_AREA_SIZE;
            double search_lat_max = mLat + SEARCH_AREA_SIZE;
            double search_lot_max = mLng + SEARCH_AREA_SIZE;	
            //String bbox_str = "135.60,34.68,135.99,34.99"; // 平等院付近 for test
            String bbox_str = search_lot_min + "," + search_lat_min + "," + search_lot_max + "," + search_lat_max;
            search_prm += "&bbox=" + bbox_str;
        }
        	
        //String access_uri = API_URI + "method=" + "flickr.photos.search" + "&api_key=" + API_KEY + "&format=" + PRM_FORMAT + "&tags=" + "japan"; // Simple Test
        String access_uri = API_URI + "method=" + PRM_METHOD + "&api_key=" + API_KEY + 
                            "&per_page=" + PRM_PER_PAGE + "&sort=" + PRM_SORT + "&extras=" + PRM_EXTRAS + 
                            "&nojsoncallback=" + PRM_NOJSONCALLBACK + "&format=" + PRM_FORMAT + search_prm;
        Log.i( LOG_TAG, "GetPhotos / access_uri:" + access_uri );

        // Web APIへアクセスし文字列を取得する
        String returnStr = mHttpClient.getStringOnWeb(access_uri);
        Log.i(LOG_TAG, "GetPhotos / returnStr : " + returnStr);

        try {
        	//returnStr = "{ photos : { photo: [ {'a' : 2, 'b' : 3, 'c' : 4} ] } }";
        	//returnStr = "{\"photos\":{\"page\":1, \"pages\":1, \"perpage\":10, \"total\":\"3\",photo : [{\"id\" : 100, \"owner\" : 200},{\"id\" : 101, \"owner\" : 201}]}, \"stat\":\"ok\"}";
        	//returnStr = "{\"photos\":{\"page\":1, \"pages\":1, \"perpage\":10, \"total\":\"3\", \"photo\":[{\"id\":\"3885783869\", \"owner\":\"30531484@N05\", \"secret\":\"dcefb6562f\", \"server\":\"3442\", \"farm\":4, \"title\":\"^734962 in Uji\", \"ispublic\":1, \"isfriend\":0, \"isfamily\":0, \"latitude\":34.889276, \"longitude\":135.80828, \"accuracy\":\"16\", \"place_id\":\"c3uTr5ybCZlei8hb.A\", \"woeid\":\"28557635\"}, {\"id\":\"3886576206\", \"owner\":\"30531484@N05\", \"secret\":\"3a28750886\", \"server\":\"3429\", \"farm\":4, \"title\":\"^734962 in Uji\", \"ispublic\":1, \"isfriend\":0, \"isfamily\":0, \"latitude\":34.890016, \"longitude\":135.807605, \"accuracy\":\"16\", \"place_id\":\"mVWKNSKbCZmjAo7.Lg\", \"woeid\":\"28557648\"}, {\"id\":\"3885779959\", \"owner\":\"30531484@N05\", \"secret\":\"13ea1b4e40\", \"server\":\"2618\", \"farm\":3, \"title\":\"^73\4962 in Uji\", \"ispublic\":1, \"isfriend\":0, \"isfamily\":0, \"latitude\":34.890016, \"longitude\":135.807605, \"accuracy\":\"16\", \"place_id\":\"mVWKNSKbCZmjAo7.Lg\", \"woeid\":\"28557648\"}]}, \"stat\":\"ok\"}";

        	// JSONを解析してDOMツリーを構築
        	this.mRetJson = new JSONObject(returnStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "GetPhotos / JSONException mRetJson");
            e.printStackTrace();
            return;
        }

        try {
            // photo配列を取り出す
        	JSONObject photos_json = this.mRetJson.getJSONObject(PHOTOS_TAG); 
        	this.mRetPhotos = photos_json.getJSONArray(PHOTO_TAG);
        	Log.i(LOG_TAG, "GetPhotos / mRetPhotos Value = " + mRetPhotos.toString());
        	// Get Lat Lon Test
	        for (int i = 0; i < mRetPhotos.length(); i++) {
	            JSONObject tmpJsonObj = mRetPhotos.getJSONObject(i);
	            Log.i(LOG_TAG, "GetPhotos / Photos / lat = " + tmpJsonObj.getDouble("latitude") + " lon = " + tmpJsonObj.getDouble("longitude"));
	        }
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        
        Log.i(LOG_TAG, "GetPhotos / GetPhots Num = " + this.length()); // 取得枚数をログに出力
    }

    // 写真の枚数
    int length() {
    	if (mRetPhotos != null) {
    		return mRetPhotos.length();
    	} else {
            Log.e(LOG_TAG, "GetPhotos / Non Photos");
            return -1;
    	}
    }

    // 写真の緯度・経度を取得
    GeoPoint getPhotoLocation(JSONObject photo_json) {
    	int tmp_lat = 0;
    	int tmp_lon = 0;
    	try {
    		tmp_lat = (int)photo_json.getDouble("latitude");
    		tmp_lon = (int)photo_json.getDouble("longitude");
        } catch (JSONException e) {
            Log.e(LOG_TAG, "GetPhotos / getPhotoLocation / " + photo_json);
            e.printStackTrace();
            return null;
        }
        return new GeoPoint(tmp_lat, tmp_lon);
    }

}
