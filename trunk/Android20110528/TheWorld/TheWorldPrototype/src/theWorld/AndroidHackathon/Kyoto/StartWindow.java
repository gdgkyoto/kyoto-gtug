package theWorld.AndroidHackathon.Kyoto;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.widget.TextView;

public class StartWindow extends Activity{
	int counter = 5;

	Timer   mTimer   = null;
	Handler mHandler = new Handler();

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startwindow);

        TextView count = (TextView)findViewById(R.id.Count_TextView);
        String text = "バトル開始まで";
        count.setText((CharSequence)text);
        count.setTextColor(Color.RED);
        count.setTextSize(70.0f);
    }

    /**
     * 入力待ち状態時処理。
     *
     */
	@Override
	protected void onResume() {
		super.onResume();

		// バトル画面へ遷移するインテント準備
		final Intent intent = new Intent(this, BattleMainActivity.class);
		
		// タイマー
		mTimer = new Timer();
		mTimer.schedule( new TimerTask(){
	        @Override
	        public void run() {
	            // mHandlerを通じてUI Threadへ処理をキューイング
	            mHandler.post( new Runnable() {
	                public void run() {
	                	if(counter >0){
		                	TextView count = (TextView)findViewById(R.id.Count_TextView);
		                    String text = String.valueOf(counter);
		                    count.setText((CharSequence)text);
		                    count.setTextColor(Color.RED);
		                    count.setTextSize(100.0f);
		                    counter--;
	                	}else{
	                		mTimer.cancel();
	                		mTimer = null;

	                		// バトル画面に遷移する
	                		startActivity(intent);
	                		finish();
	                	}
	                }
	            });
	        }
	    }, 1000, 1000);

	}

	/**
	 * 遷移時処理(Activityが裏に移動した時の処理)。
	 *
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * Activity終了時処理。
	 *
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();

	}
}


