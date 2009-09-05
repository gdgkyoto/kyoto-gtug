package jp.convi.HelloAndroid;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HelloAndroid extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //TelephonyManagerÇÃéÊìæ
        TelephonyManager mTelephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        
        //cellLocationÇÃéÊìæ
        GsmCellLocation cell = (GsmCellLocation)mTelephonyManager.getCellLocation();
        int cid = cell.getCid();	//cell locationÇÃéÊìæ
        int lid = cell.getLac();	//location area codeÇÃéÊìæ
        Log.d("HelloAndroid",String.valueOf(cid)+":"+ String.valueOf(lid) );

        //ínê}ÇÃèàóù
        MapView map_view = (MapView)findViewById(R.id.mapview);
        map_view.setBuiltInZoomControls(true);
        //KRPÇínê}ÇÃíÜêSÇ…Ç∑ÇÈ
        MapController map_ctrl=map_view.getController();
        double lat = 34.994988;
        double longitude = 135.736591;
        int latitude_1e6 = (int)(lat*1E6);
        int longitude_1e6 = (int)(longitude*1E6);
        GeoPoint point= new GeoPoint(latitude_1e6, longitude_1e6);
        map_ctrl.setCenter(point);
        map_ctrl.setZoom(15);
        
        //GeoPoint p = new GeoPoint(,);
        
        
        Button b = (Button)findViewById(R.id.Button01);
        b.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView v1 = (TextView)findViewById(R.id.Text01);
				v1.setText("ínê}ÇïWèÄÇ…ÇµÇ‹ÇµÇΩ");
		    	Log.v("Hello", "Button1 Click!");
		    	
		        //ínê}ÇÃèàóù
		        MapView map_view = (MapView)findViewById(R.id.mapview);
		    	//ínê}ÉÇÅ[ÉhÇïWèÄÇ…Ç∑ÇÈ
		    	map_view.setSatellite(false);
			}
		});

        Button b2 = (Button)findViewById(R.id.Button02);
        b2.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView v1 = (TextView)findViewById(R.id.Text01);
				v1.setText("ínê}ÇâqêØâÊëúÇ…ÇµÇ‹ÇµÇΩ");
		    	Log.v("Hello", "Button2 Click!");

		        //ínê}ÇÃèàóù
		        MapView map_view = (MapView)findViewById(R.id.mapview);
		    	map_view.setSatellite(true);
			}
		});

        Button b3 = (Button)findViewById(R.id.Button03);
        b3.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView v1 = (TextView)findViewById(R.id.Text01);
				v1.setText("Intent Sending.");
		    	Log.v("Hello", "Intent Sending!");
		    	
		    	Intent i = new Intent();
		    	i.setAction(Intent.ACTION_SEND);
		    	i.setType("image/jpeg");
		    	startActivity(i);
			}
		});

    }
    /*   
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }
    @Override
    protected void onStart(){
    	super.onStart();
    	Log.v("Hello", "onStart was Called.");
    }
    @Override
    protected void onRestart(){
    	super.onRestart();
    	Log.v("Hello", "onRestart was Called.");
    }
    @Override
    protected void onResume(){
    	super.onResume();
    	Log.v("Hello", "onResume was Called.");
    }
    @Override
    protected void onFreeze(Bundle arg0){
    }
    @Override
    protected void onPause(){
    	super.onPause();
    	Log.v("Hello", "onPause was Called.");
    }
 */
   @Override 
    protected void onStop(){
    	super.onStop();
    	Log.v("Hello", "onStop was Called.");

    	//TelephonyManagerÇÃéÊìæ
//        TelephonyManager mTelephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//        mTelephonyManager.listen(mListener, PhoneStateListener.LISTEN_CELL_LOCATION)
   }
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	Log.v("Hello", "onDestroy was Called.");
    }
 
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}

    