package com.kyotogtug.amidakuji.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.*;


/**
 * アミダくじの現情報をAjaxで取得するためのサーブレット
 * @author R.Takahashi
 */
@SuppressWarnings("serial")
public final class AmidaDataAjaxServlet extends HttpServlet {

	//ロガー
	private static final Logger log = Logger.getLogger(AmidaDataAjaxServlet.class.getName());

	/**
	 * GET処理。<br>
	 * アミダくじの現在の情報をリクエスト変数にセットし、<br>
	 * JSON形式でストリームに書き出す。
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

		//出力データを作成する
		String outData = makeData(req,id);

		//出力を行う
		resp.setContentType("text/plain"); //デバッグ用
		//resp.setContentType("application/json"); //本番用
		resp.setCharacterEncoding("Windows-31J");
		resp.getWriter().println(outData);

		log.info("ok");
	}


	/**
	 * データオブジェクトを作成する
	 * @param req - リクエスト変数
	 * @Param id - あみだくじID
	 * @return str - JSONオブジェクトの文字列
	 */
	private String makeData(HttpServletRequest req, long id){
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("title", "居酒屋選手権！");
		return obj.toString();
	}

}
