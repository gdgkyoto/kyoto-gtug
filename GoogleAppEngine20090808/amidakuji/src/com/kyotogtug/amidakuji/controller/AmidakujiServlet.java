package com.kyotogtug.amidakuji.controller;

//import java.util.Date;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kyotogtug.amidakuji.logic.AmidaConfig;
import com.kyotogtug.amidakuji.logic.AmidaLogic;
import com.kyotogtug.amidakuji.logic.AmidaLogicFactory;
import com.kyotogtug.amidakuji.logic.AmidaStatus;

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

		//�A�~�_���W�b�N���擾���A���݂̏�Ԃ𓾂�
		AmidaLogic logic = AmidaLogicFactory.get(id);
		logic.initialize();
		AmidaStatus status = logic.getStatus();

		//�ÓI���
		req.setAttribute("id",status.getId());
		req.setAttribute("title", status.getTitle());
		//req.setAttribute("users", "");
		//req.setAttribute("images", "");
		req.setAttribute("endTime", status.getEndTime());
		req.setAttribute("length", AmidaConfig.AMIDA_LENGTH);
		req.setAttribute("lastLength", AmidaConfig.AMIDA_LAST_LENGTH);
		req.setAttribute("sycInterval", AmidaConfig.SYNC_INTERVAL);

		//���I���
		//req.setAttribute("lines", "");
		req.setAttribute("currentTime", status.getCurrentTime());
		req.setAttribute("currentPositionX", status.getCurrentsPositionX());
		req.setAttribute("currentPositionY", status.getCurrentPositionY());
		req.setAttribute("finished", status.isFinished());
		req.setAttribute("leftTime", status.getLeftTime());
	}

}
