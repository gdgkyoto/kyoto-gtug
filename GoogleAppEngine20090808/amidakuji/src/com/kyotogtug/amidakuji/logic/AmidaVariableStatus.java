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
	//List<Line> lineList;

	//���ݎ���
	private Date currentTime;

	//���݈ʒu(X���W)�̃��X�g
	private List<Integer> currentPositionX;

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
	 * currentsPositionX���擾���܂��B
	 * @return currentPositionX
	 */
	List<Integer> getCurrentPositionX() {
	    return currentPositionX;
	}

	/**
	 * currentsPositionX��ݒ肵�܂��B
	 * @param currentPositionX currentPositionX
	 */
	void setCurrentsPositionX(List<Integer> currentPositionX) {
		if(locked) throw new IllegalStateException();
	    this.currentPositionX = currentPositionX;
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
