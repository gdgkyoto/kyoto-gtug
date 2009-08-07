package com.kyotogtug.amidakuji.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.*;


/**
 * �A�~�_�����̌�����Ajax�Ŏ擾���邽�߂̃T�[�u���b�g
 * @author R.Takahashi
 */
@SuppressWarnings("serial")
public final class AmidaDataAjaxServlet extends HttpServlet {

	//���K�[
	private static final Logger log = Logger.getLogger(AmidaDataAjaxServlet.class.getName());

	/**
	 * GET�����B<br>
	 * �A�~�_�����̌��݂̏������N�G�X�g�ϐ��ɃZ�b�g���A<br>
	 * JSON�`���ŃX�g���[���ɏ����o���B
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

		//�o�̓f�[�^���쐬����
		String outData = makeData(req,id);

		//�o�͂��s��
		resp.setContentType("text/plain"); //�f�o�b�O�p
		//resp.setContentType("application/json"); //�{�ԗp
		resp.setCharacterEncoding("Windows-31J");
		resp.getWriter().println(outData);

		log.info("ok");
	}


	/**
	 * �f�[�^�I�u�W�F�N�g���쐬����
	 * @param req - ���N�G�X�g�ϐ�
	 * @Param id - ���݂�����ID
	 * @return str - JSON�I�u�W�F�N�g�̕�����
	 */
	private String makeData(HttpServletRequest req, long id){
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("title", "�������I�茠�I");
		return obj.toString();
	}

}
