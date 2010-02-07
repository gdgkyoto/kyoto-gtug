package jp.fukui.mapper.form;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;

@SuppressWarnings("serial")
public class GetKeyword extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		// キーワードリストの取得
		jp.fukui.mapper.MapperDB.MapperDB db = new jp.fukui.mapper.MapperDB.MapperDB(); 
		ArrayList<String> keywordList = db.GetKeywordList();

		// キーワードをリクエストへ
		req.setAttribute("keywordList", keywordList);
		
		// ページ遷移
		RequestDispatcher disp = req.getRequestDispatcher("/setKeyword.jsp");
		try
		{
			disp.forward(req, resp);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		}
	}
}
