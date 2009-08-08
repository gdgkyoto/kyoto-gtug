package com.appspot.eitan.model;

import java.io.Serializable;
import java.util.HashSet;
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

import com.appspot.eitan.dao.WordInfoDao;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Version(strategy = VersionStrategy.VERSION_NUMBER)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(UserInfo.class.getName());

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String key;

    @Persistent
    private String email;

    @Persistent(serialized = "true")
    private HashSet<String> wordkeyset;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email != null) ? email.toLowerCase() : email;
    }

    public HashSet<String> getWordkeyset() {
        return wordkeyset;
    }

    public void setWordkeyset(HashSet<String> wordkeyset) {
        this.wordkeyset = wordkeyset;
    }

    public List<WordInfo> getWordList() {
        
        
        List<WordInfo> list = new WordInfoDao().findAll();
        
        for(WordInfo w : list) {
            System.out.println(w.getKey() + ", " + w.getSpell());
        }
        
        HashSet<String> wordkeyset2 = getWordkeyset();

        if (wordkeyset2 != null) {
            System.out.println("キー数" + wordkeyset2.size());
        } else {
            System.out.println("キーはnull");
        }

        return new WordInfoDao().getWordInfoList(wordkeyset2);
    }
}
