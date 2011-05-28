package com.lifevar;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import android.util.Log;

public class Logger {
	private static final boolean debug = true;
	private static final String TAG = "SmelMap";
	
	public static int d(String msg){
		if(debug){
			try {
				return Log.d(TAG, new String(msg.getBytes(), Charset.defaultCharset().displayName()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return 0;
		}
		else{
			return 0;
		}
	}
	
	public static int v(String msg){
		return Log.v(TAG, msg);
	}
	
	public static int e(String msg, Exception e){
		
		return Log.e(TAG, msg, e);
	}
}
