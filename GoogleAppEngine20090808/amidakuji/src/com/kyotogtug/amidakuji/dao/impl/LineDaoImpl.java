package com.kyotogtug.amidakuji.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.kyotogtug.amidakuji.dao.IAmidakujiDao;
import com.kyotogtug.amidakuji.dao.ILineDao;
import com.kyotogtug.amidakuji.jdo.PMFactory;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;
import com.kyotogtug.amidakuji.jdo.entity.Line;

/**
 * 
 * @author htatsuwaki
 */
public class LineDaoImpl implements ILineDao {

	@Override
	public void insertLine(long amidakujiId, Line line) {
		
		//キーを生成
		final Key key = KeyFactory.createKey(Amidakuji.class.getSimpleName(), amidakujiId); 
		
		final PersistenceManager persistenceManager = PMFactory.get().getPersistenceManager();
		try{
			// ID指定で取得
			final Amidakuji amidakuji = persistenceManager.getObjectById(Amidakuji.class,key);
			
			// 線リストを更新
			final List<Line> lineList = amidakuji.getLineList();
			lineList.add(line);
			
			// セット
			amidakuji.setLineList(lineList);
			
		}finally{
			persistenceManager.close();
		}
	}
}
