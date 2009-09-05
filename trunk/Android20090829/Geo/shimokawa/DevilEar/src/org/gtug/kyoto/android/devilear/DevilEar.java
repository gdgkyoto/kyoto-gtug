package org.gtug.kyoto.android.devilear;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.gtug.kyoto.android.devilear.item.SoundItem;
import org.gtug.kyoto.android.devilear.item.TextItem;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DevilEar extends Activity {
    
    private static final int ACTIVITY_BROWSE_TEXT=0;
    private static final int ACTIVITY_BROWSE_SOUND=1;
    
    private static final String LOGTAG = "DevilEar";
    
    private Context mContext;
    private RadarDrawableView mRadarDrawableView;
    private TextView mLocationInfoView;

    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private Location mLocation;
    
    private Button mSecretButton;
    
    private List<Item> mItems;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        mRadarDrawableView = new RadarDrawableView(this);
        mLocationInfoView = new TextView(this);
        mLocationInfoView.setText("Location");
        mSecretButton = new Button(this);
        mSecretButton.setText("Danger!");


        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        layout.addView(mRadarDrawableView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(mLocationInfoView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(mSecretButton, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        
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
        
        mSecretButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Find nearest item
                if (mItems != null) {
                    double distance = 40000000;
                    double[] curPos = currentLocation();
                    float[] vals = new float[]{0F};
                    Item nearest = null;
                    for (Item item : mItems) {
                        Location.distanceBetween(curPos[0], curPos[1],
                                item.getLatitude(), item.getLongitude(),
                                vals);
                        if (vals[0] < distance) {
                            distance = vals[0];
                            nearest = item;
                        }
                    }
                    if (nearest != null) {
                        // Item Found!
                        itemFound(nearest);
                    }
                }
            }
        });
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
        
        Item.openDb(this);

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
    
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
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
            //addDummyItems(35.0, 135.0);
            loadTestData();
            itemList = Item.getAll(this);
        }

        mItems = itemList;
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
    
    private void itemFound(Item item) {
        // Show Dialog Box
        Log.d(LOGTAG, "Item " + item.getId());
        ItemFoundDialog dialog = new ItemFoundDialog(this);
        dialog.configure(item);
        dialog.show();
    }
    
    private double[] currentLocation() {
        if (mLocation == null) {
            return new double[] {35.0, 135.0};
        } else {
            return new double[] {mLocation.getLatitude(), mLocation.getLongitude()};
        }
    }
    
    public void startItemViewActivity(Item item) {
        Log.d("DevilEar", "ENTER startItemViewActivity");
        
        if (item.getType().equals("text")) {
            Intent i = new Intent(this, TextBrowser.class);
            i.putExtra(ItemsDbAdapter.KEY_ROWID, item.getId());
            startActivityForResult(i, ACTIVITY_BROWSE_TEXT);
        } else if (item.getType().equals("sound")) {
            Intent i = new Intent(this, SoundBrowser.class);
            i.putExtra(ItemsDbAdapter.KEY_ROWID, item.getId());
            startActivityForResult(i, ACTIVITY_BROWSE_SOUND);            
        } else {
            Log.e(LOGTAG, "Unsupported content type");
        }
    }
    
    private void loadTestData() {
        try {
            for (int i = 1; i <= 2; i++) {
                File file = new File("/sdcard/items/item_" + i + ".properties");
                if (file.exists()) {
                    Item item = null;
                    Properties prop = new Properties();
                    prop.load(new FileInputStream(file));
                    String type = prop.getProperty("type", "");
                    if (type.equals("text")) {
                        item = new TextItem(prop);
                    } else if (type.equals("sound")) {
                        item = new SoundItem(prop);
                    }
                    if (item != null) {
                        item.save();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
}