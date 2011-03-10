package com.appspot.tweetssky.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;
import org.slim3.util.RequestLocator;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.BasicAuthorization;

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
		
		List<String> words = getHotWordsFromX();
		Random rondom = new Random();
		for (String string : words) {
			HotWord hotWord = new HotWord();
			hotWord.setWord(string);
			hotWord.setRank((int)(rondom.nextDouble() * 100 + 1));
			hotWords.add(hotWord);
		}
		
		String strict = RequestLocator.get().getParameter("strict");
		
		JSON json = new JSON(JSON.Mode.STRICT);
		json.setPrettyPrint("true".equals(strict));
		String ret = json.format(hotWords);
		
		responseWriter(ret, CONTENT_TYPE_JSON);
	}
	
	private List<String> getHotWordsFromX() {
		List<String> words = new ArrayList<String>();
		words.add("AKB");
		words.add("Sabae");
		words.add("Fukui");
		words.add("Fitea");
		words.add("jig");
		words.add("Echizen");
		words.add("Egypt");
		return words;
	}
//	private List<String> getHotWordsFromX() {
//		List<String> words = new ArrayList<String>();
//		
//		String screenname = "tweetssky";
//		String password = "yoy0312";
//		TwitterFactory factory = new TwitterFactory();
//		Twitter twitter = factory.getInstance(new BasicAuthorization(screenname, password));
//
//		final int count = 20;
//		Paging paging = new Paging(1, count);
//
//		ResponseList<Status> statusList = null;
//		try {
//			statusList = twitter.getUserTimeline("buzztter", paging);
//		} catch (TwitterException e) {
//			e.printStackTrace();
//			return words;
//		}
//		
//		for (Status status : statusList) {
//			String text = status.getText();
//			if (text.startsWith("HOT:")) {
//				words.add(text);
//			}
//		}
//		return words;
//	}
}