package primer;

import java.io.IOException;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import java.nio.charset.*;

@SuppressWarnings("serial")
public class GuestbookServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		// googleサジェスト取得
		InputStream in = new URL("http://google.com/complete/search?output=toolbar&q=Japan").openStream();
		
		// 文字コード変換
		Charset charset = Charset.forName("UTF-8");		
		InputStreamReader reader = new InputStreamReader(in,charset);
		
		// stream → 文字列
		char[] chr = new char[0];
		int lenght = reader.read(chr);
		
		for (char c : chr) 
		{
			resp.getWriter().println(c);
		}
	
		in.close();
	}
}
