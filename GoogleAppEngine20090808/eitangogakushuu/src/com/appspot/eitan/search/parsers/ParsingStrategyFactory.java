/**
 * 
 */
package com.appspot.eitan.search.parsers;


/**
 * �������ʃp�[�X�X�g���e�W�I�u�W�F�N�g�̃t�@�N�g���B
 * <pre>
 * DOM�p�[�T���x��������ASAX�Ƃ��ɐ؂�ւ��邽�߂Ɏg���B
 * </pre>
 * @author kazu
 */
public class ParsingStrategyFactory {
	
	public static ParsingStrategy getStrategy() {
		return new DOMStrategy();
	}
	
	// �C���X�^���X�s�v
	private ParsingStrategyFactory() {}
}
