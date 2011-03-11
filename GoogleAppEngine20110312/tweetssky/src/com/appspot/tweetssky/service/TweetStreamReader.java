package com.appspot.tweetssky.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.BasicAuthorization;

public class TweetStreamReader implements StatusListener {
	private String userName;
	private String password;
	private TwitterStream streamTwitter;

	public TweetStreamReader(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public static void main(String[] args) {
		TweetStreamReader streamReader = new TweetStreamReader("tweetssky", "yoy0312");  //TODO
		streamReader.start();
	}

	public void start() {
		TwitterStreamFactory factory = new TwitterStreamFactory();
		// TODO AccessTokenを指定してTwitterStreamを取得するほうがよいか
		// streamTwitter = factory.getInstance(accessToken); //TODO
		streamTwitter = factory.getInstance(
				new BasicAuthorization(userName, password));
		streamTwitter.addListener(this);
		streamTwitter.sample();
	}

	@Override
	public void onStatus(Status status) {
		if (!isJapanese(status.getText())) {
			return;
		}
		System.out.printf("%d\t%s\t%s\n", status.getId(), status.getUser()
				.getScreenName(), status.getText());
		List<String> nouns = MorphologicalAnalysis.getNouns(status.getText());
		HotWordManager wordManager = HotWordManager.getInstance();
		wordManager.addWords(nouns);
	}

	/**
	 * 日本語かどうかを返す
	 * ひらがなかカタカナを含んでいれば日本語と判定する
	 * TODO 漢字だけのtweetは抜けるが、中国語含まないためにもこのままか
	 * @param text 判別テキスト
	 * @return 日本語ならtrue
	 */
	private static boolean isJapanese(String text) {
		Matcher m = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}])")
				.matcher(text);
		return m.find();
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
	}
	@Override
	public void onException(Exception arg0) {
	}
	@Override
	public void onTrackLimitationNotice(int arg0) {
	}
	@Override
	public void onScrubGeo(long arg0, long arg1) {
	}
}