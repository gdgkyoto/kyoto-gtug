/**
 * jp.ddo.brightgenerous.android.sample.kaodroidsample.data
 * KaoRect.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.kaodroidsample.data;

/**
 * 顔画像を切り抜くための矩形データクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class KaoRect {

	/* X座標 */
	private int x;

	/** Y座標 */
	private int y;

	/** 横幅 */
	private int width;

	/** 高さ */
	private int height;

	/**
	 * コンストラクタ
	 */
	public KaoRect() {
	}

	/**
	 * コンストラクタ
	 * 
	 * @param inX
	 * @param inY
	 * @param inWidth
	 * @param inHeight
	 */
	public KaoRect(int inX, int inY, int inWidth, int inHeight) {
		this.setX(inX);
		this.setY(inY);
		this.setWidth(inWidth);
		this.setHeight(inHeight);
	}

	/**
	 * X座標を取得する
	 * 
	 * @return X座標
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * X座標を設定する
	 * 
	 * @param inX X座標
	 */
	public void setX(int inX) {
		this.x = inX;
	}

	/**
	 * Y座標を取得する
	 * 
	 * @return Y座標
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Y座標を設定する
	 * 
	 * @param inY Y座標
	 */
	public void setY(int inY) {
		this.y = inY;
	}

	/**
	 * 横幅を取得する
	 * 
	 * @return 横幅
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * 横幅を設定する
	 * 
	 * @param inWidth 横幅
	 */
	public void setWidth(int inWidth) {
		this.width = inWidth;
	}

	/**
	 * 高さを取得する
	 * 
	 * @return 高さ
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * 高さを設定する
	 * 
	 * @param inHeight 高さ
	 */
	public void setHeight(int inHeight) {
		this.height = inHeight;
	}
}
