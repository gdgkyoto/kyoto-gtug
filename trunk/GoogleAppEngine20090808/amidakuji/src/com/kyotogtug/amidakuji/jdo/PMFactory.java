package com.kyotogtug.amidakuji.jdo;

import javax.jdo.JDOHelper;

public class PMFactory {
	
	private static final javax.jdo.PersistenceManagerFactory PMF_INSTANCE
		= JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	public static javax.jdo.PersistenceManagerFactory get(){
		return PMF_INSTANCE;
	}
}
