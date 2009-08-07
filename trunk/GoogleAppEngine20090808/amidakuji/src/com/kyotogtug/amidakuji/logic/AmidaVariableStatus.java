package com.kyotogtug.amidakuji.logic;

import java.util.Date;
import java.util.List;

/**
 * あみだくじの開始時から変化するデータをあらわすクラスです<br>
 * JavaBeansパターン
 * @author R.Takahashi
 */
final class AmidaVariableStatus {
	//線リスト
	//List<Line> lineList;

	//現在時刻
	private Date currentTime;

	//現在位置(X座標)のリスト
	private List<Integer> currentPositionX;

	//現在位置(Y座標)
	private long currentPositionY;

	//終了フラグ
	private boolean finished;

	//ロック
	private boolean locked = false;

	void lock(){
		if(locked) throw new IllegalStateException();
		locked=true;
	}

	/**
	 * currentTimeを取得します。
	 * @return currentTime
	 */
	Date getCurrentTime() {
	    return currentTime;
	}

	/**
	 * currentTimeを設定します。
	 * @param currentTime currentTime
	 */
	void setCurrentTime(Date currentTime) {
		if(locked) throw new IllegalStateException();
	    this.currentTime = currentTime;
	}

	/**
	 * currentsPositionXを取得します。
	 * @return currentPositionX
	 */
	List<Integer> getCurrentPositionX() {
	    return currentPositionX;
	}

	/**
	 * currentsPositionXを設定します。
	 * @param currentPositionX currentPositionX
	 */
	void setCurrentsPositionX(List<Integer> currentPositionX) {
		if(locked) throw new IllegalStateException();
	    this.currentPositionX = currentPositionX;
	}

	/**
	 * currentPositionYを取得します。
	 * @return currentPositionY
	 */
	long getCurrentPositionY() {
	    return currentPositionY;
	}

	/**
	 * currentPositionYを設定します。
	 * @param currentPositionY currentPositionY
	 */
	void setCurrentPositionY(long currentPositionY) {
		if(locked) throw new IllegalStateException();
	    this.currentPositionY = currentPositionY;
	}

	/**
	 * finishedを取得します。
	 * @return finished
	 */
	boolean isFinished() {
	    return finished;
	}

	/**
	 * finishedを設定します。
	 * @param finished finished
	 */
	void setFinished(boolean finished) {
		if(locked) throw new IllegalStateException();
	    this.finished = finished;
	}


}
