package jp.fukui.mapper.form;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SetKeyword extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
		// �L�[���[�h�̎擾
		String setKeyword = req.getParameter("setKeyword");
		
		// �L�[���[�h�i�[
		if(setKeyword == "")
		{
			// �󕶎��G���[
			resp.getWriter().println("No input Keyword!");
		}
		else
		{
			// �L�[���[�h�̑}��
			jp.fukui.mapper.MapperDB.MapperDB db = new jp.fukui.mapper.MapperDB.MapperDB(); 
			db.NewKeyword(setKeyword);
			if(db.SetKeyword(setKeyword))
			{
				// ����
				resp.getWriter().println("Successfull!");
			}
			else
			{
				// �G���[
				resp.getWriter().println("Error!");
			}
		}
		
		resp.getWriter().println("<br /><a href=\"./setKeyword.html\">setKeyword.html</a>");
	
		resp.sendRedirect("/getKeyword");
	}
}
