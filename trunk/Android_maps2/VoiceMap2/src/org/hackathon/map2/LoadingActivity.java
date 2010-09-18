package org.hackathon.map2;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;

public class LoadingActivity  extends Activity implements LocationListener{
    private LocationManager mLocationManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }
    
    @Override
    protected void onResume() {
        if (mLocationManager != null) {
            mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
//                LocationManager.NETWORK_PROVIDER,
                0,
                0,
                this);
        }
        
        super.onResume();
    }
    
    @Override
    protected void onPause() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
        
        super.onPause();
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.v("----------", "----------");
        Log.v("Lat", String.valueOf(location.getLatitude()));
        Log.v("Lon", String.valueOf(location.getLongitude()));
        Intent i = new Intent(getApplicationContext(), VoiceRecognitionActivity.class);
        i.putExtra("lat",String.valueOf(location.getLatitude()));
        i.putExtra("lan",String.valueOf(location.getLongitude()));
        startActivity(i);
    
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
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
