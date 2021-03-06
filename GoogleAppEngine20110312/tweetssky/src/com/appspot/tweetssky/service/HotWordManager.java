package com.appspot.tweetssky.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.appspot.tweetssky.model.HotWord;


public class HotWordManager {
	private static final Logger logger = Logger.getLogger(HotWordManager.class.getName());
	//単語の出現回数を数えるためのマップ
	private Map<String, HotWord> wordsMap = new HashMap<String, HotWord>();
	private static HotWordManager instance = null;
	private HotWordManager() {
	}
	public static HotWordManager getInstance() {
		return instance;
	}
	public void addWords(List<String> words) {
		for (String word : words) {
			HotWord hotWord = wordsMap.get(word);
			if (hotWord == null) {
				wordsMap.put(word, new HotWord(word));
			} else {
				hotWord.countUp();
				
				//TODO debug
				if (hotWord.getCount() == 10) {
					List<HotWord> hots = getTop(10);
					logger.info("hot rank----------");
					for (HotWord h : hots) {
						logger.info("hot = " + h.getWord() + ", c = " + h.getCount());
					}
					break;
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<HotWord> getTop(int n) {
		List<HotWord> wordList = new ArrayList<HotWord>(wordsMap.values());
		Collections.sort(wordList);
		return wordList.subList(0, n);
	}
}
