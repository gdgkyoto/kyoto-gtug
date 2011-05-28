package com.lifevar;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class SmellMap extends MapActivity {

	private MapView mapView;
	private MapController mapController;
    static final int INITIAL_ZOOM_LEVE = 16;
	static final int INITIAL_LATITUDE = 34994548;
    static final int INITIAL_LONGITUDE = 135725412;
	public static final String TAG = "SMELLMAP";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setEnabled(true);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();
		//mapController.setZoom(LocationHelper.ZOOM_INIT);
		// marker
		Drawable pin = getResources().getDrawable( R.drawable.pin);
		PinItemizedOverlay pinOverlay = new PinItemizedOverlay( pin);
		//    mapView.getOverlays().add( pinOverlay);

		GeoPoint tokyo = new GeoPoint( 35681396, 139766049);
		GeoPoint osaka = new GeoPoint( 34701895, 135494975);
		pinOverlay.addPoint( tokyo);
		pinOverlay.addPoint( osaka);
		final MyLocationOverlay ol = new MyLocationOverlay(getApplicationContext(), mapView);
		ol.onProviderEnabled(LocationManager.GPS_PROVIDER); // GPS を使用する
		ol.enableMyLocation();
		ol.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				mapView.getController().animateTo(
						ol.getMyLocation()); // 現在位置を自動追尾する
			}
		});
		mapView.getOverlays().add(ol);
		mapController.setZoom(8);
		mapView.invalidate();

		
        // 位置とズームレベルの初期状態を設定する
        MapController controller = mapView.getController();
        GeoPoint point =  
            new GeoPoint(INITIAL_LATITUDE, INITIAL_LONGITUDE);
//        new GeoPoint(INITIAL_LATITUDE, INITIAL_LONGITUDE);
        controller.setCenter(point);
        controller.setZoom(INITIAL_ZOOM_LEVE);
        
     // イメージを地図上に表示する
