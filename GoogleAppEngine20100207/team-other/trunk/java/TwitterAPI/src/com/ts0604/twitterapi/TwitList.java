package com.ts0604.twitterapi;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;


public class TwitList {

	private String targetID = "";	//取得対象TwitterID、もしくはメールアドレス
	private String targetListName = "";	//取得対象リスト名
	private int targetListID = 0;	//取得対象リストのID
	private int memberCount = 0;	//リストのメンバー数
	private URI uri = null;	//URI

	private String[] userNames = null;	//ユーザー名
	private int[] userIDs = null;	//ユーザーID
	private String[] userScreenNames = null;	//ユーザー表示名

	private static final int USER_COUNT_IN_CURSOR = 20;	//1カーソルに含まれるユーザー数

	private boolean result = false;	//リスト取得に成功
	private String message = "";	//エラーメッセージ？
	private static final String NOT_FOUND_LIST = "対象のリストが見つかりません";
	private static final String FAILURE_GET_LISTS = "リストのリストの取得に失敗しました";
	private static final String FAILURE_GET_MEMBERS = "リストのメンバーの取得に失敗しました";

	private Twitter twitter = null;	//API本体


	public TwitList(String targetID, String targetListName)
	{
		this.targetID = targetID;
		this.targetListName = targetListName;

		twitter = TngtAccounts.getInstance().getTwitter();	//本体の取得

		PagableResponseList<UserList> lists;
		UserList list;
		try {
			lists = twitter.getUserLists(targetID, -1);
			while((list = this.findTargetList(targetListName, lists)) == null);	//対象のリストを探索;
			if(list != null)
			{
				this.targetListID = list.getId();
				this.uri = list.getURI();
				this.getUsers(list);//メンバー情報の取得
			}
			else
			{
				this.message = NOT_FOUND_LIST;
				result = false;
			}
		} catch (TwitterException e1) {
			this.message = FAILURE_GET_LISTS;
			result = false;
			e1.printStackTrace();
		}
	}

	//対象のリストを検索
	private UserList findTargetList(String targetListName, PagableResponseList<UserList> lists)
	{
		UserList list;
		//対象のリストを検索
		for(int i = 0; i < lists.size(); i++)
		{
			list = lists.get(i);
			if(list.getName().equals(targetListName))
			{
				return list;
			}
		}

		return null;
	}

	//メンバー情報取得
	private void getUsers(UserList list)
	{
		this.memberCount = list.getMemberCount();	//リストのメンバー数
		long cursor = 0L;
		long cursorMax = 0L;
		cursorMax = memberCount / USER_COUNT_IN_CURSOR + 1;	//取得したユーザー数　÷　1カーソルで取得できる最大ユーザー数 + 1

		this.userNames = new String[memberCount];
		this.userIDs = new int[memberCount];
		this.userScreenNames = new String[memberCount];
		int index = 0;
		for(int i = 0; i < cursorMax; i++)
		{
			cursor = i;
			if(cursor == 0) cursor = -1;

			PagableResponseList<User> listUsers;
			try {
				listUsers = twitter.getUserListMembers(targetID, targetListID, cursor);//リストのメンバー取得
				for(int j = 0; j < listUsers.size(); j++)
				{
					index = i * USER_COUNT_IN_CURSOR + j;
					this.userNames[index] = listUsers.get(j).getName();	//ユーザー名格納
					this.userIDs[index] = listUsers.get(j).getId();	//ID格納
					this.userScreenNames[index] = listUsers.get(j).getScreenName();//ユーザー表示名格納
				}
			} catch (TwitterException e) {
				this.message = FAILURE_GET_MEMBERS;
				result = false;
				e.printStackTrace();
			}
		}
	}

	ListTimeLine listTimeLine = null;

	//リストのタイムライン取得
	public ListTimeLine getTimeLine(int max)
	{
		try {
			ResponseList<Status> statuses = twitter.getUserListStatuses(this.targetID,
																			this.targetListID,
																			new Paging(1, max));
			this.listTimeLine = new ListTimeLine(max);
			Tubuyaki tubuyaki;
			Status status;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			dateFormat.setTimeZone(TimeZone.getTimeZone("JST"));
			for(int i = 0; i < statuses.size(); i++)
			{
				status = statuses.get(i);
				tubuyaki = new Tubuyaki((int)status.getId(),
										(int)status.getUser().getId(),
										status.getUser().getScreenName(),
										status.getText(),
										dateFormat.format(status.getCreatedAt()));
				listTimeLine.addTubuyaki(tubuyaki);
			}

		} catch (TwitterException e) {
			e.printStackTrace();
			return null;
		}
		return listTimeLine;
	}

	//メンバー数取得
	public int getMemberCount() { return this.memberCount; }

	//ユーザー名取得
	public String getName(int index) { return this.userNames[index]; }

	//リスト名取得
	public String getListName() { return this.targetListName; }

	//リスト所有者名の取得
	public String getName() { return this.targetID; }

	//ユーザーID取得
	public int getID(int index) { return this.userIDs[index]; }

	//URI取得
	public URI getURI() { return this.uri; }

	//ユーザー表示名取得
	public String getScreenName(int index) { return this.userScreenNames[index]; }

	//エラーメッセージ取得
	public String getErrMessage() { return this.message; }
}
