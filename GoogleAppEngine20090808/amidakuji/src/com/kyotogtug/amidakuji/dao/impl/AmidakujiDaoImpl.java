package com.kyotogtug.amidakuji.dao.impl;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.kyotogtug.amidakuji.dao.IAmidakujiDao;
import com.kyotogtug.amidakuji.jdo.PMFactory;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;

/**
 * 
 * @author htatsuwaki
 *
 */
public class AmidakujiDaoImpl implements IAmidakujiDao{

	@Override
	public Amidakuji getAmidakujiById(long amidakujiId) {
		
		//キーを生成
		Key key = KeyFactory.createKey(Amidakuji.class.getSimpleName(), amidakujiId); 
		
		PersistenceManager persistenceManager = PMFactory.get().getPersistenceManager();
		try{
			// ID指定で取得
			return persistenceManager.getObjectById(Amidakuji.class,key);
		
		}finally{
			persistenceManager.close();
		}
	}

	@Override
	public void insertAmidakuji(Amidakuji amidakuji) {
		PersistenceManager persistenceManager = PMFactory.get().getPersistenceManager();
		try{
			// 永続化
			persistenceManager.makePersistent(amidakuji);
		
		}finally{
			persistenceManager.close();
		}
	}

}
