/*
 * HomePage.java
 *
 * Created on 2010/02/07, 11:10
 */
 
package twkazemap.publicpage;

import org.apache.wicket.ResourceReference;
import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.GControl;
import wicket.contrib.gmap.api.GIcon;
import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.api.GMarker;
import wicket.contrib.gmap.api.GMarkerOptions;
import wicket.contrib.gmap.api.GOverlay;
import wicket.contrib.gmap.api.GPoint;
import wicket.contrib.gmap.api.GSize;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends BasePage {

    public HomePage() {
        HashMap<String,GLatLng> dummyMap = new HashMap<String,GLatLng>();
        dummyMap.put("北海道", new GLatLng(43.03, 141.21));
        dummyMap.put("青森県", new GLatLng(40.49, 140.44));
        dummyMap.put("岩手県", new GLatLng(39.42, 141.09));
        dummyMap.put("宮城県", new GLatLng(38.16, 140.52));
        dummyMap.put("秋田県", new GLatLng(39.43, 140.06));
        dummyMap.put("山形県", new GLatLng(38.15, 140.20));
        dummyMap.put("福島県", new GLatLng(37.45, 140.28));
        dummyMap.put("茨城県", new GLatLng(36.22, 140.28));
        dummyMap.put("栃木県", new GLatLng(36.33, 139.53));
        dummyMap.put("群馬県", new GLatLng(36.23, 139.03));
        dummyMap.put("埼玉県", new GLatLng(35.51, 139.38));
        dummyMap.put("千葉県", new GLatLng(35.36, 140.06));
        dummyMap.put("東京都", new GLatLng(35.41, 139.45));
        dummyMap.put("神奈川県", new GLatLng(35.26, 139.38));
        dummyMap.put("新潟県", new GLatLng(37.55, 139.02));
        dummyMap.put("富山県", new GLatLng(137.13, 36.41));
        dummyMap.put("石川県", new GLatLng(136.39, 36.33));
        dummyMap.put("福井県", new GLatLng(136.13, 36.03));
        dummyMap.put("山梨県", new GLatLng(138.34, 35.39));
        dummyMap.put("長野県", new GLatLng(138.11, 36.39));
        dummyMap.put("岐阜県", new GLatLng(136.45, 35.25));
        dummyMap.put("静岡県", new GLatLng(138.23, 34.58));
        dummyMap.put("愛知県", new GLatLng(136.54, 35.11));
        dummyMap.put("三重県", new GLatLng(136.30, 34.43));
        dummyMap.put("滋賀県", new GLatLng(135.52, 35.00));
        dummyMap.put("京都府", new GLatLng(135.46, 35.00));
        dummyMap.put("大阪府", new GLatLng(135.29, 34.41));
        dummyMap.put("兵庫県", new GLatLng(135.11, 34.41));
        dummyMap.put("奈良県", new GLatLng(135.48, 34.41));
        dummyMap.put("和歌山県", new GLatLng(135.10, 34.14));
        dummyMap.put("鳥取県", new GLatLng(134.13, 35.29));
        dummyMap.put("島根県", new GLatLng(133.04, 35.27));
        dummyMap.put("岡山県", new GLatLng(133.54, 34.39));
        dummyMap.put("広島県", new GLatLng(132.27, 34.23));
        dummyMap.put("山口県", new GLatLng(131.27, 34.11));
        dummyMap.put("徳島県", new GLatLng(134.32, 34.03));
        dummyMap.put("香川県", new GLatLng(134.02, 34.20));
        dummyMap.put("愛媛県", new GLatLng(132.44, 33.50));
        dummyMap.put("高知県", new GLatLng(133.31, 33.33));
        dummyMap.put("福岡県", new GLatLng(130.23, 33.35));
        dummyMap.put("佐賀県", new GLatLng(130.16, 33.16));
        dummyMap.put("長崎県", new GLatLng(129.52, 32.45));
        dummyMap.put("熊本県", new GLatLng(130.42, 32.48));
        dummyMap.put("大分県", new GLatLng(131.37, 33.14));
        dummyMap.put("宮崎県", new GLatLng(131.25, 31.56));
        dummyMap.put("鹿児島県", new GLatLng(130.33, 31.36));
        dummyMap.put("沖縄県", new GLatLng(127.41, 26.13));



        GMap2 map = new GMap2("map", ((Application)getApplication().get()).getGoogleMapsAPIkey());
//        map.setCenter(new GLatLng(52.37649, 4.888573));
        map.setCenter(new GLatLng(35.0, 135.0));
        map.addControl(GControl.GScaleControl);
        map.addControl(GControl.GMapTypeControl);
//        map.addControl(GControl.GSmallMapControl);
        map.addControl(GControl.GLargeMapControl);
        add(map);

//        GIcon icon = new GIcon(
//            urlFor(new ResourceReference(HomePage.class, "image.gif")).toString(),
//            urlFor(new ResourceReference(HomePage.class, "shadow.png")).toString())
//            .iconSize(new GSize(64, 64)).shadowSize(new GSize(64, 64)).iconAnchor(
//            new GPoint(19, 40)).infoWindowAnchor(new GPoint(9, 2)).infoShadowAnchor(
//            new GPoint(18, 25));
//
//        GOverlay marker = new GMarker(new GLatLng(52.37649, 4.888573), new GMarkerOptions("風邪", icon));
//        map.addOverlay(marker);
        for(Map.Entry<String,GLatLng> entry : dummyMap.entrySet()) {
            addMarker(map, entry.getKey(), entry.getValue());
        }
    }

    public void addMarker(GMap2 map, String key, GLatLng latlng) {
        GIcon icon = new GIcon(
            urlFor(new ResourceReference(HomePage.class, "image.gif")).toString(),
            urlFor(new ResourceReference(HomePage.class, "shadow.png")).toString())
            .iconSize(new GSize(64, 64)).shadowSize(new GSize(64, 64)).iconAnchor(
            new GPoint(19, 40)).infoWindowAnchor(new GPoint(9, 2)).infoShadowAnchor(
            new GPoint(18, 25));

        GOverlay marker = new GMarker(latlng, new GMarkerOptions(key, icon));
        map.addOverlay(marker);
    }
}
