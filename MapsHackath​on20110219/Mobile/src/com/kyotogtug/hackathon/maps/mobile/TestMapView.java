package com.kyotogtug.hackathon.maps.mobile;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

//import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class TestMapView extends MapActivity {
	
	private MapView view;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = new MapView(this, "0_wFaNb-8qM4sN6adtqdMqa-jlrBNvVuIdW1UQQ");
        view.setClickable(true);
        
        view.setBuiltInZoomControls(true);
        setContentView(view);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}