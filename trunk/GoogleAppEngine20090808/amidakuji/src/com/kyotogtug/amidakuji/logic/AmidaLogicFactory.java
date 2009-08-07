package com.kyotogtug.amidakuji.logic;

import java.util.logging.Logger;


/**
 * あみだくじのロジックオブジェクトを生成するクラスです。<br>
 * Flyweightパターンによる実装です。
 * @author R.Takahashi
 */
public final class AmidaLogicFactory {

	//ロガー
	private static final Logger log = Logger.getLogger(AmidaLogicFactory.class.getName());

	//ロジックオブジェクトをメモリ上にプーリングするための変数
	private static int POOL_SIZE = 100; //プールサイズ
	private static AmidaLogic[] pool = new AmidaLogic[POOL_SIZE];
	private static int last_index = 0;
	private static int last_hit_index = 0;

	/**
	 * デフォルトコンストラクタ使用禁止
	 */
	protected AmidaLogicFactory(){}

	/**
	 * あみだくじのロジックオブジェクトを返します。<br>
	 * このメソッドはスレッド同期されています。
	 * @param id - あみだくじID
	 * @return あみだくじロジックオブジェクト
	 */
	public static synchronized AmidaLogic get(long id){

		//プールに存在するか確認
		for(int i=0; i<POOL_SIZE; i++){

			int p=(i+last_hit_index)%POOL_SIZE;

			//ヒットした場合、それを返す。
			if(pool[p]!=null && pool[p].getId()==id){
				log.fine("ロジックインスタンスをキャッシュからとりだしました : index=" + p + ",id=" + id);
				last_hit_index=p;
				return pool[p];
			}
		}

		//ヒットしなかった場合
		AmidaLogic ins = new AmidaLogic(id);
		log.fine("ロジックインスタンスを生成しました : id=" + id);

		//最終インデックス更新
		int np = last_index;
		last_hit_index = last_index;
		last_index = (last_index + 1) % POOL_SIZE;

		//新しいインスタンスを返す。
		pool[np] = ins;
		log.fine("ロジックインスタンスをキャッシュに入れました : id=" + id + ",index=" + np);
		return pool[np];
	}
}
