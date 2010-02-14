package com.ts0604.twitterapi;

//つぶやき
class Tubuyaki {
	public int tubuyakiID = 0;	//つぶやきID
	public int twitterID = 0;	//発言者のTwitterID
	public String screenName = "";	//発言者の表示名
	public String text = "";	//つぶやき
	public String dateString = "";	//つぶやき日時

	public Tubuyaki(int tubuyakiID, int twitterID, String screenName, String text, String dateString)
	{
		this.tubuyakiID = tubuyakiID;
		this.twitterID = twitterID;
		this.screenName = screenName;
		this.text = text;
		this.dateString = dateString;
	}
}
