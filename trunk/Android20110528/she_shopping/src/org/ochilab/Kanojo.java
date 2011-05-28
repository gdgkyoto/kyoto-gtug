package org.ochilab;

import java.util.Random;

public class Kanojo {
	
	int soundData[] = new int[20];

	
	
	//下記の４つの変数はかならず呼び出し元から値を受け取ってください。
	public int msgDataNum = 0;
	MsgData msgData[];
	public int imgDataNum = 0;
	MsgData imgData[];
	
	public void setResParameter(MsgData[] msgdata,MsgData[] imgdata){
		this.msgData=msgdata;
		this.imgData=imgdata;
		
	}
	
	public int selectHerPicture(String situation, String emotion) {

		int num = 50;

		String[] candidateMsg = new String[num];

		// String item = "にんじん";

		int conditionNum = 0;
		
		System.out.println(situation + ":" + emotion);
		for (int i = 0; i < imgDataNum; i++) {
			// 条件が同じメッセージを見つける
			if (imgData[i].emotion.equals(emotion)
					&& imgData[i].situation.equals(situation)) {
				//String result = String.format(msgData[i].message, item);
				// 候補に追加
				candidateMsg[conditionNum] = imgData[i].message;
				conditionNum++;
				// System.out.println(result);
			}
		}

		// 乱数をりようしてどの候補を出力するか決める
		long seed = System.currentTimeMillis(); // 現在時刻のミリ秒
		Random r = new Random(seed);
		int rand = Math.abs(r.nextInt());

		System.out.println(rand + "と" + conditionNum);
		int target = (int) rand % conditionNum;

		return Integer.parseInt(candidateMsg[target]);
	}
	
	
	public String selectHerMessage(String situation, String emotion,
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
	
}
