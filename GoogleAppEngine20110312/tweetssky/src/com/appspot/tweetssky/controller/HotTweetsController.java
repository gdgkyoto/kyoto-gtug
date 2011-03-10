package com.appspot.tweetssky.controller;

import org.slim3.controller.Navigation;

public class HotTweetsController extends RestfulWebServiceController {	
	@Override
	public Navigation setUp() {
		return super.setUp();
	}

	@Override
	public void doGet() {
		hello();
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

	private void hello() {
//		ClassInfo classInfo = new ClassInfo();
//		classInfo.setName("Hello TweetSky");
//		String strict = RequestLocator.get().getParameter("strict");
//		
//		JSON json = new JSON(JSON.Mode.STRICT);
//		json.setPrettyPrint("true".equals(strict));
//		String ret = json.format(classInfo);
//		
//		responseWriter(ret, CONTENT_TYPE_JSON);
	}
}