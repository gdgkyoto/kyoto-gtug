package com.kyotogtug.amidakuji.controller;

//import java.util.Date;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kyotogtug.amidakuji.logic.AmidaConfig;
import com.kyotogtug.amidakuji.logic.AmidaLogic;
import com.kyotogtug.amidakuji.logic.AmidaLogicFactory;
import com.kyotogtug.amidakuji.logic.AmidaStatus;

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

		//アミダロジックを取得し、現在の状態を得る
		AmidaLogic logic = AmidaLogicFactory.get(id);
		logic.initialize();
		AmidaStatus status = logic.getStatus();

		//静的情報
		req.setAttribute("id",status.getId());                           //long
		req.setAttribute("title", status.getTitle());                    //String
		req.setAttribute("users", status.getUserList());                 //List<String>
		req.setAttribute("images", status.getUrlList());                 //List<String>
		req.setAttribute("endTime", status.getEndTime());                //Date
		req.setAttribute("length", AmidaConfig.AMIDA_LENGTH);            //int(100)
		req.setAttribute("lastLength", AmidaConfig.AMIDA_LAST_LENGTH);   //int(20)
		req.setAttribute("sycInterval", AmidaConfig.SYNC_INTERVAL);      //long

		//動的情報
		req.setAttribute("lines", status.getLines());                        //List<List<Object>>
		req.setAttribute("currentTime", status.getCurrentTime());
		req.setAttribute("currentPosition",status.getCurrentPosition());     //List<List<Object>>
		req.setAttribute("currentPositionY", status.getCurrentPositionY());  //int
		req.setAttribute("finished", status.isFinished());                   //boolean
		req.setAttribute("leftTime", status.getLeftTime());                  //Date
	}

}
