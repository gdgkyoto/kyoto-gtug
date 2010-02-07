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
	
	/** オーナーユーザ **/
	@Persistent
	private String ownerName;
	
	/** 緯度 **/
	@Persistent
	private Double lat;
	
	/** 経度 **/
	@Persistent
	private Double lng;
	
	/** PostGPS時刻 **/
	@Persistent
	private Date postGpsDate;
	
	/** タイムスタンプ **/
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
