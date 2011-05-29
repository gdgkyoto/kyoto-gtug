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
		// �����ꂽ�Ƃ��̏���
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			// �I�����ꂽ�Ƃ��̏���
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner1 = (Spinner) parent;
				// �e�L�X�g�r���[�̃e�L�X�g��ݒ肵�܂�
				Log.d("Spinner01", spinner1.getSelectedItem().toString());

				SL1 = spinner1.getSelectedItem().toString();

			}

			// �I������Ȃ��Ȃ����Ƃ��̏���
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner spinner2 = (Spinner) findViewById(R.id.Spinner02);
		// �����ꂽ�Ƃ��̏���
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			// �I�����ꂽ�Ƃ��̏���
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner2 = (Spinner) parent;
				// �e�L�X�g�r���[�̃e�L�X�g��ݒ肵�܂�
				Log.d("Spinner02", spinner2.getSelectedItem().toString());

				if (SL1.equals("�蓮")) {

					SL2 = spinner2.getSelectedItem().toString();

				} 

			}

			// �I������Ȃ��Ȃ����Ƃ��̏���
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Button button1 = (Button) findViewById(id.button1);
		// �{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// �{�^�����N���b�N���ꂽ���ɌĂяo����܂�

				Intent i = new Intent(getApplicationContext(), Output.class);
				startActivity(i);
			}
		});

        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        //namePrefs.add( "�k�C��" ); latiPrefs.add( 43.03 ); longPrefs.add( 141.21 );

        namePreftoGeocode = new Bundle();

        //namePreftoGeocode.putDoubleArray( "�k�C��",  new double[]{ 43.03, 141.21 } );
        namePreftoGeocode.putDoubleArray( "�k�C��",  new double[]{ 43.03, 141.21 } );
        namePreftoGeocode.putDoubleArray( "�X",    new double[]{ 40.49, 140.44 } );
        namePreftoGeocode.putDoubleArray( "���",    new double[]{ 39.42, 141.09 } );
        namePreftoGeocode.putDoubleArray( "�{��",    new double[]{ 38.16, 140.52 } );
        namePreftoGeocode.putDoubleArray( "�H�c",    new double[]{ 39.43, 140.06 } );
        namePreftoGeocode.putDoubleArray( "�R�`",    new double[]{ 38.15, 140.20 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 37.45, 140.28 } );
        namePreftoGeocode.putDoubleArray( "���",    new double[]{ 36.22, 140.28 } );
        namePreftoGeocode.putDoubleArray( "�Ȗ�",    new double[]{ 36.33, 139.53 } );
        namePreftoGeocode.putDoubleArray( "�Q�n",    new double[]{ 36.23, 139.03 } );
        namePreftoGeocode.putDoubleArray( "���",    new double[]{ 35.51, 139.38 } );
        namePreftoGeocode.putDoubleArray( "��t",    new double[]{ 35.36, 140.06 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 35.41, 139.45 } );
        namePreftoGeocode.putDoubleArray( "�_�ސ�",  new double[]{ 35.26, 139.38 } );
        namePreftoGeocode.putDoubleArray( "�V��",    new double[]{ 37.55, 139.02 } );
        namePreftoGeocode.putDoubleArray( "�x�R",    new double[]{ 36.41, 137.13 } );
        namePreftoGeocode.putDoubleArray( "�ΐ�",    new double[]{ 36.33, 136.39 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 36.03, 136.13 } );
        namePreftoGeocode.putDoubleArray( "�R��",    new double[]{ 35.39, 138.34 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 36.39, 138.11 } );
        namePreftoGeocode.putDoubleArray( "��",    new double[]{ 35.25, 136.45 } );
        namePreftoGeocode.putDoubleArray( "�É�",    new double[]{ 34.58, 138.23 } );
        namePreftoGeocode.putDoubleArray( "���m",    new double[]{ 35.11, 136.54 } );
        namePreftoGeocode.putDoubleArray( "�O�d",    new double[]{ 34.43, 136.30 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 35.00, 135.52 } );
        namePreftoGeocode.putDoubleArray( "���s",    new double[]{ 35.00, 135.46 } );
        namePreftoGeocode.putDoubleArray( "���",    new double[]{ 34.41, 135.29 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 34.41, 135.11 } );
        namePreftoGeocode.putDoubleArray( "�ޗ�",    new double[]{ 34.41, 135.48 } );
        namePreftoGeocode.putDoubleArray( "�a�̎R",  new double[]{ 34.14, 135.10 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 35.29, 134.13 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 35.27, 133.04 } );
        namePreftoGeocode.putDoubleArray( "���R",    new double[]{ 34.39, 133.54 } );
        namePreftoGeocode.putDoubleArray( "�L��",    new double[]{ 34.23, 132.27 } );
        namePreftoGeocode.putDoubleArray( "�R��",    new double[]{ 34.11, 131.27 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 34.03, 134.32 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 34.20, 134.02 } );
        namePreftoGeocode.putDoubleArray( "���Q",    new double[]{ 33.50, 132.44 } );
        namePreftoGeocode.putDoubleArray( "���m",    new double[]{ 33.33, 133.31 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 33.35, 130.23 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 33.16, 130.16 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 32.45, 129.52 } );
        namePreftoGeocode.putDoubleArray( "�F�{",    new double[]{ 32.48, 130.42 } );
        namePreftoGeocode.putDoubleArray( "�啪",    new double[]{ 33.14, 131.37 } );
        namePreftoGeocode.putDoubleArray( "�{��",    new double[]{ 31.56, 131.25 } );
        namePreftoGeocode.putDoubleArray( "������",  new double[]{ 31.36, 130.33 } );
        namePreftoGeocode.putDoubleArray( "����",    new double[]{ 26.13, 127.41 } );

        /*
        �s��	����	�ܓx	�o�x
        �D�y�s	�k�C��	43.03	141.21
        �X�s	�X��	40.49	140.44
        �����s	��茧	39.42	141.09
        ���s	�{�錧	38.16	140.52
        �H�c�s	�H�c��	39.43	140.06
        �R�`�s	�R�`��	38.15	140.20
        �����s	������	37.45	140.28
        ���ˎs	��錧	36.22	140.28
        �F�s�{�s	�Ȗ،�	36.33	139.53
        �O���s	�Q�n��	36.23	139.03
        �Y�a�s	��ʌ�	35.51	139.38
        ��t�s	��t��	35.36	140.06
        ����	�����s	35.41	139.45
        ���l�s	�_�ސ쌧	35.26	139.38
        �V���s	�V����	37.55	139.02
        �x�R�s	�x�R��	36.41	137.13
        ����s	�ΐ쌧	36.33	136.39
        ����s	���䌧	36.03	136.13
        �b�{�s	�R����	35.39	138.34
        ����s	���쌧	36.39	138.11
        �򕌎s	�򕌌�	35.25	136.45
        �É��s	�É���	34.58	138.23
        ���É��s	���m��	35.11	136.54
        �Îs	�O�d��	34.43	136.30
        ��Îs	���ꌧ	35.00	135.52
        ���s�s	���s�{	35.00	135.46
        ���s	���{	34.41	135.29
        �_�ˎs	���Ɍ�	34.41	135.11
        �ޗǎs	�ޗǌ�	34.41	135.48
        �a�̎R�s	�a�̎R��	34.14	135.10
        ����s	���挧	35.29	134.13
        ���]�s	������	35.27	133.04
        ���R�s	���R��	34.39	133.54
        �L���s	�L����	34.23	132.27
        �R���s	�R����	34.11	131.27
        �����s	������	34.03	134.32
        �����s	���쌧	34.20	134.02
        ���R�s	���Q��	33.50	132.44
        ���m�s	���m��	33.33	133.31
        �����s	������	33.35	130.23
        ����s	���ꌧ	33.16	130.16
        ����s	���茧	32.45	129.52
        �F�{�s	�F�{��	32.48	130.42
        �啪�s	�啪��	33.14	131.37
        �{��s	�{�茧	31.56	131.25
        �������s	��������	31.36	130.33
        �ߔe�s	���ꌧ	26.13	127.41
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

      String answer = "���Ȃ��͍�x�ɂ��܂�";
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
