package com.hkmosh;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HKmosh extends Activity {

	public static final String LOG_TAG = "HKmosh";

	private HKmoshLocation loc;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);

		Log.d(LOG_TAG, "create location");
		loc = new HKmoshLocation(
				(LocationManager) getSystemService(LOCATION_SERVICE));

		Log.d(LOG_TAG, "create initializeView");
		initializeView();
	}

	private ListView listView;
	private ArrayAdapter<String> adapter;

	public void initializeView() {
		listView = (ListView) findViewById(R.id.ListView01);
		adapter = new ArrayAdapter<String>(this, R.layout.list_row);
		listView.setAdapter(adapter);

		Log.d(LOG_TAG, "start getphotos from flickr");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(LOG_TAG, "resume");
	}

}

class HKmoshLocation implements LocationListener {
	
	private static final String LOG_TAG = "HKmoshLocation";

	private static final long MIN_TIME = 0;
	private static final float MIN_METER = 0;

	private LocationManager locationManager;
	
	private LinkedList locationHistory = new LinkedList();

	HKmoshLocation(LocationManager lm) {
		locationManager = lm;
		Log.v("HKmoshLocation", "create");

		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, MIN_TIME, MIN_METER, this);
	}

	public void resume() {
		Log.v("HKmoshLocation", "resume");
//		if (locationManager != null) {
//			locationManager.requestLocationUpdates(
//					LocationManager.GPS_PROVIDER, MIN_TIME, MIN_METER, this);
//		}
	}

	public void onLocationChanged(Location location) {
		Log.v(LOG_TAG, "----------");
		Log.v(LOG_TAG, "Latitude" + String.valueOf(location.getLatitude()));
		Log.v(LOG_TAG, "Longitude" + String.valueOf(location.getLongitude()));
		Log.v(LOG_TAG, "Accuracy" + String.valueOf(location.getAccuracy()));
		Log.v(LOG_TAG, "Altitude" + String.valueOf(location.getAltitude()));
		Log.v(LOG_TAG, "Time" + String.valueOf(location.getTime()));
		Log.v(LOG_TAG, "Speed" + String.valueOf(location.getSpeed()));
		Log.v(LOG_TAG, "Bearing" + String.valueOf(location.getBearing()));

		Flickr flickr = new Flickr();
		
		Log.d(LOG_TAG, "flickr create instance");
		
		List<Flickr.Entry> entries = flickr.getPhotosByLoation(location.getLatitude(), location.getLongitude());
		
		for(Flickr.Entry entry: entries) {
			
		}
		
		
		if (locationHistory.size() > 10) {
			locationHistory.removeFirst();
		}
		
		locationHistory.add(new double[]{
				location.getLatitude(), location.getLongitude()
		});
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {

		switch (status) {
		case LocationProvider.AVAILABLE:
			Log.v("Status", "AVAILABLE");
			break;
		case LocationProvider.OUT_OF_SERVICE:
			Log.v("Status", "OUT_OF_SERVICE");
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			Log.v("Status", "TEMPORARILY_UNAVAILABLE");
			break;
		}

	}
}

class HKmoshListView extends ListView {
	private ArrayAdapter<String> adapter;

	private List list = new LinkedList();

	public HKmoshListView(Context context) {
		super(context);
		adapter = new ArrayAdapter<String>(context, R.layout.list_row);
	}

	private void update() {
		for (int i = 0; i < 20; i++) {
			adapter.add("hoge");
			adapter.add("foo");
		}
	}

}
