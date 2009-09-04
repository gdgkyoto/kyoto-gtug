package gtug.kyoto;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;


	public class MapIconOverlayItem extends OverlayItem {

	    public MapIconOverlayItem(GeoPoint point){
	        super(point, "", "");
	    }
	}