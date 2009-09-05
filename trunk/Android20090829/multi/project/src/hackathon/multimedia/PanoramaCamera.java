package hackathon.multimedia;

import java.util.List;

import hackathon.multimedia.CameraView;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class PanoramaCamera extends Activity 
	implements SensorEventListener {
    private SensorManager sensorManager;//センサーマネージャ
    private Sensor        accelerometer;//加速度せンサー
    private Sensor        orientation; //回転せンサー
    private SensorData	  sensorData;
    private float[]       acceleration_value;
    private float[]       orientation_value;

    
    //アプリの初期化
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        CameraView cameraView = new CameraView(this);
        sensorData = new SensorData();
        cameraView.setSensorData(sensorData);
        acceleration_value = new float[3];
        orientation_value = new float[3];
        
        setContentView(cameraView);
        
        //センサーマネージャの取得
        sensorManager=(SensorManager)getSystemService(
            Context.SENSOR_SERVICE);
        
        //センサーの取得
        List<Sensor> list;
        list=sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size()>0) accelerometer=list.get(0);
        list=sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (list.size()>0) orientation=list.get(0);
    }

    //アプリの開始
    @Override
    protected void onResume() {
        //アプリの開始
        super.onResume();
        
        //センサーの処理の開始
        if (accelerometer!=null) {
            sensorManager.registerListener(this, 
        	    accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
        }
        if (orientation!=null) {
            sensorManager.registerListener(this, 
      	        orientation,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    //アプリの停止
    @Override
    protected void onStop() {
        //センサーの処理の停止
        sensorManager.unregisterListener(this);
        
        //アプリの停止
        super.onStop();
    }    
    
    //センサーリスナーの処理
    public void onSensorChanged(SensorEvent event) {
        //少数2桁切り捨て
        for (int i=0;i<3;i++) {
            int w=(int)(10*event.values[i]);
            event.values[i]=(float)(w/10.0f);
        }
        
        //加速度の取得
        if (event.sensor==accelerometer) {
        	acceleration_value = event.values;
//        	acceleration_value[0] = event.values[0];
//        	acceleration_value[1] = event.values[1];
//        	acceleration_value[2] = event.values[2];
        }

        //方向の取得
        if (event.sensor==orientation) {
        	orientation_value= event.values;
//        	orientation_value[0]= event.values[0];
//        	orientation_value[1] = event.values[1];
//        	orientation_value[2] = event.values[2];
        }

        if( sensorData.getFlag() ) {
            Log.e("acceleration",
                    this.acceleration_value[0] + ", " +
                    this.acceleration_value[1] + ", " +
                    this.acceleration_value[2]);
            Log.e("orientation",
                    this.orientation_value[0] + ", " +
                    this.orientation_value[1] + ", " +
                    this.orientation_value[2]);

            sensorData.setAcceleration(acceleration_value);
            sensorData.setOrientation(orientation_value);
        	sensorData.setFlag(false);
        }
    
    }

    //精度変更イベントの処理
    public void onAccuracyChanged(Sensor sensor,int accuracy) {
    }

}