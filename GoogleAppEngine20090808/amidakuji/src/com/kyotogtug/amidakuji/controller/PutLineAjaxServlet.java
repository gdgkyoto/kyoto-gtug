package com.kyotogtug.amidakuji.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyotogtug.amidakuji.logic.AmidaLogic;
import com.kyotogtug.amidakuji.logic.AmidaLogicFactory;


/**
 * アミダくじにAjaxで線を引くためのサーブレット
 * @author R.Takahashi
 */
@SuppressWarnings("serial")
public final class PutLineAjaxServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doPost(req,resp);
	}

	/**
	 * POST処理<br>
	 * アミダくじに線を引く
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//アミダくじid
		long id;
		int x;
		int y;

		//idをlong値に変換
		try{
			x = Integer.parseInt(req.getParameter("x"));
			y = Integer.parseInt(req.getParameter("y"));
			id=Long.parseLong(req.getParameter("id"));
		}
		catch(Exception e){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}


		//ロジック生成
		AmidaLogic logic= AmidaLogicFactory.get(id);

		//ヘッダ情報セット
		resp.setContentType("text/plain; charset=UTF-8");

		boolean ret = false;
		try{
			logic.initialize();
			ret =logic.insertLine(x, y);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			resp.getWriter().println("ERROR");
			return;
		}
		if(!ret){
			resp.getWriter().println("ERROR");
			return;
		}

		resp.getWriter().println("OK");
	}

}
