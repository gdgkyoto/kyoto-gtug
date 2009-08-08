package com.kyotogtug.amidakuji.dao.memoryimpl;

import com.kyotogtug.amidakuji.dao.ILineDao;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;
import com.kyotogtug.amidakuji.jdo.entity.Line;

public class LineDaoImpl implements ILineDao{

	@Override
	public void insertLine(long amidakujiId, Line line) {
		
		Amidakuji amida = MemorySingleton.getAmida(amidakujiId);
		amida.addLineList( line );
		MemorySingleton.addAmida(amida);
	}
}
