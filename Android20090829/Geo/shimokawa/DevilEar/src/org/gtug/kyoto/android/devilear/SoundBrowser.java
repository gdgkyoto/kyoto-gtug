package org.gtug.kyoto.android.devilear;

import java.io.File;
import java.io.IOException;

import org.gtug.kyoto.android.devilear.item.SoundItem;
import org.gtug.kyoto.android.devilear.item.TextItem;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SoundBrowser extends Activity {

    TextView mTitleView;
    Button mPlayPauseButton;
    boolean mIsPlaying = false;

    SoundItem mSoundItem;
    Long mItemId;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_browser);

        mTitleView = (TextView) findViewById(R.id.TextView2_1);
        mPlayPauseButton = (Button) findViewById(R.id.Button2_1);

        mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsPlaying) {
                    StopPlaying();
                } else {
                    StartPlaying();
                }
                mIsPlaying = !mIsPlaying;
            }
        });

        Button closeButton = (Button) findViewById(R.id.Button2_2);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish activity
                StopPlaying();
                setResult(RESULT_OK);
                finish();
            }
        });

        mItemId = savedInstanceState != null ? savedInstanceState
                .getLong(ItemsDbAdapter.KEY_ROWID) : null;
        if (mItemId == null) {
            Bundle extras = getIntent().getExtras();
            mItemId = extras != null ? extras.getLong(ItemsDbAdapter.KEY_ROWID)
                    : null;
        }

        populateFields();
    }

    protected void StartPlaying() {
        if (mSoundItem == null) {
            mSoundItem = SoundItem.findById(this, mItemId);
            if (mSoundItem == null) {
                return;
            }
        }

        if (mPlayer == null) {
            File file = new File(mSoundItem.getFilePath());
            mPlayer = createPlayer(file);
            if (mPlayer == null) {
                return;
            }
        }
        
        if (mPlayer.isPlaying()) {
            return;
        }

        mPlayer.start();
    }

    private MediaPlayer createPlayer(File source) {
        try {
            MediaPlayer player = new MediaPlayer();
            Log.v(getClass().getName(), "IDLE State");
            player.setDataSource(source.toString());
            player.prepare();
            return player;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void StopPlaying() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    private void populateFields() {
        if (mItemId != null) {
            mSoundItem = SoundItem.findById(this, mItemId);
            mTitleView.setText(mSoundItem.getName());
        }
    }

}
