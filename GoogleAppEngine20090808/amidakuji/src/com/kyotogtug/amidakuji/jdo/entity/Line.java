package com.kyotogtug.amidakuji.jdo.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

/**
 * ���݂������̐�
 * 
 * @author htatsuwaki
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Line {

	/** ID */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;

	/** ���̍��W */
	@Persistent
	private LinePosition linePosition;

	/** �쐬���E���� */
	@Persistent
	private Date createTime;

	/** �쐬���[�U�[ */
	@Persistent
	private User createUser;

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
	 * @return the linePosition
	 */
	public LinePosition getLinePosition() {
		return linePosition;
	}

	/**
	 * @param linePosition
	 *            the linePosition to set
	 */
	public void setLinePosition(LinePosition linePosition) {
		this.linePosition = linePosition;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createUser
	 */
	public User getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

}
