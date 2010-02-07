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
	// �쐬��
	private Date createDatetime;
	// �X�e�[�^�XId
	@Persistent
	private Long statusId;
	// �e�L�X�g
	@Persistent
	private String text;
	// ���[�UId
	@Persistent
	private Long userId;
	// ���[�U��
	@Persistent
	private String userName;
	// ���[�U�\����
	@Persistent
	private String userShowName;
	// �ꏊ
	@Persistent
	private String location;
	// �v���t�@�C���摜URL
	@Persistent
	private String profileImageUrl;
	// �W�I�L��
	@Persistent
	private Boolean geoEnabled;
	// �W�I�|�C���g
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
