package org.ochilab;

//プロフィール
public class SheProfile {
	public enum SheType {
		MAID,
		TSUNDERE;
		int GetInt(SheType type)
		{
			switch(type)
			{
			case MAID:
				return 0;
			
			case TSUNDERE:
				return 1;
			}
			return 0;
		}
		
	}

	public SheProfile() {
	}

	public SheProfile(String name, String word, String first, String romaFirst, String veryLike, String like,
			String notLike, String veryNotLike) {
		fullName = name;
		wordFinal = word;
		firstPerson = first;
		romajiFirstPerson = romaFirst;
		veryLikeItem = veryLike;
		likeItem = like;
		notLikeItem = notLike;
		veryNotLikeItem = veryNotLike;
	}
	String fullName; // 名前
	String wordFinal; // 語尾
	String firstPerson; // 一人称
	String romajiFirstPerson; // ローマ字一人称

	int bSize,wSize,hSize;
	// 複数ある場合は,でつなげる
	String hobby; // 趣味
	String ability; // 特技
	String veryLikeItem; // 超好きなもの
	String likeItem; // 好きなもの
	String notLikeItem; // 嫌いなもの
	String veryNotLikeItem; // 超嫌いなもの


};

