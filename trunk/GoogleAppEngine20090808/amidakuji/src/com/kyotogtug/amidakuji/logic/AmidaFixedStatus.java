package com.kyotogtug.amidakuji.logic;

import java.util.Date;
import java.util.List;


/**
 * あみだくじの開始時から変化しないデータをあらわすクラスです<br>
 * JavaBeansパターン
 * @author R.Takahashi
 */
final class AmidaFixedStatus {

	//あみだくじID
	private long id;

	//あみだくじタイトル
	private String title;

	//アミダくじの参加者
	private List<String> userList;

	//イメージURLリスト
	private List<String> urlList;

	//終了時刻
	private Date endTime;


	/**
	 * idを取得します。
	 * @return id
	 */
	long getId() {
	    return id;
	}

	/**
	 * idを設定します。
	 * @param id id
	 */
	void setId(long id) {
	    this.id = id;
	}

	/**
	 * titleを取得します。
	 * @return title
	 */
	String getTitle() {
	    return title;
	}

	/**
	 * titleを設定します。
	 * @param title title
	 */
	void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * userListを取得します。
	 * @return userList
	 */
	List<String> getUserList() {
	    return userList;
	}

	/**
	 * userListを設定します。
	 * @param userList userList
	 */
	void setUserList(List<String> userList) {
	    this.userList = userList;
	}

	/**
	 * urlListを取得します。
	 * @return urlList
	 */
	List<String> getUrlList() {
	    return urlList;
	}

	/**
	 * urlListを設定します。
	 * @param urlList urlList
	 */
	void setUrlList(List<String> urlList) {
	    this.urlList = urlList;
	}

	/**
	 * endTimeを取得します。
	 * @return endTime
	 */
	Date getEndTime() {
	    return endTime;
	}

	/**
	 * endTimeを設定します。
	 * @param endTime endTime
	 */
	void setEndTime(Date endTime) {
	    this.endTime = endTime;
	}


}
