/**
 * jp.ddo.brightgenerous.android.sample.kaodroidsample.data
 * Image.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.kaodroidsample.data;

import android.graphics.Bitmap;

/**
 * 画像データクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class Image {

	/** ID */
	private long id = 0L;

	/** グループID */
	private long groupId = 0L;

	/** ビットマップ */
	private Bitmap bitmap = null;

	/** 名前 */
	private String name = null;

	/**
	 * コンストラクタ
	 */
	public Image() {
	}

	/**
	 * IDを取得する
	 * 
	 * @return ID
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * IDを設定する
	 * 
	 * @param inId ID
	 */
	public void setId(long inId) {
		this.id = inId;
	}

	/**
	 * グループIDを取得する
	 * 
	 * @return グループID
	 */
	public long getGroupId() {
		return this.groupId;
	}

	/**
	 * グループIDを設定する
	 * 
	 * @param inGroupId グループID
	 */
	public void setGroupId(long inGroupId) {
		this.groupId = inGroupId;
	}

	/**
	 * ビットマップ を取得する
	 * 
	 * @return ビットマップ
	 */
	public Bitmap getBitmap() {
		return this.bitmap;
	}

	/**
	 * ビットマップ を設定する
	 * 
	 * @param inBitmap ビットマップ
	 */
	public void setBitmap(Bitmap inBitmap) {
		this.bitmap = inBitmap;
	}

	/**
	 * 名前を取得する
	 * 
	 * @return 名前
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 名前を設定する
	 * 
	 * @param inName 名前
	 */
	public void setName(String inName) {
		this.name = inName;
	}
}
