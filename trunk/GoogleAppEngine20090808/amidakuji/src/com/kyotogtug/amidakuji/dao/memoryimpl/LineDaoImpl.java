package com.kyotogtug.amidakuji.dao.memoryimpl;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.kyotogtug.amidakuji.dao.ILineDao;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;
import com.kyotogtug.amidakuji.jdo.entity.Line;

public class LineDaoImpl implements ILineDao{

	@Override
	public void insertLine(long amidakujiId, Line line) {

		Amidakuji amida = MemorySingleton.getAmida(amidakujiId);
		List<Line> lineList = amida.getLineList();
		
		Key key = KeyFactory.createKey(Line.class.getSimpleName(), MemorySingleton.createId());
		
		line.setId( key );
		
		if(lineList==null){
			lineList = new ArrayList<Line>();
		}
		
		lineList.add( line );
		amida.setLineList(lineList);
		MemorySingleton.addAmida(amida);
	}
}
