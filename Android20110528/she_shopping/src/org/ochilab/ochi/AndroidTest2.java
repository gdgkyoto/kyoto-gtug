package org.ochilab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class AndroidTest2 extends Activity {
	ImageView img;
	Button execBtn, cancelBtn;
	MediaPlayer mplayer;
	TextView herMessage, myMessage;
	int modeID = 0;

	int picData[] = new int[10];
	int soundData[] = new int[20];

	/*ここの変数はKanojoに必要です*/
	int msgDataNum = 0;
	MsgData msgData[] = new MsgData[100];
	int imgDataNum = 0;
	MsgData imgData[] = new MsgData[100];
	
	Kanojo her;
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//ここから必須項目です
		
			// GUI部品との対応づけ
		herMessage = (TextView) findViewById(R.id.textView1);
		myMessage = (TextView) findViewById(R.id.textView0);
		img = (ImageView) findViewById(R.id.imageView1);
		execBtn = (Button) findViewById(R.id.button1);
		cancelBtn = (Button) findViewById(R.id.button2);
		initMsgFile();
		initPicFile();
		her.setResParameter(msgData,imgData);
		her.msgDataNum=this.msgDataNum;
		her.imgDataNum=this.imgDataNum;				
		initSoundData();
		setBtnEvent();	
		
		//オープニング画像　
		img.setImageResource(R.drawable.opening);

		//ここまで必須項目////////////////////////////////
		
		//いかの３行はデモ用
		her = new Kanojo();
		showMyMessage(her.selectHerMessage("normal", "back", ""));
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
	 * 各モードでのGUI変更処理
	 * 
	 * @param modeID
	 */
	public void setMode(int modeID) {
		this.modeID = modeID;
		switch (modeID) {
		case 0: {
			showHerMessage("さあ、いっしょにおかいものしましょ！");
			
			playSound(1);
			execBtn.setText("買い物をはじめる");
			cancelBtn.setText("やめる");
			break;
		}
		case 1: {// 何を買う？
			showHerMessage("何を買うの？");// ここはランダムに変えたい
			showPicture(her.selectHerPicture("buy", "bad"));
			playSound(4);
			execBtn.setText("入力");
			cancelBtn.setText("買い物をやめる");

			break;
		}
		case 2: {
			showHerMessage("値段はいくら？");
			showPicture(her.selectHerPicture("buy", "bad"));
			playSound(4);
			execBtn.setText("入力");
			cancelBtn.setText("戻る");
			break;
		}
		case 3: {// いくら？
			showHerMessage("いくつかう？");
			showPicture(her.selectHerPicture("buy", "bad"));
			playSound(8);
			execBtn.setText("入力");
			cancelBtn.setText("戻る");
			break;
		}

		case 4: {// これでいい？
			showHerMessage("まあまあじゃない？");// ここは判定しだい
			showPicture(her.selectHerPicture("buy", "good"));
			execBtn.setText("これでいく");
			cancelBtn.setText("やり直す");
			break;
		}
		case 5: {// これでいい？
			showHerMessage("がんばったね？");// ここは判定しだい
			showPicture(6);
			playSound(5);
			execBtn.setEnabled(false);
			cancelBtn.setEnabled(false);
			break;
		}
		}

	}

	/**
	 * 実行ボタンを押した場合の処理
	 */
	private void execBtnClick() {

		// ここのmodeIDは現在のモードです。

		switch (modeID) {
		case 0: {

			//あたらしくウィンドウを開く場合のサンプル
			//Intent test = new Intent(this, testActibity.class);
			//startActivity(test);

			 //開始処理
			 setMode(1);
			break;
		}
		case 1: {//

			// 品物の入力処理

			setMode(2);
			break;
		}
		case 2: {//
			// 値段の入力処理

			setMode(3);
			break;
		}
		case 3: {//
			// 個数の入力処理

			setMode(4);
			break;
		}

		case 4: {//
			// 判定にOKをした

			// 購入情報をリストへ。。。

			// ふたたび購入モードへ
			setMode(1);
			break;
		}
		}

	}

	/**
	 * キャンセル側のボタンを押した場合の処理
	 */
	private void cancelBtnClick() {
		switch (modeID) {

		case 1: {// 何を買う？
			setMode(5);
			break;
		}
		case 2: {// これでいい？
			setMode(1);
			break;
		}
		case 3: {// いくら？
			setMode(2);
			break;
		}

		case 4: {// これでいい？
			setMode(3);
			break;
		}
		}
	}

	private void initMsgFile() {
		try {
			Resources Resource = this.getResources();
			InputStreamReader in = new InputStreamReader(
					Resource.openRawResource(R.raw.message));
			BufferedReader br = new BufferedReader(in);
			// 最終行まで読み込む
			String line = "";
			int i = 0;
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				String[] format = line.split(",");
				msgData[i] = new MsgData();
				msgData[i].situation = format[1];
				msgData[i].emotion = format[2];
				msgData[i].message = format[3];
				i++;
			}
			System.out.println(i + "個のデータを格納");
			msgDataNum = i;
			br.close();

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}

	}

	/**
	 * イベントリスナーとの対応処理はここに書く
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

	private void initPicFile() {
		picData[0] = R.drawable.p00;
		picData[1] = R.drawable.p01;
		picData[2] = R.drawable.p02;
		picData[3] = R.drawable.p03;
		picData[4] = R.drawable.p04;
		picData[5] = R.drawable.p05;
		picData[6] = R.drawable.p06;
		picData[7] = R.drawable.p07;
		try {
			Resources Resource = this.getResources();
			InputStreamReader in = new InputStreamReader(
					Resource.openRawResource(R.raw.facestatetable));
			BufferedReader br = new BufferedReader(in);

			// 最終行まで読み込む
			String line = "";
			int i = 0;
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				String[] format = line.split(",");
				imgData[i] = new MsgData();
				imgData[i].situation = format[1];
				imgData[i].emotion = format[2];
				imgData[i].message = format[3];
				i++;
			}
			System.out.println(i + "個のデータを格納");
			imgDataNum = i;
			br.close();

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}
		

	}

}