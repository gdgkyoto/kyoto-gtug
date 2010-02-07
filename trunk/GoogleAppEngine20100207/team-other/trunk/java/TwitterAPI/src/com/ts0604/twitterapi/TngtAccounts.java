package com.ts0604.twitterapi;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TngtAccounts {
	private static TngtAccounts instance = null;
	private static final int accountNum = 10;	//事前に用意したつながったー用アカウント数
	private String[] tunagattaIDs = new String[accountNum];	//つながったー用アカウントのID
	private String[] tunagattaPasses = new String[accountNum];	//つながったー用アカウントのパスワード
	private int[] remainings = new int[accountNum];	//各アカウントのAPI残り使用回数
	private int nowUsing = 0;	//現在使用中のアカウントのインデックス

	private Twitter twitter = null;	//API本体

	//プライベートコンストラクタ
	private TngtAccounts(String[] tunagattaIDs, String[] tunagattaPasses)
	{
		this.tunagattaIDs = tunagattaIDs;
		this.tunagattaPasses = tunagattaPasses;
		this.createTwitter();
	}

	//プライベートコンストラクタ
	private TngtAccounts()
	{
		for(Integer i = 0; i < accountNum; i++)
		{
			this.tunagattaIDs[i] = "tngt00" + i.toString();
			this.tunagattaPasses[i] = "oth10fri";
			this.remainings[i] = 150;
		}

		this.createTwitter();
	}

	//API本体の生成
	private void createTwitter()
	{
		this.twitter = new TwitterFactory().getInstance(tunagattaIDs[nowUsing], tunagattaPasses[nowUsing]);
		try {
			//残り使用回数取得
			this.remainings[nowUsing] = this.twitter.getRateLimitStatus().getRemainingHits();
			if(this.remainings[nowUsing] < 10)
			{
				this.nowUsing = (this.nowUsing + 1) % accountNum;
				this.createTwitter();
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	//インスタンス取得
	public static TngtAccounts getInstance(String[] tunagattaIDs, String[] tunagattaPasses)
	{

		if(instance == null)
			return new TngtAccounts(tunagattaIDs, tunagattaPasses);

		return instance;
	}

	//インスタンス取得
	public static TngtAccounts getInstance()
	{
		if(instance == null)
			return new TngtAccounts();

		return instance;
	}

	//TwitterAPI取得
	public Twitter getTwitter()
	{
		return this.twitter;
	}

	//残り使用回数
	public int getRemainings()
	{
		int ret = 0;
		for(int i = 0; i < accountNum; i++)
		{
			ret += this.remainings[i];
		}
		return ret;
	}


}
