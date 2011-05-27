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
		ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2); // img��1�{����2�{�Ɋg��
		scale.setDuration(3000); // 3000ms�����ăA�j���[�V��������
		ImageView1.startAnimation(scale); // �A�j���[�V�����K�p
		
		
		
		Textview1.setPadding(100, 300, 100, 100);
		Textview1.setTextColor(Color.BLUE);
		Textview1.setTextSize(25);
        Textview1.setText("�ȂɁA�����́H"); 

        
        //�{�^���N���b�N���̏�������������
        this.btSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
 
	            try {
                    // �C���e���g�쐬
                    Intent intent = new Intent(
                            RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // ACTION_WEB_SEARCH
                    intent.putExtra(
                            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(
                            RecognizerIntent.EXTRA_PROMPT,
                            "�ȂɁA�����̂��ȁ`"); // ���D���ȕ����ɕύX�ł��܂�
                    
                    // �C���e���g���s
                    startActivityForResult(intent, REQUEST_CODE);
                } catch (ActivityNotFoundException e) {
                    // ���̃C���e���g�ɉ����ł���A�N�e�B�r�e�B���C���X�g�[������Ă��Ȃ��ꍇ
                    Toast.makeText(FirstActivity.this,"ActivityNotFoundException", Toast.LENGTH_LONG).show();
                }								
		        
			}//End of public void onClick
			
		});//End of this.btSend.setOnClickListener(new OnClickListener() {        
    }//End of Create


    // �A�N�e�B�r�e�B�I�����ɌĂяo�����
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // �������������C���e���g�ł���Ή�������
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String resultsString = "";
            
            // ���ʕ����񃊃X�g
            ArrayList<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            
            //�ŏI�I�ɕ\������镶����́AresultString�Ɋi�[����Ă���
            for (int i = 0; i< results.size(); i++) {
                // �����ł́A�����񂪕����������ꍇ�Ɍ������Ă��܂�
                resultsString += results.get(i);
            }
            
            if(resultsString.toString().equals("���イ��")){           	
		        MediaPlayer mp = MediaPlayer.create(getBaseContext() ,R.raw.yoxsya01hal_1);
		        mp.start();
				ImageView1.setImageResource(R.drawable.p01501); 
		        RotateAnimation rotate = new RotateAnimation(0, 360, ImageView1.getWidth()/2, ImageView1.getHeight()/2); // img�̒��S�����ɁA0�x����360�x�ɂ����ĉ�]
		        rotate.setDuration(3000); // 3000ms�����ăA�j���[�V��������
		        ImageView1.startAnimation(rotate); // �A�j���[�V�����K�p
				Textview1.setPadding(100, 300, 100, 100);
				Textview1.setTextColor(Color.BLUE);
				Textview1.setTextSize(25);
		        Textview1.setText("��������������"); 		        
            }
		    else if(resultsString.toString().equals("�g�}�g")){           	
			    MediaPlayer mp = MediaPlayer.create(getBaseContext() ,R.raw.kyaa01mayu);
			    mp.start();		
				ImageView1.setImageResource(R.drawable.p01502); 
		        AlphaAnimation alpha = new AlphaAnimation(0.1f,1); // �����x��1����0.1�ɕω�������
		        alpha.setDuration(3000); // 3000ms�����ăA�j���[�V��������
		        ImageView1.startAnimation(alpha); // �A�j���[�V�����K�p
				Textview1.setPadding(100, 300, 100, 100);
				Textview1.setTextColor(Color.BLUE);
				Textview1.setTextSize(25);
		        Textview1.setText("����������������������"); 				    

            }//End of if
    }//End of onActivityResult    
    }
}//End of public class MainActivity extends Activity {
