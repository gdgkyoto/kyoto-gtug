/**
 * jp.ddo.brightgenerous.android.sample.slot.view.slotcell
 * AbstractSlotCell.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot.view.slotcell;

import jp.ddo.brightgenerous.android.sample.slot.view.SlotView;

/**
 * スロットセル抽象クラス。
 * 
 * @version 1.0
 * @author KATOU
 */
abstract public class AbstractSlotCell implements SlotCell {

	/** 横幅 */
	private int width = 0;

	/** 高さ */
	private int height = 0;

	/** 動作フラグ */
	private boolean isRunning = false;

	/** 方向 */
	private int direction = SlotCell.DIRECTION_DOWN;

	/** 結果 */
	private int result = 0;

	/**
	 * 一つの値を表示するフレーム量<br>
	 * 1以上でなければならない。<br>
	 * 値が小さいほど速い。<br>
	 * FPV / FPS 秒間、画面上に数字が表示される。<br>
	 */
	private int fpv = 10;

	/** 現在のFPV進捗レート */
	private float rate = 0.0f;

	/**
	 * コンストラクタ。
	 * 
	 * @param inSlotView スロットビュー
	 * @param inFpv FPV
	 */
	protected AbstractSlotCell(SlotView inSlotView, int inFpv) {
		this.setSize(inSlotView.getWidth(), inSlotView.getHeight());
		if (inFpv < 1) {
			throw new IllegalArgumentException("FPV が不正");
		}
		this.setFpv(inFpv);
	}

	/**
	 * 描画サイズを変更する。
	 * 
	 * @param inWidth 横幅
	 * @param inHeight 高さ
	 */
	public void setSize(int inWidth, int inHeight) {
		this.width = inWidth;
		this.height = inHeight;
	}

	/**
	 * 横幅を取得する。
	 * 
	 * @return 横幅
	 */
	protected int getWidth() {
		return this.width;
	}

	/**
	 * 高さを取得する。
	 * 
	 * @return 高さ
	 */
	protected int getHeight() {
		return this.height;
	}

	/**
	 * FPVを設定する。
	 * 
	 * @param inFpv FPV
	 */
	public void setFpv(int inFpv) {
		this.fpv = inFpv;
	}

	/**
	 * 現在のFPV進捗レートを取得する。
	 * 
	 * @return 現在のFPV進捗レート
	 */
	protected float getRate() {
		return this.rate;
	}

	/**
	 * 方向を設定する。
	 * 
	 * @param inDirection 方向
	 */
	public void setDirection(int inDirection) {
		this.direction = inDirection;
	}

	protected boolean isDirectionUp() {
		return this.direction == SlotCell.DIRECTION_UP;
	}

	protected boolean isDirectionDown() {
		return this.direction == SlotCell.DIRECTION_DOWN;
	}

	/**
	 * 開始処理をする。
	 */
	public void start() {
		this.isRunning = true;
		this.onStart();
	}

	/**
	 * 開始時の処理をする。
	 */
	protected void onStart() {
	}

	/**
	 * 更新処理をする。
	 */
	public void update() {
		if (this.updateable()) {
			if (this.isDirectionDown()) {
				this.rate += 1.0f / this.fpv;
				if (1.0f <= this.rate) {
					this.next();
					this.rate = 0.0f;
				}
			} else {
				this.rate -= 1.0f / this.fpv;
				if (this.rate < 0.0f) {
					if (this.isRunning) {
						this.prev();
					}
					this.rate = 1.0f - (1.0f / this.fpv);
					if (!this.isRunning) {
						this.rate = 0.0f;
					}
				}
			}
		}
	}

	/**
	 * 停止処理をする。
	 */
	public void stop() {
		this.isRunning = false;
		if (this.updateable()) {
			if (this.isDirectionDown()) {
				this.result = this.convValue2Result(this.getNextValue());
			} else {
				this.result = this.convValue2Result(this.getValue());
			}
		} else {
			this.result = this.convValue2Result(this.getValue());
		}
		this.onStop();
	}

	/**
	 * 停止時の処理をする。
	 */
	protected void onStop() {
	}

	/**
	 * 動作フラグを取得する。
	 * 
	 * @return 動作フラグ
	 */
	protected boolean isRunning() {
		return this.isRunning;
	}

	/**
	 * ビュー更新可能かどうかを取得する。
	 * 
	 * @return ビュー更新可能の場合はtrue
	 */
	public boolean updateable() {
		return this.isRunning() || (this.getRate() != 0.0f);
	}

	/**
	 * 結果の値を取得する。
	 * 
	 * @return 結果の値
	 */
	public int getResult() {
		return this.result;
	}

	/**
	 * 値を取得する。
	 * 
	 * @return 値
	 */
	abstract protected int getValue();

	/**
	 * 次の値に移行する。
	 */
	abstract protected void next();

	/**
	 * 次の値を取得する。
	 * 
	 * @return 次の値
	 */
	abstract protected int getNextValue();

	/**
	 * 前の値に移行する。
	 */
	abstract protected void prev();

	/**
	 * 前の値を取得する。
	 * 
	 * @return 前の値
	 */
	abstract protected int getPrevValue();

	/**
	 * 値を結果に変換する。
	 * 
	 * @param inValue 値
	 * @return 結果
	 */
	abstract protected int convValue2Result(int inValue);
}
