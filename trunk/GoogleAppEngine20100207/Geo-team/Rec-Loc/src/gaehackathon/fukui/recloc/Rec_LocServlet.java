package gaehackathon.fukui.recloc;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Rec_LocServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
