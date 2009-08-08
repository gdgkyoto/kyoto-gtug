package com.kyotogtug.amidakuji.jdo.entity;

import javax.jdo.annotations.Persistent;

/**
 * 座標位置
 * 
 * @author htatsuwaki
 * 
 */
public class LinePosition {

	/** ライン左端セル行 */
	@Persistent
	private int leftRow;

	/** ライン左端セル列 */
	@Persistent
	private int leftCol;

	/** ライン左端X座標 */
	@Persistent
	private int leftX;

	/** ライン左端Y座標 */
	@Persistent
	private int leftY;

	/** ライン右端セル行 */
	@Persistent
	private int rightRow;

	/** ライン右端セル列 */
	@Persistent
	private int rightCol;

	/** ライン右端X座標 */
	@Persistent
	private int rightX;

	/** ライン右端Y座標 */
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
