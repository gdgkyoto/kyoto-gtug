package kyoto_gtug.tutorial1.Shuden;

import kyoto_gtug.tutorial1.Shuden.R;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.*;

public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // get LocationManager
        final LocationManager mLocationManager= (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        // make LocationListener
        LocationListener mLocationListener = new LocationListener(){
			public void onStatusChanged(String provider, int status, Bundle extras) {}
			public void onProviderEnabled(String provider) {}
			public void onProviderDisabled(String provider) {}			
			public void onLocationChanged(Location location) {
		        
				// get location infomation
				Location loc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		        
		        // Print location data(latitude and longitude)
		        Log.d("LOG_TAG", String.valueOf(loc.getLatitude()) + ":" + String.valueOf(loc.getLongitude()));

		        // get station list
		        StationDataList stationDataList = new StationDataList();
		        try {
					stationDataList.updateStationDataList(loc.getLatitude(), loc.getLongitude());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// make URL to request to Google Transit
				String URL = "http://www.google.co.jp/transit?";
				URL += "ie=UTF8" + "&";
				try {
					URL += "saddr=" + URLEncoder.encode(stationDataList.getStationData()[0].Name, "UTF-8") + "&";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					URL += "daddr=" + URLEncoder.encode("è¨ëq(ãûìs)", "UTF-8") + "&";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				URL += "ttype=" + "last" + "&";
				URL += "output=" + "mobile" + "&";
				URL += "dirmode=" + "transit";
				
				// print URL
				Log.d("LOG_TAG", URL);
				
				// Display Shuden to WebView
				WebView webview;
				webview = (WebView) findViewById(R.id.webview);
				webview.getSettings().setJavaScriptEnabled(true);
				webview.loadUrl(URL);
				
				// Stop update location information
				mLocationManager.removeUpdates(this);

			}
        };
        
        // Update location information
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);

    }
}