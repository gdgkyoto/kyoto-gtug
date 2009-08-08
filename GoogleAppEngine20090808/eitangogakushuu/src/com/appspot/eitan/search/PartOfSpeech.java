/**
 * 
 */
package com.appspot.eitan.search;

/**
 * �i���̎�ނ�\���B
 * <pre>
 * �V���A���C�Y�����Ƃ��ɂǂ��Ȃ邩������Ȃ��̂ŁA
 * �t�B�[���h�͂Ƃ肠�����������Ȃ��悤�ɂ��Ă����B
 * </pre>
 * @author kazu
 */
public enum PartOfSpeech {
	/** ���� */
	NOUN,
	/** �㖼�� */
	PRONOUN,
	/** ���� */
	VERB,
	/** �`�e�� */
	ADJECTIVE,
	/** ���� */
	ADVERB,
	/** �O�u�� */
	PREPOSITION,
	/** �ڑ��� */
	CONJUNCTION,
	/** ������ */
	INTERJECTION,
	/** ��i�i���ł͂Ȃ������X�|���X�Ɋ܂܂�Ă���j */
	PHRASE;
	
	public static PartOfSpeech fromString(String s) {
		if (s.equals("Noun"))         return NOUN;
		if (s.equals("Pronoun"))      return PRONOUN;
		if (s.equals("Verb"))         return VERB;
		if (s.equals("Adjective"))    return ADJECTIVE;
		if (s.equals("Adverb"))       return ADVERB;
		if (s.equals("Preposition"))  return PREPOSITION;	
		if (s.equals("Conjunction"))  return CONJUNCTION;		
		if (s.equals("Interjection")) return INTERJECTION;	
		if (s.equals("Phrase"))       return PHRASE;
		throw new IllegalArgumentException("<" + s + ">�͖����ȕi��");
	}	
}
