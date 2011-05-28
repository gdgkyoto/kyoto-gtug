package theWorld.AndroidHackathon.Kyoto;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.text.Layout;
import android.text.Layout.Alignment;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartWindow extends Activity implements OnClickListener {


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
        count.setTextSize(40.0f);




    }


    /**
     * 入力待ち状態時処理。
     *
     */
	@Override
	protected void onResume() {
		super.onResume();

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
		                    count.setTextSize(40.0f);
		                    counter--;
	                	}else{
	                		mTimer.cancel();
	                		mTimer = null;

	                		//Intent記述
//	                		Intent intent = new Intent(this, .class);
//	                		startActivity(intent);


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


	public void onClick(View v) {
		switch(v.getId()){
    	case R.id.start_Button:

    		Toast.makeText(StartWindow.this, "start", Toast.LENGTH_LONG).show();
            break;
        default:
        	break;
	}

	}

}
