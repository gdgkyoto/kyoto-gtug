package kyoto_gtug.tutorial1.Shuden;

import kyoto_gtug.tutorial1.Shuden.R;
import android.app.Activity;
import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;


import java.io.UnsupportedEncodingException;
import java.net.*;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class main extends MapActivity {
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
	
				// Stop update location information
				mLocationManager.removeUpdates(this);
				
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

				// make MapView
				MapView mapView = (MapView)findViewById(R.id.mapview);
				mapView.setBuiltInZoomControls(true);
				MapController mapCtrl = mapView.getController();
				mapCtrl.setZoom(16);
				
				//
				GeoPoint point = new GeoPoint((int)(loc.getLatitude()*1e6), (int)(loc.getLongitude()*1e6));
				
				mapCtrl.animateTo(point);
			}
        };
        
        // Update location information
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);

    }
    
    @Override
    protected boolean isRouteDisplayed(){
    	return false;
    }
}