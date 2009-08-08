package com.kyotogtug.amidakuji.dao.memoryimpl;

import java.util.Map;
import java.util.TreeMap;

import com.google.appengine.api.datastore.Key;
import com.kyotogtug.amidakuji.jdo.entity.Amidakuji;

public class MemorySingleton {

	private static Map< Long ,  Amidakuji > map = new TreeMap<Long, Amidakuji>();
	
	public static synchronized Map< Long ,  Amidakuji > getMap(){
		
		return map;
	}
	
	public static synchronized Amidakuji getAmida( long key ){
		return getMap().get( key );
	}
	
	public static synchronized void addAmida( Amidakuji amidakuji ){
		map.put(amidakuji.getId().getId(), amidakuji);
	}
	
}
