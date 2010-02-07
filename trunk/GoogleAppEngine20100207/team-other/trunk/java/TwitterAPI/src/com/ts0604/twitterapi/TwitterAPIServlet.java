package com.ts0604.twitterapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import twitter4j.TwitterException;

@SuppressWarnings("serial")
public class TwitterAPIServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
	    resp.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();
	    out.println("<html>" +
	        "<head>" +
	        "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />" +
	        "</head>" +
	        "<body>");


	    //リスト表示
		TwitList twitList = new TwitList("ts0604", "test0202");	//リスト生成
		StringBuffer sb = new StringBuffer();
		Integer id;
		id = twitList.getMemberCount();	//メンバー数
		out.println("ts0604のリスト\n\nメンバー数 " + id.toString());
		Integer remainings = TngtAccounts.getInstance().getRemainings();
		out.println("\n残り使用回数 " + remainings.toString() + "\n");
		for(int i = 0; i < twitList.getMemberCount(); i++)
		{
			id = twitList.getID(i);
			out.println(id.toString() + " " + twitList.getScreenName(i));
		}

		//リストのタイムライン表示
		ListTimeLine listTimeLine = twitList.getTimeLine(200);	//タイムライン取得 200件
		Tubuyaki tubuyaki;
		out.println("\n\nts0604のリストのタイムライン\n");
		for(int i = 0; i < listTimeLine.length(); i++)
		{
			tubuyaki = listTimeLine.get(i);
			id = tubuyaki.tubuyakiID;
			out.println(id.toString() + " " + tubuyaki.screenName + " " + tubuyaki.dateString + tubuyaki.text);
		}
		out.println("</body></html>");

	}
}
