/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.game.slot.view.cell
 * SlotCell.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.game.slot.view.cell;

import android.graphics.Canvas;

/**
 * スロットセルインタフェース。<br>
 * SlotView に strategy を提供する。
 * 
 * @version 1.0
 * @author KATOU
 */
abstract public interface SlotCell {

	/** 方向 上 */
	static public final int DIRECTION_UP = 1;

	/** 方向 下 */
	static public final int DIRECTION_DOWN = 2;

	/**
	 * リセットする。
	 */
	abstract public void reset();

	/**
	 * 方向を設定する。
	 * 
	 * @param inDirection 方向
	 */
	abstract public void setDirection(int inDirection);

	/**
	 * 結果の値を取得する。
	 * 
	 * @return 結果の値
	 */
	abstract public int getResult();

	/**
	 * FPVを設定する。
	 * 
	 * @param inFpv FPV
	 */
	abstract public void setFpv(int inFpv);

	/**
	 * 描画サイズを変更する。
	 * 
	 * @param inWidth 横幅
	 * @param inHeight 高さ
	 */
	abstract public void setSize(int inWidth, int inHeight);

	/**
	 * 開始処理をする。
	 */
	abstract public void start();

	/**
	 * 更新処理をする。
	 */
	abstract public void update();

	/**
	 * 停止処理をする。
	 */
	abstract public void stop();

	/**
	 * 描画処理をする。
	 * 
	 * @param inCanvas キャンバス
	 */
	abstract public void draw(Canvas inCanvas);

	/**
	 * ビュー更新可能かどうかを取得する。
	 * 
	 * @return ビュー更新可能の場合はtrue
	 */
	abstract public boolean updateable();
}
