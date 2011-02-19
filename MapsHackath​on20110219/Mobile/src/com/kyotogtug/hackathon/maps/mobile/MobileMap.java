package com.kyotogtug.hackathon.maps.mobile;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

//import android.app.Activity;
import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class MobileMap extends MapActivity implements SensorEventListener {
	
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
        view = new MapView(this, "0jYwRrkrj9pvB9wHd37l0OXz0T3o9-FHROuKdSA");
        view.setClickable(true);
        
        view.setBuiltInZoomControls(true);
        setContentView(view);
        
/*        
        int ido = (int)(34.996465 * 1E6);
        int keido = (int)(135.740032*1E6); 
*/
        final MyLocationOverlay overlay =
            new MyLocationOverlay(getApplicationContext(), view);
        overlay.onProviderEnabled(LocationManager.GPS_PROVIDER); // GPS を使用する
        overlay.enableMyLocation();
        overlay.runOnFirstFix(new Runnable() {
            @Override
            public void run() {
                view.getController().animateTo(
                    new GeoPoint(overlay.getMyLocation().getLatitudeE6() + 1500, overlay.getMyLocation().getLongitudeE6())); // 現在位置を自動追尾する
//                view.getController().animateTo(
//                        overlay.getMyLocation()); // 現在位置を自動追尾する
            }
        });
        view.getOverlays().add(overlay);
        view.invalidate();
        
        
//        mc = view.getController();
//        mc.animateTo(new GeoPoint(ido + 1500, keido))
//        mc.animateTo(overlay.getMyLocation());
        
	    /* センサ・マネージャを取得する */
	    mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    
/*	    
	    // 画像を地図上に配置するオーバーレイ
	    Bitmap bmp = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_myplaces);
	    MyOverlay overlay = new MyOverlay(bmp, new GeoPoint(ido, keido));
	    List<Overlay> list = view.getOverlays();
	    list.add(overlay);
*/
	    
	}
	
	
	public class MyOverlay extends Overlay {
		  private final Bitmap bmp;
		  private final GeoPoint gpoint;
		 
		  public MyOverlay(Bitmap bmp, GeoPoint gp) {
		    this.bmp = bmp;
		    this.gpoint = gp;
		  }
		 
		  public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		    Projection pro = mapView.getProjection();//Mapと画面の位置を計算するオブジェクト
		    Point p = pro.toPixels(gpoint, null);    //ロケーションから、表示する位置を計算する
		    canvas.drawBitmap(bmp, p.x, p.y, null);  //表示する場所へ画像を配置する。
		  }
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