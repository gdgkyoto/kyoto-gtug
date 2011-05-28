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

	int msgDataNum = 0;
	MsgData msgData[] = new MsgData[100];

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// GUI部品との対応づけ
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
	 * 各モードでのGUI変更処理
	 * 
	 * @param modeID
	 */
	public void setMode(int modeID) {
		this.modeID = modeID;
		switch (modeID) {
		case 0: {
			showHerMessage("さあ、いっしょにおかいものしましょ！");
			showPicture(6);
			playSound(1);
			execBtn.setText("買い物をはじめる");
			cancelBtn.setText("やめる");
			break;
		}
		case 1: {// 何を買う？
			showHerMessage("何を買うの？");// ここはランダムに変えたい
			showPicture(2);
			playSound(4);
			execBtn.setText("入力");
			cancelBtn.setText("買い物をやめる");

			break;
		}
		case 2: {
			showHerMessage("値段はいくら？");
			showPicture(3);
			playSound(4);
			execBtn.setText("入力");
			cancelBtn.setText("戻る");
			break;
		}
		case 3: {// いくら？
			showHerMessage("いくつかう？");
			showPicture(4);
			playSound(8);
			execBtn.setText("入力");
			cancelBtn.setText("戻る");
			break;
		}

		case 4: {// これでいい？
			showHerMessage("まあまあじゃない？");// ここは判定しだい
			showPicture(5);
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

	private String selectHerMessage(String situation, String emotion,
			String item) {

		int num = 50;

		String[] candidateMsg = new String[num];

		// String item = "にんじん";

		int conditionNum = 0;
		
		System.out.println(situation + ":" + emotion);
		for (int i = 0; i < msgDataNum; i++) {
			// 条件が同じメッセージを見つける
			if (msgData[i].emotion.equals(emotion)
					&& msgData[i].situation.equals(situation)) {
				//String result = String.format(msgData[i].message, item);
				// 候補に追加
				candidateMsg[conditionNum] = msgData[i].message;
				conditionNum++;
				// System.out.println(result);
				break;
			}
		}

		// 乱数をりようしてどの候補を出力するか決める
		long seed = System.currentTimeMillis(); // 現在時刻のミリ秒
		Random r = new Random(seed);
		int rand = Math.abs(r.nextInt());

		System.out.println(rand + "と" + conditionNum);
		int target = (int) rand % conditionNum;

		return (String.format(candidateMsg[target], item));
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