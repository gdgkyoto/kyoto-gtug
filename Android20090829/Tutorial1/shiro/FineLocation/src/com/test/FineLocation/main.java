package com.test.FineLocation;

import android.app.Activity;
import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.util.Log;

public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // get LocationManager
        LocationManager mLocationManager= (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        // make LocationListener
        LocationListener mLocationListener = new LocationListener(){
			public void onStatusChanged(String provider, int status, Bundle extras) {}
			public void onProviderEnabled(String provider) {}
			public void onProviderDisabled(String provider) {}			
			public void onLocationChanged(Location location) {}  
        };
        
        // Update location information
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        
        // Waiting for getting location...
        Location loc = null;
        while(loc == null) {
        	 loc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        
        // Stop update location information
        mLocationManager.removeUpdates(mLocationListener);
        
        // Print location data(latitude and longitude)
        Log.d("LOG_TAG", String.valueOf(loc.getLatitude()) + ":" + String.valueOf(loc.getLongitude()));
    }
}