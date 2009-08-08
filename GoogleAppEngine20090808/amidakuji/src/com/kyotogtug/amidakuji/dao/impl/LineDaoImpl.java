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
		
		//�L�[�𐶐�
		final Key key = KeyFactory.createKey(Amidakuji.class.getSimpleName(), amidakujiId); 
		
		final PersistenceManager persistenceManager = PMFactory.get().getPersistenceManager();
		try{
			// ID�w��Ŏ擾
			final Amidakuji amidakuji = persistenceManager.getObjectById(Amidakuji.class,key);
			
			// �����X�g���X�V
			final List<Line> lineList = amidakuji.getLineList();
			lineList.add(line);
			
			// �Z�b�g
			amidakuji.setLineList(lineList);
			
		}finally{
			persistenceManager.close();
		}
	}
}
