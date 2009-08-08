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
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "false")
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
	private List<String> imageUrlList;

	/** Googleメールアドレスのリスト */
	@Persistent
	private List<String> mailAddressList;

	/** あみだくじ線の本リスト */
	@Persistent
	private List<Line> lineList;

	/**
	 * @return the lineList
	 */
	public List<Line> getLineList() {
		return lineList;
	}

	/**
	 * @return the lineList
	 */
	public void addLineList( Line line ) {
		if( this.lineList==null ){
			this.lineList = new ArrayList<Line>();
		}
		this.lineList.add( line );
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
	 */
	public void addMailAddressList(String mail) {
		if( this.mailAddressList==null ){
			this.mailAddressList = new ArrayList<String>();
		}
		mailAddressList.add(mail);
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
	 * @return the imageUrlList
	 */
	public void addImageUrlList( String url ) {
		if( this.imageUrlList==null ){
			this.imageUrlList = new ArrayList<String>();
		}
		imageUrlList.add(url);
	}

	/**
	 * @param imageUrlList the imageUrlList to set
	 */
	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}

}
