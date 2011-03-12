package com.appspot.tweetssky.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;

import org.apache.commons.lang.StringUtils;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestLocator;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


public class HotTweetsController extends RestfulWebServiceController {
	@Override
	public Navigation setUp() {
		return super.setUp();
	}

	@Override
	public void doGet() {
		String paramHotWord = RequestLocator.get().getParameter("hot");
		if (StringUtils.isBlank(paramHotWord)) {
			responseWriter(SC_BAD_REQUEST);
		}
		String paramCount = RequestLocator.get().getParameter("count");
		if (StringUtils.isBlank(paramCount)) {
			paramCount = "5";
		}
		int count = 0;
		try {
			count = Integer.parseInt(paramCount);
		} catch (NumberFormatException e) {
			responseWriter(SC_BAD_REQUEST);
		}
		
		String hotIndex = RequestLocator.get().getParameter("hot_index");
		
		getHotTweets(paramHotWord, count, hotIndex);

		return;
    }
	
    @Override
    public void doPost() {
    }

	private void getHotTweets(String paramHotWord, int count, String hotIndex) {
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query(paramHotWord);
		query.setLocale("ja");
		query.setLang("japanese");
//		query.since("2009-10-25");
//		query.until("2010-10-25");
//		query.setResultType(query.RECENT);
		query.setRpp(count);
//		QueryResult result = twitter.search(query);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			e.printStackTrace();
			responseWriter(SC_BAD_REQUEST);
			return;
		}
	    List<Tweet> tweets = result.getTweets();
	    Map map = new HashMap();
	    map.put("tweets", tweets);
	    map.put("hot_index", hotIndex);
	    

	    //TODO
//	    for (Tweet tweet : tweets) {
//			List<String> nouns = MorphologicalAnalysis.getNouns(tweet.getText());
//		}
	    
		String strict = RequestLocator.get().getParameter("strict");
		JSON json = new JSON(JSON.Mode.STRICT);
		json.setPrettyPrint("true".equals(strict));
		String ret = json.format(map);
		System.out.println(ret);
		responseWriter(ret, CONTENT_TYPE_JSON);
	}
}
