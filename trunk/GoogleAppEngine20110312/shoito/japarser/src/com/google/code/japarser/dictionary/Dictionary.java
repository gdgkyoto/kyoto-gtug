package com.google.code.japarser.dictionary;

import java.util.List;

public interface Dictionary {
	boolean find(String url);
	
	List<String> lookup(String url);
}
