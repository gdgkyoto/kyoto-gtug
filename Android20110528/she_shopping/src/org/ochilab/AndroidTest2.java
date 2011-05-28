



package org.ochilab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class AndroidTest2 extends Activity {
	ImageView img;
	Button execBtn, cancelBtn;
	MediaPlayer mplayer;
	TextView herMessage, myMessage;
	int modeID = 0;

	int picData[] = new int[10];
	int soundData[] = new int[20];

	int msgDataNum = 0;
	MsgData msgData[] = new MsgData[100];

	private int REQUEST_CODE = 0;  //M.hashimoto
	private String item_from_voice;  //M.hashimoto
	private String price_from_voice; //M.hashimoto
	private String itemCount_from_voice; //M.hashimoto
	
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// GUI���i�Ƃ̑Ή��Â�
		herMessage = (TextView) findViewById(R.id.textView1);
		myMessage = (TextView) findViewById(R.id.textView0);
		img = (ImageView) findViewById(R.id.imageView1);
		execBtn = (Button) findViewById(R.id.button1);
		cancelBtn = (Button) findViewById(R.id.button2);

		String str;
		// img.setImageResource(R.drawable.p01501);
		// showPicture(2);

		initMsgFile();
		initSoundData();
		initPicData();

		setBtnEvent();

		showMyMessage(selectHerMessage("normal", "back", ""));
		setMode(0);

	}

	public void showHerMessage(String msg) {
		herMessage.setText(msg);

	}

	public void showMyMessage(String msg) {
		myMessage.setText(msg);

	}

	public void playSound(int id) {
		mplayer = MediaPlayer.create(this, soundData[id]);
		mplayer.start();

	}

	public void showPicture(int id) {
		img.setImageResource(picData[id]);

	}

	/**
	 * �e���[�h�ł�GUI�ύX����
	 * 
	 * @param modeID
	 */
	public void setMode(int modeID) {
		this.modeID = modeID;
		switch (modeID) {
		case 0: {
			showHerMessage("�����A��������ɂ��������̂��܂���I");
			showPicture(6);
			playSound(1);
			execBtn.setText("���������͂��߂�");
			cancelBtn.setText("��߂�");
			break;
		}
		case 1: {// ���𔃂��H
			showHerMessage("���𔃂��́H");// �����̓����_���ɕς�����
			showPicture(2);
			playSound(4);
			execBtn.setText("����");
			cancelBtn.setText("����������߂�");

			break;
		}
		case 2: {
			showHerMessage("�l�i�͂�����H");
			showPicture(3);
			playSound(4);
			execBtn.setText("����");
			cancelBtn.setText("�߂�");
			break;
		}
		case 3: {// ������H
			showHerMessage("���������H");
			showPicture(4);
			playSound(8);
			execBtn.setText("����");
			cancelBtn.setText("�߂�");
			break;
		}

		case 4: {// ����ł����H
			showHerMessage("�܂��܂�����Ȃ��H");// �����͔��肵����
			showPicture(5);
			execBtn.setText("����ł���");
			cancelBtn.setText("��蒼��");
			break;
		}
		case 5: {// ����ł����H
			showHerMessage("����΂����ˁH");// �����͔��肵����
			showPicture(6);
			playSound(5);
			execBtn.setEnabled(false);
			cancelBtn.setEnabled(false);
			break;
		}
		}

	}

	/**
	 * ���s�{�^�����������ꍇ�̏���
	 */
	private void execBtnClick() {

		// ������modeID�͌��݂̃��[�h�ł��B

		switch (modeID) {
		case 0: {

			//�����炵���E�B���h�E���J���ꍇ�̃T���v��
			//Intent test = new Intent(this, testActibity.class);
			//startActivity(test);

			 //�J�n����
			 setMode(1);
			 

			 
			 
			break;
		}
		case 1: {//

			// �i���̓��͏���
			getInputfromVoice(101);//�������́@
			setMode(2);
			break;
		}
		case 2: {//
			// �l�i�̓��͏���
			getInputfromVoice(201);//�������́@
			setMode(3);
			break;
		}
		case 3: {//
			// ���̓��͏���
			getInputfromVoice(301);//�������́@
			setMode(4);
			
			 Toast.makeText(AndroidTest2.this,item_from_voice+"/"+price_from_voice+"/"+itemCount_from_voice, Toast.LENGTH_LONG).show();
			
			break;
		}

		case 4: {//
			// �����OK������

			// �w���������X�g�ցB�B�B

			// �ӂ����эw�����[�h��
			setMode(1);
			break;
		}
		}

	}

	/**
	 * �L�����Z�����̃{�^�����������ꍇ�̏���
	 */
	private void cancelBtnClick() {
		switch (modeID) {

		case 1: {// ���𔃂��H
			setMode(5);
			break;
		}
		case 2: {// ����ł����H
			setMode(1);
			break;
		}
		case 3: {// ������H
			setMode(2);
			break;
		}

		case 4: {// ����ł����H
			setMode(3);
			break;
		}
		}
	}

	public void getInputfromVoice(int requestCode){
		
		///M.hashimoto ��������
        try {
            // �C���e���g�쐬
            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // ACTION_WEB_SEARCH
            intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(
                    RecognizerIntent.EXTRA_PROMPT,
                    "VoiceRecognitionTest"); // ���D���ȕ����ɕύX�ł��܂�
            
            REQUEST_CODE = requestCode ;
            // �C���e���g���s
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // ���̃C���e���g�ɉ����ł���A�N�e�B�r�e�B���C���X�g�[������Ă��Ȃ��ꍇ
            Toast.makeText(AndroidTest2.this,"ActivityNotFoundException", Toast.LENGTH_LONG).show();
        }	
		///M.hashimoto ��������
		
		
		
		
	}
	
	
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
            
            if(requestCode == 101){
                Toast.makeText(AndroidTest2.this,"�i��"+resultsString, Toast.LENGTH_LONG).show();
           	    item_from_voice = resultsString;  //M.hashimoto
            }
            else if(requestCode == 201){
                Toast.makeText(AndroidTest2.this,"��"+resultsString, Toast.LENGTH_LONG).show();
                price_from_voice = resultsString;  //M.hashimoto
            }           
            else if(requestCode == 301){
                Toast.makeText(AndroidTest2.this,"�l�i"+resultsString, Toast.LENGTH_LONG).show();
           	    itemCount_from_voice = resultsString;  //M.hashimoto
            }                       
        
            
        }//End of if
    }//End of onActivityResult    	
	
	
	
	
	
	private String selectHerMessage(String situation, String emotion,
			String item) {

		int num = 50;

		String[] candidateMsg = new String[num];

		// String item = "�ɂ񂶂�";

		int conditionNum = 0;
		
		System.out.println(situation + ":" + emotion);
		for (int i = 0; i < msgDataNum; i++) {
			// �������������b�Z�[�W��������
			if (msgData[i].emotion.equals(emotion)
					&& msgData[i].situation.equals(situation)) {
				//String result = String.format(msgData[i].message, item);
				// ���ɒǉ�
				candidateMsg[conditionNum] = msgData[i].message;
				conditionNum++;
				// System.out.println(result);
				break;
			}
		}

		// ��������悤���Ăǂ̌����o�͂��邩���߂�
		long seed = System.currentTimeMillis(); // ���ݎ����̃~���b
		Random r = new Random(seed);
		int rand = Math.abs(r.nextInt());

		System.out.println(rand + "��" + conditionNum);
		int target = (int) rand % conditionNum;

		return (String.format(candidateMsg[target], item));
	}

	private void initMsgFile() {
		try {
			Resources Resource = this.getResources();
			InputStreamReader in = new InputStreamReader(
					Resource.openRawResource(R.raw.message));
			BufferedReader br = new BufferedReader(in);

			// �ŏI�s�܂œǂݍ���
			String line = "";
			int i = 0;
			while ((line = br.readLine()) != null) {

				// 1�s���f�[�^�̗v�f�ɕ���
				String[] format = line.split(",");
				msgData[i] = new MsgData();
				msgData[i].situation = format[1];
				msgData[i].emotion = format[2];
				msgData[i].message = format[3];
				i++;
			}
			System.out.println(i + "�̃f�[�^���i�[");
			msgDataNum = i;
			br.close();

		} catch (FileNotFoundException e) {
			// File�I�u�W�F�N�g�������̗�O�ߑ�
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReader�I�u�W�F�N�g�̃N���[�Y���̗�O�ߑ�
			e.printStackTrace();
		}

	}

	/**
	 * �C�x���g���X�i�[�Ƃ̑Ή������͂����ɏ���
	 */
	private void setBtnEvent() {
		execBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				execBtnClick();
			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelBtnClick();
			}
		});
	}

	class MsgData {
		String situation;
		String emotion;
		String message;

	}

	private void initSoundData() {
		soundData[1] = R.raw.irasyai;
		soundData[2] = R.raw.kyaa;
		soundData[3] = R.raw.saitei;
		soundData[4] = R.raw.madamada01mayu;
		soundData[5] = R.raw.yoxsya;
		soundData[6] = R.raw.mousukosi;
		soundData[7] = R.raw.madamadane01kawamoto;
		soundData[8] = R.raw.keikoku01mayu;
		soundData[9] = R.raw.syuryo01kawamoto;
		soundData[9] = R.raw.moukae;

	}

	private void initPicData() {

		picData[1] = R.drawable.p01302;
		picData[2] = R.drawable.p01501;
		picData[3] = R.drawable.p01502;
		picData[4] = R.drawable.p01601;
		picData[5] = R.drawable.p01602;
		picData[6] = R.drawable.p019021;

	}

}