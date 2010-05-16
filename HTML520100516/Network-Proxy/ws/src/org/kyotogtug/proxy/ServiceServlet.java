package org.kyotogtug.proxy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceServlet extends HttpServlet {
	
	private int counter = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//super.doGet(req, resp);
		SocketManager manager = SocketManager.getInstance();
		
		String url = "http://google.co.jp/";
		String request = url + ++counter;
		manager.getSocket().onMessage((byte)0, request);
		
		String response = url + counter;
		resp.getWriter().println(response);
	}
}
