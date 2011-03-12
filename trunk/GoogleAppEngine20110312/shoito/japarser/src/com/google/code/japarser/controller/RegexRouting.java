package com.google.code.japarser.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.slim3.controller.router.Routing;

public class RegexRouting extends Routing {

	public RegexRouting(String from, String to) throws NullPointerException {
		super(from, to);
	}

	@Override
	protected void setFrom(String from) throws NullPointerException {
		if (from == null) {
			throw new NullPointerException("The from parameter is null.");
		}
		this.from = from;
		fromPattern = Pattern.compile(from);
	}

	@Override
	protected void setTo(String to) {
		if (to == null) {
			throw new NullPointerException("The to parameter is null.");
		}
		this.to = to;
	}

	@Override
	public String route(HttpServletRequest request, String path) throws NullPointerException {
		if (request == null) {
			throw new NullPointerException("The request parameter is null.");
		}
		if (path == null) {
			throw new NullPointerException("The path parameter is null.");
		}
		Matcher matcher = fromPattern.matcher(path);
		if (!matcher.find()) {
			return null;
		}
		return matcher.replaceFirst(to);
	}
}