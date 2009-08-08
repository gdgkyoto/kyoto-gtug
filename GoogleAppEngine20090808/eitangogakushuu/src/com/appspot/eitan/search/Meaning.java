/**
 * 
 */
package com.appspot.eitan.search;

/**
 * –|–óŒ‹‰Êî•ñB
 * @author kazu
 */
public class Meaning {
	
	private String meaning;
	private PartOfSpeech partOfSpeech;
	
	public Meaning(String meaning, PartOfSpeech partOfSpeech) {
		this.meaning = meaning;
		this.partOfSpeech = partOfSpeech;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	public PartOfSpeech getPartOfSpeech() {
		return partOfSpeech;
	}
	
	@Override
	public String toString() {
		return "meaning: <" + meaning + ">, part_of_speech: " 
			+ partOfSpeech.toString();
	}
}
