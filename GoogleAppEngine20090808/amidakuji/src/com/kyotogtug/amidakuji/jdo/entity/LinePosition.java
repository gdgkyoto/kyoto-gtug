package com.kyotogtug.amidakuji.jdo.entity;

import javax.jdo.annotations.Persistent;

/**
 * ���W�ʒu
 * 
 * @author htatsuwaki
 * 
 */
public class LinePosition {

	/** ���C�����[�Z���s */
	@Persistent
	private int leftRow;

	/** ���C�����[�Z���� */
	@Persistent
	private int leftCol;

	/** ���C�����[X���W */
	@Persistent
	private int leftX;

	/** ���C�����[Y���W */
	@Persistent
	private int leftY;

	/** ���C���E�[�Z���s */
	@Persistent
	private int rightRow;

	/** ���C���E�[�Z���� */
	@Persistent
	private int rightCol;

	/** ���C���E�[X���W */
	@Persistent
	private int rightX;

	/** ���C���E�[Y���W */
	@Persistent
	private int rightY;

	/**
	 * @return the leftRow
	 */
	public int getLeftRow() {
		return leftRow;
	}

	/**
	 * @param leftRow
	 *            the leftRow to set
	 */
	public void setLeftRow(int leftRow) {
		this.leftRow = leftRow;
	}

	/**
	 * @return the leftCol
	 */
	public int getLeftCol() {
		return leftCol;
	}

	/**
	 * @param leftCol
	 *            the leftCol to set
	 */
	public void setLeftCol(int leftCol) {
		this.leftCol = leftCol;
	}

	/**
	 * @return the leftX
	 */
	public int getLeftX() {
		return leftX;
	}

	/**
	 * @param leftX
	 *            the leftX to set
	 */
	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	/**
	 * @return the leftY
	 */
	public int getLeftY() {
		return leftY;
	}

	/**
	 * @param leftY
	 *            the leftY to set
	 */
	public void setLeftY(int leftY) {
		this.leftY = leftY;
	}

	/**
	 * @return the rightRow
	 */
	public int getRightRow() {
		return rightRow;
	}

	/**
	 * @param rightRow
	 *            the rightRow to set
	 */
	public void setRightRow(int rightRow) {
		this.rightRow = rightRow;
	}

	/**
	 * @return the rightCol
	 */
	public int getRightCol() {
		return rightCol;
	}

	/**
	 * @param rightCol
	 *            the rightCol to set
	 */
	public void setRightCol(int rightCol) {
		this.rightCol = rightCol;
	}

	/**
	 * @return the rightX
	 */
	public int getRightX() {
		return rightX;
	}

	/**
	 * @param rightX
	 *            the rightX to set
	 */
	public void setRightX(int rightX) {
		this.rightX = rightX;
	}

	/**
	 * @return the rightY
	 */
	public int getRightY() {
		return rightY;
	}

	/**
	 * @param rightY
	 *            the rightY to set
	 */
	public void setRightY(int rightY) {
		this.rightY = rightY;
	}

}
