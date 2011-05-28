package theWorld.AndroidHackathon.Kyoto;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;



	public class charaMake extends Activity implements OnClickListener{
		// 返ってきた時の認証用コード(数字は適当なもので良い)
	    private static final int REQUEST_CODE_VOICE_RECO = 123;
		private static final String PROMPT_MES = null;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.chara_make);
	        findViewById(R.id.button_select_stand).setOnClickListener(this);
	        findViewById(R.id.button_select_power).setOnClickListener(this);
	        findViewById(R.id.button_toSelectRival).setOnClickListener(this);
    }
	public void onClick(View view){
	   	switch(view.getId() ){
	   	//スタンドを選択
	   	case R.id.button_select_stand:
	   	//	Intent intent = new Intent(this, charaMake.class);
	   	//	startActivity(intent);
	   	//	finish();
	   		Log.i("aaa" ,"aa");
	   		StartVoiceRecognition();
	   		break;
	   	//パワーを検出
	   	case R.id.button_select_power:
	   		Intent intent = new Intent(this, charaMake.class);
		   	startActivity(intent);
		   	finish();
		   	break;
		//次へ進む
		case R.id.button_toSelectRival:
	   		Intent intent2 = new Intent(this, selectRival.class);
		   	startActivity(intent2);
		   	//finish();
		   	break;
	   	}
	   
	   	
	   	
   }

	public void StartVoiceRecognition(){
   	 try {
            // インテント作成
   		 // 認識された音声を文字列として取得する
            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            // 音声認識に使う言語モデルを指定する
            intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            // プロンプトの表示内容を指定する
            intent.putExtra(
                    RecognizerIntent.EXTRA_PROMPT,PROMPT_MES);
            
            // インテントを発行する(音声認識ツールが戻り値ありモードで起動する)
            startActivityForResult(intent, REQUEST_CODE_VOICE_RECO);
        } catch (ActivityNotFoundException e) {
            // 音声認識ツールがインストールされていない場合、トーストでエラーメッセージを表示する
          //  Toast.makeText(this,
          // 		 R.string.err_mes_001, Toast.LENGTH_LONG).show();
        }
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
           
           if( result.equals("the world") ){
        	   Log.i("stand" ,"the world");
        	   TextView a= (TextView)findViewById(R.id.stand_name);
        	   a.setText("スタンド：the world");
        		   
           }
           else if( result.equals("スタープラチナ")){
        	   Log.i("stand" ,"スタープラチナ");
        	   TextView a= (TextView)findViewById(R.id.stand_name);
        	   a.setText("スタンド：スタープラチナ");
           }
       
       }
       
       super.onActivityResult(requestCode, resultCode, data);
   }
}

