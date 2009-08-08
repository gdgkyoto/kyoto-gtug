package com.kyotogtug.amidakuji.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyotogtug.amidakuji.logic.AmidaConfig;
import com.kyotogtug.amidakuji.logic.AmidaLogic;
import com.kyotogtug.amidakuji.logic.AmidaLogicFactory;
import com.kyotogtug.amidakuji.logic.AmidaStatus;

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
		resp.setCharacterEncoding("UTF-8");
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

		//�A�~�_���W�b�N���擾���A���݂̏�Ԃ𓾂�
		AmidaLogic logic = AmidaLogicFactory.get(id);
		logic.initialize();
		AmidaStatus status = logic.getStatus();

		//�f�[�^�쐬
		JSONObject obj = new JSONObject();

		//���[�U�[���X�g
		JSONArray users = new JSONArray();
		users.addAll(status.getUserList());

		//URL���X�g
		JSONArray urls = new JSONArray();
		urls.addAll(status.getUrlList());


		//�ÓI���
		obj.put("id", id);
		obj.put("title", "�e�X�g�^�C�g��");
		obj.put("users", users);
		obj.put("images", urls);
		obj.put("length", AmidaConfig.AMIDA_LENGTH);            //int(100)
		obj.put("lastLength", AmidaConfig.AMIDA_LAST_LENGTH);   //int(20)
		obj.put("sycInterval", AmidaConfig.SYNC_INTERVAL);      //long

		//���I���
		obj.put("finished", false);
		obj.put("leftTime", 1000);

		return obj.toString();
	}

}

