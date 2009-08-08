package com.kyotogtug.amidakuji.jdo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.users.User;

/**
 * 
 * あみだくじ
 * 
 * @author htatsuwaki
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Amidakuji {

	/** ID */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;

	/** タイトル */
	@Persistent
	private String title;

	/** 終了時刻 */
	@Persistent
	private Date endTime;

	/** あみだくじの長さ */
	@Persistent
	private int length;

	/** イメージURLのリスト */
	@Persistent
	private List<String> imageUrlList = new ArrayList<String>();

	/** Googleメールアドレスのリスト */
	@Persistent
	private List<String> mailAddressList = new ArrayList<String>();

	/** あみだくじ線の本リスト */
	@Persistent
	private List<Line> lineList = new ArrayList<Line>();

	/**
	 * @return the lineList
	 */
	public List<Line> getLineList() {
		return lineList;
	}

	/**
	 * @param lineList the lineList to set
	 */
	public void setLineList(List<Line> lineList) {
		this.lineList = lineList;
	}

	/**
	 * @return the id
	 */
	public Key getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Key id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the mailAddressList
	 */
	public List<String> getMailAddressList() {
		return mailAddressList;
	}

	/**
	 * @param mailAddressList the mailAddressList to set
	 */
	public void setMailAddressList(List<String> mailAddressList) {
		this.mailAddressList = mailAddressList;
	}

	/**
	 * @return the imageUrlList
	 */
	public List<String> getImageUrlList() {
		return imageUrlList;
	}

	/**
	 * @param imageUrlList the imageUrlList to set
	 */
	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}

}
