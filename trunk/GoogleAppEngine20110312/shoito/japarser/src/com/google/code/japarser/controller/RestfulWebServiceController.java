package com.google.code.japarser.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.com.google.io.base.IORuntimeException;

public abstract class RestfulWebServiceController extends Controller {
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_XML = "application/xml";
	
	public static final int SC_OK = HttpServletResponse.SC_OK;
	public static final int SC_CREATED = HttpServletResponse.SC_CREATED;
	public static final int SC_NO_CONTENT = HttpServletResponse.SC_NO_CONTENT;

	public static final int SC_BAD_REQUEST = HttpServletResponse.SC_BAD_REQUEST;
	public static final int SC_NOT_FOUND = HttpServletResponse.SC_NOT_FOUND;
	public static final int SC_CONFLICT = HttpServletResponse.SC_CONFLICT;

	public static final int SC_INTERNAL_SERVER_ERROR = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	public static final int SC_NOT_IMPLEMENTED = HttpServletResponse.SC_NOT_IMPLEMENTED;

	@Override
	public Navigation run() {
		if (isGet()) {
			doGet();
			return null;
		} else if (isPost()) {
			doPost();
			return null;
		} else if (isPut()) {
			doPut();
			return null;
		} else if (isDelete()) {
			doDelete();
			return null;
		}
		
		try {
			response.sendError(SC_NOT_IMPLEMENTED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void doGet() {
		try {
			response.sendError(SC_NOT_IMPLEMENTED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void doPost() {
		try {
			response.sendError(SC_NOT_IMPLEMENTED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void doPut() {
		try {
			response.sendError(SC_NOT_IMPLEMENTED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void doDelete() {
		try {
			response.sendError(SC_NOT_IMPLEMENTED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public boolean isNull(String str) {
		if (asString(str) == null) {
			try {
				response.sendError(SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public void responseWriter(String text) {
		responseWriter(text, null, null);
	}

	public void responseWriter(String text, String contentType) {
		responseWriter(text, contentType, null);
	}

	public void responseWriter(String text, String contentType, String encoding) {
		responseWriter(SC_OK, text, contentType, encoding);
	}

	public void responseWriter(int status) {
		responseWriter(status, null, null, null);
	}

	public void responseWriter(int status, String text) {
		responseWriter(status, text, null, null);
	}

	public void responseWriter(int status, String text, String contentType) {
		responseWriter(status, text, contentType, null);
	}

	public void responseWriter(int status, String text, String contentType,	String encoding) {
		if (status == 0) {
			status = SC_OK;
		}

		if (!(status == SC_OK || status == SC_CREATED || status == SC_NO_CONTENT)) {
			try {
				response.sendError(status);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		if (contentType == null) {
			contentType = "text/plain";
		}

		if (encoding == null) {
			encoding = request.getCharacterEncoding();
			if (encoding == null) {
				encoding = "utf-8";
			}
		}

		response.setStatus(status);
		response.setContentType(contentType + "; charset=" + encoding);

		try {
			PrintWriter out = null;
			try {
				out = new PrintWriter(new OutputStreamWriter(
						response.getOutputStream(), encoding));
				out.print(text);
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}
}
