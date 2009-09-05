package org.kyotogtug.hackathon090905.gamet.kaodroid.game.pazzle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class KaodroidPazzle extends Activity {
    KaodroidPazzleView pazzleview;
    int img_num = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pazzleview = new KaodroidPazzleView(this);
        pazzleview.setActivity(this);
        img_num = pazzleview.loadPicture(0);
        setContentView(pazzleview);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	super.onCreateOptionsMenu(menu);
    	for (int i=0; i<img_num; i++)
    	{
    		menu.add(0, i, 0, "image"+i);
    	}
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        pazzleview.loadPicture(item.getItemId());
        return true;
    }
}