package com.kyotogtug.amidakuji.dao;

import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;

/**
 * ���݂������Ɋւ���DAO�̃C���^�[�t�F�[�X�ł��B
 * @author htatsuwaki
 *
 */
public interface IAmidakujiDao {

	/**
	 * 
	 * ���݂�������ǉ����܂��B
	 * 
	 * @param amidakuji-�ǉ����������݂������I�u�W�F�N�g
	 */
	void insertAmidakuji( Amidakuji amidakuji );
	
	/**
	 * ���݂��������擾���܂��B
	 * @param amidakujiId-���݂�����ID
	 * @return ���݂������C���X�^���X
	 */
	Amidakuji getAmidakujiById( long amidakujiId );
	
}
