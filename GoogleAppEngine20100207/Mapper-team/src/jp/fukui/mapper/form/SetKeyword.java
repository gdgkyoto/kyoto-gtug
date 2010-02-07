package jp.fukui.mapper.form;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SetKeyword extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
		// キーワードの取得
		String setKeyword = req.getParameter("setKeyword");
		
		// キーワード格納
		if(setKeyword == "")
		{
			// 空文字エラー
			resp.getWriter().println("No input Keyword!");
		}
		else
		{
			// キーワードの挿入
			jp.fukui.mapper.MapperDB.MapperDB db = new jp.fukui.mapper.MapperDB.MapperDB(); 
			if(db.SetKeyword(setKeyword))
			{
				// 成功
				resp.getWriter().println("Successfull!");
			}
			else
			{
				// エラー
				resp.getWriter().println("Error!");
			}
		}
		
		resp.getWriter().println("<br /><a href=\"./setKeyword.html\">setKeyword.html</a>");
	
		resp.sendRedirect("/getKeyword");
	}
}
