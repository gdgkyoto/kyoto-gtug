package jp.mucchin.listtest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends ListActivity {
	/** Called when the activity is first created. */

	private Button InputNameButton;
	private Button inputPriceButton;
	private Button inputItemCountButton;
	private Button inputPriceSumButton;
	
	private TextView InputNameTextView;
	private TextView InputPriceTextView;
	private TextView InputPriceSumTextView;
	private TextView InputItemCountTextView;
	private TextView BudgetTextValue;

	private ImageView ImageView1;
    private Drawable d;
    
	//private static final int REQUEST_CODE = 0;
	private static int REQUEST_CODE = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		this.InputNameButton = (Button) findViewById(R.id.InputNameButton);
		this.inputPriceButton = (Button) findViewById(R.id.InputPriceButton);
		this.inputItemCountButton = (Button) findViewById(R.id.InputItemCountButton);	
//		this.inputPriceSumButton = (Button) findViewById(R.id.InputPriceSumButton);
		this.InputNameTextView = (TextView) findViewById(R.id.InputName);
		this.InputPriceTextView = (TextView) findViewById(R.id.InputPrice);
		this.InputItemCountTextView = (TextView) findViewById(R.id.InputItemCount);
		this.InputPriceSumTextView = (TextView) findViewById(R.id.InputPriceSum);

		this.ImageView1 = (ImageView)findViewById(R.id.ImageView1);
		this.BudgetTextValue = (TextView) findViewById(R.id.budget);	

        //Intent���擾���� 
        Intent intent = getIntent();
        
        //Intent�ɕۑ����ꂽ�f�[�^�����o��
        String data = intent.getStringExtra("budget");
        this.BudgetTextValue.setText(data);
		
		// �i�����̓{�^��
		this.InputNameButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					// �C���e���g�쐬
					Intent intent = new Intent(
							RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // ACTION_WEB_SEARCH
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
							RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
							"VoiceRecognitionTest"); // ���D���ȕ����ɕύX�ł��܂�
					REQUEST_CODE = 1 ;

					// �C���e���g���s
					startActivityForResult(intent, REQUEST_CODE);
				} catch (ActivityNotFoundException e) {
					// ���̃C���e���g�ɉ����ł���A�N�e�B�r�e�B���C���X�g�[������Ă��Ȃ��ꍇ
					Toast.makeText(SecondActivity.this,
							"ActivityNotFoundException", Toast.LENGTH_LONG)
							.show();
				}
			}
		});// End of this.InputNameButton.setOnClickListener(new
			// OnClickListener() {

		// �l�i���̓{�^��
		this.inputPriceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					// �C���e���g�쐬
					Intent intent = new Intent(
							RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // ACTION_WEB_SEARCH
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
							RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
							"VoiceRecognitionTest2"); // ���D���ȕ����ɕύX�ł��܂�
					REQUEST_CODE = 2 ;

					// �C���e���g���s
					startActivityForResult(intent, REQUEST_CODE);
				} catch (ActivityNotFoundException e) {
					// ���̃C���e���g�ɉ����ł���A�N�e�B�r�e�B���C���X�g�[������Ă��Ȃ��ꍇ
					Toast.makeText(SecondActivity.this,
							"ActivityNotFoundException", Toast.LENGTH_LONG)
							.show();
				}
			}
		});// End of this.InputNameButton.setOnClickListener(new
			// OnClickListener() {

		// �����̓{�^��
		this.inputItemCountButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					// �C���e���g�쐬
					Intent intent = new Intent(
							RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // ACTION_WEB_SEARCH
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
							RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
							"VoiceRecognitionTest2"); // ���D���ȕ����ɕύX�ł��܂�
					REQUEST_CODE = 3 ;

					// �C���e���g���s
					startActivityForResult(intent, REQUEST_CODE);
				} catch (ActivityNotFoundException e) {
					// ���̃C���e���g�ɉ����ł���A�N�e�B�r�e�B���C���X�g�[������Ă��Ȃ��ꍇ
					Toast.makeText(SecondActivity.this,
							"ActivityNotFoundException", Toast.LENGTH_LONG)
							.show();
				}
			}
		});// End of this.InputNameButton.setOnClickListener(new
			// OnClickListener() {
		
	}// End of public void onCreate(Bundle savedInstanceState) {

	// �A�N�e�B�r�e�B�I�����ɌĂяo�����
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		
		// �������������C���e���g�ł���Ή�������
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			String resultsString = "";

			// ���ʕ����񃊃X�g
			ArrayList<String> results = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

			// �ŏI�I�ɕ\������镶����́AresultString�Ɋi�[����Ă���
			for (int i = 0; i < results.size(); i++) {
				// �����ł́A�����񂪕����������ꍇ�Ɍ������Ă��܂�
				resultsString += results.get(i);
			}
			//�i���Ȃ�
			if(requestCode == 1 ){
				//�������͂����i�����C���v�b�g�{�b�N�X�ɕ\��
				InputNameTextView.setText(resultsString);
				//�����ŉ摜��\��
				ImageView1.setImageResource(R.drawable.uju); 
			    //getImage();
			//���z�Ȃ�
			}else if(requestCode == 2){
				InputPriceTextView.setText(resultsString);
			//���Ȃ�
			}else if(requestCode == 3){
				InputItemCountTextView.setText(resultsString);
				
			}

		}//End of if (requestCode == REQUEST_CODE 
	}//End of onActivityResult

	public void getImage(){
        try{
            URL url = new URL("http://www.oudoiro.com/Plala_Photo/0809/080924.jpg");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            InputStream in = http.getInputStream();
            d = Drawable.createFromStream(in, "a");//�R�R���֘A,Drawable���g��
            in.close();
        }catch(Exception e){

        }

        d.setBounds(150, 150, 143, 150);
        ImageView1.setImageDrawable(d);

	}//End of getImage
	
	
	
	
	
	
}// End of public class ListTestActivity extends ListActivity 