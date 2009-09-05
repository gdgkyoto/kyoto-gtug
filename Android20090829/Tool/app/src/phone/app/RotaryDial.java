package phone.app;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;


/**
 * Rotaly_dial
 * @author kitamura
 *
 */
public class RotaryDial extends Activity {

	private RotaryDialView dialView;
	
	public static final String PARAM_DIAL_PERSON_NUMBER = "DIAL_PERSON_NUMBER";
	public static final String PARAM_DIAL_PERSON_NAME = "DIAL_PERSON_NAME";
	
	/** 発信実行フラグ 0=発信を実行しない  1=DIAL_PERSON_NUMBERの値で発信を行う */
	public static final String PARAM_DIAL_CALL_FLG = "DIAL_CALL_FLG";
	
	/** メイン画面に戻ってきたときに、そのままダイヤル画面に遷移するかどうかのフラグ
	 *  電話帳画面から戻ってきた際に、ダイヤル画面に遷移するときに使用する
	 *  int 0=何もしない 1=ダイヤル画面に遷移する */
	public static final String PARAM_MOVE_TO_DIAL_MODE = "MOVE_TO_DIAL_MODE";
	
	/** int 0=normal 1=yutori */
	public static final String PARAM_YUTORI_MODE_FLG = "YUTORI_MODE_FLG";
	
	private MediaPlayer callingPlayer;
	private MediaPlayer ringtonePlayer;
	
	
	private TelephonyManager telephonyManager;
	private int telephonyState;
	private String incommingNumber;
	private int STATE_RINGING = 0;
	private int STATE_OFFHOOK = 1;
	private int STATE_NORMAL = 2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener mListener = new PhoneStateListener() {
			public void onCallStateChanged(int state, String number) {
				Log.d("phone","onCallStateChanged");
				switch (state) {
				case TelephonyManager.CALL_STATE_RINGING:
					telephonyState = STATE_RINGING;
					incommingNumber = number;
					Log.d("phone", "phone ringing");
					break;

				case TelephonyManager.CALL_STATE_OFFHOOK:
					telephonyState = STATE_OFFHOOK;
					Log.d("phone", "phone offhook");
					break;

				case TelephonyManager.CALL_STATE_IDLE:
					telephonyState = STATE_NORMAL;
					Log.d("phone", "phone idle");
					break;

				}
				;
			}

		};
		telephonyManager.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE);
		
    	callingPlayer    = MediaPlayer.create(this, R.raw.dial1);       	
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        dialView = new RotaryDialView(this);
        dialView.setDialEventListener(new DialEventListener(){
        	@Override
        	public void openContactListView() {
        		openContactListActivity();
        	}
        	@Override
        	public void openDialView() {
        		openDialActivity();
        	}
        });
        setContentView(dialView);
        
        
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        sm.registerListener(new SenserListener(),s,0);

       
	}

    /**
     * コンタクトリスト画面へ遷移する。
     * コンタクトリストActivityを起動
     */
    private void openContactListActivity(){
    	Intent intent = new Intent(RotaryDial.this,ContactListActivity.class);
		startActivityForResult(intent, 0);
    }
    
    /**
     * ダイヤル画面へ遷移する。
     * ダイヤル画面Activityを起動
     */
    private void openDialActivity(){
    	Intent intent = new Intent(RotaryDial.this,DialActivity.class);
		startActivityForResult(intent, 0);
    }

    // 実際に電話をコールする
    private void onAcutalCall(String phoneNumber) {

    	callingPlayer.setLooping(true);
   		callingPlayer.seekTo(0);
   		callingPlayer.start();
	    try{
	    	   Thread.sleep(1000);
	    	}catch(InterruptedException e){ }
   		callingPlayer.pause();

   		
   		
    	Intent callIntent = new 
    	Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));

    	callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(callIntent);
    }   
    
    /**
     * SubActityからの結果受け取り
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d("phone", "onActivityResult");
    	if( resultCode == RESULT_OK ){
        	Log.d("phone", "RESULT_OK!");
    	}else{
        	Log.d("phone", "RESULT_NG!");
    	}
    	
    	if( data == null ){
    		return ;
    	}
		
    	// 電話番号の取得
    	String number;
    	number = data.getStringExtra(PARAM_DIAL_PERSON_NUMBER);
    	Log.d("phone","onActivityResult.NUMBER="+number);
    	
    	// かける相手の名前取得
    	String name;
    	name = data.getStringExtra(PARAM_DIAL_PERSON_NAME);
    	
    	if( name != null ){
        	Log.d("phone","NAME="+name);
    	}

    	if( data.getIntExtra(PARAM_MOVE_TO_DIAL_MODE, 0) == 1 ){
    		Intent intent = new Intent(RotaryDial.this,DialActivity.class);
    		intent.putExtra(PARAM_DIAL_PERSON_NAME, name);
    		intent.putExtra(PARAM_DIAL_PERSON_NUMBER, number);
			startActivityForResult(intent, 0);
    	}
    	
    	if( data.getIntExtra(PARAM_DIAL_CALL_FLG, 0) == 1 ){

    		Log.d("phone", "Debug : CALL!! number=" + number);
    		onAcutalCall(number);
    	}else{
    		Log.d("phone", "Debug : nothing ");
    	}
    }

    /**
     * 加速度センサー受信部
     * @author KENJI
     *
     */
    private class SenserListener implements SensorEventListener{

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			Log.d("phone", "1="+event.values[0] + " 2="+event.values[1]+" 3="+event.values[2]);
		}
    	
    }
}
