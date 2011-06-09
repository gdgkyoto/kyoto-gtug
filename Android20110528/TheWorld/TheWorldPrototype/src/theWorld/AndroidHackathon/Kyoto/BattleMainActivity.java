package theWorld.AndroidHackathon.Kyoto;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * バトル画面クラス
 * 
 * @author scarviz
 *
 */
public class BattleMainActivity extends BaseActivity {
	// 認証用コード
    private static final int REQUEST_CODE_VOICE_RECO = 123;
    // プロンプト表示用
    private static final String PROMPT_MES = "相手より多く打ち込め！！";
    
    // タイマー
 	Timer mTimer = null;
 	// ハンドラ
	Handler mHandler = new Handler();
 	
 	// 各コントロール
 	TextView mBattleMes;
 	TextView mPoewr;
 	TextView mPointGauge;
 	RatingBar mRatingBar;
 	TextView mEnemyPoewr;
 	TextView mEnemyPointGauge;
 	RatingBar mEnemyRatingBar;
 	
 	// ラウンドカウンタ
 	int roundCnt = 1;
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_main);
		
		// 各コントロールを取得する
		mBattleMes = (TextView)findViewById(R.id.battleMes);
		mPoewr = (TextView)findViewById(R.id.power);
		mPointGauge = (TextView)findViewById(R.id.pointGauge);
		mRatingBar = (RatingBar)findViewById(R.id.ratingBar);
		mEnemyPoewr = (TextView)findViewById(R.id.enemyPower);
		mEnemyPointGauge = (TextView)findViewById(R.id.enemyPointGauge);
		mEnemyRatingBar = (RatingBar)findViewById(R.id.enemyRatingBar);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// ラウンド数表示
		mBattleMes.setText(String.valueOf(roundCnt) + "R");
		
		// 定期処理
		ExecuteRegularProcessing();
		
	}
	
	/**
	 * 定期処理。
	 * 
	 */
	private void ExecuteRegularProcessing(){
        // Timerを設定する
		mTimer = new Timer(true);
        // 第二引数の数値(ミリ秒)後に1度だけタスクを実行
		mTimer.schedule(new TimerTask(){
			@Override
			public void run() {
	            mHandler.post( new Runnable() {
	                public void run() {
						// 5秒後にバトル開始
						StartBattle();
	                }
	            });
			}},5*1000);
	}
	
	/**
	 * バトルを開始する。
	 * 
	 */
	private void StartBattle(){
		// 「Fight!!」表示
		mBattleMes.setText(R.string.battleMes01);

        // 音声認識開始
        super.StartVoiceRecognition(PROMPT_MES,REQUEST_CODE_VOICE_RECO);
        
        // ラウンドカウンタを繰り上げる
        roundCnt++;
	}
	
    /**
     * アクティビティ終了時に結果を受け取る。
     * 
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 返った来たコードが認証用コードと一致する(自分で投げたインテント)、かつ、結果が正常の場合
        if (requestCode == REQUEST_CODE_VOICE_RECO 
        		&& resultCode == RESULT_OK) {
            // 結果用
        	String result = "";
            
            // 結果文字列を取得する
            ArrayList<String> resultList = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            
            // 類似する文字列を取得しているので、今回は最初の文字列を表示用として格納する
            result = resultList.get(0);
            
            //結果をトーストで表示する
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        }
        
        super.onActivityResult(requestCode, resultCode, data);
    }
	
	/**
	 * 一時停止時イベント。
	 * 
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	/**
	 * 終了時イベント。
	 * 
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// GCに解放させるため優先度を上げる
		mTimer = null;
		mHandler = null;
		mBattleMes = null;
		mPoewr = null;
	 	mPointGauge = null;
	 	mRatingBar = null;
	 	mEnemyPoewr = null;
	 	mEnemyPointGauge = null;
	 	mEnemyRatingBar = null;
	}

}
