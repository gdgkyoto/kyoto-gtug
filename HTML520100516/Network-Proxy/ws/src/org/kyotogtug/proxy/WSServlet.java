package org.kyotogtug.proxy;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class WSServlet extends WebSocketServlet {

	private static final long serialVersionUID = 12345L;

	private final Set<WSSocket> clients = new CopyOnWriteArraySet<WSSocket>();

	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest req, String protocol) {
		return new WSSocket();
	}

	
	class WSSocket implements WebSocket {

		Outbound bound;

		@Override
		public void onConnect(Outbound arg0) {
			this.bound = arg0;
			clients.add(this);
			onMessage((byte) 0, "WebSocket is success!!!");
		}
		
		@Override
		public void onDisconnect() {
			clients.remove(this);
		}

		@Override
		public void onMessage(byte frame, String data) {
			
			for (WSSocket client : clients) {
				try {
					client.bound.sendMessage(frame, data);
				} catch (IOException e) {
					
				}
			}

		}

		@Override
		public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

		}

	}
}
