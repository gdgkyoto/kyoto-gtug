package com.kyotogtug.amidakuji.logic;

public final class AmidaConfig {

	/**
	 * �C���X�^���X���֎~
	 */
	protected AmidaConfig(){}


	//���݂������̒���
	public static final int AMIDA_LENGTH = 100;

	//�ŏI�̈�
	public static final int AMIDA_LAST_LENGTH = 100;

	//�����Ԋu(ms)
	public static final  long SYNC_INTERVAL = 3000;

	//�P�}�X�i�ނ̂ɕK�v�Ȏ���(ms)
	public static final long TIME_TO_MOVE = 6000;

	//���v���v����(ms)
	public static final long TOTAL_TIME = AMIDA_LENGTH * TIME_TO_MOVE;
}
