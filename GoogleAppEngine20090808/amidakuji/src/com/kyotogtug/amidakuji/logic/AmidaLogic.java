package com.kyotogtug.amidakuji.logic;

import java.util.*;
import java.util.logging.Logger;

import com.kyotogtug.amidakuji.dao.*;
import com.kyotogtug.amidakuji.dao.impl.AmidakujiDaoImpl;
import com.kyotogtug.amidakuji.dao.impl.LineDaoImpl;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;
import com.kyotogtug.amidakuji.jdo.entity.Line;

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



	//-------------------- ���\�b�h --------------------

	/**
	 * �R���X�g���N�^
	 * @param id - ���݂�����ID
	 */
	AmidaLogic(long id){
		this.id = id;
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
	 * @param y - ���̍����Y���W (0��Y��AMIDA_LENGTH)
	 */
	public synchronized boolean insertLine(int x, int y){

		//����������Ă��Ȃ��ꍇ
		if(!initialized)  throw new IllegalStateException("���W�b�N�I�u�W�F�N�g�͏���������Ă��܂���");

		//Y���W�`�F�b�N
		if(y<=0 || y>=AmidaConfig.AMIDA_LENGTH)
			throw new IllegalArgumentException("y���W���s���ł� : y=" + y);

		//X���W�`�F�b�N
		if(false) throw new IllegalArgumentException("x���W���s���ł� : x=" + x);

		//�X�V����
		refresh();

		//�w�肳�ꂽy���W�����݂�y���W���傫���ꍇ�A�������
		if(variableStatus.getCurrentPositionY()>=y){
			Line line = new Line();
			line.setCreateUser(null);
			line.setCreateTime(new Date());
			line.setXPoint(x);
			line.setYPoint(y);

			ILineDao dao = new LineDaoImpl();
			dao.insertLine(id, new Line());
			return true;
		}

		return false;
	}

	/**
	 * �I�u�W�F�N�g�̏��������s���܂�
	 */
	public synchronized void initialize(){

		if(initialized) return;

		//�f�[�^�擾
		IAmidakujiDao dao = new AmidakujiDaoImpl();
		Amidakuji amidakuji = dao.getAmidakujiById(this.id);
		if(amidakuji==null) throw new IllegalArgumentException("Amidakuji�I�u�W�F�N�g�擾�Ɏ��s : id=" + id);


		//�Œ�����쐬
		AmidaFixedStatus fixedStatus = new AmidaFixedStatus();
		fixedStatus.setId(id);
		fixedStatus.setTitle(amidakuji.getTitle());
		fixedStatus.setUrlList(amidakuji.getImageUrlList());
		fixedStatus.setUserList(amidakuji.getMailAddressList());
		fixedStatus.setEndTime(amidakuji.getEndTime());
		this.fixedStatus = fixedStatus;
		log.fine("�Œ���̏��������s���܂����B");

		//�Ϗ����쐬
		AmidaVariableStatus variableStatus = new AmidaVariableStatus();
		variableStatus.setCurrentPositionY(-1); //Y���W��-1
		variableStatus.setFinished(false); //finished�t���O��false�ɂ��Ă���
		variableStatus.setLineList(null);
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
		newVariableStatus.setCurrentPosition(preVariableStatus.getCurrentPosition());
		newVariableStatus.setFinished(preVariableStatus.isFinished());
		newVariableStatus.setLineList(preVariableStatus.getLineList());

		//���łɑO�̒i�K�ŏI����Ă���ꍇ�͂��̂܂ܕԂ��B
		if(preVariableStatus.isFinished()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("���łɏI�����Ă��邽�߁A��Ԃ͍X�V����܂���ł����B");
			return;
		}


		//�I������
		long leftTime = fixedStatus.getEndTime().getTime() - currentTime.getTime(); //�c�莞��
		if(leftTime<0) leftTime=0;
		newVariableStatus.setFinished((leftTime>0) ? false : true);

		//Y���W���v�Z
		long elapsedTime = AmidaConfig.TOTAL_TIME - leftTime; //�o�ߎ���
		if (elapsedTime <0) elapsedTime=0;
		int y = (leftTime>0) ? (int)((long)elapsedTime / AmidaConfig.TIME_TO_MOVE) : AmidaConfig.AMIDA_LENGTH;
		newVariableStatus.setCurrentPositionY(y);

		//Y���W���O�ƕς��Ȃ��ꍇ�A�������AX���W�̍X�V�̓X�L�b�v����
		if(y==preVariableStatus.getCurrentPositionY()){
			newVariableStatus.lock();
			this.variableStatus = newVariableStatus;
			log.fine("Y���W�ɕω����Ȃ����߁A��������X���W�͍X�V����܂���ł��� : y=" + y);
			return;
		}

		//���݂�X���W���v�Z
		int xsize = fixedStatus.getUserList().size();
		Integer[] xpos = new Integer[xsize];
		for(int i=0; i<xsize; i++){
			xpos[i]=i;
		}

		//lineList�擾
		IAmidakujiDao dao = new AmidakujiDaoImpl();
		Amidakuji amida = dao.getAmidakujiById(id);
		List<Line> lineList = amida.getLineList();
		log.info("line count=" + lineList.size());
		List<Line> sortList = new ArrayList<Line>(lineList);

		//lineList2��y���W���Ƀ\�[�g����
		int n = sortList.size();
		for(int i=0;i<n;i++){
		    int min = i ;
		    for(int j=i+1;j<n;j++){
		    	if(sortList.get(min).getYPoint()>sortList.get(j).getYPoint())  min = j ;
		    }
		    //�X���b�v
		    Line a = sortList.get(i);
		    Line b = sortList.get(min);
		    sortList.set(min, a);
		    sortList.set(i, b);
		}


		//�X���b�v
		for(Line line: sortList){
			System.out.println(line.getXPoint() + "," + line.getYPoint());
			int x=line.getXPoint();
			int tmp = xpos[x];
			xpos[x] = xpos[x+1];
			xpos[x+1] = tmp;
		}

		//�����X�g���琶��
		List<List<Object>> position = new ArrayList<List<Object>>();
		for(int i=0; i<xpos.length; i++){
			List<Object> plist = new ArrayList<Object>();
			plist.add(Integer.valueOf(xpos[i]));
			plist.add(Integer.valueOf(y));
			plist.add(fixedStatus.getUserList().get(i));
			position.add(plist);
		}

		//�����X�g�쐬
		List<List<Object>> lineListOutput = new ArrayList<List<Object>>();
		for(Line line : lineList){
			List<Object>e  = new ArrayList<Object>();
			e.add(line.getXPoint());
			e.add(line.getYPoint());
			if(line.getCreateUser()!=null){
				e.add(line.getCreateUser().getEmail());
			}
			else{
				e.add(null);
			}
			lineListOutput.add(e);
		}

		//���ʂ��Z�b�g����
		newVariableStatus.setLineList(lineListOutput);
		newVariableStatus.setCurrentPosition(position);

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



