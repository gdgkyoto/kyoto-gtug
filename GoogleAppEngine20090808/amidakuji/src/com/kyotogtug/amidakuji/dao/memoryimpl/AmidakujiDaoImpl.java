package com.kyotogtug.amidakuji.dao.memoryimpl;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.kyotogtug.amidakuji.dao.IAmidakujiDao;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;

public class AmidakujiDaoImpl implements IAmidakujiDao{

	@Override
	public Amidakuji getAmidakujiById(long amidakujiId) {
		return MemorySingleton.getAmida( amidakujiId );
	}

	@Override
	public void insertAmidakuji(Amidakuji amidakuji) {
		//ÉLÅ[Çê∂ê¨
		Key key = KeyFactory.createKey(Amidakuji.class.getSimpleName(), MemorySingleton.createId());
		
		amidakuji.setId(key);
		
		MemorySingleton.addAmida(amidakuji);
	}
	
}
