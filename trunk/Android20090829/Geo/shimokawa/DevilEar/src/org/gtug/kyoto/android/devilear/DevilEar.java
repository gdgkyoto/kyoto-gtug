package org.gtug.kyoto.android.devilear;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DevilEar extends Activity {
    
    private ItemsDbAdapter mDbHelper;
    private List<ImageView> mItemMarks;
    private Context mContext;
    private RadarDrawableView mRadarDrawableView;
    
    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private Location mLocation;
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        mDbHelper = new ItemsDbAdapter(this);
        mDbHelper.open();
        
        mRadarDrawableView = new RadarDrawableView(this);
        setContentView(mRadarDrawableView);
        mContext = getApplicationContext();
        
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensorEventListener = new SensorEventListener() {
            
            @Override
            public void onSensorChanged(SensorEvent event) {
                // TODO Auto-generated method stub
                float[] values = event.values;
                mRadarDrawableView.setOrientation(values[0]);
                mRadarDrawableView.invalidate();
            }
            
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //Log.d("SensorTest", "onAccuracyChanged : " + accuracy);
            }
        };
        
        mLocationListener = new LocationListener() {
            
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("DevilEar", "provider " + provider + "'s status is changed to " + status);
            }
            
            @Override
            public void onProviderEnabled(String provider) {
                Log.d("DevilEar", "provider changed to " + provider);
            }
            
            @Override
            public void onProviderDisabled(String provider) {
                Log.d("DevilEar", "provider " + provider + " enabled");                
            }
            
            @Override
            public void onLocationChanged(Location location) {
                if (mLocation == null) {
                    mLocation = location;
                } else {
                    float d = location.distanceTo(mLocation);
                    if (d > 10.0F) {
                        mLocation = location;
                    }
                }
                mRadarDrawableView.setLocation(location);
            }
        };
    }
    
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mRadarDrawableView.shiftRadius();
            return true;
        }
        return super.onTouchEvent(event);
    }



    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        
        reloadData();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        mDbHelper.close();
        
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        mSensorManager.unregisterListener(mSensorEventListener);
        mLocationManager.removeUpdates(mLocationListener);
        mLocationManager = null;
        
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (sensorList.size() > 0) {
            mSensorManager.registerListener(mSensorEventListener, sensorList.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*60, 5.0F, mLocationListener);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*60, 5.0F, mLocationListener);
        
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }
    
    private void reloadData() {
        List<Item> itemList = new ArrayList<Item>();

        Cursor itemsCursor = mDbHelper.fetchAllItems();
        startManagingCursor(itemsCursor);
        
        while (itemsCursor.moveToNext()) {
            double lat = itemsCursor.getDouble(itemsCursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LATITUDE));
            double lon = itemsCursor.getDouble(itemsCursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LONGITUDE));
            Item item = new Item();
            item.setLocation(lat, lon);
            itemList.add(item);
        }
        
        if (itemList.size() == 0 && mLocation == null) {
            addDummyItems(35.0, 135.0);
        }

        mRadarDrawableView.setItems(itemList);
    }
     
    private void addDummyItems(double lat, double lon) {
        long curTime = System.currentTimeMillis();
        mDbHelper.createItem("text", null, "Hello", curTime, lat + 0.001, lon + 0.001);
        mDbHelper.createItem("text", null, "Hello", curTime - 1000, lat - 0.001, lon - 0.001);
        mDbHelper.createItem("text", null, "Hello", curTime - 2000, lat + 0.001, lon - 0.001);
        mDbHelper.createItem("text", null, "Hello", curTime - 3000, lat - 0.001, lon + 0.002);
    }
    
}