package com.appspot.tweetssky.controller;

import java.util.ArrayList;
import java.util.List;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;
import org.slim3.util.RequestLocator;

import com.appspot.tweetssky.model.HotWord;

public class HotWordsController extends RestfulWebServiceController {	
	@Override
	public Navigation setUp() {
		return super.setUp();
	}

	@Override
	public void doGet() {
		getHotWords();
		return;
    }
	
    @Override
    public void doPost() {
    }

	private void getHotWords() {
		List<HotWord> hotWords = getDummyHotWords();		
		String strict = RequestLocator.get().getParameter("strict");
		
		JSON json = new JSON(JSON.Mode.STRICT);
		json.setPrettyPrint("true".equals(strict));
		String ret = json.format(hotWords);
		
		responseWriter(ret, CONTENT_TYPE_JSON);
	}
	
	private List<HotWord> getDummyHotWords() {
		List<HotWord> list = new ArrayList<HotWord>();
		list.add(new HotWord("地震", 40));
		list.add(new HotWord("津波", 25));
		list.add(new HotWord("AKB", 12));
		list.add(new HotWord("東京", 12));
		list.add(new HotWord("ハッカソン", 5));
		list.add(new HotWord("デモ", 4));
		list.add(new HotWord("昼食", 3));
		list.add(new HotWord("リラックマ", 2));
		list.add(new HotWord("開発", 2));
		list.add(new HotWord("鯖江", 1));
		return list;
	}
}