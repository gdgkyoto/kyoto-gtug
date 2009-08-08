/**
 * 
 */
package com.appspot.eitan.search;

/**
 * 品詞の種類を表す。
 * <pre>
 * シリアライズしたときにどうなるか分からないので、
 * フィールドはとりあえず持たせないようにしておく。
 * </pre>
 * @author kazu
 */
public enum PartOfSpeech {
	/** 名詞 */
	NOUN,
	/** 代名詞 */
	PRONOUN,
	/** 動詞 */
	VERB,
	/** 形容詞 */
	ADJECTIVE,
	/** 副詞 */
	ADVERB,
	/** 前置詞 */
	PREPOSITION,
	/** 接続詞 */
	CONJUNCTION,
	/** 感動詞 */
	INTERJECTION,
	/** 句（品詞ではないがレスポンスに含まれている） */
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
		throw new IllegalArgumentException("<" + s + ">は無効な品詞");
	}	
}