/*
        for (int i=0; i< ume.length;) {
≈        	int x = ume[i++];
        	int y = ume[i++];
        	int c = ume[i++];
        	if (c != 0xfffefefe) {
            GeoPoint pt = 
                new GeoPoint(INITIAL_LATITUDE+y*256, INITIAL_LONGITUDE+x*256);
            setOverlay(pt, c);
        	}
        }
 */
        Utils utils = new Utils();
        List<Fragrance> fralist = null;
        try {
			fralist =  utils.getFragrance(0,0,150,150,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int span = mapView.getLatitudeSpan();
		if (fralist != null) {
			Log.v(TAG,"Fla size:"+fralist.size());
			for (Fragrance fra : fralist) {
	            GeoPoint pt = 
	                new GeoPoint( (int)(fra.latitude * 1e6), (int)(fra.longitude * 1e6));
				Log.v(TAG,"Fla latitude:"+fra.latitude);
				Log.v(TAG,"Fla longitude:"+fra.longitude);
				Log.v(TAG,"Fla epara:"+fra.epara1);
				Log.v(TAG,"Fla epara:"+fra.epara2);
				Log.v(TAG,"Fla epara:"+fra.epara3);
	            int c = fra.epara3;
	            c += fra.epara2 << 8;
	            c += fra.epara1 << 16;
	            setOverlay(pt, c+0xff000000);
			}
		}
		else {
			Log.v(TAG,"Fla is null");
		}
		//	        setContentView(mapView);
		//		LinearLayout zoomView = (LinearLayout)mapView.getZoomControls(); 
		//	    zoomView.setLayoutParams( new ViewGroup.LayoutParams 
		//	( ViewGroup.LayoutParams.FILL_PARENT, 
		//	ViewGroup.LayoutParams.FILL_PARENT ) ); 
		//	    zoomView.setGravity(Gravity.BOTTOM + Gravity.CENTER_HORIZONTAL);
		//	    mapView.addView(zoomView); 
		//	    mapView.displayZoomControls(true); 

		//	setContentView(new SmellMapView(this));

	}



	// Option Menus
	private static final int MENU_POST = 0,
	MENU_FORTUNE_TELL = 1,
	MENU_QUIT = 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item0 = menu.add(0, MENU_POST, 0, "投稿");
		item0.setIcon(android.R.drawable.ic_menu_add);

		MenuItem item1 = menu.add(0, MENU_FORTUNE_TELL, 1, "占い");
		item1.setIcon(android.R.drawable.ic_menu_call);

		MenuItem item2 = menu.add(0, MENU_QUIT, 2, "EXIT");
		item2.setIcon(android.R.drawable.ic_menu_crop);


		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_POST:
			return true;
		case MENU_FORTUNE_TELL:
			return true;
		case MENU_QUIT:
			finish();
			return true;
		}
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onClick(View view){
	    Intent i = new Intent(this, FragranceSenseActivity.class);
	    startActivity(i);
	}

	private void setOverlay(GeoPoint point, int c) {

		// Overlayクラスを生成する
		Bitmap bitmap = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
		Canvas cv = new Canvas(bitmap);
		cv.drawColor(c);
		IconOverlay overlay = new IconOverlay(bitmap, point);

		// 生成したOverlayクラスを追加する
		MapView map_view = (MapView) findViewById(R.id.mapview);
		List<Overlay> overlays = map_view.getOverlays();
		overlays.add(overlay);
	}

	// 地図上に表示されるオーバーレイ
	private class IconOverlay extends Overlay {

		// 描画するアイコン
		Bitmap mIcon;
		int mOffsetX;
		int mOffsetY;

		// アイコンを表示する位置
		GeoPoint mPoint;

		IconOverlay(Bitmap icon, GeoPoint initial) {
			// アイコンと、アイコンの中心のオフセット
			mIcon = icon;
			mOffsetX = 0 - icon.getWidth() / 2;
			mOffsetY = 0 - icon.getHeight() / 2;
			mPoint = initial;
		}

		// 地図のタップ時に呼び出される
		@Override
		public boolean onTap(GeoPoint point, MapView mapView) {
			// タップされた位置を記録する
//			mPoint = point;
			//	           Log.i(TAG, "Point = " + point.getLatitudeE6() + " , " + point.getLongitudeE6());
			return super.onTap(point, mapView);
		}

		// 地図の描画時に、shadow=true, shadow=falseと2回呼び出される
		@Override
		public void draw(Canvas canvas, MapView mapView,
				boolean shadow) {
			super.draw(canvas, mapView, shadow);
			if (!shadow) {
//		           Log.i(TAG, "Draw = ");
				// 地図上の場所と、描画用のCanvasの座標の変換
				Projection projection = mapView.getProjection();
				Point point = new Point();
				projection.toPixels(mPoint, point);
				point.offset(mOffsetX, mOffsetY);
				// アイコンを描画
				canvas.drawBitmap(mIcon, point.x, point.y, null);
			}
		}
	};
	   private final static int []  ume = {
		   0,255,0xfffefefe,6,255,0xfffefefe,12,255,0xfffefefe,18,255,0xfffefefe,24,255,0xfffefefe,30,255,0xfffefefe,36,255,0xfffefefe,42,255,0xfffefefe,48,255,0xfffefefe,54,255,0xfffefefe,60,255,0xfffefefe,66,255,0xfffefefe,72,255,0xfffefefe,78,255,0xfffefefe,84,255,0xfffefefe,90,255,0xfffefefe,96,255,0xfffefefe,102,255,0xfffefefe,108,255,0xfffefefe,114,255,0xfffefefe,120,255,0xfffefefe,126,255,0xfffefefe,132,255,0xfffefefe,138,255,0xfffefefe,144,255,0xfffefefe,150,255,0xfffefefe,156,255,0xfffefefe,162,255,0xfffefefe,168,255,0xfffefefe,174,255,0xfffefefe,180,255,0xfffefefe,186,255,0xfffefefe,192,255,0xfffefefe,198,255,0xfffefefe,204,255,0xfffefefe,210,255,0xfffefefe,216,255,0xfffefefe,222,255,0xfffefefe,228,255,0xfffefefe,234,255,0xfffefefe,240,255,0xfffefefe,246,255,0xfffefefe,252,255,0xfffefefe,
		   0,249,0xfffefefe,6,249,0xfffefefe,12,249,0xfffefefe,18,249,0xfffefefe,24,249,0xfffefefe,30,249,0xfffefefe,36,249,0xfffefefe,42,249,0xfffefefe,48,249,0xfffefefe,54,249,0xfffefefe,60,249,0xfffefefe,66,249,0xfffefefe,72,249,0xfffefefe,78,249,0xfffefefe,84,249,0xfffefefe,90,249,0xfffefefe,96,249,0xfffefefe,102,249,0xfffefefe,108,249,0xfffefefe,114,249,0xfffefefe,120,249,0xfffefefe,126,249,0xfff73c81,132,249,0xfff12b6a,138,249,0xfff12b6a,144,249,0xfff987b6,150,249,0xfffefefe,156,249,0xfff53575,162,249,0xfff9498a,168,249,0xfffefefe,174,249,0xfffefefe,180,249,0xfffefefe,186,249,0xfffefefe,192,249,0xfffefefe,198,249,0xfffefefe,204,249,0xfffefefe,210,249,0xfffefefe,216,249,0xfffefefe,222,249,0xfffefefe,228,249,0xfffefefe,234,249,0xfffefefe,240,249,0xfffefefe,246,249,0xfffefefe,252,249,0xfffefefe,
		   0,243,0xfffefefe,6,243,0xfffefefe,12,243,0xfffefefe,18,243,0xfffefefe,24,243,0xfffefefe,30,243,0xfffefefe,36,243,0xfffefefe,42,243,0xfffefefe,48,243,0xfffefefe,54,243,0xfffefefe,60,243,0xfffefefe,66,243,0xfffefefe,72,243,0xfffefefe,78,243,0xfffefefe,84,243,0xfffefefe,90,243,0xfffefefe,96,243,0xfffefefe,102,243,0xfffefefe,108,243,0xfffefefe,114,243,0xfff9498a,120,243,0xfff53575,126,243,0xffe71856,132,243,0xffc90b48,138,243,0xffd50c47,144,243,0xffc90b48,150,243,0xfff53575,156,243,0xffe71856,162,243,0xffd6124d,168,243,0xffe71856,174,243,0xfffefefe,180,243,0xfffefefe,186,243,0xfffefefe,192,243,0xfffefefe,198,243,0xfffefefe,204,243,0xfffefefe,210,243,0xfffefefe,216,243,0xfffefefe,222,243,0xfffefefe,228,243,0xfffefefe,234,243,0xfffefefe,240,243,0xfffefefe,246,243,0xfffefefe,252,243,0xfffefefe,
		   0,237,0xfffefefe,6,237,0xfffefefe,12,237,0xfffefefe,18,237,0xfffefefe,24,237,0xfffefefe,30,237,0xfffefefe,36,237,0xfffefefe,42,237,0xfffefefe,48,237,0xfffefefe,54,237,0xfffefefe,60,237,0xfffefefe,66,237,0xfffefefe,72,237,0xfffefefe,78,237,0xfffefefe,84,237,0xfffefefe,90,237,0xfffefefe,96,237,0xfffefefe,102,237,0xfffefefe,108,237,0xfff53575,114,237,0xffec1d61,120,237,0xffe71856,126,237,0xffc90b48,132,237,0xffc80437,138,237,0xffaa022b,144,237,0xffaa022b,150,237,0xffaa022b,156,237,0xffaa022b,162,237,0xffaa022b,168,237,0xffc90b48,174,237,0xffc90b48,180,237,0xfffefefe,186,237,0xfffefefe,192,237,0xfffefefe,198,237,0xfffefefe,204,237,0xfffefefe,210,237,0xfffefefe,216,237,0xfffefefe,222,237,0xfffefefe,228,237,0xfffefefe,234,237,0xfffefefe,240,237,0xfffefefe,246,237,0xfffefefe,252,237,0xfffefefe,
		   0,231,0xfffefefe,6,231,0xfffefefe,12,231,0xfffefefe,18,231,0xfffefefe,24,231,0xfffefefe,30,231,0xfffefefe,36,231,0xfffefefe,42,231,0xfffefefe,48,231,0xfffefefe,54,231,0xffd65a90,60,231,0xfff9498a,66,231,0xfff86597,72,231,0xfff95596,78,231,0xffe94785,84,231,0xffb82769,90,231,0xfffefefe,96,231,0xfffefefe,102,231,0xfff57696,108,231,0xffe71856,114,231,0xffc90b48,120,231,0xffc90b48,126,231,0xffaa022b,132,231,0xffaa022b,138,231,0xffaa022b,144,231,0xffaa022b,150,231,0xffaa022b,156,231,0xffaa022b,162,231,0xffaa022b,168,231,0xffb2032d,174,231,0xffc90b48,180,231,0xffc90b48,186,231,0xfffefefe,192,231,0xfffefefe,198,231,0xfffefefe,204,231,0xfffefefe,210,231,0xfffefefe,216,231,0xfffefefe,222,231,0xfffefefe,228,231,0xfffefefe,234,231,0xfffefefe,240,231,0xfffefefe,246,231,0xfffefefe,252,231,0xfffefefe,
		   0,225,0xfffefefe,6,225,0xfffefefe,12,225,0xfffefefe,18,225,0xfffefefe,24,225,0xfffefefe,30,225,0xfffefefe,36,225,0xfffefefe,42,225,0xffd53a82,48,225,0xffca2468,54,225,0xffb71863,60,225,0xffb82769,66,225,0xffb71359,72,225,0xffb71863,78,225,0xffb82769,84,225,0xffd62768,90,225,0xffb71863,96,225,0xffb71863,102,225,0xffe71856,108,225,0xffd50c47,114,225,0xffaa022b,120,225,0xffaa022b,126,225,0xffaa022b,132,225,0xffaa022b,138,225,0xffaa022b,144,225,0xffaa022b,150,225,0xffaa022b,156,225,0xffaa022b,162,225,0xffaa022b,168,225,0xffaa022b,174,225,0xffaa022b,180,225,0xffaa022b,186,225,0xffc96477,192,225,0xfffefefe,198,225,0xfffefefe,204,225,0xfffefefe,210,225,0xfffefefe,216,225,0xfffefefe,222,225,0xfffefefe,228,225,0xfffefefe,234,225,0xfffefefe,240,225,0xfffefefe,246,225,0xfffefefe,252,225,0xfffefefe,
		   0,219,0xfffefefe,6,219,0xfffefefe,12,219,0xfffefefe,18,219,0xfffefefe,24,219,0xfffefefe,30,219,0xfffefefe,36,219,0xffd42d72,42,219,0xffd62768,48,219,0xffb40d52,54,219,0xffac0647,60,219,0xffac0647,66,219,0xffac0647,72,219,0xffac0647,78,219,0xffac0647,84,219,0xffb40d52,90,219,0xffca2468,96,219,0xffec1d61,102,219,0xffc80437,108,219,0xffc80437,114,219,0xffaa022b,120,219,0xff9a0229,126,219,0xffaa022b,132,219,0xfffad08a,138,219,0xffaa022b,144,219,0xffaa022b,150,219,0xff960119,156,219,0xfffad08a,162,219,0xffaa022b,168,219,0xffaa022b,174,219,0xffaa022b,180,219,0xffaa022b,186,219,0xffc74767,192,219,0xfffefefe,198,219,0xfffefefe,204,219,0xfffefefe,210,219,0xfffefefe,216,219,0xfffefefe,222,219,0xfffefefe,228,219,0xfffefefe,234,219,0xfffefefe,240,219,0xfffefefe,246,219,0xfffefefe,252,219,0xfffefefe,
		   0,213,0xfffefefe,6,213,0xfffefefe,12,213,0xfffefefe,18,213,0xfffefefe,24,213,0xfffefefe,30,213,0xffd42d72,36,213,0xffca2468,42,213,0xffb40d52,48,213,0xffac0647,54,213,0xffac0647,60,213,0xffac0647,66,213,0xffac0647,72,213,0xffac0647,78,213,0xffb60848,84,213,0xffd62768,90,213,0xfff53575,96,213,0xffd50c47,102,213,0xffc9134a,108,213,0xffc80437,114,213,0xffaa022b,120,213,0xffaa022b,126,213,0xffaa022b,132,213,0xffc74648,138,213,0xffaa022b,144,213,0xff960119,150,213,0xffb71639,156,213,0xffe88e53,162,213,0xffaa022b,168,213,0xffaa022b,174,213,0xffaa022b,180,213,0xffaa022b,186,213,0xffd62768,192,213,0xfffefefe,198,213,0xfffefefe,204,213,0xfffefefe,210,213,0xfffefefe,216,213,0xfffefefe,222,213,0xfffefefe,228,213,0xfffefefe,234,213,0xfffefefe,240,213,0xfffefefe,246,213,0xfffefefe,252,213,0xfffefefe,
		   0,207,0xfffefefe,6,207,0xfffefefe,12,207,0xfffefefe,18,207,0xfffefefe,24,207,0xfff95596,30,207,0xffc81b63,36,207,0xffac0647,42,207,0xffac0647,48,207,0xffa90435,54,207,0xffac0647,60,207,0xffac0647,66,207,0xffac0647,72,207,0xffc90b48,78,207,0xfff12b6a,84,207,0xfff53575,90,207,0xfff9498a,96,207,0xffd5053a,102,207,0xffe88e53,108,207,0xffcf522f,114,207,0xffc80437,120,207,0xffaa022b,126,207,0xffe96f72,132,207,0xffcb3231,138,207,0xffaa022b,144,207,0xffa5031b,150,207,0xffe77987,156,207,0xffe35233,162,207,0xffaa022b,168,207,0xffaa022b,174,207,0xffaa022b,180,207,0xffaa022b,186,207,0xffc91556,192,207,0xfffefefe,198,207,0xfffefefe,204,207,0xfffefefe,210,207,0xfffefefe,216,207,0xfffefefe,222,207,0xfffefefe,228,207,0xfffefefe,234,207,0xfffefefe,240,207,0xfffefefe,246,207,0xfffefefe,252,207,0xfffefefe,
		   0,201,0xfffefefe,6,201,0xfffefefe,12,201,0xfffefefe,18,201,0xfffefefe,24,201,0xffea3675,30,201,0xffc91556,36,201,0xffa90435,42,201,0xffa90435,48,201,0xffb60848,54,201,0xfff12b6a,60,201,0xffd62768,66,201,0xfff53575,72,201,0xfff53575,78,201,0xfff53575,84,201,0xfff53575,90,201,0xfff9498a,96,201,0xfff9498a,102,201,0xfff9498a,108,201,0xfffa67a9,114,201,0xfffa5ca2,120,201,0xfff9498a,126,201,0xffd6124d,132,201,0xffaa022b,138,201,0xffaa022b,144,201,0xffaa022b,150,201,0xffaa022b,156,201,0xffaa022b,162,201,0xffe88e53,168,201,0xffaa022b,174,201,0xffaa022b,180,201,0xffaa022b,186,201,0xffb60848,192,201,0xfffefefe,198,201,0xfffefefe,204,201,0xfffefefe,210,201,0xfffefefe,216,201,0xfffefefe,222,201,0xfffefefe,228,201,0xfffefefe,234,201,0xfffefefe,240,201,0xfffefefe,246,201,0xfffefefe,252,201,0xfffefefe,
		   0,195,0xfffefefe,6,195,0xfffefefe,12,195,0xfffefefe,18,195,0xffea3675,24,195,0xffac0647,30,195,0xffb60848,36,195,0xfff53575,42,195,0xfff53575,48,195,0xfff12b6a,54,195,0xffe92765,60,195,0xfff46689,66,195,0xffe84578,72,195,0xfff53575,78,195,0xfff73c81,84,195,0xfff2af6e,90,195,0xffd26c33,96,195,0xfff9498a,102,195,0xfffa75b7,108,195,0xfff53575,114,195,0xffe92765,120,195,0xffe3053b,126,195,0xffe71856,132,195,0xffaa022b,138,195,0xffaa022b,144,195,0xfff9498a,150,195,0xffaa022b,156,195,0xffaa022b,162,195,0xffb42d30,168,195,0xffaa022b,174,195,0xffaa022b,180,195,0xfff9498a,186,195,0xffe94785,192,195,0xfffefefe,198,195,0xfffefefe,204,195,0xfffefefe,210,195,0xfffefefe,216,195,0xfffefefe,222,195,0xfffefefe,228,195,0xfffefefe,234,195,0xfffefefe,240,195,0xfffefefe,246,195,0xfffefefe,252,195,0xfffefefe,
		   0,189,0xfffefefe,6,189,0xfffefefe,12,189,0xfffefefe,18,189,0xffac0647,24,189,0xffa90435,30,189,0xffd62768,36,189,0xfff53575,42,189,0xfff53575,48,189,0xfff53575,54,189,0xfff53575,60,189,0xfff12b6a,66,189,0xffd7294a,72,189,0xfff53575,78,189,0xfff9498a,84,189,0xfff53575,90,189,0xfffa67a9,96,189,0xfff9498a,102,189,0xfff9498a,108,189,0xffe9ac57,114,189,0xffd5053a,120,189,0xffd50c47,126,189,0xfff6437b,132,189,0xffc80437,138,189,0xffb70534,144,189,0xffc80437,150,189,0xffe71856,156,189,0xfff7548a,162,189,0xffe92765,168,189,0xffd26c33,174,189,0xfff7548a,180,189,0xfffa67a9,186,189,0xffc90b48,192,189,0xffd62768,198,189,0xffc81b63,204,189,0xffc81b63,210,189,0xffca2468,216,189,0xffea3675,222,189,0xfffefefe,228,189,0xfffefefe,234,189,0xfffefefe,240,189,0xfffefefe,246,189,0xfffefefe,252,189,0xfffefefe,
		   0,183,0xfffefefe,6,183,0xfffefefe,12,183,0xffb82769,18,183,0xffea3675,24,183,0xfff53575,30,183,0xfff12b6a,36,183,0xfff53575,42,183,0xfff53575,48,183,0xfff53575,54,183,0xfff53575,60,183,0xfff53575,66,183,0xfff53575,72,183,0xfff9498a,78,183,0xfff9498a,84,183,0xfff53575,90,183,0xfff9498a,96,183,0xffe96f72,102,183,0xffc80437,108,183,0xffc9134a,114,183,0xffc80437,120,183,0xfff7548a,126,183,0xffd26c33,132,183,0xfff9498a,138,183,0xfff9498a,144,183,0xffd6124d,150,183,0xfff9498a,156,183,0xfff95596,162,183,0xfffa75b7,168,183,0xfff53575,174,183,0xfffa67a9,180,183,0xffc80437,186,183,0xffaa022b,192,183,0xffac0647,198,183,0xffb60848,204,183,0xffac0647,210,183,0xffac0647,216,183,0xffac0647,222,183,0xffb71863,228,183,0xffb71863,234,183,0xfffefefe,240,183,0xfffefefe,246,183,0xfffefefe,252,183,0xfffefefe,
		   0,177,0xfffefefe,6,177,0xfffefefe,12,177,0xffe73c82,18,177,0xfff9498a,24,177,0xfff53575,30,177,0xfff53575,36,177,0xfff53575,42,177,0xfff53575,48,177,0xfff53575,54,177,0xfff53575,60,177,0xfffad08a,66,177,0xfff53575,72,177,0xfff6437b,78,177,0xfff95596,84,177,0xfff9498a,90,177,0xffe92765,96,177,0xffcb3231,102,177,0xffe3114d,108,177,0xffc80437,114,177,0xfff53575,120,177,0xffe71856,126,177,0xfff28f91,132,177,0xfff9498a,138,177,0xfff9498a,144,177,0xfff95596,150,177,0xfff86597,156,177,0xfff95596,162,177,0xfff53575,168,177,0xffc90b48,174,177,0xffb2032d,180,177,0xffaa022b,186,177,0xffaa022b,192,177,0xffa90435,198,177,0xff9a0229,204,177,0xffac0647,210,177,0xffac0647,216,177,0xff9b0944,222,177,0xffac0647,228,177,0xffac0647,234,177,0xffb71359,240,177,0xffb71863,246,177,0xfffefefe,252,177,0xfffefefe,
		   0,171,0xfffefefe,6,171,0xfffefefe,12,171,0xfff9498a,18,171,0xfff9498a,24,171,0xfff53575,30,171,0xfff9498a,36,171,0xfff53575,42,171,0xfff53575,48,171,0xfff9498a,54,171,0xfff9498a,60,171,0xffe47138,66,171,0xfff9498a,72,171,0xfff9498a,78,171,0xfff9498a,84,171,0xfff9498a,90,171,0xfff9498a,96,171,0xfff57696,102,171,0xffe71856,108,171,0xff960119,114,171,0xffb72955,120,171,0xffb70534,126,171,0xfff28f91,132,171,0xfff95596,138,171,0xfffa67a9,144,171,0xfff9498a,150,171,0xffd62768,156,171,0xffd8245a,162,171,0xffc80437,168,171,0xffc80437,174,171,0xffaa022b,180,171,0xffaa022b,186,171,0xffaa022b,192,171,0xffaa022b,198,171,0xffaa022b,204,171,0xff9a0229,210,171,0xffd76577,216,171,0xff9b0534,222,171,0xff9a0229,228,171,0xffac0647,234,171,0xffac0647,240,171,0xffb71359,246,171,0xfffefefe,252,171,0xfffefefe,
		   0,165,0xfffefefe,6,165,0xfffefefe,12,165,0xfffefefe,18,165,0xfff9498a,24,165,0xfff9498a,30,165,0xfff53575,36,165,0xfff9498a,42,165,0xfff9498a,48,165,0xfff9498a,54,165,0xfff9498a,60,165,0xfff9498a,66,165,0xfff2af6e,72,165,0xfff9498a,78,165,0xfff95596,84,165,0xfffa67a9,90,165,0xfffa67a9,96,165,0xfff46689,102,165,0xfff46689,108,165,0xff960119,114,165,0xfff28f91,120,165,0xffa5031b,126,165,0xffc91556,132,165,0xfff86597,138,165,0xfff95596,144,165,0xfff698a8,150,165,0xff960119,156,165,0xff9a0229,162,165,0xffc80437,168,165,0xffc80437,174,165,0xffaa022b,180,165,0xffaa022b,186,165,0xffaa022b,192,165,0xffaa022b,198,165,0xffd62768,204,165,0xffe7744e,210,165,0xffb34c16,216,165,0xff9a0229,222,165,0xff9a0229,228,165,0xffa90435,234,165,0xffb60848,240,165,0xffb60848,246,165,0xffb71863,252,165,0xfffefefe,
		   0,159,0xfffefefe,6,159,0xfffefefe,12,159,0xfffefefe,18,159,0xfff9498a,24,159,0xfff9498a,30,159,0xfff53575,36,159,0xfff9498a,42,159,0xfff9498a,48,159,0xfff9498a,54,159,0xfff9498a,60,159,0xfff9498a,66,159,0xfff9498a,72,159,0xfff53575,78,159,0xfffa67a9,84,159,0xfffa75b7,90,159,0xfff797b7,96,159,0xfff95596,102,159,0xffe94667,108,159,0xffaa022b,114,159,0xfff28f91,120,159,0xffd8245a,126,159,0xffaa022b,132,159,0xfff28f91,138,159,0xffc9134a,144,159,0xffa90435,150,159,0xffd74777,156,159,0xff960119,162,159,0xffc90b48,168,159,0xffd73757,174,159,0xffd26c33,180,159,0xffb42d30,186,159,0xfff7548a,192,159,0xffd6124d,198,159,0xffb70534,204,159,0xff9a0229,210,159,0xffaa022b,216,159,0xff9a0229,222,159,0xff9a0229,228,159,0xffaa022b,234,159,0xffac0647,240,159,0xffb60848,246,159,0xffac0647,252,159,0xfffefefe,
		   0,153,0xfffefefe,6,153,0xfffefefe,12,153,0xfffefefe,18,153,0xfff95596,24,153,0xfff94e90,30,153,0xfff9498a,36,153,0xfff9498a,42,153,0xfff9498a,48,153,0xfff9498a,54,153,0xfff95596,60,153,0xfff9498a,66,153,0xfff9498a,72,153,0xfff9498a,78,153,0xfffa67a9,84,153,0xfff788a9,90,153,0xfffa8bc4,96,153,0xfff7abaf,102,153,0xfff6ab8e,108,153,0xfff6ab8e,114,153,0xfff7abaf,120,153,0xffed906d,126,153,0xffed906d,132,153,0xffe7744e,138,153,0xfff28f91,144,153,0xfff7abaf,150,153,0xffaa022b,156,153,0xffb70534,162,153,0xfff788a9,168,153,0xffc80437,174,153,0xfff698a8,180,153,0xffb70534,186,153,0xffaa022b,192,153,0xffaa022b,198,153,0xff9a0229,204,153,0xffaa022b,210,153,0xffb71645,216,153,0xffa90435,222,153,0xff9a0229,228,153,0xff9a0229,234,153,0xff9a0229,240,153,0xffac0647,246,153,0xffac0647,252,153,0xffda89a6,
		   0,147,0xfffefefe,6,147,0xfffefefe,12,147,0xfffefefe,18,147,0xfffefefe,24,147,0xfffa67a9,30,147,0xfffa5ca2,36,147,0xfff95596,42,147,0xfff28f91,48,147,0xfff6cb72,54,147,0xfff9498a,60,147,0xfff9498a,66,147,0xfff9498a,72,147,0xfff95596,78,147,0xfff9498a,84,147,0xfffa67a9,90,147,0xfff7abaf,96,147,0xfff9b1c9,102,147,0xfff28f91,108,147,0xfffad08a,114,147,0xffd5704b,120,147,0xfff2af6e,126,147,0xffe88e53,132,147,0xffe88e53,138,147,0xfff6cb72,144,147,0xffe96f72,150,147,0xffb9344a,156,147,0xffd85777,162,147,0xffd76577,168,147,0xffaa022b,174,147,0xff960119,180,147,0xff960119,186,147,0xff9a0229,192,147,0xffb71645,198,147,0xfff7abaf,204,147,0xffe88e53,210,147,0xffb54e2a,216,147,0xffc90b48,222,147,0xffa90435,228,147,0xffaa022b,234,147,0xffac0647,240,147,0xffa90435,246,147,0xffac0647,252,147,0xffe94785,
		   0,141,0xfffefefe,6,141,0xfffefefe,12,141,0xfffefefe,18,141,0xfffefefe,24,141,0xfffefefe,30,141,0xfffa67a9,36,141,0xfffa5ca2,42,141,0xfff9498a,48,141,0xfff6437b,54,141,0xfff95596,60,141,0xfffa5ca2,66,141,0xfff9498a,72,141,0xffe71856,78,141,0xfff9498a,84,141,0xfff6cb72,90,141,0xffd54869,96,141,0xfffac8d3,102,141,0xffe88e53,108,141,0xffed906d,114,141,0xffca4d17,120,141,0xfffad08a,126,141,0xffd26c33,132,141,0xffd26c33,138,141,0xffe7744e,144,141,0xfff7abaf,150,141,0xfff9c6af,156,141,0xffc90b48,162,141,0xffaa022b,168,141,0xffaa022b,174,141,0xfff57696,180,141,0xfff57696,186,141,0xffaa022b,192,141,0xffaa022b,198,141,0xffd71855,204,141,0xffd71855,210,141,0xffc91556,216,141,0xffd71855,222,141,0xfff53575,228,141,0xfff53575,234,141,0xfff53575,240,141,0xffb60848,246,141,0xfff12b6a,252,141,0xffc81b63,
		   0,135,0xfffefefe,6,135,0xfffefefe,12,135,0xfffefefe,18,135,0xfffefefe,24,135,0xfffefefe,30,135,0xfffefefe,36,135,0xfffa67a9,42,135,0xfffa67a9,48,135,0xfffa67a9,54,135,0xfffa67a9,60,135,0xfff9498a,66,135,0xfff95596,72,135,0xfff9498a,78,135,0xfff698a8,84,135,0xfff9b1c9,90,135,0xfff7abaf,96,135,0xffe96f72,102,135,0xfff28f91,108,135,0xffe7744e,114,135,0xffb13311,120,135,0xffca4d17,126,135,0xfff2af6e,132,135,0xffb13311,138,135,0xffd26c33,144,135,0xffd26c33,150,135,0xfff7abaf,156,135,0xfff28f91,162,135,0xffd96786,168,135,0xff9a0229,174,135,0xff960119,180,135,0xff9a0229,186,135,0xffaa022b,192,135,0xffaa022b,198,135,0xfff12b6a,204,135,0xfff53575,210,135,0xfff53575,216,135,0xfff53575,222,135,0xfff53575,228,135,0xfff9498a,234,135,0xfff9498a,240,135,0xfff9498a,246,135,0xfff95596,252,135,0xfffa67a9,
		   0,129,0xfffefefe,6,129,0xfffefefe,12,129,0xfffefefe,18,129,0xfffefefe,24,129,0xfffefefe,30,129,0xfffefefe,36,129,0xfff53575,42,129,0xffe71856,48,129,0xffe9336b,54,129,0xffe88e53,60,129,0xffe84578,66,129,0xfff9b1c9,72,129,0xfff7abaf,78,129,0xfff7abaf,84,129,0xfff9b1c9,90,129,0xfff7abaf,96,129,0xfff698a8,102,129,0xffe7744e,108,129,0xffe9ac57,114,129,0xffcf8939,120,129,0xffb34c16,126,129,0xfff2af6e,132,129,0xff960119,138,129,0xffda886d,144,129,0xffd5704b,150,129,0xfff28f91,156,129,0xffd71855,162,129,0xffb70534,168,129,0xffaa022b,174,129,0xffaa022b,180,129,0xffaa022b,186,129,0xffaa022b,192,129,0xffe92765,198,129,0xfff53575,204,129,0xfff53575,210,129,0xfff53575,216,129,0xfff53575,222,129,0xfff53575,228,129,0xfff53575,234,129,0xfff73c81,240,129,0xfff9498a,246,129,0xfff53575,252,129,0xfffefefe,
		   0,123,0xfffefefe,6,123,0xfffefefe,12,123,0xfffefefe,18,123,0xfffefefe,24,123,0xfff86597,30,123,0xffec1d61,36,123,0xffe71856,42,123,0xffe71856,48,123,0xffd5053a,54,123,0xffc80437,60,123,0xffe92765,66,123,0xfff53575,72,123,0xffd50c47,78,123,0xffe71856,84,123,0xfff28f91,90,123,0xff972b0f,96,123,0xffd45666,102,123,0xffe7744e,108,123,0xffd26c33,114,123,0xffb13311,120,123,0xff890614,126,123,0xff780a0f,132,123,0xfff6ab8e,138,123,0xffe88e53,144,123,0xffed906d,150,123,0xfff7abaf,156,123,0xfff9b1c9,162,123,0xfff9b1c9,168,123,0xffea3675,174,123,0xffc90b48,180,123,0xffe92765,186,123,0xfff53575,192,123,0xfff53575,198,123,0xfff53575,204,123,0xfff53575,210,123,0xfff73c81,216,123,0xfff53575,222,123,0xfff9498a,228,123,0xfff53575,234,123,0xfff53575,240,123,0xfff9498a,246,123,0xfff53575,252,123,0xfffefefe,
		   0,117,0xfffefefe,6,117,0xfffefefe,12,117,0xfffefefe,18,117,0xfffefefe,24,117,0xffe71856,30,117,0xffc80437,36,117,0xfff2af6e,42,117,0xffe88e53,48,117,0xffc80437,54,117,0xffc80437,60,117,0xffea3675,66,117,0xfffa67a9,72,117,0xffd6124d,78,117,0xffd5053a,84,117,0xffe71856,90,117,0xfffa67a9,96,117,0xfff7abaf,102,117,0xffc73717,108,117,0xffd26c33,114,117,0xfff2af6e,120,117,0xffed906d,126,117,0xffd26c33,132,117,0xffe88e53,138,117,0xffcf522f,144,117,0xfff7abaf,150,117,0xffaa022b,156,117,0xffc80437,162,117,0xffd6124d,168,117,0xfff9498a,174,117,0xfff788a9,180,117,0xfff6cb72,186,117,0xfff876a9,192,117,0xfff987b6,198,117,0xfff53575,204,117,0xfff53575,210,117,0xfff53575,216,117,0xffe96f72,222,117,0xfff9498a,228,117,0xfff73c81,234,117,0xfff53575,240,117,0xfff3306e,246,117,0xfff53575,252,117,0xfffefefe,
		   0,111,0xfffefefe,6,111,0xfffefefe,12,111,0xfffefefe,18,111,0xfff12b6a,24,111,0xffd50c47,30,111,0xffc80437,36,111,0xffaa022b,42,111,0xffaa022b,48,111,0xffaa022b,54,111,0xffc80437,60,111,0xffc80437,66,111,0xffe71856,72,111,0xffc80437,78,111,0xffc80437,84,111,0xffe6225d,90,111,0xfff987b6,96,111,0xfff788a9,102,111,0xfff28f91,108,111,0xffb2032d,114,111,0xfff28f91,120,111,0xfff6ab8e,126,111,0xffd5704b,132,111,0xffc8564a,138,111,0xfff28f91,144,111,0xff960119,150,111,0xff9a0229,156,111,0xfff9b1c9,162,111,0xffc9134a,168,111,0xfff9498a,174,111,0xffc80437,180,111,0xffb13311,186,111,0xffc80437,192,111,0xfff9498a,198,111,0xfff9498a,204,111,0xffea3675,210,111,0xffc90b48,216,111,0xfffad08a,222,111,0xfff53575,228,111,0xfff9498a,234,111,0xfff9498a,240,111,0xfff9498a,246,111,0xfff95596,252,111,0xfffefefe,
		   0,105,0xfffefefe,6,105,0xfffefefe,12,105,0xfff53575,18,105,0xffc90b48,24,105,0xffaa022b,30,105,0xffaa022b,36,105,0xffaa022b,42,105,0xffaa022b,48,105,0xffaa022b,54,105,0xffaa022b,60,105,0xffaa022b,66,105,0xffb70534,72,105,0xffaa022b,78,105,0xfffbd4e4,84,105,0xfff95596,90,105,0xfffa67a9,96,105,0xfff9b1c9,102,105,0xffaa022b,108,105,0xffaa022b,114,105,0xffd7768a,120,105,0xfff7abaf,126,105,0xffb42d30,132,105,0xffb62748,138,105,0xff7d1425,144,105,0xff881317,150,105,0xffc80437,156,105,0xffaa022b,162,105,0xffb71645,168,105,0xfffa67a9,174,105,0xffaa022b,180,105,0xffa90435,186,105,0xfff9498a,192,105,0xfff95596,198,105,0xfff95596,204,105,0xfff9498a,210,105,0xfff9498a,216,105,0xffac0647,222,105,0xfff9498a,228,105,0xfff9498a,234,105,0xfff95596,240,105,0xfffa67a9,246,105,0xfffefefe,252,105,0xfffefefe,
		   0,99,0xfffefefe,6,99,0xfffefefe,12,99,0xffa90435,18,99,0xffaa022b,24,99,0xffaa022b,30,99,0xffaa022b,36,99,0xffaa022b,42,99,0xffaa022b,48,99,0xffaa022b,54,99,0xffaa022b,60,99,0xffaa022b,66,99,0xffb71639,72,99,0xffe86797,78,99,0xffaa022b,84,99,0xffe71856,90,99,0xfff9b1c9,96,99,0xff9a0229,102,99,0xff960119,108,99,0xfffefefe,114,99,0xffaa022b,120,99,0xfffac8d3,126,99,0xfff2af6e,132,99,0xffaa022b,138,99,0xffaa022b,144,99,0xfffceaf2,150,99,0xfff53575,156,99,0xffe92765,162,99,0xffc90b48,168,99,0xffe3114d,174,99,0xfff987b6,180,99,0xfff9498a,186,99,0xffb60848,192,99,0xfff9498a,198,99,0xfff95596,204,99,0xfff95596,210,99,0xfff95596,216,99,0xfff95596,222,99,0xfff95596,228,99,0xfffa5ca2,234,99,0xfffa67a9,240,99,0xfffefefe,246,99,0xfffefefe,252,99,0xfffefefe,
		   0,93,0xfffefefe,6,93,0xfffefefe,12,93,0xffaa022b,18,93,0xffaa022b,24,93,0xffaa022b,30,93,0xffaa022b,36,93,0xffaa022b,42,93,0xffaa022b,48,93,0xffed906d,54,93,0xffaa022b,60,93,0xfffac8d3,66,93,0xffb2032d,72,93,0xffc9134a,78,93,0xfffa8bc4,84,93,0xfffac8d3,90,93,0xfffa67a9,96,93,0xff960119,102,93,0xff960119,108,93,0xffb34c16,114,93,0xffd71855,120,93,0xfff28f91,126,93,0xffc80437,132,93,0xff9a0229,138,93,0xffaa022b,144,93,0xffaa022b,150,93,0xffe86797,156,93,0xffb70534,162,93,0xffaa022b,168,93,0xffaa022b,174,93,0xffc80437,180,93,0xfff28f91,186,93,0xfff53575,192,93,0xfff95596,198,93,0xffc91556,204,93,0xfffa67a9,210,93,0xfffa67a9,216,93,0xfffa67a9,222,93,0xfffa67a9,228,93,0xfffa67a9,234,93,0xfffefefe,240,93,0xfffefefe,246,93,0xfffefefe,252,93,0xfffefefe,
		   0,87,0xfffefefe,6,87,0xfffefefe,12,87,0xffb60848,18,87,0xfff6437b,24,87,0xfff9498a,30,87,0xffd71855,36,87,0xffaa022b,42,87,0xffcb636a,48,87,0xffe88e53,54,87,0xffe92765,60,87,0xfff12b6a,66,87,0xfffa67a9,72,87,0xfffad08a,78,87,0xfff7abaf,84,87,0xffe71856,90,87,0xffd5053a,96,87,0xffe71856,102,87,0xffb2032d,108,87,0xffca2468,114,87,0xffc80437,120,87,0xfff7abaf,126,87,0xffaa022b,132,87,0xffa92446,138,87,0xffc90b48,144,87,0xff9a0229,150,87,0xfff9b1c9,156,87,0xffc80437,162,87,0xffc90b48,168,87,0xffb70534,174,87,0xfff12b6a,180,87,0xfff6cb72,186,87,0xffe71856,192,87,0xfffa67a9,198,87,0xfffa67a9,204,87,0xfff95596,210,87,0xffd42d72,216,87,0xfffa67a9,222,87,0xfff95596,228,87,0xfffefefe,234,87,0xfffefefe,240,87,0xfffefefe,246,87,0xfffefefe,252,87,0xfffefefe,
		   0,81,0xfffefefe,6,81,0xfffefefe,12,81,0xfff53575,18,81,0xfff9498a,24,81,0xfff12b6a,30,81,0xfff53575,36,81,0xfff3306e,42,81,0xfff12b6a,48,81,0xffe6225d,54,81,0xfff12b6a,60,81,0xfff53575,66,81,0xfff53575,72,81,0xffd98d4b,78,81,0xfff53575,84,81,0xfff12b6a,90,81,0xffc80437,96,81,0xffe92765,102,81,0xffea3675,108,81,0xffd50c47,114,81,0xfff12b6a,120,81,0xfff9498a,126,81,0xffa5031b,132,81,0xffb94f6c,138,81,0xffaa022b,144,81,0xffaa022b,150,81,0xffd87797,156,81,0xffa90435,162,81,0xffaa022b,168,81,0xff960119,174,81,0xffc90b48,180,81,0xfff12b6a,186,81,0xffc9134a,192,81,0xffd73768,198,81,0xfffefefe,204,81,0xffea77a7,210,81,0xfffefefe,216,81,0xfffefefe,222,81,0xfffefefe,228,81,0xfffefefe,234,81,0xfffefefe,240,81,0xfffefefe,246,81,0xfffefefe,252,81,0xfffefefe,
		   0,75,0xfffefefe,6,75,0xfffefefe,12,75,0xffc9134a,18,75,0xffaa022b,24,75,0xffaa022b,30,75,0xffc90b48,36,75,0xffd71855,42,75,0xfff53575,48,75,0xfff6ab8e,54,75,0xffe88e53,60,75,0xfff9498a,66,75,0xffb70534,72,75,0xfff53575,78,75,0xffe3114d,84,75,0xffe71856,90,75,0xfff9498a,96,75,0xfff95596,102,75,0xffc80437,108,75,0xffc90b48,114,75,0xfff9498a,120,75,0xfff53575,126,75,0xff960119,132,75,0xfff7abaf,138,75,0xff960119,144,75,0xffaa022b,150,75,0xfffbe38c,156,75,0xfff876a9,162,75,0xff9a0229,168,75,0xff9a0229,174,75,0xffe92765,180,75,0xffe92765,186,75,0xfff12b6a,192,75,0xffd71855,198,75,0xfffefefe,204,75,0xfffefefe,210,75,0xfffefefe,216,75,0xfffefefe,222,75,0xfffefefe,228,75,0xfffefefe,234,75,0xfffefefe,240,75,0xfffefefe,246,75,0xfffefefe,252,75,0xfffefefe,
		   0,69,0xfffefefe,6,69,0xfffefefe,12,69,0xffd62768,18,69,0xffb70534,24,69,0xffaa022b,30,69,0xffb70534,36,69,0xffd6124d,42,69,0xfff53575,48,69,0xfff53575,54,69,0xfff53575,60,69,0xffd71855,66,69,0xffaa022b,72,69,0xfff53575,78,69,0xffd6124d,84,69,0xfff53575,90,69,0xfff9498a,96,69,0xfffa67a9,102,69,0xffc80437,108,69,0xffc80437,114,69,0xfff6437b,120,69,0xfff12b6a,126,69,0xff960119,132,69,0xfffbe3eb,138,69,0xff960119,144,69,0xffb2032d,150,69,0xffed906d,156,69,0xffe7744e,162,69,0xffaa022b,168,69,0xffc9134a,174,69,0xffa90435,180,69,0xfff53575,186,69,0xfff9498a,192,69,0xfff9498a,198,69,0xfffefefe,204,69,0xfffefefe,210,69,0xfffefefe,216,69,0xfffefefe,222,69,0xfffefefe,228,69,0xfffefefe,234,69,0xfffefefe,240,69,0xfffefefe,246,69,0xfffefefe,252,69,0xfffefefe,
		   0,63,0xfffefefe,6,63,0xfffefefe,12,63,0xfffefefe,18,63,0xfffefefe,24,63,0xfff9498a,30,63,0xfff53575,36,63,0xfff9498a,42,63,0xfff53575,48,63,0xfff53575,54,63,0xffc80437,60,63,0xfff53575,66,63,0xffea3675,72,63,0xffd50c47,78,63,0xfff9498a,84,63,0xfff9498a,90,63,0xfff9498a,96,63,0xfffa67a9,102,63,0xffc80437,108,63,0xffc80437,114,63,0xffd26c33,120,63,0xffe6225d,126,63,0xff960119,132,63,0xfffceaf2,138,63,0xff960119,144,63,0xffaa022b,150,63,0xffaa022b,156,63,0xffb86b2a,162,63,0xffc90b48,168,63,0xffaa022b,174,63,0xfff6437b,180,63,0xffe92765,186,63,0xfff9498a,192,63,0xfff95596,198,63,0xfffefefe,204,63,0xfffefefe,210,63,0xfffefefe,216,63,0xfffefefe,222,63,0xfffefefe,228,63,0xfffefefe,234,63,0xfffefefe,240,63,0xfffefefe,246,63,0xfffefefe,252,63,0xfffefefe,
		   0,57,0xfffefefe,6,57,0xfffefefe,12,57,0xfffefefe,18,57,0xfffefefe,24,57,0xffaa022b,30,57,0xfff9498a,36,57,0xfff9498a,42,57,0xfff9498a,48,57,0xfff9498a,54,57,0xffaa022b,60,57,0xffd71855,66,57,0xffd71855,72,57,0xffa90435,78,57,0xfff95596,84,57,0xfff95596,90,57,0xfffa6db1,96,57,0xfff28f91,102,57,0xfffbd4e4,108,57,0xffaa022b,114,57,0xffaa022b,120,57,0xffd6124d,126,57,0xff960119,132,57,0xfffac8d3,138,57,0xff960119,144,57,0xff960119,150,57,0xffd6124d,156,57,0xffaa022b,162,57,0xfff53575,168,57,0xffd71855,174,57,0xffc90b48,180,57,0xfff9498a,186,57,0xfff9498a,192,57,0xfffa67a9,198,57,0xfffefefe,204,57,0xfffefefe,210,57,0xfffefefe,216,57,0xfffefefe,222,57,0xfffefefe,228,57,0xfffefefe,234,57,0xfffefefe,240,57,0xfffefefe,246,57,0xfffefefe,252,57,0xfffefefe,
		   0,51,0xfffefefe,6,51,0xfffefefe,12,51,0xfffefefe,18,51,0xfffefefe,24,51,0xffa90435,30,51,0xfff95596,36,51,0xfff9498a,42,51,0xfff9498a,48,51,0xfff9498a,54,51,0xffe9336b,60,51,0xffb70534,66,51,0xfff95596,72,51,0xfffa67a9,78,51,0xfffa67a9,84,51,0xfffa75b7,90,51,0xfffa75b7,96,51,0xffd98d4b,102,51,0xffb62748,108,51,0xff960119,114,51,0xff960119,120,51,0xffb70534,126,51,0xffaa022b,132,51,0xffc86a84,138,51,0xffb70534,144,51,0xffb70534,150,51,0xfff53575,156,51,0xffc9134a,162,51,0xfff53575,168,51,0xfff53575,174,51,0xfff9498a,180,51,0xffc90b48,186,51,0xffd62768,192,51,0xffd62768,198,51,0xfffefefe,204,51,0xfffefefe,210,51,0xfffefefe,216,51,0xfffefefe,222,51,0xfffefefe,228,51,0xfffefefe,234,51,0xfffefefe,240,51,0xfffefefe,246,51,0xfffefefe,252,51,0xfffefefe,
		   0,45,0xfffefefe,6,45,0xfffefefe,12,45,0xfffefefe,18,45,0xfffefefe,24,45,0xfffefefe,30,45,0xfffa67a9,36,45,0xfffa5ca2,42,45,0xfffa67a9,48,45,0xfffa67a9,54,45,0xfffa67a9,60,45,0xfff95596,66,45,0xfffa67a9,72,45,0xfffa67a9,78,45,0xfffa75b7,84,45,0xfffa75b7,90,45,0xffb70534,96,45,0xffaa022b,102,45,0xffaa022b,108,45,0xffaa022b,114,45,0xffaa022b,120,45,0xffa90435,126,45,0xfffad08a,132,45,0xfff2af6e,138,45,0xffd6124d,144,45,0xffd71855,150,45,0xfff53575,156,45,0xffb70534,162,45,0xffe71856,168,45,0xfff53575,174,45,0xfff9498a,180,45,0xfff95596,186,45,0xffd62768,192,45,0xffca2468,198,45,0xfffefefe,204,45,0xfffefefe,210,45,0xfffefefe,216,45,0xfffefefe,222,45,0xfffefefe,228,45,0xfffefefe,234,45,0xfffefefe,240,45,0xfffefefe,246,45,0xfffefefe,252,45,0xfffefefe,
		   0,39,0xfffefefe,6,39,0xfffefefe,12,39,0xfffefefe,18,39,0xfffefefe,24,39,0xfffefefe,30,39,0xfffefefe,36,39,0xfffa75b7,42,39,0xfffa75b7,48,39,0xfffa75b7,54,39,0xfffa8bc4,60,39,0xfffa75b7,66,39,0xfffa75b7,72,39,0xfffb79c1,78,39,0xfffefefe,84,39,0xfffefefe,90,39,0xffaa022b,96,39,0xffaa022b,102,39,0xffaa022b,108,39,0xffaa022b,114,39,0xffaa022b,120,39,0xffe88e53,126,39,0xffaa022b,132,39,0xfff9498a,138,39,0xfff9498a,144,39,0xffd71855,150,39,0xffc90b48,156,39,0xfff53575,162,39,0xffc80437,168,39,0xfff95596,174,39,0xfff95596,180,39,0xfffa67a9,186,39,0xfff95596,192,39,0xfffefefe,198,39,0xfffefefe,204,39,0xfffefefe,210,39,0xfffefefe,216,39,0xfffefefe,222,39,0xfffefefe,228,39,0xfffefefe,234,39,0xfffefefe,240,39,0xfffefefe,246,39,0xfffefefe,252,39,0xfffefefe,
		   0,33,0xfffefefe,6,33,0xfffefefe,12,33,0xfffefefe,18,33,0xfffefefe,24,33,0xfffefefe,30,33,0xfffefefe,36,33,0xfffefefe,42,33,0xfffefefe,48,33,0xfffefefe,54,33,0xfffefefe,60,33,0xfffefefe,66,33,0xfffefefe,72,33,0xfffefefe,78,33,0xfffefefe,84,33,0xfffefefe,90,33,0xfffefefe,96,33,0xffaa022b,102,33,0xffaa022b,108,33,0xffe92765,114,33,0xfff9498a,120,33,0xffd63847,126,33,0xffc90b48,132,33,0xfff53575,138,33,0xfff9498a,144,33,0xffea3675,150,33,0xfff9498a,156,33,0xfff95596,162,33,0xffea3675,168,33,0xfff9498a,174,33,0xfff9498a,180,33,0xfff53575,186,33,0xffe85787,192,33,0xfffefefe,198,33,0xfffefefe,204,33,0xfffefefe,210,33,0xfffefefe,216,33,0xfffefefe,222,33,0xfffefefe,228,33,0xfffefefe,234,33,0xfffefefe,240,33,0xfffefefe,246,33,0xfffefefe,252,33,0xfffefefe,
		   0,27,0xfffefefe,6,27,0xfffefefe,12,27,0xfffefefe,18,27,0xfffefefe,24,27,0xfffefefe,30,27,0xfffefefe,36,27,0xfffefefe,42,27,0xfffefefe,48,27,0xfffefefe,54,27,0xfffefefe,60,27,0xfffefefe,66,27,0xfffefefe,72,27,0xfffefefe,78,27,0xfffefefe,84,27,0xfffefefe,90,27,0xfffefefe,96,27,0xffc90b48,102,27,0xffd71855,108,27,0xfff9498a,114,27,0xfff9498a,120,27,0xfff9498a,126,27,0xffe92765,132,27,0xffea3675,138,27,0xfff9498a,144,27,0xffc90b48,150,27,0xfff9498a,156,27,0xfff9498a,162,27,0xffc90b48,168,27,0xfff9498a,174,27,0xfff95596,180,27,0xfff9498a,186,27,0xfffefefe,192,27,0xfffefefe,198,27,0xfffefefe,204,27,0xfffefefe,210,27,0xfffefefe,216,27,0xfffefefe,222,27,0xfffefefe,228,27,0xfffefefe,234,27,0xfffefefe,240,27,0xfffefefe,246,27,0xfffefefe,252,27,0xfffefefe,
		   0,21,0xfffefefe,6,21,0xfffefefe,12,21,0xfffefefe,18,21,0xfffefefe,24,21,0xfffefefe,30,21,0xfffefefe,36,21,0xfffefefe,42,21,0xfffefefe,48,21,0xfffefefe,54,21,0xfffefefe,60,21,0xfffefefe,66,21,0xfffefefe,72,21,0xfffefefe,78,21,0xfffefefe,84,21,0xfffefefe,90,21,0xfffefefe,96,21,0xfffefefe,102,21,0xfffa67a9,108,21,0xfffa67a9,114,21,0xfff94e90,120,21,0xfff95596,126,21,0xfff53575,132,21,0xffe6225d,138,21,0xfffa67a9,144,21,0xffe92765,150,21,0xfffa5ca2,156,21,0xfff9498a,162,21,0xffd62768,168,21,0xffd62768,174,21,0xfffa67a9,180,21,0xfffefefe,186,21,0xfffefefe,192,21,0xfffefefe,198,21,0xfffefefe,204,21,0xfffefefe,210,21,0xfffefefe,216,21,0xfffefefe,222,21,0xfffefefe,228,21,0xfffefefe,234,21,0xfffefefe,240,21,0xfffefefe,246,21,0xfffefefe,252,21,0xfffefefe,
		   0,15,0xfffefefe,6,15,0xfffefefe,12,15,0xfffefefe,18,15,0xfffefefe,24,15,0xfffefefe,30,15,0xfffefefe,36,15,0xfffefefe,42,15,0xfffefefe,48,15,0xfffefefe,54,15,0xfffefefe,60,15,0xfffefefe,66,15,0xfffefefe,72,15,0xfffefefe,78,15,0xfffefefe,84,15,0xfffefefe,90,15,0xfffefefe,96,15,0xfffefefe,102,15,0xfffefefe,108,15,0xfffa67a9,114,15,0xfffa67a9,120,15,0xfffa67a9,126,15,0xffb60848,132,15,0xffd71855,138,15,0xfff95596,144,15,0xfff95596,150,15,0xfff9498a,156,15,0xfff9498a,162,15,0xfffa67a9,168,15,0xfff9498a,174,15,0xfffefefe,180,15,0xfffefefe,186,15,0xfffefefe,192,15,0xfffefefe,198,15,0xfffefefe,204,15,0xfffefefe,210,15,0xfffefefe,216,15,0xfffefefe,222,15,0xfffefefe,228,15,0xfffefefe,234,15,0xfffefefe,240,15,0xfffefefe,246,15,0xfffefefe,252,15,0xfffefefe,
		   0,9,0xfffefefe,6,9,0xfffefefe,12,9,0xfffefefe,18,9,0xfffefefe,24,9,0xfffefefe,30,9,0xfffefefe,36,9,0xfffefefe,42,9,0xfffefefe,48,9,0xfffefefe,54,9,0xfffefefe,60,9,0xfffefefe,66,9,0xfffefefe,72,9,0xfffefefe,78,9,0xfffefefe,84,9,0xfffefefe,90,9,0xfffefefe,96,9,0xfffefefe,102,9,0xfffefefe,108,9,0xfffefefe,114,9,0xffd65a90,120,9,0xfffa67a9,126,9,0xfffa75b7,132,9,0xfffa67a9,138,9,0xffca2468,144,9,0xfff9498a,150,9,0xffd85587,156,9,0xfffefefe,162,9,0xfffefefe,168,9,0xfffefefe,174,9,0xfffefefe,180,9,0xfffefefe,186,9,0xfffefefe,192,9,0xfffefefe,198,9,0xfffefefe,204,9,0xfffefefe,210,9,0xfffefefe,216,9,0xfffefefe,222,9,0xfffefefe,228,9,0xfffefefe,234,9,0xfffefefe,240,9,0xfffefefe,246,9,0xfffefefe,252,9,0xfffefefe,
		   0,3,0xfffefefe,6,3,0xfffefefe,12,3,0xfffefefe,18,3,0xfffefefe,24,3,0xfffefefe,30,3,0xfffefefe,36,3,0xfffefefe,42,3,0xfffefefe,48,3,0xfffefefe,54,3,0xfffefefe,60,3,0xfffefefe,66,3,0xfffefefe,72,3,0xfffefefe,78,3,0xfffefefe,84,3,0xfffefefe,90,3,0xfffefefe,96,3,0xfffefefe,102,3,0xfffefefe,108,3,0xfffefefe,114,3,0xfffefefe,120,3,0xfffefefe,126,3,0xfffefefe,132,3,0xfffefefe,138,3,0xfffefefe,144,3,0xfffefefe,150,3,0xfffefefe,156,3,0xfffefefe,162,3,0xfffefefe,168,3,0xfffefefe,174,3,0xfffefefe,180,3,0xfffefefe,186,3,0xfffefefe,192,3,0xfffefefe,198,3,0xfffefefe,204,3,0xfffefefe,210,3,0xfffefefe,216,3,0xfffefefe,222,3,0xfffefefe,228,3,0xfffefefe,234,3,0xfffefefe,240,3,0xfffefefe,246,3,0xfffefefe,252,3,0xfffefefe,
	   };

}