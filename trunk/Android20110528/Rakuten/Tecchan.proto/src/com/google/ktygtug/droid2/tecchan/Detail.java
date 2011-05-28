package com.google.ktygtug.droid2.tecchan;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends Activity {
    public final static String LOG_TAG = "tecchan";

    private SoundPool mSoundPool = null;
    private volatile int mStreamId = 0;
    private int[] mSoundIds = new int[]{0,0,0};  //  サウンドファイル


    private int mMenuIdx = -1;

    private ImageView mImgInfo = null;
    private TextView mTxtInfo = null;

    ImageButton btn01 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(Activity.RESULT_CANCELED);

        mMenuIdx = getIntent().getIntExtra(Main.KEY_IDX,-1);
        if(mMenuIdx == -1){
            finish();
        }else{
            setContentView(R.layout.detail);
            mImgInfo = (ImageView)findViewById(R.id.imageMain);

            mTxtInfo = (TextView)findViewById(R.id.textInfo);
            mTxtInfo.setText(null);

            mSoundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);

            int baseid = R.raw.whistle011 + mMenuIdx * 3;
            mSoundIds[0] = mSoundPool.load(this,baseid + 0,0);
            mSoundIds[1] = mSoundPool.load(this,baseid + 1,0);
            mSoundIds[2] = mSoundPool.load(this,baseid + 2,0);
        }

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
        if(mSoundIds[0] > 0){
            stopSound();
            mStreamId = mSoundPool.play(mSoundIds[0],1f,1f,0,0,1f);
        }

    }

    //  ボタン２クリックリスナ
    public void OnClick_Btn02(View v) {
        if(mSoundIds[1] > 0){
            stopSound();
            mStreamId = mSoundPool.play(mSoundIds[1],1f,1f,0,0,1f);
        }

    }

    //  ボタン３クリックリスナ
    public void OnClick_Btn03(View v) {
        if(mSoundIds[2] > 0){
            stopSound();
            mStreamId = mSoundPool.play(mSoundIds[2],1f,1f,0,0,1f);
        }

    }

    //  ボタン４クリックリスナ
    public void OnClick_Btn04(View v) {
        startActivityForResult(new Intent(this,Rakuten.class),1);
    }

}
