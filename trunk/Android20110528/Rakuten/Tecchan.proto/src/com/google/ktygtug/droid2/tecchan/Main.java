package com.google.ktygtug.droid2.tecchan;

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
    public static final String LOG_TAG = "tecchan";
    public static final String KEY_IDX = "Main.Key.Idx";

    private SoundPool mSoundPool = null;
    private volatile int mStreamId = 0;

    private int[][] mSountIds = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0}
    };

    private int mMenuIdx = -1;

    private boolean tag2Idx(View v){
        boolean ret = false;

        int idx = v.getId() - R.id.btn01;
        if(idx >= 0 && idx < 12){
            mMenuIdx = idx;
            ret = true;
        }
        return ret;
    }

    OnTouchListener touchListener = new OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch ((event.getAction() & MotionEvent.ACTION_MASK)) {
            case MotionEvent.ACTION_DOWN:
                tag2Idx(v);
                if(mMenuIdx >= 0){
                    //  走行音スタート
                    stopSound();
                    mStreamId = mSoundPool.play(mSountIds[0][mMenuIdx],1f,1f,0,-1,1f); //  ループ
                }
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
            tag2Idx(v);
            if(mMenuIdx >= 0){
                //  汽笛
                mStreamId = mSoundPool.play(mSountIds[1][mMenuIdx],1f,1f,0,0,1f);
                Intent intent = new Intent(Main.this,Detail.class);
                intent.putExtra(KEY_IDX,mMenuIdx);
                startActivityForResult(intent,1);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ImageButton btn = (ImageButton)findViewById(R.id.btn01);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn02);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn03);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn04);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn05);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn06);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn07);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn08);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn09);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn10);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn11);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);
        btn = (ImageButton)findViewById(R.id.btn12);
        btn.setOnTouchListener(touchListener);
        btn.setOnClickListener(clickListener);

        mSoundPool = new SoundPool(30,AudioManager.STREAM_MUSIC,0);
        //  走行音

        for(int i = 0; i < 12; ++i){
            mSountIds[0][i] = mSoundPool.load(this,R.raw.trail011 + i,0);
        }
        //  汽笛
        mSountIds[1][0] = mSoundPool.load(this,R.raw.whistle011,0);
        mSountIds[1][1] = mSoundPool.load(this,R.raw.whistle022,0);
        mSountIds[1][2] = mSoundPool.load(this,R.raw.whistle031,0);
        mSountIds[1][3] = mSoundPool.load(this,R.raw.whistle041,0);
        mSountIds[1][4] = mSoundPool.load(this,R.raw.whistle051,0);
        mSountIds[1][5] = mSoundPool.load(this,R.raw.whistle061,0);
        mSountIds[1][6] = mSoundPool.load(this,R.raw.whistle071,0);
        mSountIds[1][7] = mSoundPool.load(this,R.raw.whistle081,0);
        mSountIds[1][8] = mSoundPool.load(this,R.raw.whistle091,0);
        mSountIds[1][9] = mSoundPool.load(this,R.raw.whistle101,0);
        mSountIds[1][10] = mSoundPool.load(this,R.raw.whistle111,0);
        mSountIds[1][11] = mSoundPool.load(this,R.raw.whistle111,0);
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
