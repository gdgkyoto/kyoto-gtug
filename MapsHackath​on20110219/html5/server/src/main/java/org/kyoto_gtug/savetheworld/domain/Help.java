package org.kyoto_gtug.savetheworld.domain;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Index;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.commons.lang.StringEscapeUtils;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
@DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY)
public class Help {

    @Persistent(primaryKey="true", valueStrategy=IdGeneratorStrategy.IDENTITY)
    private Long id;
    private Double lat;
    private Double lng;
    private String message;
    @Index(name="HELP_DATE_IDX")
    private Long date;
    
    public Help(Double lat, Double lng, String message) {
        this(null, lat, lng, message);
    }

    public Help(Long id, Double lat, Double lng, String message) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void escapeHtml() {
        message = StringEscapeUtils.escapeHtml(message);
    }

}
