package com.prototechno.soundtest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class soundtest extends Activity implements OnCompletionListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        // サウンドデータの初期化
        mMp = MediaPlayer.create(this, R.raw.sound);
       
        

        // サウンド関連のリスナーのセット
        mMp.setOnCompletionListener(this);
        
       
    }
    
    /**
     * サウンド再生データを保持します。
     */
    private MediaPlayer mMp;
 
    /**
     * 再生完了時に呼び出されます。
     */
    public void onCompletion(MediaPlayer arg0) {
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
        menu.add(0, 0, 0, "PLAY");
        menu.add(0, 1, 1, "STOP");
        
        return true;
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case 0:
            try {
                // サウンド再生開始
                mMp.start();
                
            } catch (Exception e) {}
            return true;
            
        case 1:
            try {
                // サウンド再生停止
                mMp.stop();
                mMp.prepare();
                
            } catch (Exception e) {}
            return true;
        }
        return false;
    }
}