package gaehackathon.fukui.recloc;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * データストアアクセス用
 * @author ゆうた
 *
 */
public final class PMF {
	private static final PersistenceManagerFactory pmfInstance = 
		JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	private PMF() {
		
	}
	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
