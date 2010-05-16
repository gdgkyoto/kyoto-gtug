package org.kyotogtug.proxy;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.jetty.websocket.WebSocket;

public class SocketManager {

	LinkedList<ProxyClientSocket> clientSockets = new LinkedList<ProxyClientSocket>();

	private static SocketManager manager;

	static {
		manager = new SocketManager();
	}

	private SocketManager() {}

	// ソケットを返す
	public synchronized ProxyClientSocket getSocket() {
		while(clientSockets.size() > 0) {
			ProxyClientSocket socket = clientSockets.remove(0);
			if(socket.isConnecting()) {
				clientSockets.add(socket);
				return socket;
			}
		}
		
		throw new RuntimeException("connecting socket is empty");
	}

	public ProxyClientSocket createSocket() {
		ProxyClientSocket socket = new ProxyClientSocket();
		clientSockets.add(socket);
		return socket;
	}
	
	public void sendMessageAll(byte frame , String data) {
		for (ProxyClientSocket client : SocketManager.getInstance().clientSockets) {
			try {
				client.bound.sendMessage(frame, data);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public static SocketManager getInstance() {
		return manager;
	}

	static class ProxyClientSocket implements WebSocket {

		private Outbound bound;

		private boolean connected = false;

		@Override
		public void onConnect(Outbound arg0) {
			this.bound = arg0;
			this.connected = true;
			onMessage((byte) 0, "WebSocket is success!!!");
		}

		@Override
		public void onDisconnect() {
			this.connected = false;
		}

		@Override
		public void onMessage(byte frame, String data) {
			try {
				System.out.println("receive message:" + data);
				this.bound.sendMessage(frame, data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
			
		}

		public boolean isConnecting() {
			return connected;
		}

		public String forwardRequest(Map headers, String bodyText) {

			// bound.sendMessage("");
			return "";
		}

		private String hashToJson(Map<String, String> headers) {
			for (Map.Entry<String, String> obj : headers.entrySet()) {

			}
			return "";
		}
	}
}
