package com.kyotogtug.amidakuji;

import java.io.IOException;
import javax.servlet.http.*;
import java.io.PrintWriter;

import java.util.Date;
import java.util.logging.Logger;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Key;
import com.kyotogtug.amidakuji.dao.IAmidakujiDao;
import com.kyotogtug.amidakuji.dao.ILineDao;
import com.kyotogtug.amidakuji.dao.memoryimpl.AmidakujiDaoImpl;
import com.kyotogtug.amidakuji.dao.memoryimpl.LineDaoImpl;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;
import com.kyotogtug.amidakuji.jdo.entity.Line;

/**
 * @author Hashimoto
 *
 */

@SuppressWarnings("serial")
public class MakeAmidaServlet extends HttpServlet {
	//public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	//	resp.setContentType("text/plain");
	//	resp.getWriter().println("(doGet) Hello, world");
	//}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//resp.setContentType("text/plain");
		resp.setContentType("text/html; charset=UTF-8");
		//resp.setDateHeader("Expires", 0);
		//resp.setHeader("Progma", "no-cache");
		//resp.setHeader("Cache-Control", "no-cache");

		//resp.getWriter().println("Hello, world");

		//フォームに入力された値の取得

		//request.setCharacterEncoding("Windows-31J");
		req.setCharacterEncoding("UTF-8");

		//アミダのタイトル
		String amidaTitle01 = req.getParameter("amidaTitle");

		//参加者のEMail
		String playerEmail01 = req.getParameter("playerEmail1");
		String playerEmail02 = req.getParameter("playerEmail2");
		String playerEmail03 = req.getParameter("playerEmail3");
		String playerEmail04 = req.getParameter("playerEmail4"); playerEmail04 = "a@gmail.com";
		String playerEmail05 = req.getParameter("playerEmail5"); playerEmail05 = "a@gmail.com";
		String playerEmail06 = req.getParameter("playerEmail6"); playerEmail06 = "a@gmail.com";

		//ゴールの選択肢
		String goal01 = req.getParameter("goal1");
		String goal02 = req.getParameter("goal2");
		String goal03 = req.getParameter("goal3");
		String goal04 = req.getParameter("goal4"); goal04 = goal01;
		String goal05 = req.getParameter("goal5"); goal05 = goal02;
		String goal06 = req.getParameter("goal6"); goal06 = goal03;

		//終了日時
		String finishDateTime01 = req.getParameter("finishDateTime");

		//アミダ初期データの作成

		/* Amidakujiを登録 */
		List<String> mailList = new ArrayList<String>();
		//mailList.add( "hirohiro.tornade@gmail.com" );
		mailList.add( playerEmail01 );
		mailList.add( playerEmail02 );
		mailList.add( playerEmail03 );
//		mailList.add( playerEmail04 );
//		mailList.add( playerEmail05 );
//		mailList.add( playerEmail06 );

		List<String> urlList = new ArrayList<String>();
		//urlList.add( "http://tbn2.google.com/images?q=tbn:yWyWojyPlyH1YM:http://katourosagazou1.up.seesaa.net/image/B2C3C6A3A5EDA1BCA5B5A1A1B2E8C1FCA1A1CAC9BBE6A1A1A5ADA5EAA5F3A5D3A5D0A5ECA5C3A5B8.jpg" );
		//urlList.add( "http://tbn2.google.com/images?q=tbn:lst6tn8Vfpjs8M:http://www.namisaru.net/photo/20080730.jpg" );
		//urlList.add( "http://tbn0.google.com/images?q=tbn:PwTIquqhOIQkiM:http://kengo.preston-net.com/archives/firefox_wallpaper.jpg" );
		urlList.add( goal01 );
		urlList.add( goal02 );
		urlList.add( goal03 );
//		urlList.add( goal04 );
//		urlList.add( goal05 );
//		urlList.add( goal06 );

		Amidakuji amidakuji = new Amidakuji();
		amidakuji.setImageUrlList(urlList);
		amidakuji.setMailAddressList(mailList);
		amidakuji.setLength(100);
		//amidakuji.setEndTime(toDate("2009/08/08 17:30:00"));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND, (int)com.kyotogtug.amidakuji.logic.AmidaConfig.TOTAL_TIME);
		//amidakuji.setEndTime(toDate(finishDateTime01));
		amidakuji.setEndTime(cal.getTime());
		//amidakuji.setTitle( "壁紙選手権！" );
		amidakuji.setTitle( amidaTitle01 );

		IAmidakujiDao amidakujiDao = new AmidakujiDaoImpl();
		amidakujiDao.insertAmidakuji(amidakuji);


		//アミダ作成完了の表示
		//resp.sendRedirect("makeAmidaDone.jsp");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println ("<head>");
        out.println("<title>Make Amida done</title>");
        out.println ("</head>");
        out.println  ("<body>");
        out.println("<p>新しいアミダくじを作成しました</p>");
        out.println("<p>参加者リスト<br />");
        out.println(playerEmail01 + "<br />");
        out.println(playerEmail02 + "<br />");
        out.println(playerEmail03 + "<br />");
        out.println(playerEmail04 + "<br />");
        out.println(playerEmail05 + "<br />");
        out.println(playerEmail06 + "<br />");
        out.println("</p>");
        out.println("<p>ゴール一覧<br />");
        out.println(goal01 + "<br />");
        out.println(goal02 + "<br />");
        out.println(goal03 + "<br />");
        out.println(goal04 + "<br />");
        out.println(goal05 + "<br />");
        out.println(goal06 + "<br />");
        out.println("</p>");
        out.println("<p>ゴール到着時刻<br />");
        out.println(finishDateTime01 + "<br />");
        out.println("</p>");
        out.println("<p>参加者に招待メールを発送しました</p>");
        out.println("</body>");
        out.println("</html>");
	}

	private Date toDate(String string) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
