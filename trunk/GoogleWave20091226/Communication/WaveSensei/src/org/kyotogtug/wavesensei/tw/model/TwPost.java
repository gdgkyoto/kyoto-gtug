package org.kyotogtug.wavesensei.tw.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model
public class TwPost implements Serializable {

  private static final long serialVersionUID = 1L;

  @Attribute(primaryKey = true)
  private Key key;

  @Attribute(version = true)
  private Long version;

  private Integer schemaVersion = 1;

  private String waveId;

  private String postBlipId;

  private String creator;

  private String content;

  private String postTwitId;

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
   *          the key
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
   *          the version
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
   *          the schema version
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
    TwPost other = (TwPost) obj;
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

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPostTwitId() {
    return postTwitId;
  }

  public void setPostTwitId(String postTwitId) {
    this.postTwitId = postTwitId;
  }

  public String getPostBlipId() {
    return postBlipId;
  }

  public void setPostBlipId(String postBlipId) {
    this.postBlipId = postBlipId;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("TwTwit[");
    sb.append("waveId:").append(waveId).append(", ");
    sb.append("postBlipId:").append(postBlipId).append(", ");
    sb.append("creator:").append(creator).append(", ");
    sb.append("content:").append(content).append(", ");
    sb.append("created:").append(created).append(", ");
    sb.append("postTwitId:").append(postTwitId).append("]");
    return sb.toString();
  }
}
