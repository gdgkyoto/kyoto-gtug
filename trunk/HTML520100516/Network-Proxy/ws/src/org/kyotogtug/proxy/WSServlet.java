package org.kyotogtug.proxy;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class WSServlet extends WebSocketServlet {

	private static final long serialVersionUID = 12345L;

	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest req,
			String protocol) {
		SocketManager manager = SocketManager.getInstance();
		return manager.createSocket();
	}
}
