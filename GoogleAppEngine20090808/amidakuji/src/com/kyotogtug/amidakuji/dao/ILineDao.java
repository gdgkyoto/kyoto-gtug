package com.kyotogtug.amidakuji.dao;


import com.kyotogtug.amidakuji.jdo.entity.Line;

/**
 * 線に関するDAOのインターフェースです。
 * @author htatsuwaki
 *
 */
public interface ILineDao {

	/**
	 * 線を追加します。
	 * @param admidakujiId あみだくじのID
	 * @param line 追加する線の情報
	 */
	void insertLine( long admidakujiId , Line line );
	
}
