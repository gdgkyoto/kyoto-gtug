package org.kyotogtug.wavesensei.tw.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model
public class TwReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private Integer schemaVersion = 1;
    
    private String inreplyToStatusId;

    private String userScreenName;
    
    private String userProfileImageUrl;
    
    private String replyTwitId;
    
    private String waveId;
    
    private String postBlipId;
    
    private String replyBlipId;
    
    private String text;
    
    private Date created;

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Returns the schema version.
     *
     * @return the schema version
     */
    public Integer getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * Sets the schema version.
     *
     * @param schemaVersion
     *            the schema version
     */
    public void setSchemaVersion(Integer schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TwReply other = (TwReply) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public String getWaveId() {
      return waveId;
    }

    public void setWaveId(String waveId) {
      this.waveId = waveId;
    }

    public String getReplyTwitId() {
      return replyTwitId;
    }

    public void setReplyTwitId(String replyTwitId) {
      this.replyTwitId = replyTwitId;
    }

    public String getInreplyToStatusId() {
      return inreplyToStatusId;
    }

    public void setInreplyToStatusId(String inreplyToStatusId) {
      this.inreplyToStatusId = inreplyToStatusId;
    }

    public String getUserScreenName() {
      return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
      this.userScreenName = userScreenName;
    }

    public String getUserProfileImageUrl() {
      return userProfileImageUrl;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
      this.userProfileImageUrl = userProfileImageUrl;
    }

    public String getPostBlipId() {
      return postBlipId;
    }

    public void setPostBlipId(String postBlipId) {
      this.postBlipId = postBlipId;
    }

    public String getReplyBlipId() {
      return replyBlipId;
    }

    public void setReplyBlipId(String replyBlipId) {
      this.replyBlipId = replyBlipId;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    public Date getCreated() {
      return created;
    }

    public void setCreated(Date created) {
      this.created = created;
    }
}
