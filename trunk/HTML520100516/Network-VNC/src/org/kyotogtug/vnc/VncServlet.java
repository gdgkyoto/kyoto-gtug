package org.kyotogtug.vnc;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class VncServlet extends WebSocketServlet{

	private final Set<VncWebSocket> clients = new CopyOnWriteArraySet<VncWebSocket>();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		return new VncWebSocket();
	}

}
