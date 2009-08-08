package com.kyotogtug.amidakuji.logic;

import java.util.Date;
import java.util.List;


/**
 * ���݂������̊J�n������ω����Ȃ��f�[�^������킷�N���X�ł�<br>
 * JavaBeans�p�^�[��
 * @author R.Takahashi
 */
final class AmidaFixedStatus {

	//���݂�����ID
	private long id;

	//���݂������^�C�g��
	private String title;

	//�A�~�_�����̎Q����
	private List<String> userList;

	//�C���[�WURL���X�g
	private List<String> urlList;

	//�I������
	private Date endTime;


	/**
	 * id���擾���܂��B
	 * @return id
	 */
	long getId() {
	    return id;
	}

	/**
	 * id��ݒ肵�܂��B
	 * @param id id
	 */
	void setId(long id) {
	    this.id = id;
	}

	/**
	 * title���擾���܂��B
	 * @return title
	 */
	String getTitle() {
	    return title;
	}

	/**
	 * title��ݒ肵�܂��B
	 * @param title title
	 */
	void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * userList���擾���܂��B
	 * @return userList
	 */
	List<String> getUserList() {
	    return userList;
	}

	/**
	 * userList��ݒ肵�܂��B
	 * @param userList userList
	 */
	void setUserList(List<String> userList) {
	    this.userList = userList;
	}

	/**
	 * urlList���擾���܂��B
	 * @return urlList
	 */
	List<String> getUrlList() {
	    return urlList;
	}

	/**
	 * urlList��ݒ肵�܂��B
	 * @param urlList urlList
	 */
	void setUrlList(List<String> urlList) {
	    this.urlList = urlList;
	}

	/**
	 * endTime���擾���܂��B
	 * @return endTime
	 */
	Date getEndTime() {
	    return endTime;
	}

	/**
	 * endTime��ݒ肵�܂��B
	 * @param endTime endTime
	 */
	void setEndTime(Date endTime) {
	    this.endTime = endTime;
	}


}
