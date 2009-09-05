package com.googlecode.kyoto_gtug.MapTest;

//import android.app.Activity;
import com.google.android.maps.*;
import android.os.Bundle;

import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MapTest extends MapActivity {
	MapView mapView;
	Button gpsButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //MapView map_view = (MapView)findViewById(R.id.mapview);
        init();
        
    }
    
    @Override
    protected boolean isRouteDisplayed()
    {
    	return false;
    }
    
    public void init()
    {
    	initMap();
    	initButton();
    }
    
    public void initMap()
    {
    	mapView = (MapView)findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true); 	
    }
    
    public void initButton()
    {
    	gpsButton = (Button)findViewById(R.id.gpsButton);
    	GpsButtonClickListener listener = new GpsButtonClickListener( this );
    	gpsButton.setOnClickListener(listener);
    }
    
    public void setPosInMap( double latitude, double longitude )
    {
    	MapController mapCtrl = mapView.getController();
    	int latitude_1e6 = (int)( latitude * 1E6 );
    	int longitude_1e6 = (int)( longitude * 1E6 );
    	GeoPoint point = new GeoPoint( latitude_1e6, longitude_1e6 );
    	mapCtrl.setCenter( point );
    	mapCtrl.setZoom( 16 );
    }
    
    public void clickedGpsButton()
    {
    	setPosInMap( 34.0d + 59.0d / 60 + 42.0d / 3600,
   		     		 135.0d + 44.0d / 60 + 19.0d / 3600 );
    }
    
    class GpsButtonClickListener implements OnClickListener
    {
    	MapTest target;
    	public GpsButtonClickListener( MapTest t )
    	{
    		target = t;
    	}
    	
    	public void onClick(View v)
    	{
    		target.clickedGpsButton();
    	}
    }
}