package com.google.code.japarser.parser;

import japa.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

public interface Parser<T> {
	public T parse(InputStream is) throws IOException, ParseException;
}
