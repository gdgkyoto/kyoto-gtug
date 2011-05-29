package com.Liferoid.android;

import com.Liferoid.android.R.id;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import android.widget.Toast;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;

public class Liferoid extends Activity implements LocationListener {
	/** Called when the activity is first created. */

	Spinner spinner1;
	String Select_location;
	String Set_location;

    Bundle  namePreftoGeocode;
    LocationManager mLocationManager;

	private static String SL1, SL2;

	void setX(String x) {
		SL1 = x;
	}

	String getX() {
		return SL1;
	}

	void setY(String y) {
		SL2 = y;
	}

	String getY() {
		return SL2;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Spinner spinner1 = (Spinner) findViewById(R.id.Spinner01);
		// ‰Ÿ‚³‚ê‚½‚Æ‚«‚Ìˆ—
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			// ‘I‘ğ‚³‚ê‚½‚Æ‚«‚Ìˆ—
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner1 = (Spinner) parent;
				// ƒeƒLƒXƒgƒrƒ…[‚ÌƒeƒLƒXƒg‚ğİ’è‚µ‚Ü‚·
				Log.d("Spinner01", spinner1.getSelectedItem().toString());

				SL1 = spinner1.getSelectedItem().toString();

			}

			// ‘I‘ğ‚³‚ê‚È‚­‚È‚Á‚½‚Æ‚«‚Ìˆ—
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner spinner2 = (Spinner) findViewById(R.id.Spinner02);
		// ‰Ÿ‚³‚ê‚½‚Æ‚«‚Ìˆ—
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			// ‘I‘ğ‚³‚ê‚½‚Æ‚«‚Ìˆ—
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner2 = (Spinner) parent;
				// ƒeƒLƒXƒgƒrƒ…[‚ÌƒeƒLƒXƒg‚ğİ’è‚µ‚Ü‚·
				Log.d("Spinner02", spinner2.getSelectedItem().toString());

