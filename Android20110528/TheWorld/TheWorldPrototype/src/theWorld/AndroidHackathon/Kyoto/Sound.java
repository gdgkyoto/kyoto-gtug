package theWorld.AndroidHackathon.Kyoto;

import jun.maruyama.kyoto.android.test.R;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Sound {

	public MediaPlayer mPlayer;

	public SoundPool soundPool;
	public int soundID[];

	public void Initialize(){
		
		mPlayer = new MediaPlayer.create(this, R.raw.eggtopsamp);
	      
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
   	soundID[0] = soundPool.load(this, R.raw , 1);
   	
	}
	
	public void PlayBGM(){
		
		 mPlayer.start();
		
		
		
	}
   	
		
	}
	
}
