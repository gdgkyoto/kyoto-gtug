package com.kyotogtug.amidakuji.logic;

import java.util.logging.Logger;


/**
 * ���݂������̃��W�b�N�I�u�W�F�N�g�𐶐�����N���X�ł��B<br>
 * Flyweight�p�^�[���ɂ������ł��B
 * @author R.Takahashi
 */
public final class AmidaLogicFactory {

	//���K�[
	private static final Logger log = Logger.getLogger(AmidaLogicFactory.class.getName());

	//���W�b�N�I�u�W�F�N�g����������Ƀv�[�����O���邽�߂̕ϐ�
	private static int POOL_SIZE = 100; //�v�[���T�C�Y
	private static AmidaLogic[] pool = new AmidaLogic[POOL_SIZE];
	private static int last_index = 0;
	private static int last_hit_index = 0;

	/**
	 * �f�t�H���g�R���X�g���N�^�g�p�֎~
	 */
	protected AmidaLogicFactory(){}

	/**
	 * ���݂������̃��W�b�N�I�u�W�F�N�g��Ԃ��܂��B<br>
	 * ���̃��\�b�h�̓X���b�h��������Ă��܂��B
	 * @param id - ���݂�����ID
	 * @return ���݂��������W�b�N�I�u�W�F�N�g
	 */
	public static synchronized AmidaLogic get(long id){

		//�v�[���ɑ��݂��邩�m�F
		for(int i=0; i<POOL_SIZE; i++){

			int p=(i+last_hit_index)%POOL_SIZE;

			//�q�b�g�����ꍇ�A�����Ԃ��B
			if(pool[p]!=null && pool[p].getId()==id){
				log.fine("���W�b�N�C���X�^���X���L���b�V������Ƃ肾���܂��� : index=" + p + ",id=" + id);
				last_hit_index=p;
				return pool[p];
			}
		}

		//�q�b�g���Ȃ������ꍇ
		AmidaLogic ins = new AmidaLogic(id);
		log.fine("���W�b�N�C���X�^���X�𐶐����܂��� : id=" + id);

		//�ŏI�C���f�b�N�X�X�V
		int np = last_index;
		last_hit_index = last_index;
		last_index = (last_index + 1) % POOL_SIZE;

		//�V�����C���X�^���X��Ԃ��B
		pool[np] = ins;
		log.fine("���W�b�N�C���X�^���X���L���b�V���ɓ���܂��� : id=" + id + ",index=" + np);
		return pool[np];
	}
}
