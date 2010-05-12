package org.kyotogtug.vnc;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.websocket.WebSocket;

public class VncWebSocket implements WebSocket{


	private Outbound outbound ;

//	public VncWebSocket( VncServlet vncServlet ){
//		this.vncServlet = vncServlet;
//	}

	@Override
	public void onConnect(Outbound outbound) {
		System.out.println("onConnect!");
		this.outbound = outbound;
		try {
			outbound.sendMessage((byte)0, "Connected!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onDisconnect() {
		System.out.println("onDisconnect!");
	}

	@Override
	public void onMessage(byte arg0, String data) {
		System.out.println("onMessage!");
		try {
			outbound.sendMessage((byte)0, "Received!:"+data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
		System.out.println("onMessage2!");
	}

}
