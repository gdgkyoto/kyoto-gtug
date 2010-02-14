package com.ts0604.twitterapi;

import java.util.Date;

//リストのタイムライン
class ListTimeLine {
	private Tubuyaki[] tubuyakiLists = null;	//つぶやきのリスト
	private int index = 0;	//現在のインデックス
	private static final int countInPage = 20;	//1ページ(タイムライン)に含まれるつぶやき数

	//コンストラクタ
	public ListTimeLine()
	{
		this.tubuyakiLists = new Tubuyaki[countInPage];
	}

	public void addTubuyaki(Tubuyaki tubuyaki)
	{
		this.tubuyakiLists[index] = tubuyaki;
		index++;
	}

	public int length()
	{
		return index;
	}

	public Tubuyaki get(int index)
	{
		return this.tubuyakiLists[index];
	}

}