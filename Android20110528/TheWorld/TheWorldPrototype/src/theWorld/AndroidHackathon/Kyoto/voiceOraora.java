package theWorld.AndroidHackathon.Kyoto;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.TextView;
import android.widget.Toast;

public class voiceOraora extends Activity {

	private static final String PROMPT_MES = null;
	private static final int REQUEST_CODE_VOICE_RECO = 0;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_oraora);
       //音声検出
        StartVoiceRecognition();
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
	           
	           TextView a= (TextView)findViewById(R.id.textView1);
	           
	          //声の長さでパワーが決まる
	          int num = result.length();
	          a.setText("声パワー" +  num);
	        
	         
	       
	       }
	       
	       super.onActivityResult(requestCode, resultCode, data);
	       
	   }
}
