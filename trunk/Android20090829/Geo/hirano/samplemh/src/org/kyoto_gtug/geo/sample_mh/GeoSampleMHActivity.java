package org.kyoto_gtug.geo.sample_mh;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kyoto_gtug.geo.sample_mh.MyLightFlickr;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
//import android.view.Window;

public class GeoSampleMHActivity extends MapActivity {
    /** Called when the activity is first created. */
	// Android Maps API Key
	private final static String MAP_API_KEY="0wS5ODosCSb4lh_q9yLXSqpPmMTdVnQJXIUxKXQ";
	
	// ログ用タグ
	static final String LOG_TAG = "GeoSampleMH";

	// GPSへの問い合わせ周期
	private final int MIN_TIME = 0;
	static final int MIN_METER = 0;
	
	// Objects
	private MapView				mapView;
	private MapController 		mapCtrl;
	private LocationManager 	locMgr;
	private MyLocationListener 	myLocListener;
	PhotosPinOverray photosPinOverray; //// ピンを表示するためのオーバーレイ
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Log.i(LOG_TAG, "onCreate");
    	setContentView(R.layout.main);

    	//requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	// MapView作成
    	mapView = new MapView(this, MAP_API_KEY); 
    	mapView.setClickable(true);
    	setContentView(mapView);
    	Log.i(LOG_TAG, "create MapView");
    	
    	// ズーム用のレイアウト配置
    	//LinerLayout zoomLayout = (LinerLayout) 

    	
    	// MapController の作成
    	mapCtrl = mapView.getController();
    	mapCtrl.setCenter( new GeoPoint( (int)(34.889291 * 1E6), (int)(135.807612 * 1E6) ) ); // 初期Point平等院近辺
    	mapCtrl.setZoom(10); //(16);
    	Log.i(LOG_TAG, "create MapController");
    	
