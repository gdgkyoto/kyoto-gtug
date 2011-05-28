package com.lifevar;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    static final int INITIAL_LATITUDE = 35156807;
    static final int INITIAL_LONGITUDE = 136925412;
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
		mapController.setZoom(21);
		mapView.invalidate();

		

		
		
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


	private void setOverlay(GeoPoint point, int c) {

		// Overlayクラスを生成する
		Bitmap bitmap = Bitmap.createBitmap(8, 8, Bitmap.Config.ARGB_8888);
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
			mPoint = point;
			//	           Log.i(TAG, "Point = " + point.getLatitudeE6() + " , " + point.getLongitudeE6());
			return super.onTap(point, mapView);
		}

		// 地図の描画時に、shadow=true, shadow=falseと2回呼び出される
		@Override
		public void draw(Canvas canvas, MapView mapView,
				boolean shadow) {
			super.draw(canvas, mapView, shadow);
			if (!shadow) {
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

}