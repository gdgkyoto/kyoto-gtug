package com.googlecode.kyotogtug.android20110528.rakuten.tecchan.proto;

import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Detail extends Activity {
    public final static String LOG_TAG = "tecchan";

    private SoundPool mSoundPool = null;
    private volatile int mStreamId = 0;
    private HashMap<String,Integer> mSoundMap = new HashMap<String, Integer>(); //  サウンドマップ

    OnClickListener clickListener = new OnClickListener() {
        public void onClick(View v) {
            //  汽笛
            mStreamId = mSoundPool.play(mSoundMap.get("gyuin"),1f,1f,0,0,1f);

        }
    };


    ImageButton btn01 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(Activity.RESULT_CANCELED);

        setContentView(R.layout.detail);



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
            mSoundPool.release();
            mSoundPool = null;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        //        stopSound();
    }

    private void stopSound(){
        if(mSoundPool != null && mStreamId != 0){
            mSoundPool.stop(mStreamId);
            mStreamId = 0;
        }
    }

    //  ボタン１クリックリスナ
    public void OnClick_Btn01(View v) {

    }

    //  ボタン２クリックリスナ
    public void OnClick_Btn02(View v) {

    }

    //  ボタン３クリックリスナ
    public void OnClick_Btn03(View v) {

    }

    //  ボタン４クリックリスナ
    public void OnClick_Btn04(View v) {

    }

    //  ボタン５クリックリスナ
    public void OnClick_Btn05(View v) {

    }

}
