/**
 * 
 */
package com.appspot.eitan.search.parsers;

import java.io.InputStream;
import java.util.List;

import com.appspot.eitan.search.Meaning;


/**
 * �|�󌟍�����XML���p�[�X����X�g���e�W�[�̃C���^�[�t�F�[�X�B
 * @author kazu
 */
public interface ParsingStrategy {
	
	/**
	 * �|�󌟍����ʂ��p�[�X����B
	 * @param xml �|�󌟍����ʂ�XML�X�g���[���B
	 * @return �|�󌟍����ʂ̃��X�g�B�����Ɏ��s�����ꍇ�A��̃��X�g��Ԃ��B
	 */
	List<Meaning> parse(InputStream xml);
}