    	// LocationManager の作成・リスナーの設定
    	myLocListener = new MyLocationListener();
    	locMgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_METER, myLocListener );
    	Log.i(LOG_TAG, "create MyLocationListener");
    	
    	// MapView上に表示したいビットマップ情報を、リソースから取得
    	Drawable human = getResources().getDrawable(R.drawable.human);
    	human.setBounds(0, 0, human.getMinimumWidth(), human.getMinimumHeight());
    	// ピンを表示するためのオーバーレイを生成
    	photosPinOverray = new PhotosPinOverray(human);
    	// MapViewからOverlayリストを取得
    	List<Overlay> overlays = mapView.getOverlays();
    	// Overlayリストに追加する
    	overlays.add( photosPinOverray );
    	// 自分の位置にピンを追加
    	//photosPinOverray.addPhotoPin(new GeoPoint( (int)(34.889291 * 1E6), (int)(135.807612 * 1E6)), "http://www.miosys.co.jp/", "Now Current Point");
    	photosPinOverray.addPhotoPin(new GeoPoint( (int)(34.889291 * 1E6), (int)(135.807612 * 1E6)), "google.streetview:cbll=34.892402,135.8055", "Now Current Point");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.i(LOG_TAG, "onDestroy");
		// ロケーションマネージャからリスナーを削除
		locMgr.removeUpdates(myLocListener);
	}
	
	@Override
	// 経路描画のON・OFF
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
		//return true;
	}

	// イベントリスナー
	private class MyLocationListener implements LocationListener {
		// 現在位置Point
		private GeoPoint currentLocation;
		
		private MyLocationListener() {
			Log.i(LOG_TAG, "constructor MyLocationListener");
		}

		// 位置情報取得イベント
		public void onLocationChanged(Location location) {
			//Log.i(LOG_TAG, "MyLocationListener onLocationChanged");
			// 緯度・経度の取得
			GeoPoint now_pos = new GeoPoint( (int)(location.getLatitude() * 1E6), (int)(location.getLongitude() * 1E6) );
			//GeoPoint now_pos = new GeoPoint( (int)(34.889291 * 1E6), (int)(135.807612 * 1E6) ); // Set Dummy Point
			currentLocation = now_pos;
			mapCtrl.setCenter(currentLocation); // 現在位置を地図のセンターに

			// Flickr から現在位置を中心とした一定範囲の写真を取得
			MyLightFlickr lightFlickr = new MyLightFlickr();
			lightFlickr.setCurrentGPoint(currentLocation);
			lightFlickr.GetPhotos();

			// 現在地から検索した写真情報のJsonArrayを取得
			JSONArray tempPhotoJsonArray = lightFlickr.getPhotoJsonArray();
			Log.i(LOG_TAG, "onLocationChanged / Photos num = " + tempPhotoJsonArray.length());
					
	        // Get Lat Lon Test
			try {
		    	// MapView上に表示したいビットマップ情報を、リソースから取得
		    	Drawable pin = getResources().getDrawable(R.drawable.pin);
		    	pin.setBounds(0, 0, pin.getMinimumWidth(), pin.getMinimumHeight());
		    	// ピンを表示するためのオーバーレイを生成
				PhotosPinOverray tmpPhotosPinOverray = new PhotosPinOverray(pin);

				for (int i = 0; i < tempPhotoJsonArray.length(); i++) {
					JSONObject tmpJsonObj = tempPhotoJsonArray.getJSONObject(i);
					Log.i(LOG_TAG, "onLocationChanged / Photos / lat = " + tmpJsonObj.getDouble("latitude") + " lon = " + tmpJsonObj.getDouble("longitude"));
			    	// 写真の場所にピンを追加
					String photo_uri = "http://www.flickr.com/photos/" + tmpJsonObj.getString("owner") + "/" + tmpJsonObj.getString("id") + "/";
					Log.i(LOG_TAG, "onLocationChanged / Photos / photo_uri = " + photo_uri);
			    	tmpPhotosPinOverray.addPhotoPin(new GeoPoint( (int)(tmpJsonObj.getDouble("latitude") * 1E6), (int)(tmpJsonObj.getDouble("longitude") * 1E6)), photo_uri, "photo data");
			    	//tmpPhotosPinOverray.addPhotoPin(new GeoPoint( (int)((34.889291 + i) * 1E6), (int)(135.807612 * 1E6)), "Now Point", "Now Location");
		    	}
		    	// MapViewからOverlayリストを取得
		    	List<Overlay> overlays = mapView.getOverlays();
		    	// Overlayリストに追加する
		    	overlays.add( tmpPhotosPinOverray );

			} catch (JSONException e) {
	            Log.e(LOG_TAG, "onLocationChanged / getJSONObject");
	            e.printStackTrace();
	            return;
	        }
			
		}

		//  位置情報取得イベント有効化時イベント処理
		public void onProviderEnabled(String provider) {
		}
		
		// 位置情報取得無効化時イベント処理
		public void onProviderDisabled(String provider) {
		}
		
		// 位置情報状態変更時イベント処理
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	// 写真位置にピンを表示するためのオーバーレイ 
	public class PhotosPinOverray extends ItemizedOverlay<OverlayItem> {

		private LinkedList<OverlayItem> list;
		private Context mContext;
		
		public PhotosPinOverray(Drawable defaultMarker) {
			super(defaultMarker);
			// TODO Auto-generated constructor stub
			list = new LinkedList<OverlayItem>();
			
			// markerの基点を設定
			boundCenterBottom(defaultMarker);
			((BitmapDrawable)defaultMarker).setAntiAlias(false);
		}
		
		public void addPhotoPin(GeoPoint gp, String title, String desc) {
			OverlayItem photo_pin = new OverlayItem(gp, title, desc);
			list.add(photo_pin);
			populate();
		}

	    void clearPhotoPins() {
	        if (list != null) {
	            list.clear();
	            populate();
	        }
	    }

	    @Override
	    protected OverlayItem createItem(int index) {
	        return list.get(index);
		}

		@Override
		public int size() {
	        return list.size();
		}
		
	    protected boolean onTap(int index) {
	    	OverlayItem photo_pin =  createItem(index);
	        setFocus(photo_pin);   //前面に表示する
	        String uri = photo_pin.getTitle();
	        Log.i(LOG_TAG, "PhotosPinOverray onTap / uri = " + uri);
	        //Intent intent = new Intent( "android.intent.action.VIEW", Uri.parse("http://www.miosys.co.jp/"));
        	Intent intent = new Intent( "android.intent.action.VIEW", Uri.parse(uri));
        	startActivity(intent);

	        //invalidate();
	        //String msg = photo.getTitle(); 
	        //Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	        return false;
	    }
	}


}

