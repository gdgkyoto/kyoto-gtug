package com.kyotogtug.amidakuji.controller;

//import java.util.Date;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * �A�~�_�����̉�ʂ̂��߂̃T�[�u���b�g
 * @author R.Takahashi
 */
@SuppressWarnings("serial")
public class AmidakujiServlet extends HttpServlet {

	//���K�[
	private static final Logger log = Logger.getLogger(AmidakujiServlet.class.getName());

	//�f�t�H���g�̃t�H���[�h��
	private static final String DEF_FORWARD = "/WEB-INF/jsp/amidakuji.jsp";

	/**
	 * GET�����B<br>
	 * �A�~�_�����̐ÓI�ȏ��A���݂̏������N�G�X�g�ϐ��ɃZ�b�g���A<br>
	 * /WEB-INF/jsp/amidakuji.jsp�Ƀt�H���[�h����B
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//�A�~�_����id
		long id;

		//id��long�l�ɕϊ�
		try{
			id=Long.parseLong(req.getParameter("id"));
		}
		catch(NumberFormatException e){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		//��ʂ�`�����߂̏����f�[�^���Z�b�g����i�b��j
		makeData(req,id);

		//�t�H���[�h
		try{
			req.getRequestDispatcher(DEF_FORWARD).forward(req, resp);
		}
		catch(ServletException e){
			log.severe("�t�H���[�h�Ɏ��s");
			log.severe(e.getMessage());
		}
	}


	/**
	 * �f�[�^�쐬�B�b��
	 * @param req
	 * @param id
	*/
	private void makeData(HttpServletRequest req, long id) {

		//�ÓI���
		req.setAttribute("id",id);
		req.setAttribute("title", "�������I�茠�I");
		/*
		req.setAttribute("users", "");
		req.setAttribute("images", "");
		req.setAttribute("endTime", date);
		req.setAttribute("length",  Integer.valueOf(100));
		req.setAttribute("lastLength", Integer.valueOf(20));
		req.setAttribute("sycInterval", Long.valueOf(3000));
		*/

		//���I���
		//req.setAttribute("lines", "");
	}

}
