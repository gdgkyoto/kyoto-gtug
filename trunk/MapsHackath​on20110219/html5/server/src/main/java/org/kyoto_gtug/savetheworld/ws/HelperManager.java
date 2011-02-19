package org.kyoto_gtug.savetheworld.ws;

import java.util.HashSet;
import java.util.Set;

import org.kyoto_gtug.savetheworld.domain.Help;
import org.kyoto_gtug.savetheworld.domain.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperManager {

	private static HelperManager instance;
	private static Logger logger = LoggerFactory.getLogger(HelperManager.class);
	
	synchronized public static HelperManager getInstance() {
		if (instance == null) {
			instance = new HelperManager();
		}
		return instance;
	}
	
	private Set<Helper> helpers = new HashSet<Helper>();
	
	public void add(Helper helper) {
		helpers.add(helper);
	}
	
	public void notifyHelp(Help help) {
		for (Helper helper : helpers) {
			helper.notifyHelp(help);
		}
	}

	public void removeHelper(Helper helper) {
		logger.info("HelperManager.removeHelper");
		if (helper == null) {
			return;
		}
		helpers.remove(helper);
		logger.info("Helper removed");		
	}
}
