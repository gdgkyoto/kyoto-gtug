package gaehackathon.fukui.recloc;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * �f�[�^�X�g�A�A�N�Z�X�p
 * @author �䂤��
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
