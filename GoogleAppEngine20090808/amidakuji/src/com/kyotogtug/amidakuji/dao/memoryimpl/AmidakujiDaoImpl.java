package com.kyotogtug.amidakuji.dao.memoryimpl;

import com.kyotogtug.amidakuji.dao.IAmidakujiDao;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;

public class AmidakujiDaoImpl implements IAmidakujiDao{

	@Override
	public Amidakuji getAmidakujiById(long amidakujiId) {
		return MemorySingleton.getAmida( amidakujiId );
	}

	@Override
	public void insertAmidakuji(Amidakuji amidakuji) {
		MemorySingleton.addAmida(amidakuji);
	}
	
}
