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
//		String paramUrl = RequestLocator.get().getParameter("url");
//		if (StringUtils.isBlank(paramUrl)) {
//			responseWriter(SC_BAD_REQUEST);
//		}
//		
//		for (String urlString : lookup(paramUrl)) {
//			try {
//				URL url = new URL(urlString);
//				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//				conn.setRequestMethod("GET");
//				conn.setConnectTimeout(5 * 1000);
//				conn.setReadTimeout(30 * 1000);
//				conn.connect();
//				
//				// ローカル環境ではhttps://への接続は証明書の絡みで失敗する
//				// App Engineにデプロイ後は問題なく動作する
//				int responseCode = conn.getResponseCode();
//				if (responseCode == HttpURLConnection.HTTP_OK) {
//					parse(conn.getInputStream());
//				}
//			} catch (MalformedURLException e) {
//				responseWriter(SC_BAD_REQUEST);
//			} catch (IOException e) {
//				responseWriter(SC_INTERNAL_SERVER_ERROR);
//			} catch (ParseException e) {
//				responseWriter(SC_INTERNAL_SERVER_ERROR);
//			} 
//		}
//
//		responseWriter(SC_NOT_FOUND);
		return;
    }
	
    @Override
    public void doPost() {
    }

	private void getHotWords() {
		List<HotWord> hotWords = new ArrayList<HotWord>();
		
		HotWord hotWord = new HotWord();
		hotWord.setWord("AKB");
		hotWord.setRank(56);
		hotWords.add(hotWord);
		HotWord hotWord2 = new HotWord();
		hotWord2.setWord("Sabaeee");
		hotWord2.setRank(32);
		hotWords.add(hotWord2);
		
		String strict = RequestLocator.get().getParameter("strict");
		
		JSON json = new JSON(JSON.Mode.STRICT);
		json.setPrettyPrint("true".equals(strict));
		String ret = json.format(hotWords);
		
		responseWriter(ret, CONTENT_TYPE_JSON);
	}
}