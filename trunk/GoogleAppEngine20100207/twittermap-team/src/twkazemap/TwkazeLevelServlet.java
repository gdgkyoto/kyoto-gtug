package twkazemap;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class TwkazeLevelServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		String kazeLevel = TwkazeLevelLogic.getKazeLevel(latitude, longitude);
		resp.setContentType("text/plain");
		resp.getWriter().println(kazeLevel);
	}
}
