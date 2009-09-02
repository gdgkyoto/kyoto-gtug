package org.gtug.kyoto.android.devilear;

import android.location.Location;


public class Item {
    
    private double latitude;
    private double longitude;
    
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public void setLocation(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
    
    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
