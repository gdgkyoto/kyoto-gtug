package jp.mucchin.listtest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.layout;
import android.R.string;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FirstActivity extends Activity {
	private Button btSend;
    private ImageView ImageView1;
    private ImageView ImageView2;
    private TextView Textview1;
    private static final int REQUEST_CODE = 0;
   // private Drawable d;

    private Bitmap image1;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first2);
        
        this.btSend = (Button)findViewById(R.id.ButtonSend);
        this.ImageView1 = (ImageView)findViewById(R.id.ImageView1);
        this.ImageView2 = (ImageView)findViewById(R.id.ImageView2);
        this.Textview1 = (TextView)findViewById(R.id.Textview1);        


		ImageView1.setImageResource(R.drawable.p01302); 
		ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2); // imgを1倍から2倍に拡大
		scale.setDuration(3000); // 3000msかけてアニメーションする
		ImageView1.startAnimation(scale); // アニメーション適用
		
		
		
		Textview1.setPadding(100, 300, 100, 100);
		Textview1.setTextColor(Color.BLUE);
		Textview1.setTextSize(25);
        Textview1.setText("なに、買うの？"); 

        
        //ボタンクリック時の処理を実装する
        this.btSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
 
	            try {
                    // インテント作成
                    Intent intent = new Intent(
                            RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // ACTION_WEB_SEARCH
                    intent.putExtra(
                            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(
                            RecognizerIntent.EXTRA_PROMPT,
                            "なに、買うのかな～"); // お好きな文字に変更できます
                    
                    // インテント発行
                    startActivityForResult(intent, REQUEST_CODE);
                } catch (ActivityNotFoundException e) {
                    // このインテントに応答できるアクティビティがインストールされていない場合
                    Toast.makeText(FirstActivity.this,"ActivityNotFoundException", Toast.LENGTH_LONG).show();
                }								
		        
			}//End of public void onClick
			
		});//End of this.btSend.setOnClickListener(new OnClickListener() {        
    }//End of Create


    // アクティビティ終了時に呼び出される
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 自分が投げたインテントであれば応答する
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String resultsString = "";
            
            // 結果文字列リスト
            ArrayList<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            
            //最終的に表示される文字列は、resultStringに格納されている
            for (int i = 0; i< results.size(); i++) {
                // ここでは、文字列が複数あった場合に結合しています
                resultsString += results.get(i);
            }
            
            if(resultsString.toString().equals("きゅうり")){           	
		        MediaPlayer mp = MediaPlayer.create(getBaseContext() ,R.raw.moukae);
		        mp.start();            
            }//End of if
    }//End of onActivityResult    
    }
}//End of public class MainActivity extends Activity {
