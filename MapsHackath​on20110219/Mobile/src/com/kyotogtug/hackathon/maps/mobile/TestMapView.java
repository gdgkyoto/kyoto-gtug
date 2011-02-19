package com.kyotogtug.hackathon.maps.mobile;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

//import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class TestMapView extends MapActivity implements SensorEventListener {
	
	private MapView view;

	private int now_zoom = 1;
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	private SensorManager mSensorManager;

    private static final int MATRIX_SIZE = 16;
	/* 回転行列 */
    float[]  inR = new float[MATRIX_SIZE];
    float[] outR = new float[MATRIX_SIZE];
    float[]    I = new float[MATRIX_SIZE];

    /* センサーの値 */
    float[] orientationValues   = new float[3];
    float[] magneticValues      = new float[3];
    float[] accelerometerValues = new float[3];
    
    MapController mc;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = new MapView(this, "0_wFaNb-8qM4sN6adtqdMqa-jlrBNvVuIdW1UQQ");
        view.setClickable(true);
        
        view.setBuiltInZoomControls(true);
        setContentView(view);
        
        
        int ido = (int)(34.996465 * 1E6);
        int keido = (int)(135.740032*1E6); 
        mc = view.getController();
        mc.animateTo(new GeoPoint(ido, keido));
	    
	    /* センサ・マネージャを取得する */
	    mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}
	
	
	private boolean mIsMagSensor;
	private boolean mIsAccSensor;

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		// センサの取得
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

	    // センサマネージャへリスナーを登録(implements SensorEventListenerにより、thisで登録する)
        for (Sensor sensor : sensors) {

        	if( sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
        		mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        		mIsMagSensor = true;
        	}

        	if( sensor.getType() == Sensor.TYPE_ACCELEROMETER){
        		mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        		mIsAccSensor = true;
        	}
        }
	}
	
	
	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();

		//センサーマネージャのリスナ登録破棄
	    if (mIsMagSensor || mIsAccSensor) {
	        mSensorManager.unregisterListener(this);
	        mIsMagSensor = false;
	        mIsAccSensor = false;
	    }
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	public void onSensorChanged(SensorEvent event) 
	{
     if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) return;

		switch (event.sensor.getType()) {
			case Sensor.TYPE_MAGNETIC_FIELD:
				magneticValues = event.values.clone();
				break;
			case Sensor.TYPE_ACCELEROMETER:
				accelerometerValues = event.values.clone();
	                             break;
		}

		if (magneticValues != null && accelerometerValues != null) {
	
			SensorManager.getRotationMatrix(inR, I, accelerometerValues, magneticValues);
	
			//Activityの表示が縦固定の場合。横向きになる場合、修正が必要です
			SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
			SensorManager.getOrientation(outR, orientationValues);
	
			Log.v("Orientation",
	          String.valueOf( radianToDegree(orientationValues[0]) ) + ", " + //Z軸方向,azmuth
	          String.valueOf( radianToDegree(orientationValues[1]) ) + ", " + //X軸方向,pitch
		            String.valueOf( radianToDegree(orientationValues[2]) ) );       //Y軸方向,roll
		            
		            
			
		//	Log.v("Orientation", String.valueOf( radianToDegree(orientationValues[2]) ) );
			
			int cur_zoom = 1;
			
			int param  = 1;
			if (radianToDegree(orientationValues[param]) < 0) {
				cur_zoom = 11;
			} else if (radianToDegree(orientationValues[param]) < 10) {
				cur_zoom = 12;
			} else if (radianToDegree(orientationValues[param]) < 20) {
				cur_zoom = 13;
			} else if (radianToDegree(orientationValues[param]) < 30) {
				cur_zoom = 14;
			} else if (radianToDegree(orientationValues[param]) < 40) {
				cur_zoom = 15;
			} else if (radianToDegree(orientationValues[param]) < 50) {
				cur_zoom = 16;
			} else if (radianToDegree(orientationValues[param]) < 60) {
				cur_zoom = 17;
			} else if (radianToDegree(orientationValues[param]) < 70) {
				cur_zoom = 18;
			} else if (radianToDegree(orientationValues[param]) < 80) {
				cur_zoom = 19;
			} else if (radianToDegree(orientationValues[param]) <= 90) {
				cur_zoom = 20;
			} else if (radianToDegree(orientationValues[param]) < 170) {
				cur_zoom = 20;
			} else if (radianToDegree(orientationValues[param]) < 180) {
				cur_zoom = 19;
			} else {
				cur_zoom = 20;
			}
			
			if (cur_zoom != this.now_zoom) {
				now_zoom = cur_zoom;
				doZoom(view, now_zoom);
			}
			
		}
	}

	int radianToDegree(float rad)
	{
		return (int) Math.floor( Math.toDegrees(rad) ) ;
	}
	
    protected boolean doZoom(MapView myMap, int zm) {
        myMap.getController().setZoom(zm);
        return true;
    }
	
}