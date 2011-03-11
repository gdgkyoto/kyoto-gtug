package com.appspot.tweetssky.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.reduls.gomoku.Morpheme;
import net.reduls.gomoku.Tagger;


public class MorphologicalAnalysis {
	public static List<String> getNouns(String text) {
		List<String> words = new ArrayList<String>();
		Set<String> wordSet = new HashSet<String>();
		
		//TODO urlを予め正規表現ではぶく
		
		List<Morpheme> parse = Tagger.parse(text);
		for (Morpheme morpheme : parse) {
//			System.out.println("surface = " + morpheme.surface + ", feature = " + morpheme.feature);
			if (morpheme.surface.length() > 1
					&& morpheme.feature.startsWith("名詞")
					&& !wordSet.contains(morpheme.surface)) {
				words.add(morpheme.surface);
				wordSet.add(morpheme.surface);
			}
		}
		return words;
	}
}
