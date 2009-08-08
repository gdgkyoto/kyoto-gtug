package com.kyotogtug.amidakuji.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyotogtug.amidakuji.logic.AmidaConfig;
import com.kyotogtug.amidakuji.logic.AmidaLogic;
import com.kyotogtug.amidakuji.logic.AmidaLogicFactory;
import com.kyotogtug.amidakuji.logic.AmidaStatus;

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
		resp.setCharacterEncoding("UTF-8");
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

		//アミダロジックを取得し、現在の状態を得る
		AmidaLogic logic = AmidaLogicFactory.get(id);
		logic.initialize();
		AmidaStatus status = logic.getStatus();

		//データ作成
		JSONObject obj = new JSONObject();

		//ユーザーリスト
		JSONArray users = new JSONArray();
		users.addAll(status.getUserList());

		//URLリスト
		JSONArray urls = new JSONArray();
		urls.addAll(status.getUrlList());


		//静的情報
		obj.put("id", id);
		obj.put("title", "テストタイトル");
		obj.put("users", users);
		obj.put("images", urls);
		obj.put("length", AmidaConfig.AMIDA_LENGTH);            //int(100)
		obj.put("lastLength", AmidaConfig.AMIDA_LAST_LENGTH);   //int(20)
		obj.put("sycInterval", AmidaConfig.SYNC_INTERVAL);      //long

		//動的情報
		obj.put("lines", makeArray(status.getLines()));
		obj.put("currentPosition", makeArray(status.getCurrentPosition()));
		obj.put("currentPositionY", status.getCurrentPositionY());
		obj.put("finished", status.isFinished());
		obj.put("leftTime", status.getLeftTime());

		return obj.toString();
	}

	JSONArray makeArray(List<List<Object>> list){
		JSONArray ret = new JSONArray();
		for(List<Object> l : list){
			JSONArray a = new JSONArray();
			a.addAll(l);
			ret.add(a);
		}
		return ret;
	}
}

