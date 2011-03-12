package com.appspot.tweetssky.controller;

import org.slim3.controller.Navigation;

import com.appspot.tweetssky.service.TweetStreamReader;

public class GetStreamController extends RestfulWebServiceController {
	@Override
	public Navigation setUp() {
		return super.setUp();
	}

	@Override
	public void doGet() {
		getTwitterStream();
	}
	
    @Override
    public void doPost() {
    }

    private void getTwitterStream() {
		System.out.println("stream start");
		//TODO account 直でいいのか？
		TweetStreamReader streamReader = new TweetStreamReader("tweetssky", "yoy0312");
		streamReader.start();
    }
}
