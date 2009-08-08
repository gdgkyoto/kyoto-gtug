package com.kyotogtug.amidakuji.logic;

public final class AmidaConfig {

	/**
	 * インスタンス化禁止
	 */
	protected AmidaConfig(){}


	//あみだくじの長さ
	public static final int AMIDA_LENGTH = 100;

	//最終領域
	public static final int AMIDA_LAST_LENGTH = 80;

	//同期間隔(ms)
	public static final  long SYNC_INTERVAL = 3000;

	//１マス進むのに必要な時間(ms)
	public static final long TIME_TO_MOVE = 6000;

	//合計所要時間(ms)
	public static final long TOTAL_TIME = AMIDA_LENGTH * TIME_TO_MOVE;
}
