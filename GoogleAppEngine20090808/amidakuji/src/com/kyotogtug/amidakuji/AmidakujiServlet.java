package com.kyotogtug.amidakuji;

//import java.util.Date;
//import java.util.logging.Logger;
import java.io.IOException;
//import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * É_É~Å[
 */
@SuppressWarnings("serial")
public class AmidakujiServlet extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.getOutputStream().println("amida");
	}



}
