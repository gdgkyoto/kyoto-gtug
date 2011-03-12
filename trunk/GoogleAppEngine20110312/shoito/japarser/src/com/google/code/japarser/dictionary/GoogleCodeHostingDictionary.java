package com.google.code.japarser.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleCodeHostingDictionary implements Dictionary {
	private Pattern pattern = Pattern.compile("//code.google.com/p/(.*)/source/browse");
	
	@Override
	public boolean find(String url) {
		Matcher matcher = pattern.matcher(url);
		return matcher.find();
	}
	
	@Override
	public List<String> lookup(String url) {
		Matcher matcher = pattern.matcher(url);
		List<String> ret = new ArrayList<String>();
		ret.add(matcher.replaceAll("//$1.googlecode.com/svn"));
		ret.add(matcher.replaceAll("//$1.googlecode.com/hg"));
		return ret;
	}
}
