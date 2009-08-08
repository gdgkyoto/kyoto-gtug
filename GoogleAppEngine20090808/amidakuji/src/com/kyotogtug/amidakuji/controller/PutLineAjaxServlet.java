package com.kyotogtug.amidakuji.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyotogtug.amidakuji.logic.AmidaLogic;
import com.kyotogtug.amidakuji.logic.AmidaLogicFactory;


/**
 * �A�~�_������Ajax�Ő����������߂̃T�[�u���b�g
 * @author R.Takahashi
 */
@SuppressWarnings("serial")
public final class PutLineAjaxServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doPost(req,resp);
	}

	/**
	 * POST����<br>
	 * �A�~�_�����ɐ�������
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//�A�~�_����id
		long id;
		int x;
		int y;

		//id��long�l�ɕϊ�
		try{
			x = Integer.parseInt(req.getParameter("x"));
			y = Integer.parseInt(req.getParameter("y"));
			id=Long.parseLong(req.getParameter("id"));
		}
		catch(Exception e){
			System.out.println("parse error");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		System.out.println("parse ok);


		//���W�b�N����
		AmidaLogic logic= AmidaLogicFactory.get(id);

		//�w�b�_���Z�b�g
		resp.setContentType("text/plain; charset=UTF-8");

		boolean ret = false;
		try{
			logic.initialize();
			ret =logic.insertLine(x, y);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			resp.getWriter().println("ERROR");
			return;
		}
		if(!ret){
			resp.getWriter().println("ERROR");
			return;
		}

		resp.getWriter().println("OK");
	}

}
