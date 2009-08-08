package com.kyotogtug.amidakuji.logic;

import java.util.Date;
import java.util.List;

/**
 * ���݂������̊J�n������ω�����f�[�^������킷�N���X�ł�<br>
 * JavaBeans�p�^�[��
 * @author R.Takahashi
 */
final class AmidaVariableStatus {
	//�����X�g
	List<List<Object>> lineList;

	//���ݎ���
	private Date currentTime;

	//���݈ʒu(x,y,user���X�g)
	private List<List<Object>> currentPosition;

	//���݈ʒu(Y���W)
	private long currentPositionY;

	//�I���t���O
	private boolean finished;

	//���b�N
	private boolean locked = false;

	void lock(){
		if(locked) throw new IllegalStateException();
		locked=true;
	}

	/**
	 * lineList���擾���܂��B
	 * @return lineList
	 */
	public List<List<Object>>  getLineList() {
	    return lineList;
	}

	/**
	 * lineList��ݒ肵�܂��B
	 * @param lineList lineList
	 */
	public void setLineList(List<List<Object>> lineList) {
	    this.lineList = lineList;
	}

	/**
	 * currentTime���擾���܂��B
	 * @return currentTime
	 */
	Date getCurrentTime() {
	    return currentTime;
	}

	/**
	 * currentTime��ݒ肵�܂��B
	 * @param currentTime currentTime
	 */
	void setCurrentTime(Date currentTime) {
		if(locked) throw new IllegalStateException();
	    this.currentTime = currentTime;
	}

	/**
	 * currentPosition���擾���܂��B
	 * @return currentPosition
	 */
	public List<List<Object>> getCurrentPosition() {
	    return currentPosition;
	}

	/**
	 * currentPosition��ݒ肵�܂��B
	 * @param currentPosition currentPosition
	 */
	public void setCurrentPosition(List<List<Object>> currentPosition) {
	    this.currentPosition = currentPosition;
	}

	/**
	 * currentPositionY���擾���܂��B
	 * @return currentPositionY
	 */
	long getCurrentPositionY() {
	    return currentPositionY;
	}

	/**
	 * currentPositionY��ݒ肵�܂��B
	 * @param currentPositionY currentPositionY
	 */
	void setCurrentPositionY(long currentPositionY) {
		if(locked) throw new IllegalStateException();
	    this.currentPositionY = currentPositionY;
	}

	/**
	 * finished���擾���܂��B
	 * @return finished
	 */
	boolean isFinished() {
	    return finished;
	}

	/**
	 * finished��ݒ肵�܂��B
	 * @param finished finished
	 */
	void setFinished(boolean finished) {
		if(locked) throw new IllegalStateException();
	    this.finished = finished;
	}


}
