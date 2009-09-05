package gtug.kyoto;



import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;


import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MapController;
import com.google.android.maps.GeoPoint;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.app.Dialog;
import android.content.Context;
//import android.content.Intent;
import android.graphics.drawable.Drawable;


public class Map extends MapActivity implements LocationListener { 
	
	MapController map_ctrl;
	MapView map_view;
	GeoPoint point;
	
	String LOG_TAG = "Map";
	
	/** Called when the activity is first created. */ 
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.map); 
		
		Log.v(LOG_TAG, "aaaa");
		
		map_view = (MapView)findViewById(R.id.map_view); 
		//map_view.getOverlays().clear();
		map_view.setClickable(true);
		map_view.setBuiltInZoomControls(true);

		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
		
		Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		GeoPoint point = new GeoPoint((int)(loc.getLatitude()*1E6),
                 (int)(loc.getLongitude()*1E6));

		map_ctrl= map_view.getController();
		map_ctrl.setCenter(point); 
		map_ctrl.setZoom(16);

		createMapIcon(point, R.drawable.map_icon_red);
	}
	
	public void createMapIcon(GeoPoint geo_point, int icon_resource) {
		Drawable icon = getResources().getDrawable(icon_resource);
		MapIconOverlay iconOverlay = new MapIconOverlay(icon);
		
        map_view.getOverlays().add(iconOverlay);
        iconOverlay.addPoint(geo_point);
	}
	
	@Override 
	protected boolean isRouteDisplayed() { 
		return false; 
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		point = 
			new GeoPoint((int)(location.getLatitude()*1E6),
				         (int)(location.getLongitude()*1E6));
		map_ctrl.animateTo(point);
		createMapIcon(point, R.drawable.map_icon_android);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	} 
	
	
	public class MapIconOverlay extends ItemizedOverlay<MapIconOverlayItem> {

		private List<GeoPoint> points = new ArrayList<GeoPoint>();
		
		public MapIconOverlay(Drawable defaultMarker) {
			// TODO Auto-generated constructor stub
			super( boundCenterBottom(defaultMarker) );
		}

		@Override
		protected MapIconOverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			//return null;
	        GeoPoint gpoint = points.get(i);
	        return new MapIconOverlayItem(gpoint);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			//return 0;
			return points.size();
		}
		
	    public void addPoint(GeoPoint point) {
	        this.points.add(point);
	        populate();
	    }
		
	    public void clearPoint() {
	        this.points.clear();
	        populate();
	    }
        
		@Override
		protected boolean onTap(int index) {
			map_ctrl.animateTo(this.getItem(index).getPoint());
			
			Dialog map_dialog = new Dialog(Map.this);
			map_dialog.setTitle(R.string.map_dialog_title);
			map_dialog.setContentView(R.layout.map_text);
			map_dialog.show();
			return true;
		}

		
	}

}