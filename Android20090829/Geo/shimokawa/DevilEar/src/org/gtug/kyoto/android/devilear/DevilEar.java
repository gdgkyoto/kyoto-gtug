package org.gtug.kyoto.android.devilear;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.gtug.kyoto.android.devilear.item.TextItem;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DevilEar extends Activity {
    
    //private ItemsDbAdapter mDbHelper;
    private List<ImageView> mItemMarks;
    private Context mContext;
    private RadarDrawableView mRadarDrawableView;
    private TextView mLocationInfoView;

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
        
        Item.openDb(this);
                
        mRadarDrawableView = new RadarDrawableView(this);
        mLocationInfoView = new TextView(this);
        mLocationInfoView.setText("Location");        

        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        layout.addView(mRadarDrawableView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(mLocationInfoView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
       
        setContentView(layout);
        //setContentView(R.layout.main);
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
                if (status == LocationProvider.AVAILABLE) {
                    mLocationInfoView.setText(provider + ": AVAILABLE");
                } else if (status == LocationProvider.OUT_OF_SERVICE) {
                    mLocationInfoView.setText(provider + ": OUT OF SERVICE");
                } else if (status == LocationProvider.TEMPORARILY_UNAVAILABLE) {
                    mLocationInfoView.setText(provider + ": TEMPORARILY UNAVAILABLE");
                } else {
                    mLocationInfoView.setText(provider + ": ?");
                }
            }
            
            @Override
            public void onProviderEnabled(String provider) {
                Log.d("DevilEar", "provider changed to " + provider);
                mLocationInfoView.setText("provider => " + provider);
            }
            
            @Override
            public void onProviderDisabled(String provider) {
                Log.d("DevilEar", "provider " + provider + " disabled");                
                mLocationInfoView.setText("provider " + provider + " disabled");
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
                mLocationInfoView.setText("(" + location.getLatitude() + ", " + location.getLongitude() + ")");
            }
        };
    }
    
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //mRadarDrawableView.shiftRadius();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //showDialog(0);
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
        Item.closeDb();
        
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
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*30, 5.0F, mLocationListener);
        //mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*60, 5.0F, mLocationListener);
        
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }
    
    private void reloadData() {
        List<Item> itemList = Item.getAll(this);

        // cheat code
        if (mLocation != null) {
            double dLat = mLocation.getLatitude() - 35.0;
            double dLon = mLocation.getLongitude() - 135.0;
            for (Item item : itemList) {
                item.setLatitude(item.getLatitude() + dLat);
                item.setLongitude(item.getLongitude() + dLon);
            }
        }
        
        if (itemList.size() == 0) {
            addDummyItems(35.0, 135.0);
            itemList = Item.getAll(this);
        }

        mRadarDrawableView.setItems(itemList);
    }
     
    private void addDummyItems(double lat, double lon) {
        long curTime = System.currentTimeMillis();
        
        try {
            TextItem item = new TextItem();
            item.setDescription("This is a text");
            item.setDate(curTime);
            item.setLatitude(lat + 0.001);
            item.setLongitude(lon + 0.001);
            item.setText("Text Item 1");
            item.save();

            item = new TextItem();
            item.setDate(curTime - 1000);
            item.setLatitude(lat - 0.001);
            item.setLongitude(lon - 0.001);
            item.setText("Text Item 2");
            item.save();


            item = new TextItem();
            item.setDate(curTime - 2000);
            item.setLatitude(lat + 0.001);
            item.setLongitude(lon - 0.001);
            item.setText("Text Item 3");
            item.save();


            item = new TextItem();
            item.setDate(curTime - 3000);
            item.setLatitude(lat - 0.001);
            item.setLongitude(lon + 0.002);
            item.setText("Text Item 4");
            item.save();
        } catch (IOException e) {
            Log.d("DevilEar", e.getMessage());
        }
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dlg = null;
        if (id == 0) {
            final Dialog dialog = new Dialog(this);
            dlg = dialog;
            dialog.setContentView(R.layout.item_found_dialog);
            dialog.setTitle("Hi!");
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText("Hello! aaaaaaaaaaaaaaaaaaaaaaaaaaa hoge hoge mage mage yes no");
            ImageView image = (ImageView) dialog.findViewById(R.id.image);
            image.setImageResource(R.drawable.text_icon);
            
            View v = dialog.findViewById(R.id.image);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });
        }
        return dlg;
    }



    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        if (id == 0) {
            // item found
            Log.d("DevilEar", "onPrepareDialog");
        }
        // TODO Auto-generated method stub
        super.onPrepareDialog(id, dialog);
    }
    
    
    
}