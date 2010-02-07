package com.ts0604.twitterapi;

import java.util.Date;

//リストのタイムライン
public class ListTimeLine {
	private Tubuyaki[] tubuyakiLists = null;	//つぶやきのリスト
	private int index = 0;	//現在のインデックス

	//コンストラクタ
	public ListTimeLine(int tubuyakiMax)
	{
		this.tubuyakiLists = new Tubuyaki[tubuyakiMax];
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