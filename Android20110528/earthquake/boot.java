package gtug.hackathon.shinsai;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;



public class boot extends Activity implements LocationListener {
    Bundle  namePreftoGeocode;
    LocationManager mLocationManager;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);       
        
       Toast.makeText(this, "Toast2", Toast.LENGTH_SHORT).show(); 
       
 		mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
       mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

	   Log.v("sinsai.geocode", "start!" );

        //namePrefs.add( "北海道" ); latiPrefs.add( 43.03 ); longPrefs.add( 141.21 );

        namePreftoGeocode = new Bundle();

        //namePreftoGeocode.putDoubleArray( "北海道",  new double[]{ 43.03, 141.21 } );
        namePreftoGeocode.putDoubleArray( "北海道",  new double[]{ 43.03, 141.21 } );
        namePreftoGeocode.putDoubleArray( "青森",    new double[]{ 40.49, 140.44 } );
        namePreftoGeocode.putDoubleArray( "岩手",    new double[]{ 39.42, 141.09 } );
        namePreftoGeocode.putDoubleArray( "宮城",    new double[]{ 38.16, 140.52 } );
        namePreftoGeocode.putDoubleArray( "秋田",    new double[]{ 39.43, 140.06 } );
        namePreftoGeocode.putDoubleArray( "山形",    new double[]{ 38.15, 140.20 } );
        namePreftoGeocode.putDoubleArray( "福島",    new double[]{ 37.45, 140.28 } );
        namePreftoGeocode.putDoubleArray( "茨城",    new double[]{ 36.22, 140.28 } );
        namePreftoGeocode.putDoubleArray( "栃木",    new double[]{ 36.33, 139.53 } );
        namePreftoGeocode.putDoubleArray( "群馬",    new double[]{ 36.23, 139.03 } );
        namePreftoGeocode.putDoubleArray( "埼玉",    new double[]{ 35.51, 139.38 } );
        namePreftoGeocode.putDoubleArray( "千葉",    new double[]{ 35.36, 140.06 } );
        namePreftoGeocode.putDoubleArray( "東京",    new double[]{ 35.41, 139.45 } );
        namePreftoGeocode.putDoubleArray( "神奈川",  new double[]{ 35.26, 139.38 } );
        namePreftoGeocode.putDoubleArray( "新潟",    new double[]{ 37.55, 139.02 } );
        namePreftoGeocode.putDoubleArray( "富山",    new double[]{ 36.41, 137.13 } );
        namePreftoGeocode.putDoubleArray( "石川",    new double[]{ 36.33, 136.39 } );
        namePreftoGeocode.putDoubleArray( "福井",    new double[]{ 36.03, 136.13 } );
        namePreftoGeocode.putDoubleArray( "山梨",    new double[]{ 35.39, 138.34 } );
        namePreftoGeocode.putDoubleArray( "長野",    new double[]{ 36.39, 138.11 } );
        namePreftoGeocode.putDoubleArray( "岐阜",    new double[]{ 35.25, 136.45 } );
        namePreftoGeocode.putDoubleArray( "静岡",    new double[]{ 34.58, 138.23 } );
        namePreftoGeocode.putDoubleArray( "愛知",    new double[]{ 35.11, 136.54 } );
        namePreftoGeocode.putDoubleArray( "三重",    new double[]{ 34.43, 136.30 } );
        namePreftoGeocode.putDoubleArray( "滋賀",    new double[]{ 35.00, 135.52 } );
        namePreftoGeocode.putDoubleArray( "京都",    new double[]{ 35.00, 135.46 } );
        namePreftoGeocode.putDoubleArray( "大阪",    new double[]{ 34.41, 135.29 } );
        namePreftoGeocode.putDoubleArray( "兵庫",    new double[]{ 34.41, 135.11 } );
        namePreftoGeocode.putDoubleArray( "奈良",    new double[]{ 34.41, 135.48 } );
        namePreftoGeocode.putDoubleArray( "和歌山",  new double[]{ 34.14, 135.10 } );
        namePreftoGeocode.putDoubleArray( "鳥取",    new double[]{ 35.29, 134.13 } );
        namePreftoGeocode.putDoubleArray( "島根",    new double[]{ 35.27, 133.04 } );
        namePreftoGeocode.putDoubleArray( "岡山",    new double[]{ 34.39, 133.54 } );
        namePreftoGeocode.putDoubleArray( "広島",    new double[]{ 34.23, 132.27 } );
        namePreftoGeocode.putDoubleArray( "山口",    new double[]{ 34.11, 131.27 } );
        namePreftoGeocode.putDoubleArray( "徳島",    new double[]{ 34.03, 134.32 } );
        namePreftoGeocode.putDoubleArray( "香川",    new double[]{ 34.20, 134.02 } );
        namePreftoGeocode.putDoubleArray( "愛媛",    new double[]{ 33.50, 132.44 } );
        namePreftoGeocode.putDoubleArray( "高知",    new double[]{ 33.33, 133.31 } );
        namePreftoGeocode.putDoubleArray( "福岡",    new double[]{ 33.35, 130.23 } );
        namePreftoGeocode.putDoubleArray( "佐賀",    new double[]{ 33.16, 130.16 } );
        namePreftoGeocode.putDoubleArray( "長崎",    new double[]{ 32.45, 129.52 } );
        namePreftoGeocode.putDoubleArray( "熊本",    new double[]{ 32.48, 130.42 } );
        namePreftoGeocode.putDoubleArray( "大分",    new double[]{ 33.14, 131.37 } );
        namePreftoGeocode.putDoubleArray( "宮崎",    new double[]{ 31.56, 131.25 } );
        namePreftoGeocode.putDoubleArray( "鹿児島",  new double[]{ 31.36, 130.33 } );
        namePreftoGeocode.putDoubleArray( "沖縄",    new double[]{ 26.13, 127.41 } );

        /*
        市名	県名	緯度	経度
        札幌市	北海道	43.03	141.21
        青森市	青森県	40.49	140.44
        盛岡市	岩手県	39.42	141.09
        仙台市	宮城県	38.16	140.52
        秋田市	秋田県	39.43	140.06
        山形市	山形県	38.15	140.20
        福島市	福島県	37.45	140.28
        水戸市	茨城県	36.22	140.28
        宇都宮市	栃木県	36.33	139.53
        前橋市	群馬県	36.23	139.03
        浦和市	埼玉県	35.51	139.38
        千葉市	千葉県	35.36	140.06
        東京	東京都	35.41	139.45
        横浜市	神奈川県	35.26	139.38
        新潟市	新潟県	37.55	139.02
        富山市	富山県	36.41	137.13
        金沢市	石川県	36.33	136.39
        福井市	福井県	36.03	136.13
        甲府市	山梨県	35.39	138.34
        長野市	長野県	36.39	138.11
        岐阜市	岐阜県	35.25	136.45
        静岡市	静岡県	34.58	138.23
        名古屋市	愛知県	35.11	136.54
        津市	三重県	34.43	136.30
        大津市	滋賀県	35.00	135.52
        京都市	京都府	35.00	135.46
        大阪市	大阪府	34.41	135.29
        神戸市	兵庫県	34.41	135.11
        奈良市	奈良県	34.41	135.48
        和歌山市	和歌山県	34.14	135.10
        鳥取市	鳥取県	35.29	134.13
        松江市	島根県	35.27	133.04
        岡山市	岡山県	34.39	133.54
        広島市	広島県	34.23	132.27
        山口市	山口県	34.11	131.27
        徳島市	徳島県	34.03	134.32
        高松市	香川県	34.20	134.02
        松山市	愛媛県	33.50	132.44
        高知市	高知県	33.33	133.31
        福岡市	福岡県	33.35	130.23
        佐賀市	佐賀県	33.16	130.16
        長崎市	長崎県	32.45	129.52
        熊本市	熊本県	32.48	130.42
        大分市	大分県	33.14	131.37
        宮崎市	宮崎県	31.56	131.25
        鹿児島市	鹿児島県	31.36	130.33
        那覇市	沖縄県	26.13	127.41
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

      String answer = "あなたは今xにいます";
      Log.v("sinsai.geocode", answer.replace( "x", prefs[ minpref ] ) );
      Toast.makeText(this, answer.replace( "x", prefs[ minpref ] ), Toast.LENGTH_SHORT).show(); 
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
		// TODO Auto-generated method stub
		
	}
}
