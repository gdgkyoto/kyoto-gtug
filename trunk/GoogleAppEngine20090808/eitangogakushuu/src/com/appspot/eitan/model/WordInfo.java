package com.appspot.eitan.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;

import com.appspot.eitan.model.beans.Meaning;
import com.appspot.eitan.model.beans.RefInfo;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Version(strategy = VersionStrategy.VERSION_NUMBER)
public class WordInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(WordInfo.class.getName());

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String key;

    @Persistent
    private String spell;   //  綴り

    @Persistent(serialized = "true")
    private HashMap<String,RefInfo> refmap; //  参照情報マップ

    @Persistent(serialized = "true")
    private List<Meaning> meaninglist;  //  和訳リスト

    @Persistent
    private Text memo;  //  メモ

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the version
     */
    public long getVersion() {
        return (Long) JDOHelper.getVersion(this);
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public HashMap<String, RefInfo> getRefmap() {
        return refmap;
    }

    public void setRefmap(HashMap<String, RefInfo> refmap) {
        this.refmap = refmap;
    }

    public List<Meaning> getMeaninglist() {
        return meaninglist;
    }

    public void setMeaninglist(List<Meaning> meaninglist) {
        this.meaninglist = meaninglist;
    }

    public Text getMemo() {
        return memo;
    }

    public void setMemo(Text memo) {
        this.memo = memo;
    }
    
    public int getPublicCount() {
        int result = 0;
        for (RefInfo ri : this.refmap.values()) {
            result += ri.refCount;
        }
        return result;
    }
}
