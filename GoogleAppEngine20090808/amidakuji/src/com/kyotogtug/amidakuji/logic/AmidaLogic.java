package com.kyotogtug.amidakuji.logic;

import java.util.*;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.users.User;


/**
 * �A�~�_�����̃��W�b�N����������N���X�ł��B<br>
 * ���̃N���X�̃C���X�^���X�́AAmidaLogicFactory�N���X��get()���\�b�h�Ŏ擾���Ă��������B
 * @author R.Takahashi
 */
public final class AmidaLogic {

	//���K�[
	private static final Logger log = Logger.getLogger(AmidaLogic.class.getName());




	//-------------------- �ϐ� --------------------

	//�������t���O
	private boolean initialized = false;

	//���݂�����ID
	private long id;

	//���݂������̌Œ���
	private AmidaFixedStatus fixedStatus = null;

	//���݂������̉Ϗ��
	private AmidaVariableStatus variableStatus = null;

	//�f�o�b�O�p
	private Date endTimeForDebug;


	//-------------------- ���\�b�h --------------------

	/**
	 * �R���X�g���N�^
	 * @param id - ���݂�����ID
	 */
	AmidaLogic(long id){
		this.id = id;

		//�f�o�b�O�p
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND, (int)AmidaConfig.TOTAL_TIME);
		endTimeForDebug = cal.getTime();
	}

	/**
	 * ID���擾���܂��B
	 * @return id - ���݂�����ID
	 */
	long getId(){
		return this.id;
	}


	/**
	 * ���݂������̏�Ԃ�Ԃ��܂�<br>
	 * ���̃��\�b�h�̓X���b�h��������Ă��܂��B<br>
	 * ��ɁAinitialize()���\�b�h�ŏ��������s���K�v������܂��B
	 */
	public synchronized AmidaStatus getStatus(){

		//����������Ă��Ȃ��ꍇ
		if(!initialized) throw new IllegalStateException("���W�b�N�I�u�W�F�N�g�͏���������Ă��܂���");

		//�X�V����
		refresh();

		//�A�~�_�����̏�Ԃ�\��
		AmidaStatus status = new AmidaStatus(fixedStatus, variableStatus);

		//��Ԃ�Ԃ�
		return status;
	}


	/**
	 * ���݂������ɉ����̑}�����s���܂�<br>
	 * ���͕s�\�ȏꍇ�A��O�𓊂��܂��B<br>
	 * ���̃��\�b�h�̓X���b�h��������Ă��܂��B<br>
	 * ��ɁAinitialize()���\�b�h�ŏ��������s���K�v������܂��B
	 * @param x - ���̍����X���W (0��X���Q���l��)
	 * @param y - ���̍����Y���W (0��Y��AMIDA_LENGTH+AMIDA_LAST_LENGTH)
	 */
	public synchronized boolean insertLine(int x, int y){

		//����������Ă��Ȃ��ꍇ
		if(!initialized)  throw new IllegalStateException("���W�b�N�I�u�W�F�N�g�͏���������Ă��܂���");

		//Y���W�`�F�b�N
		if(y<=0 || y>=AmidaConfig.AMIDA_LENGTH + AmidaConfig.AMIDA_LAST_LENGTH)
			throw new IllegalArgumentException("y���W���s���ł� : y=" + y);

		//X���W�`�F�b�N
		if(false) throw new IllegalArgumentException("x���W���s���ł� : x=" + x);

		//�X�V����
		refresh();

		//�w�肳�ꂽy���W�����݂�y���W���傫���ꍇ�A�������
		if(variableStatus.getCurrentPositionY()>=y){
			return true;
		}

		return false;
	}

	/**
	 * �I�u�W�F�N�g�̏��������s���܂�
	 */
	public synchronized void initialize(){

		if(initialized) return;

		//�Œ�����쐬
		AmidaFixedStatus fixedStatus = new AmidaFixedStatus();
		fixedStatus.setId(id);
		fixedStatus.setTitle("test");
		fixedStatus.setUrlList(new ArrayList<Link>());
		fixedStatus.setUserList(new ArrayList<User>());
		fixedStatus.setEndTime(endTimeForDebug);
		this.fixedStatus = fixedStatus;
		log.fine("�Œ���̏��������s���܂����B");

		//��������DB������o��
		AmidaVariableStatus variableStatus = new AmidaVariableStatus();
		variableStatus.setCurrentPositionY(-1); //Y���W��-1
		variableStatus.setFinished(false); //finished�t���O��false�ɂ��Ă���
		variableStatus.lock(); //�I�u�W�F�N�g���b�N
		this.variableStatus = variableStatus;
		log.fine("�Ϗ��̏��������s���܂����B");

		initialized = true;
	}


	/**
	 * ���ݏ����쐬���܂�
	 */
	private void refresh(){

		AmidaVariableStatus preVariableStatus = variableStatus;
		AmidaVariableStatus newVariableStatus = new AmidaVariableStatus();

		//�f�[�^���R�s�[����(���ݎ����ȊO)
		Date currentTime = new Date();
		newVariableStatus.setCurrentTime(currentTime);
		newVariableStatus.setCurrentPositionY(preVariableStatus.getCurrentPositionY());
		newVariableStatus.setCurrentsPositionX(preVariableStatus.getCurrentPositionX());
		newVariableStatus.setFinished(preVariableStatus.isFinished());

		//���łɑO�̒i�K�ŏI����Ă���ꍇ�͂��̂܂ܕԂ��B
		if(preVariableStatus.isFinished()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("���łɏI�����Ă��邽�߁A��Ԃ͍X�V����܂���ł����B");
			return;
		}

		//�Ϗ����X�V����B
		//�X�V�����́AY���W�A�������AX���W�̏�(������)
		//���������X�V���ꂽ�ꍇ�A���ʂ�DB�ɕۑ�����

		//�I������
		long leftTime = fixedStatus.getEndTime().getTime() - currentTime.getTime(); //�c�莞��
		if(leftTime<0) leftTime=0;
		newVariableStatus.setFinished((leftTime>0) ? false : true);

		//Y���W���v�Z
		long elapsedTime = AmidaConfig.TOTAL_TIME - leftTime; //�o�ߎ���
		int y = (leftTime>0) ? (int)((long)elapsedTime / AmidaConfig.TIME_TO_MOVE) : AmidaConfig.AMIDA_LENGTH;
		newVariableStatus.setCurrentPositionY(y);

		//Y���W���O�ƕς��Ȃ��ꍇ�A�������AX���W�̍X�V�̓X�L�b�v����
		if(y==preVariableStatus.getCurrentPositionY()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("Y���W�ɕω����Ȃ����߁A��������X���W�͍X�V����܂���ł��� : y=" + y);
			return;
		}

		//���������v�Z
		newVariableStatus.setCurrentsPositionX(new ArrayList<Integer>());


		//�I�u�W�F�N�g�����b�N
		newVariableStatus.lock();
		this.variableStatus = newVariableStatus;
		log.fine("���݂������̏�Ԃ��X�V����܂��� : y=" + y);

	}



	/**
	 * ��������DB������o���܂�(������)
	 */
	/*
	private List<Line> restoreLineHistory(){

	}
	*/


}