				if (SL1.equals("è“®")) {

					SL2 = spinner2.getSelectedItem().toString();

				} 

			}

			// ‘I‘ğ‚³‚ê‚È‚­‚È‚Á‚½‚Æ‚«‚Ìˆ—
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Button button1 = (Button) findViewById(id.button1);
		// ƒ{ƒ^ƒ“‚ªƒNƒŠƒbƒN‚³‚ê‚½‚ÉŒÄ‚Ño‚³‚ê‚éƒR[ƒ‹ƒoƒbƒNƒŠƒXƒi[‚ğ“o˜^‚µ‚Ü‚·
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ƒ{ƒ^ƒ“‚ªƒNƒŠƒbƒN‚³‚ê‚½‚ÉŒÄ‚Ño‚³‚ê‚Ü‚·

				Intent i = new Intent(getApplicationContext(), Output.class);
				startActivity(i);
			}
		});

        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        //namePrefs.add( "–kŠC“¹" ); latiPrefs.add( 43.03 ); longPrefs.add( 141.21 );

        namePreftoGeocode = new Bundle();

        //namePreftoGeocode.putDoubleArray( "–kŠC“¹",  new double[]{ 43.03, 141.21 } );
        namePreftoGeocode.putDoubleArray( "–kŠC“¹",  new double[]{ 43.03, 141.21 } );
        namePreftoGeocode.putDoubleArray( "ÂX",    new double[]{ 40.49, 140.44 } );
        namePreftoGeocode.putDoubleArray( "Šâè",    new double[]{ 39.42, 141.09 } );
        namePreftoGeocode.putDoubleArray( "‹{é",    new double[]{ 38.16, 140.52 } );
        namePreftoGeocode.putDoubleArray( "H“c",    new double[]{ 39.43, 140.06 } );
        namePreftoGeocode.putDoubleArray( "RŒ`",    new double[]{ 38.15, 140.20 } );
        namePreftoGeocode.putDoubleArray( "•Ÿ“‡",    new double[]{ 37.45, 140.28 } );
        namePreftoGeocode.putDoubleArray( "ˆïé",    new double[]{ 36.22, 140.28 } );
        namePreftoGeocode.putDoubleArray( "“È–Ø",    new double[]{ 36.33, 139.53 } );
        namePreftoGeocode.putDoubleArray( "ŒQ”n",    new double[]{ 36.23, 139.03 } );
        namePreftoGeocode.putDoubleArray( "é‹Ê",    new double[]{ 35.51, 139.38 } );
        namePreftoGeocode.putDoubleArray( "ç—t",    new double[]{ 35.36, 140.06 } );
        namePreftoGeocode.putDoubleArray( "“Œ‹",    new double[]{ 35.41, 139.45 } );
        namePreftoGeocode.putDoubleArray( "_“Şì",  new double[]{ 35.26, 139.38 } );
        namePreftoGeocode.putDoubleArray( "VŠƒ",    new double[]{ 37.55, 139.02 } );
        namePreftoGeocode.putDoubleArray( "•xR",    new double[]{ 36.41, 137.13 } );
        namePreftoGeocode.putDoubleArray( "Îì",    new double[]{ 36.33, 136.39 } );
        namePreftoGeocode.putDoubleArray( "•Ÿˆä",    new double[]{ 36.03, 136.13 } );
        namePreftoGeocode.putDoubleArray( "R—œ",    new double[]{ 35.39, 138.34 } );
        namePreftoGeocode.putDoubleArray( "’·–ì",    new double[]{ 36.39, 138.11 } );
        namePreftoGeocode.putDoubleArray( "Šò•Œ",    new double[]{ 35.25, 136.45 } );
        namePreftoGeocode.putDoubleArray( "Ã‰ª",    new double[]{ 34.58, 138.23 } );
        namePreftoGeocode.putDoubleArray( "ˆ¤’m",    new double[]{ 35.11, 136.54 } );
        namePreftoGeocode.putDoubleArray( "Od",    new double[]{ 34.43, 136.30 } );
        namePreftoGeocode.putDoubleArray( " ‰ê",    new double[]{ 35.00, 135.52 } );
        namePreftoGeocode.putDoubleArray( "‹“s",    new double[]{ 35.00, 135.46 } );
        namePreftoGeocode.putDoubleArray( "‘åã",    new double[]{ 34.41, 135.29 } );
        namePreftoGeocode.putDoubleArray( "•ºŒÉ",    new double[]{ 34.41, 135.11 } );
        namePreftoGeocode.putDoubleArray( "“Ş—Ç",    new double[]{ 34.41, 135.48 } );
        namePreftoGeocode.putDoubleArray( "˜a‰ÌR",  new double[]{ 34.14, 135.10 } );
        namePreftoGeocode.putDoubleArray( "’¹æ",    new double[]{ 35.29, 134.13 } );
        namePreftoGeocode.putDoubleArray( "“‡ª",    new double[]{ 35.27, 133.04 } );
        namePreftoGeocode.putDoubleArray( "‰ªR",    new double[]{ 34.39, 133.54 } );
        namePreftoGeocode.putDoubleArray( "L“‡",    new double[]{ 34.23, 132.27 } );
        namePreftoGeocode.putDoubleArray( "RŒû",    new double[]{ 34.11, 131.27 } );
        namePreftoGeocode.putDoubleArray( "“¿“‡",    new double[]{ 34.03, 134.32 } );
        namePreftoGeocode.putDoubleArray( "ì",    new double[]{ 34.20, 134.02 } );
        namePreftoGeocode.putDoubleArray( "ˆ¤•Q",    new double[]{ 33.50, 132.44 } );
        namePreftoGeocode.putDoubleArray( "‚’m",    new double[]{ 33.33, 133.31 } );
        namePreftoGeocode.putDoubleArray( "•Ÿ‰ª",    new double[]{ 33.35, 130.23 } );
        namePreftoGeocode.putDoubleArray( "²‰ê",    new double[]{ 33.16, 130.16 } );
        namePreftoGeocode.putDoubleArray( "’·è",    new double[]{ 32.45, 129.52 } );
        namePreftoGeocode.putDoubleArray( "ŒF–{",    new double[]{ 32.48, 130.42 } );
        namePreftoGeocode.putDoubleArray( "‘å•ª",    new double[]{ 33.14, 131.37 } );
        namePreftoGeocode.putDoubleArray( "‹{è",    new double[]{ 31.56, 131.25 } );
        namePreftoGeocode.putDoubleArray( "­™“‡",  new double[]{ 31.36, 130.33 } );
        namePreftoGeocode.putDoubleArray( "‰«“ê",    new double[]{ 26.13, 127.41 } );

        /*
        s–¼	Œ§–¼	ˆÜ“x	Œo“x
        D–ys	–kŠC“¹	43.03	141.21
        ÂXs	ÂXŒ§	40.49	140.44
        ·‰ªs	ŠâèŒ§	39.42	141.09
        å‘äs	‹{éŒ§	38.16	140.52
        H“cs	H“cŒ§	39.43	140.06
        RŒ`s	RŒ`Œ§	38.15	140.20
        •Ÿ“‡s	•Ÿ“‡Œ§	37.45	140.28
        …ŒËs	ˆïéŒ§	36.22	140.28
        ‰F“s‹{s	“È–ØŒ§	36.33	139.53
        ‘O‹´s	ŒQ”nŒ§	36.23	139.03
        ‰Y˜as	é‹ÊŒ§	35.51	139.38
        ç—ts	ç—tŒ§	35.36	140.06
        “Œ‹	“Œ‹“s	35.41	139.45
        ‰¡•ls	_“ŞìŒ§	35.26	139.38
        VŠƒs	VŠƒŒ§	37.55	139.02
        •xRs	•xRŒ§	36.41	137.13
        ‹à‘òs	ÎìŒ§	36.33	136.39
        •Ÿˆäs	•ŸˆäŒ§	36.03	136.13
        b•{s	R—œŒ§	35.39	138.34
        ’·–ìs	’·–ìŒ§	36.39	138.11
        Šò•Œs	Šò•ŒŒ§	35.25	136.45
        Ã‰ªs	Ã‰ªŒ§	34.58	138.23
        –¼ŒÃ‰®s	ˆ¤’mŒ§	35.11	136.54
        ’Ãs	OdŒ§	34.43	136.30
        ‘å’Ãs	 ‰êŒ§	35.00	135.52
        ‹“ss	‹“s•{	35.00	135.46
        ‘åãs	‘åã•{	34.41	135.29
        _ŒËs	•ºŒÉŒ§	34.41	135.11
        “Ş—Çs	“Ş—ÇŒ§	34.41	135.48
        ˜a‰ÌRs	˜a‰ÌRŒ§	34.14	135.10
        ’¹æs	’¹æŒ§	35.29	134.13
        ¼]s	“‡ªŒ§	35.27	133.04
        ‰ªRs	‰ªRŒ§	34.39	133.54
        L“‡s	L“‡Œ§	34.23	132.27
        RŒûs	RŒûŒ§	34.11	131.27
        “¿“‡s	“¿“‡Œ§	34.03	134.32
        ‚¼s	ìŒ§	34.20	134.02
        ¼Rs	ˆ¤•QŒ§	33.50	132.44
        ‚’ms	‚’mŒ§	33.33	133.31
        •Ÿ‰ªs	•Ÿ‰ªŒ§	33.35	130.23
        ²‰ês	²‰êŒ§	33.16	130.16
        ’·ès	’·èŒ§	32.45	129.52
        ŒF–{s	ŒF–{Œ§	32.48	130.42
        ‘å•ªs	‘å•ªŒ§	33.14	131.37
        ‹{ès	‹{èŒ§	31.56	131.25
        ­™“‡s	­™“‡Œ§	31.36	130.33
        “ß”es	‰«“êŒ§	26.13	127.41
        */
	}

	@Override
	public void onLocationChanged(Location location) {
	   //Toast.makeText(this,String.valueOf(location.getLatitude()),Toast.LENGTH_SHORT).show();
	   //Toast.makeText(this,String.valueOf(location.getLongitude()),Toast.LENGTH_SHORT).show();
	   
	   Log.v("sinsai.geocode", "changed" );
	   Log.v("sinsai.geocode", String.valueOf(location.getLatitude()));
      Log.v("sinsai.geocode", String.valueOf(location.getLongitude()));

      double longitude, latitude;
      String[] prefs = namePreftoGeocode.keySet().toArray( new String[0] );

      int minpref = -1;
      double minscore = Double.MAX_VALUE;
      longitude = location.getLongitude();
      latitude = location.getLatitude();
      for( int i = 0; i < prefs.length ; i++ ){
        double[] code = namePreftoGeocode.getDoubleArray( prefs[i] );
        double a = ( code[0] - latitude );
        double b = ( code[1] - longitude );
        double score = a*a + b*b ;
        if( score < minscore ){
            minscore = score;
            minpref = i;
        }
      }

      String answer = "‚ ‚È‚½‚Í¡x‚É‚¢‚Ü‚·";
      Log.v("sinsai.geocode", answer.replace( "x", prefs[ minpref ] ) );
      //Toast.makeText(this, answer.replace( "x", prefs[ minpref ] ), Toast.LENGTH_SHORT).show(); 
       SL2 = prefs[ minpref ];
    }
	
	@Override
    protected void onResume() {
        if (mLocationManager != null) {
            mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,              0,                0,                this);
         }
        super.onResume();
    }
    
    @Override
    protected void onPause() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }        
        super.onPause();
    }
    
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		 switch (status) {
	        case LocationProvider.AVAILABLE:
	            Log.v("sinsai.geocode", "AVAILABLE");
	            break;
	        case LocationProvider.OUT_OF_SERVICE:
	            Log.v("sinsai.geocode", "OUT_OF_SERVICE");
	            break;
	        case LocationProvider.TEMPORARILY_UNAVAILABLE:
	            Log.v("sinsai.geocode", "TEMPORARILY_UNAVAILABLE");
	            break;
	        }		
	}
}
