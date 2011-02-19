package org.kyoto_gtug.savetheworld.ws;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

@SuppressWarnings("serial")
public class HelpStandByWebSocketServlet extends WebSocketServlet {

	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		return new HelpStandByWebSocket();
	}

}
