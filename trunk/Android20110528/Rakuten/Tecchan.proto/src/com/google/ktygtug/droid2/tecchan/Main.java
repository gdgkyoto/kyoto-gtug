package com.google.ktygtug.droid2.tecchan;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class Main extends Activity {
    public final static String LOG_TAG = "tecchan";

    private SoundPool mSoundPool = null;
    private volatile int mStreamId = 0;
    private HashMap<String,Integer> mSoundMap = new HashMap<String, Integer>(); //  サウンドマップ

    OnTouchListener touchListener = new OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch ((event.getAction() & MotionEvent.ACTION_MASK)) {
            case MotionEvent.ACTION_DOWN:
                //  走行音スタート
                stopSound();
                mStreamId = mSoundPool.play(mSoundMap.get("zugyan"),1f,1f,0,-1,1f); //  ループ
                break;
            case MotionEvent.ACTION_UP:
                //  走行音ストップ
                stopSound();
                break;
            }
            return false;
        }
    };

    OnClickListener clickListener = new OnClickListener() {
        public void onClick(View v) {
            //  汽笛
            mStreamId = mSoundPool.play(mSoundMap.get("gyuin"),1f,1f,0,0,1f);

            startActivityForResult(new Intent(Main.this,Detail.class),0);

        }
    };

    ImageButton btn01 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn01 = (ImageButton)findViewById(R.id.btn01);
        btn01.setOnTouchListener(touchListener);
        btn01.setOnClickListener(clickListener);


        mSoundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);


        int soundid = mSoundPool.load(this,R.raw.zugyan,0);
        mSoundMap.put("zugyan",soundid);
        soundid = mSoundPool.load(this,R.raw.gyuin,0);
        mSoundMap.put("gyuin",soundid);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSoundPool != null){
            stopSound();
            mSoundPool.release();
            mSoundPool = null;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private void stopSound(){
        if(mSoundPool != null && mStreamId != 0){
            mSoundPool.stop(mStreamId);
            mStreamId = 0;
        }
    }


}
