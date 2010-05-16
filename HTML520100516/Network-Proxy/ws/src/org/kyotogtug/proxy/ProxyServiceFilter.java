package org.kyotogtug.proxy;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kyotogtug.proxy.SocketManager.ProxyClientSocket;

public class ProxyServiceFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hres = (HttpServletResponse) res;

		System.out.println(hreq.getServletPath());

		if (hreq.getServletPath().equals("/")) {

			SocketManager manager = SocketManager.getInstance();
			try {

				Map<String, Object> response = manager.getSocket().forwardRequest(null, null,
						hreq.getRequestURL().toString());
				
				Map <String, String>headers = (Map)response.get("HEADERS");
				
				for(String key: headers.keySet()) {
					hres.setHeader(key, headers.get(key));
				}
				
				hres.getWriter().println(response.get("BODY"));
	
			} catch(Exception e) {
				
				hres.getWriter().println("socket is empty");
				
			}
		} else {

			chain.doFilter(req, res);

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
