package com.google.code.japarser.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubDictionary implements Dictionary {
	private Pattern pattern = Pattern.compile("//github.com/(.*)/blob/(.*)");
	
	@Override
	public boolean find(String url) {
		Matcher matcher = pattern.matcher(url);
		return matcher.find();
	}
	
	@Override
	public List<String> lookup(String url) {
		Matcher matcher = pattern.matcher(url);
		List<String> ret = new ArrayList<String>();
		ret.add(matcher.replaceAll("//github.com/$1/raw/$2"));
		return ret;
	}
}
