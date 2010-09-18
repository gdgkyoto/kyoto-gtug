package org.hackathon.map2;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ViewMapActivity extends MapActivity {

	private MapView map;
	private double lat;
	private double lon;
	private String voice;
	private MyLocation mMyLocation = null;
	private List<Overlay> overlayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		lat = extras.getDouble("lat");
		lon = extras.getDouble("lon");
		voice = extras.getString("voice");

		map = (MapView) findViewById(R.id.myMap);
		map.setEnabled(true);
		map.setClickable(true);
		map.setBuiltInZoomControls(true);
		
		GeoPoint gp = new GeoPoint((int)lat,(int)lon);
		
		mMyLocation = new MyLocation(this,map,gp);
		mMyLocation.enableMyLocation();
		
		overlayList=map.getOverlays();
		overlayList.add(mMyLocation);

		Log.d("lat=======",""+lat);
		Log.d("lon=======",""+lon);
		Log.d("voice=======",voice);
		Toast.makeText(getApplicationContext(), voice,Toast.LENGTH_LONG);
		

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class MyLocation extends MyLocationOverlay {

		private MapView mv;
		private GeoPoint gp;

		public MyLocation(Context context, MapView mapView, GeoPoint point) {

			super(context, mapView);
			this.mv = mapView;
			this.gp = point;
			// TODO Auto-generated constructor stub
		}

		@Override
		public synchronized void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			super.onLocationChanged(location);
			mv.getController().setCenter(gp);
		}
		
	}

}
