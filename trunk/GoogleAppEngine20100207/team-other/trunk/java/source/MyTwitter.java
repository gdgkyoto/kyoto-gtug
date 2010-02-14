package com.ts0604.twitterapi;

import twitter4j.Twitter;

public class MyTwitter {

	public static boolean basicAuth(String name, String pass)
	{
		String[] names = new String[]{name};
		String[] passes = new String[]{pass};

		TngtAccounts account = TngtAccounts.getInstance(names, passes);
		if(account.getTwitter() != null)
			return true;

		return false;

	}

	public static String getList(String name, String listName)
	{
		TwitList twitList = new TwitList(name, listName);	//リスト生成
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i = 0; i < twitList.getMemberCount() - 1; i++)
		{
			//ユーザー表示名
			sb.append("{\"screen_name\":\"");
			sb.append(twitList.getScreenName(i));
			sb.append("\"},");
		}
		sb.append("{\"screen_name\":\"");
		sb.append(twitList.getScreenName(twitList.getMemberCount() - 1));
		sb.append("\"}]");

		return sb.toString();
	}

	//リストのタイムライン取得
	public static String getListTimeLine(String name, String listName, int page)
	{
		return getListTimeLineSub(name, listName, page);
	}

	//リストのタイムライン取得(最初のページ)
	public static String getListTimeLine(String name, String listName)
	{
		return getListTimeLineSub(name, listName, 1);
	}

	private static String getListTimeLineSub(String name, String listName, int page)
	{
		TwitList twitList = new TwitList(name, listName);	//リスト生成
		ListTimeLine timeLine = twitList.getTimeLine(page);
		StringBuffer sb = new StringBuffer();
		Tubuyaki tubuyaki;
		sb.append("[");
		for(int i = 0; i < timeLine.length() - 1; i++)
		{
			tubuyaki = timeLine.get(i);
			//ユーザー表示名
			sb.append("{\"screen_name\":\"");
			tubuyaki.screenName = tubuyaki.screenName.replace('"', '”');
			sb.append(tubuyaki.screenName);
			sb.append("\",");
			//つぶやき
			sb.append("\"text\":\"");
			tubuyaki.text = tubuyaki.text.replace('"', '”');
			sb.append(tubuyaki.text);
			sb.append("\"},");
		}
		tubuyaki = timeLine.get(timeLine.length() - 1);
		//ユーザー表示名
		sb.append("{\"screen_name\":\"");
		tubuyaki.screenName = tubuyaki.screenName.replace('"', '”');
		sb.append(tubuyaki.screenName);
		sb.append("\",");
		//つぶやき
		sb.append("\"text\":\"");
		tubuyaki.text = tubuyaki.text.replace('"', '”');
		sb.append(tubuyaki.text);
		sb.append("\"}]");

		return sb.toString();
	}

}
