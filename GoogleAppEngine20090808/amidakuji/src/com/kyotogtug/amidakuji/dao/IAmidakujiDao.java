package com.kyotogtug.amidakuji.dao;

import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;

/**
 * あみだくじに関するDAOのインターフェースです。
 * @author htatsuwaki
 *
 */
public interface IAmidakujiDao {

	/**
	 * 
	 * あみだくじを追加します。
	 * 
	 * @param amidakuji-追加したいあみだくじオブジェクト
	 */
	void insertAmidakuji( Amidakuji amidakuji );
	
	/**
	 * あみだくじを取得します。
	 * @param amidakujiId-あみだくじID
	 * @return あみだくじインスタンス
	 */
	Amidakuji getAmidakujiById( long amidakujiId );
	
}
