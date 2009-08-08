/**
 * 
 */
package com.appspot.eitan.search;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.appspot.eitan.search.parsers.ParsingStrategy;
import com.appspot.eitan.search.parsers.ParsingStrategyFactory;


/**
 * smart fm APIを使って、単語の翻訳検索結果を得る。
 * @author kazu
 */
public class SmartFmItemSearcher {
	
	private ParsingStrategy parser;
	
	public SmartFmItemSearcher() {
		this(ParsingStrategyFactory.getStrategy());
	}
	
	public SmartFmItemSearcher(ParsingStrategy parser) {
		this.parser = parser;
	}

	public List<Meaning> search(String word) {
		return search(word, new SearchOption());
	}
	
	public List<Meaning> search(String word, SearchOption option) {
		try {
			URL url = buildURL(word, option);
			InputStream is = fetch(url);
			return parser.parse(is);
		}
		catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<Meaning>();
		}
	}
	
	private URL buildURL(String word, SearchOption option) 
		throws MalformedURLException {
		return new URL("http://api.smart.fm/items/matching/" 
				+ word + ".xml" + option.toURLFragment());
	}
	
	private InputStream fetch(URL url) throws IOException {
		return url.openStream();
	}
}
