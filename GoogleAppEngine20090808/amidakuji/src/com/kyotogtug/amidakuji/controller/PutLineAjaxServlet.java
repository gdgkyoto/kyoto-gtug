package com.kyotogtug.amidakuji.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * �A�~�_������Ajax�Ő����������߂̃T�[�u���b�g
 * @author R.Takahashi
 */
@SuppressWarnings("serial")
public final class PutLineAjaxServlet extends HttpServlet {

	/**
	 * POST����<br>
	 * �A�~�_�����ɐ�������
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


		resp.setContentType("text/plain; charset=Windows-31J");
		resp.getWriter().println("OK");
	}

}
