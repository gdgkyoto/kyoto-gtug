package org.gtug.kyoto.android.devilear;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.location.Location;
import android.view.View;

public class RadarDrawableView extends View {
    
    private ShapeDrawable shapeDrawable;
    private ShapeDrawable markDrawable;
    
    private int width = 320;
    private int height = 320;
    
    //private Location mLoc;
    
    private double[] mLocation = new double[] {35, 135};
    private Location mNorthEast;
    private Location mSouthWest;
    private double mRadius = 1000;
    private float mOrientation;
    
    private List<Item> mItems;
    
    
    public RadarDrawableView(Context context) {
        super(context);
        
        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(0xFF008800);
        shapeDrawable.setBounds(0, 0, width, height);
        
        markDrawable = new ShapeDrawable(new OvalShape());
        markDrawable.getPaint().setColor(0x88FFFF00);
        markDrawable.setBounds(155, 155, 165, 165);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        shapeDrawable.draw(canvas);
        
        if (mLocation == null) {
            return;
        }
        
        if (mItems != null) {
            double theta = mOrientation * Math.PI / 180.0;
            double c = Math.cos(theta);
            double s = Math.sin(theta);
            for (Item item : mItems) {
                Point pos = locToScreen(item.getLatitude(), item.getLongitude(), c, s);
                if (pos != null) {
                    markDrawable.setBounds(pos.x - 5, pos.y - 5, pos.x + 5, pos.y + 5);
                    markDrawable.draw(canvas);
                }
            }
        }
        
    }
    
    public void setArea(Location center, double radius) {
        mLocation = new double[] {center.getLatitude(), center.getLongitude()};
        mRadius = radius;
    }

    public void setLocation(Location center) {
        mLocation = new double[] {center.getLatitude(), center.getLongitude()};
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }
    
    public void setOrientation(float orientation) {
        mOrientation = orientation;
    }
    
    public void shiftRadius() {
        if (mRadius < 100) {
            mRadius = 8000;
        } else {
            mRadius /= 2;
        }
        //mRadiusInDegree = kmToDigree(mRadius);
        invalidate();
    }
    
//    private double kmToDigree(double km) {
//        float lat;
//        if (mLoc.getLatitude() < 75.0) {
//            lat = (float) (mLoc.getLatitude() * Math.PI / 180.0);
//        } else {
//            lat = (float) (75 * Math.PI / 180.0);
//        }
//        return (360.0 * km / 40000.0 / Math.cos(lat));
//    }
    
    private Point locToScreen(double lat, double lon, double rotCos, double rotSin) {
        if (mLocation == null) {
            return null;
        }
        float[] diff = new float[3];
        Location.distanceBetween(mLocation[0], mLocation[1], lat, lon, diff);
        if (diff[0] > mRadius) {
            return null;
        }
        double theta = diff[1] * Math.PI / 180.0;
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        double r = 160 * diff[0] / mRadius;
        
        double north = r * cos;
        double east = r * sin;
        
        double x0 = east;
        double y0 = -north;
        
        int x = (int) (x0 * rotCos + y0 * rotSin) + 160;
        int y = (int) (-x0 * rotSin + y0 * rotCos) + 160; 
        return new Point(x, y);
    }
    
}
