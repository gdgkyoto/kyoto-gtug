package com.lifevar;

import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class SmellMap extends MapActivity {
	
	private MapView mapView;
	private MapController mapController;

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
}