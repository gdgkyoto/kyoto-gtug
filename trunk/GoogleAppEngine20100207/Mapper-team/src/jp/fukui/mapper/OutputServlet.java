package jp.fukui.mapper;

import java.io.IOException;

import javax.servlet.http.*;

import jp.fukui.mapper.xml.MapperXML;

@SuppressWarnings("serial")
public class OutputServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String keyword = req.getParameter("keyword");
		
		MapperXML xml = new MapperXML(keyword);
		String mapperXml = xml.CreateXML();
		
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().println(mapperXml);

	}
}
