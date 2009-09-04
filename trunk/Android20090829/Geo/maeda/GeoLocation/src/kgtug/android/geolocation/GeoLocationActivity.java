package kgtug.android.geolocation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class GeoLocationActivity extends Activity implements OnItemClickListener {
	ArrayList<Row> coll;
  	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);

    	//-----------------------------------------------
    	coll = new ArrayList<Row>();
    	coll.add(new Row("A", R.drawable.test));
    	coll.add(new Row("B", R.drawable.test));
    	coll.add(new Row("C", R.drawable.test));
    	coll.add(new Row("D", R.drawable.test));
    	coll.add(new Row("E", R.drawable.test));
    	coll.add(new Row("F", R.drawable.test));
    	coll.add(new Row("G", R.drawable.test));
    	coll.add(new Row("H", R.drawable.test));
    	coll.add(new Row("I", R.drawable.test));
    	coll.add(new Row("J", R.drawable.test));
    	coll.add(new Row("K", R.drawable.test));
    	coll.add(new Row("L", R.drawable.test));
    	coll.add(new Row("M", R.drawable.test));
    	coll.add(new Row("N", R.drawable.test));

    	//-----------------------------------------------
    	ListView lv = new ListView(this);
    	lv.setAdapter(new ImageViewAdapter(this, coll));
   		setContentView(lv);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    	if(arg2 == 0) {
    		Row rw = (Row)coll.get(arg2);
    		setTitle(rw.getText());
    	}
    	else if(arg2 == 1)
    		finish();
    }
}