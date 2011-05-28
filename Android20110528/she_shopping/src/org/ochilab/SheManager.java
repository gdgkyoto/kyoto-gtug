package org.ochilab;

import java.util.Random;

import org.ochilab.SheProfile;

public class SheManager {
	private static SheManager instance = new SheManager();

	private SheManager() {
		m_SheResponse = new SheResponse();
	}

	public enum SheFaceType { VERY_HAPPY, HAPPY, NORMAL, UNHAPPY, VERY_UNHAPPY }

	
	// レスポンス
	private class SheResponse {
		SheFaceType faceNumber; // 表情
		String speak; // しゃべり言葉
		String romaji; // ローマ字
		String situation;
		String emotion;
	};

	private SheProfile m_SheProfile = null;
	private SheResponse m_SheResponse;

	
	// インスタンス取得メソッド
	public static SheManager getInstance() {
		return instance;
	}

	public void SetSheProfile(SheProfile.SheType type) {
		
		if(type == SheProfile.SheType.MAID)
		{
			m_SheProfile = new SheProfile(
					"メイドさん",		//名前
					"です",			//語尾
					"私",			//一人称
					"watashi",		//ローマ字一人称
					"カレー，ケーキ，クレープ，マンゴー",	// 超好きなもの
					"パスタ，コーヒー，カップヌードル",	// 好きなもの
					"みかん，梅干し，パン",			// 嫌いなもの
					"にんじん，ピーマン，ゴーヤ，しいたけ"	// 超嫌いなもの
					);
		}
		else if(type == SheProfile.SheType.TSUNDERE)
		{
			m_SheProfile = new SheProfile(
					"ツンデレ子さん",		//名前
					"よ",				//語尾
					"あたし",				//一人称
					"atashi",			//ローマ字一人称
					"プリン，いちご，メロンパン",	// 超好きなもの
					"チョコレート，コーヒー，たい焼き",	// 好きなもの
					"ポテトチップス，カップヌードル，せんべい",			// 嫌いなもの
					"チーカマ，カルパス，するめ"	// 超嫌いなもの
					);
		}
	}
	
	// しゃべった言葉を取得
	public String GetSpeak() {
		return m_SheResponse.speak;
	}

	// しゃべったローマ字を取得
	public String GetRomaji() {
		return m_SheResponse.romaji;
	}
	
	// 表情を取得
	public SheFaceType GetFace() {
		return m_SheResponse.faceNumber;
	}
	public String GetSituation() {
		return m_SheResponse.situation;
	}
	public String GetEmotion() {
		return m_SheResponse.emotion;
	}
	
	
	
	// 味見
	public Boolean IsTasting(String Name, String item) {
		//複数チェック
		if(item.indexOf(",") != -1){
			String[] likes = item.split(",");
			for (String like : likes) {
				if (like == Name) {
					return true;
				}
			}
		}
		//単数チェック
		else{
			if (item == Name) {
				return true;
			}
		}
		return false;
	}
	// 品物名からランダム
	private int analyzeItem(String item){
        char c;
        int patternNum=6;
        int code=0;
        int result=0;
        int length = item.length();
        for (int i=0;i<length;i++){
            c= item.charAt(i);
            code += (int )c;
        }
        System.out.println(code);
        result= code%patternNum;
        System.out.println(result);
        return result;
	}
	// レスポンスを得るための情報セット
	public void SetResponseInfomation(String Name, String price, String itemCount) {
		Random r = new Random();
		
		//好き嫌いは決め打ち
		if(IsTasting(Name, m_SheProfile.veryLikeItem))
		{
			m_SheResponse.situation = "buy";
			m_SheResponse.emotion = "best";
		}
		else if(IsTasting(Name, m_SheProfile.likeItem))
		{
			m_SheResponse.situation = "buy";
			m_SheResponse.emotion = "good";
		}
		else if(IsTasting(Name, m_SheProfile.veryNotLikeItem))
		{
			m_SheResponse.situation = "buy";
			m_SheResponse.emotion = "worst";
		}
		else if(IsTasting(Name, m_SheProfile.notLikeItem))
		{
			m_SheResponse.situation = "buy";
			m_SheResponse.emotion = "bad";
		}
		//好きでも嫌いでもないものはランダム
		else
		{
			int result = analyzeItem(Name);
			
			if(result > 0)
			{
				m_SheResponse.faceNumber = SheFaceType.NORMAL;
				m_SheResponse.speak = "私は普通です";
			}
		}
		
		//
		
		/*			

		
		Random r = new Random();
		
		//好き嫌いは決め打ち
		if(IsTasting(Name, m_SheProfile.veryLikeItem))
		{
			int n = r.nextInt(5);
			
			m_SheResponse.situation = "buy";
			m_SheResponse.emotion = "best";

			
			m_SheResponse.faceNumber = SheFaceType.VERY_HAPPY;
			if(n == 0){
				m_SheResponse.speak = "やった！" + Name + "！もう一個買おうよ！！";
				m_SheResponse.romaji = "yatta!" + Name + "! mouikko kaouyo!!";
			}
			else if(n == 1)
			{
				m_SheResponse.speak = "美味しそ" + m_SheProfile.firstPerson +  "それ大好きなんだ！！";
				m_SheResponse.speak = "oishiso-" + m_SheProfile.romajiFirstPerson +  "sore daisukinanda!!";
			}
		}
		else if(IsTasting(Name, m_SheProfile.likeItem))
		{
			m_SheResponse.faceNumber = SheFaceType.HAPPY;
			m_SheResponse.speak = "私は好きです";
		}
		else if(IsTasting(Name, m_SheProfile.veryNotLikeItem))
		{
			m_SheResponse.faceNumber = SheFaceType.VERY_UNHAPPY;
			m_SheResponse.speak = "私は大嫌いです";
		}
		else if(IsTasting(Name, m_SheProfile.notLikeItem))
		{
			m_SheResponse.faceNumber = SheFaceType.UNHAPPY;
			m_SheResponse.speak = "私は嫌いです";
		}
		//好きでも嫌いでもないものはランダム
		else
		{
			int result = analyzeItem(Name);
			
			if(result > 0)
			{
				m_SheResponse.faceNumber = SheFaceType.NORMAL;
				m_SheResponse.speak = "私は普通です";
			}
		}
*/			
		
	}
}
