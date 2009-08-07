package com.kyotogtug.amidakuji.controller;

//import java.util.Date;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * アミダくじの画面のためのサーブレット
 * @author R.Takahashi
 */
@SuppressWarnings("serial")
public class AmidakujiServlet extends HttpServlet {

	//ロガー
	private static final Logger log = Logger.getLogger(AmidakujiServlet.class.getName());

	//デフォルトのフォワード先
	private static final String DEF_FORWARD = "/WEB-INF/jsp/amidakuji.jsp";

	/**
	 * GET処理。<br>
	 * アミダくじの静的な情報、現在の情報をリクエスト変数にセットし、<br>
	 * /WEB-INF/jsp/amidakuji.jspにフォワードする。
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//アミダくじid
		long id;

		//idをlong値に変換
		try{
			id=Long.parseLong(req.getParameter("id"));
		}
		catch(NumberFormatException e){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		//画面を描くための初期データをセットする（暫定）
		makeData(req,id);

		//フォワード
		try{
			req.getRequestDispatcher(DEF_FORWARD).forward(req, resp);
		}
		catch(ServletException e){
			log.severe("フォワードに失敗");
			log.severe(e.getMessage());
		}
	}


	/**
	 * データ作成。暫定
	 * @param req
	 * @param id
	*/
	private void makeData(HttpServletRequest req, long id) {

		//静的情報
		req.setAttribute("id",id);
		req.setAttribute("title", "居酒屋選手権！");
		/*
		req.setAttribute("users", "");
		req.setAttribute("images", "");
		req.setAttribute("endTime", date);
		req.setAttribute("length",  Integer.valueOf(100));
		req.setAttribute("lastLength", Integer.valueOf(20));
		req.setAttribute("sycInterval", Long.valueOf(3000));
		*/

		//動的情報
		//req.setAttribute("lines", "");
	}

}
