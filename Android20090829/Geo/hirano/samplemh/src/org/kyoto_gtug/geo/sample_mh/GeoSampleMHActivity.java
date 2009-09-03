package org.kyoto_gtug.geo.sample_mh;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
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
    	
    	// MapController の作成
    	mapCtrl = mapView.getController();
    	mapCtrl.setCenter( new GeoPoint( (int)(34.889291 * 1E6), (int)(135.807612 * 1E6) ) ); // 初期Point平等院近辺
    	mapCtrl.setZoom(16);
    	Log.i(LOG_TAG, "create MapController");
    	
    	// LocationManager の作成・リスナーの設定
    	myLocListener = new MyLocationListener();
    	locMgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_METER, myLocListener );
    	Log.i(LOG_TAG, "create MyLocationListener");
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

}