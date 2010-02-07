package gaehackathon.fukui.recloc.model;

import javax.jdo.annotations.*;
import com.google.appengine.api.users.User;

import java.util.Calendar;
import java.util.Date;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class GpsRecord {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	/** �I�[�i�[���[�U **/
	@Persistent
	private String ownerName;
	
	/** �ܓx **/
	@Persistent
	private Double lat;
	
	/** �o�x **/
	@Persistent
	private Double lng;
	
	/** PostGPS���� **/
	@Persistent
	private Date postGpsDate;
	
	/** �^�C���X�^���v **/
	@Persistent
	private Date timestamp;
	
	
	public GpsRecord(RecLocUser user, Double lat, Double lng, Date gpsDate) {
		this.ownerName = user.getName();
		this.lat = lat;
		this.lng = lng;
		this.postGpsDate = gpsDate;
		this.timestamp = Calendar.getInstance().getTime();
	}
	
	public Long getId() {
		return id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public Double getLatitude() {
		return lat;
	}
	public Double getLongitude() {
		return lng;
	}
	public Date getGpsDate() {
		return postGpsDate;
	}
	public Date getTimestamp() {
		return timestamp;	
	}
}
