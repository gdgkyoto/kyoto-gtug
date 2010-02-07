package gaehackathon.fukui.recloc.model;

import java.security.MessageDigest;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class RecLocHashCode {
	@PrimaryKey
	@Persistent
	private String hashCode;
	
	@Persistent
	private String userName;

	public RecLocHashCode(String userName) {
		this.userName = userName;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update("xGHhc5pLUF312iMT".getBytes());
			md.update(userName.getBytes());
			byte[] b = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte bc : b) {
				String bstr = Integer.toHexString(bc & 0xFF);
				if (bstr.length() == 1) {
					sb.append('0');
				}
				sb.append(bstr);
			}
			this.hashCode = sb.toString();
		} catch (Exception e) {
			throw new RuntimeException("Create " + this.getClass().getName() + " instance error.");
		}
	}
	
	public String getHashCode() {
		return hashCode;
	}
	public String getUserName() {
		return userName;
	}
}
