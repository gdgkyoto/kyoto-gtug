package com.kyotogtug.amidakuji.dao;


import com.kyotogtug.amidakuji.jdo.entity.Line;

/**
 * ���Ɋւ���DAO�̃C���^�[�t�F�[�X�ł��B
 * @author htatsuwaki
 *
 */
public interface ILineDao {

	/**
	 * ����ǉ����܂��B
	 * @param admidakujiId ���݂�������ID
	 * @param line �ǉ�������̏��
	 */
	void insertLine( long amidakujiId , Line line );
	
}
