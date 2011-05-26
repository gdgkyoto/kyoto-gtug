package com.gmail.y.a.unix.parrot;

import java.util.ArrayList;
import java.util.Locale;

import net.gimite.jatts.JapaneseTextToSpeech;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private static final int REQUEST_CODE = 525;
	
    private TextView statusTextView;
    private TextView recognizedStringTextView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /* オブジェクトの取得 */
        statusTextView = (TextView)findViewById(R.id.statusTextView);
        recognizedStringTextView = (TextView)findViewById(R.id.recognizedStringTextView);
        
        /* ボタンにイベントのリスナを登録 */
        Button recognizeButton = (Button)findViewById(R.id.recognizeButton);
        recognizeButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.recognizeButton:
				/* RecognizeButtonが押された場合 */
				issueRecognizeIntent();
				break;
		}
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
    		String resultString = "";
    		ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
    		
    		for(String result : results){
    			resultString += result;
    		}
    		
    		recognizedStringTextView.setText(resultString);
    		
    		/* 入力されたテキストを話す */
    		statusTextView.setText("音声合成の開始");
    		speakInJapanese(resultString);
    	}
    }

    private void speakInJapanese(String text){
    	JapaneseTextToSpeech tts = new JapaneseTextToSpeech(this,null);
    	tts.speak(text, TextToSpeech.QUEUE_FLUSH,null);
		statusTextView.setText("音声合成の終了");
    }
    
	private void issueRecognizeIntent(){
		/* ステータスの変更 */
		statusTextView.setText("音声認識の開始");
		
		/* インテントの発行 */
		try{
				Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPANESE.toString());
				intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "話しかけてください");
				
				startActivityForResult(intent, REQUEST_CODE);
		}
		catch(ActivityNotFoundException e){
			Toast.makeText(Main.this, "Activity that can handle a recognizer intent was NOT found.", Toast.LENGTH_LONG).show();
			
		}
	}
}