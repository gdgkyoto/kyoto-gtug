package com.kyotogtug.amidakuji.logic;

import java.util.Date;
import java.util.List;


public final class AmidaStatus {

	private AmidaFixedStatus fixedStatus = null;
	private AmidaVariableStatus variableStatus = null;

	AmidaStatus(AmidaFixedStatus fs, AmidaVariableStatus vs){
		this.fixedStatus = fs;
		this.variableStatus = vs;
	}

	//-------------------- �J�n������Œ�Ȃ��� --------------------

	void setFixedStatus(AmidaFixedStatus status){
		fixedStatus = status;
	}

	public long getId(){
		return fixedStatus.getId();
	}
	public String getTitle(){
		return fixedStatus.getTitle();
	}
	public List<String> getUserList() {
	    return fixedStatus.getUserList();
	}
	public List<String> getUrlList() {
	    return fixedStatus.getUrlList();
	}
	public Date getEndTime(){
		return fixedStatus.getEndTime();
	}

	//-------------------- �J�n������ω�������� --------------------

	void setVariableStatus(AmidaVariableStatus status){
		variableStatus = status;
	}

	public List<List<Object>> getLines(){
		return variableStatus.getLineList();
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
	public List<List<Object>> getCurrentPosition(){
		return variableStatus.getCurrentPosition();
	}
	public long getCurrentPositionY() {
	    return variableStatus.getCurrentPositionY();
	}

}
