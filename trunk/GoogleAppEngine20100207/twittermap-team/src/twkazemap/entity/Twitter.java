package twkazemap.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Twitter {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	// 作成日
	private Date createDatetime;
	// ステータスId
	@Persistent
	private Long statusId;
	// テキスト
	@Persistent
	private String text;
	// ユーザId
	@Persistent
	private Long userId;
	// ユーザ名
	@Persistent
	private String userName;
	// ユーザ表示名
	@Persistent
	private String userShowName;
	// 場所
	@Persistent
	private String location;
	// プロファイル画像URL
	@Persistent
	private String profileImageUrl;
	// ジオ有効
	@Persistent
	private Boolean geoEnabled;
	// ジオポイント
	@Persistent
	private String geoPosition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserShowName() {
		return userShowName;
	}

	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public Boolean getGeoEnabled() {
		return geoEnabled;
	}

	public void setGeoEnabled(Boolean geoEnabled) {
		this.geoEnabled = geoEnabled;
	}

	public String getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(String geoPosition) {
		this.geoPosition = geoPosition;
	}
}
