/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.data
 * Group.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.data;

/**
 * グループデータクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class Group {

	/** ID */
	private long id = 0L;

	/** 名前 */
	private String name = null;

	/**
	 * コンストラクタ
	 */
	public Group() {
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
