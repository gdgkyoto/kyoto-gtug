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
		
		//ƒL[‚ğ¶¬
		Key key = KeyFactory.createKey(Amidakuji.class.getSimpleName(), amidakujiId); 
		
		PersistenceManager persistenceManager = PMFactory.get().getPersistenceManager();
		try{
			// IDw’è‚Åæ“¾
			return persistenceManager.getObjectById(Amidakuji.class,key);
		
		}finally{
			persistenceManager.close();
		}
	}

	@Override
	public void insertAmidakuji(Amidakuji amidakuji) {
		PersistenceManager persistenceManager = PMFactory.get().getPersistenceManager();
		try{
			// ‰i‘±‰»
			persistenceManager.makePersistent(amidakuji);
		
		}finally{
			persistenceManager.close();
		}
	}

}
