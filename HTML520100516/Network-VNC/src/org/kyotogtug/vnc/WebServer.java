package org.kyotogtug.vnc;


import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.kyotogtug.vnc.ui.SystemTrayIcon;
/**
 * Webサーバメインクラス
 * @author kitamura
 *
 */
public class WebServer extends WebSocketServlet{
	
	/** get log instance */
	private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(WebServer.class);
	
	@Override
	  protected WebSocket doWebSocketConnect(HttpServletRequest req, String protocol) {
	    return new VncWebSocket();
	  }

	  public static void main(String[] args) throws Exception {
		  WebServer main = new WebServer();
		  main.start();

		  SystemTrayIcon icon = new SystemTrayIcon();
	  }

	public void start() throws Exception {
		log.debug("サーバスタート");

		Server server = new Server(8090);
		VncServlet servlet = new VncServlet();

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase("./html");

		ServletHolder wsServletHolder = new ServletHolder(servlet);
		wsServletHolder.setInitParameter("bufferSize", Integer.toString(
				8192 * 256, 10));
		ServletContextHandler wsServletContextHandler = new ServletContextHandler();
		wsServletContextHandler.addServlet(wsServletHolder, "/ws/*");

		HandlerList handlerList = new HandlerList();
		handlerList.setHandlers(new Handler[] { resourceHandler,
				wsServletContextHandler });
		server.setHandler(handlerList);
		server.start();
	}

}
