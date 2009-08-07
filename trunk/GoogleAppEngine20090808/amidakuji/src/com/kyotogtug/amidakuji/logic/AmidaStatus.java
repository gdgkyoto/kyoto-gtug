package com.kyotogtug.amidakuji.logic;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.users.User;

public final class AmidaStatus {

	private AmidaFixedStatus fixedStatus = null;
	private AmidaVariableStatus variableStatus = null;

	AmidaStatus(AmidaFixedStatus fs, AmidaVariableStatus vs){
		this.fixedStatus = fs;
		this.variableStatus = vs;
	}

	//-------------------- 開始時から固定なもの --------------------

	void setFixedStatus(AmidaFixedStatus status){
		fixedStatus = status;
	}

	public long getId(){
		return fixedStatus.getId();
	}
	public String getTitle(){
		return fixedStatus.getTitle();
	}
	public List<User> getUserList() {
	    return fixedStatus.getUserList();
	}
	public List<Link> getUrlList() {
	    return fixedStatus.getUrlList();
	}
	public Date getEndTime(){
		return fixedStatus.getEndTime();
	}

	//-------------------- 開始時から変化するもの --------------------

	void setVariableStatus(AmidaVariableStatus status){
		variableStatus = status;
	}

	public Date getCurrentTime() {
	    return variableStatus.getCurrentTime();
	}
	public long getLeftTime() {
		long l = fixedStatus.getEndTime().getTime() - variableStatus.getCurrentTime().getTime();
		return (l>0) ? l : 0;
	}
	public boolean isFinished() {
		return variableStatus.isFinished();
	}
	public List<Integer> getCurrentsPositionX() {
	    return variableStatus.getCurrentPositionX();
	}
	public long getCurrentPositionY() {
	    return variableStatus.getCurrentPositionY();
	}

}
