package com.lifevar;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class FragranceSenseActivity extends Activity implements OnClickListener {
    LocationManager locmgr;
    int period;
    FragranceTask task;
    ProgressBar prog;
    Fragrance frag;
    
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragrance);
        
        locmgr = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        period = 1;
        
        prog = (ProgressBar)findViewById(R.id.progressBar1);
        prog.setMax(1000);
        
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
        frag = null;
    }
    
    protected void onResume() {
        super.onResume();
        
        if(task == null){
            task = new FragranceTask();
            Thread t = new Thread(task);
            t.start();
        }
    }
    
    protected void onPause() {
        super.onPause();
        if(task!=null){
            task.stop();
            task = null;
        }
    }
    
    protected void onDestroy() {
        super.onDestroy();
        //念のため
        if(task!=null){
            task.stop();
            task = null;
        }
    }

    public void poke(Fragrance frag){
        
        double a = 0;
        a += frag.epara1*frag.epara1;
        a += frag.epara2*frag.epara2;
        a += frag.epara3*frag.epara3;
        a += frag.epara4*frag.epara4;
        a += frag.epara5*frag.epara5;
        a += frag.epara6*frag.epara6;
        a += frag.epara7*frag.epara7;
        a += frag.epara8*frag.epara8;
        double b = Math.sqrt(a);
        
        Logger.d("b="+b);
        prog.setProgress((int)b);
        this.frag = frag;
    }
    
    private class FragranceTask implements Runnable{
        private boolean isLoop = true;
        private Random rand;
        private Location loc;
        private Handler handler = new Handler();
        
        public void stop(){
            isLoop = false;
        }
        
        public FragranceTask(){
            rand = new Random(System.currentTimeMillis());
            loc = locmgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        
        private Fragrance selectGragrance(){
            Fragrance ret = new Fragrance();
            
            Calendar cal = Calendar.getInstance();
            ret.date = cal.getTime();
            ret.latitude = loc.getLatitude();
            ret.longitude = loc.getLongitude();
            ret.epara1 = rand.nextInt(255);
            ret.epara2 = rand.nextInt(255);
            ret.epara3 = rand.nextInt(255);
            ret.epara4 = rand.nextInt(255);
            ret.epara5 = rand.nextInt(255);
            ret.epara6 = rand.nextInt(255);
            ret.epara7 = rand.nextInt(255);
            ret.epara8 = rand.nextInt(255);
            ret.period = period;
            
            return ret;
        }
        
        public void run() {
            while(isLoop){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                
                final Fragrance ret = selectGragrance();
                
                handler.post(new Runnable() {
                    public void run() {
                        poke(ret);
                    }
                });
            }
        }
        
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, RegistActivity.class);
        i.putExtra("fragrance", frag);
        startActivity(i);
    }
}
