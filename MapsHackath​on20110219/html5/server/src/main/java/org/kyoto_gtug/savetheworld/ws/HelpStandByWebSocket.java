package org.kyoto_gtug.savetheworld.ws;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket;
import org.kyoto_gtug.savetheworld.domain.Help;
import org.kyoto_gtug.savetheworld.domain.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpStandByWebSocket implements WebSocket {
	
	private static Logger logger = LoggerFactory.getLogger(HelpStandByWebSocket.class);
	private static HelperManager manager = HelperManager.getInstance();

	private Outbound outbound;
	private Helper helper;
	
	public void onConnect(Outbound outbound) {
		logger.info("Connected.");
		this.outbound = outbound;
		helper = new Helper(this);
	}

	public void onDisconnect() {
		logger.info("Disconnected");
		manager.removeHelper(helper);
	}

	public void onFragment(boolean arg0, byte arg1, byte[] arg2, int arg3,
			int arg4) {
		logger.info("onFragment");
	}

	/**
	 * 外部から受け取ったデータを処理する
	 */
	public void onMessage(byte arg0, String message) {
		logger.info("onMessage");
		System.out.println("onMessage" + message);
		manager.add(helper);
	}

	public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
		logger.info("onMessage2");
		System.out.println("onMessage2");
	}

	public void notifyHelp(Help help) {
		try {
			outbound.sendMessage("Hey!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
