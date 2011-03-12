package com.google.code.japarser.controller;

import org.slim3.controller.router.RouterImpl;

public class RegexRouter extends RouterImpl {

	public void addRegexRouting(String from, String to) throws NullPointerException {
		routingList.add(new RegexRouting(from, to));
	}
}
