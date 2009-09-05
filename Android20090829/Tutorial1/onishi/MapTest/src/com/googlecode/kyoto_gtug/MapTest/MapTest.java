package com.googlecode.kyoto_gtug.MapTest;

//import android.app.Activity;
import com.google.android.maps.*;
import android.os.Bundle;

public class MapTest extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MapView map_view = (MapView)findViewById(R.id.mapview);
        map_view.setBuiltInZoomControls(true);
    }
    
    @Override
    protected boolean isRouteDisplayed()
    {
    	return false;
    }
}