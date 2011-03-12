package com.google.code.japarser.controller;

import japa.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.arnx.jsonic.JSON;

import org.apache.commons.lang.StringUtils;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.util.RequestLocator;

import com.google.code.japarser.dictionary.BitbucketDictionary;
import com.google.code.japarser.dictionary.Dictionary;
import com.google.code.japarser.dictionary.GitHubDictionary;
import com.google.code.japarser.dictionary.GoogleCodeHostingDictionary;
import com.google.code.japarser.model.ClassInfo;
import com.google.code.japarser.parser.JavaParser;
import com.google.code.japarser.parser.Parser;

public class SrcController extends RestfulWebServiceController {
	private List<Dictionary> dictionaries;
	private Parser<ClassInfo> parser;
	
	@Override
	public Navigation setUp() {
		dictionaries = new ArrayList<Dictionary>();
		dictionaries.add(new GoogleCodeHostingDictionary());
		dictionaries.add(new GitHubDictionary());
		dictionaries.add(new BitbucketDictionary());
		parser = new JavaParser();
		return super.setUp();
	}

	@Override
	public void doGet() {
		String paramUrl = RequestLocator.get().getParameter("url");
		if (StringUtils.isBlank(paramUrl)) {
			responseWriter(SC_BAD_REQUEST);
			return;
		}
		
		for (String urlString : lookup(paramUrl)) {
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5 * 1000);
				conn.setReadTimeout(30 * 1000);
				conn.connect();
				
				// ローカル環境ではhttps://への接続は証明書の絡みで失敗する
				// App Engineにデプロイ後は問題なく動作する
				int responseCode = conn.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					parse(conn.getInputStream());
					return;
				}
			} catch (MalformedURLException e) {
				responseWriter(SC_BAD_REQUEST);
				return;
			} catch (IOException e) {
				responseWriter(SC_INTERNAL_SERVER_ERROR);
				return;
			} catch (ParseException e) {
				responseWriter(SC_INTERNAL_SERVER_ERROR);
				return;
			} 
		}

		responseWriter(SC_NOT_FOUND);
		return;
    }
	
    @Override
    public void doPost() {
        FileItem formFile = requestScope("file");
        if (formFile == null || formFile.getFileName() == null || !formFile.getFileName().endsWith(".java")) { //他言語対応時に.javaを除く
        	responseWriter(SC_BAD_REQUEST);
        	return;
        }

        BufferedInputStream bis = null;
        try {
	        bis = new BufferedInputStream(new ByteArrayInputStream(formFile.getData()));
	        parse(bis);
		} catch (IOException e) {
			responseWriter(SC_INTERNAL_SERVER_ERROR);
			return;
        } catch (ParseException e) {
        	responseWriter(SC_INTERNAL_SERVER_ERROR);
        	return;
        } catch (Exception e) {
        	responseWriter(SC_INTERNAL_SERVER_ERROR);
        	return;
		} finally {
			if (bis != null) {
				try { bis.close(); } catch (IOException e) {}
			}
        }
    }
	
	private void parse(InputStream is) throws ParseException, IOException {
		ClassInfo classInfo = parser.parse(is);
		String pretty = RequestLocator.get().getParameter("pretty");
		
		JSON json = new JSON(JSON.Mode.STRICT);
		json.setSuppressNull(true);
		json.setPrettyPrint("true".equals(pretty));
		String ret = json.format(classInfo);
		
		responseWriter(ret, CONTENT_TYPE_JSON);
	}
	
	private List<String> lookup(String url) {
		for (Dictionary dictionary : dictionaries) {
			if (dictionary.find(url)) {
				return dictionary.lookup(url);
			}
		}
		
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(url);
		return ret;
	}
}