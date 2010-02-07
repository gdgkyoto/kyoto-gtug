/*
 * HomePage.java
 *
 * Created on 2010/02/07, 11:10
 */
 
package twkazemap.publicpage;

import org.apache.wicket.ResourceReference;
import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GIcon;
import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GMarker;
import wicket.contrib.gmap.api.GMarkerOptions;
import wicket.contrib.gmap.api.GOverlay;
import wicket.contrib.gmap.api.GPoint;
import wicket.contrib.gmap.api.GSize;

public class HomePage extends BasePage {

    public HomePage() {
        GMap2 map = new GMap2("map", ((Application)getApplication().get()).getGoogleMapsAPIkey());
        map.setCenter(new GLatLng(52.37649, 4.888573));
        add(map);

        GIcon icon = new GIcon(
            urlFor(new ResourceReference(HomePage.class, "image.gif")).toString(),
            urlFor(new ResourceReference(HomePage.class, "shadow.png")).toString())
            .iconSize(new GSize(64, 64)).shadowSize(new GSize(64, 64)).iconAnchor(
            new GPoint(19, 40)).infoWindowAnchor(new GPoint(9, 2)).infoShadowAnchor(
            new GPoint(18, 25));

        GOverlay marker = new GMarker(new GLatLng(52.37649, 4.888573), new GMarkerOptions("風邪", icon));
        map.addOverlay(marker);
    }
}
