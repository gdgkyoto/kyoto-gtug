package com.appspot.eitan.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.slim3.jdo.GenericDao;
import org.slim3.jdo.SelectQuery;

import com.appspot.eitan.model.WordInfo;
import com.appspot.eitan.model.WordInfoMeta;
import com.appspot.eitan.model.beans.Meaning;
import com.appspot.eitan.model.beans.RefInfo;
import com.google.appengine.api.datastore.Text;

public class WordInfoDao extends GenericDao<WordInfo> {

    private static final WordInfoMeta m = new WordInfoMeta();

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(WordInfoDao.class.getName());

    public WordInfoDao() {
        super(WordInfo.class);
    }

    public WordInfoDao(PersistenceManager pm) {
        super(WordInfo.class, pm);
    }

    @Override
    protected SelectQuery<WordInfo> from() {
        return new SelectQuery<WordInfo>(pm, m.getModelClass());
    }

    public WordInfo getByKey(String key) {
        return find(key);
    }

    public WordInfo getBySpell(String spell) {
        return from().where(m.spell.eq(spell)).getFirstResult();
    }

    public List<WordInfo> getWordInfoList(Collection<String> keyCollection) {
        if (keyCollection == null || keyCollection.isEmpty()) {
            return new ArrayList<WordInfo>();
        }

        Query query = pm.newQuery(WordInfo.class);
        query.setFilter("key == :keys");
        List<WordInfo> wordInfoList =
            (List<WordInfo>) query.execute(keyCollection);

        return wordInfoList;
    }

    public List<WordInfo> findAll() {
        return from().getResultList();
    }

    synchronized public WordInfo insert(String spell,
            List<Meaning> meaninglist, Text memo, String userkey) {
        WordInfo info = getBySpell(spell);
        if (info == null) {
            info = new WordInfo();
            info.setSpell(spell);
            info.setMeaninglist(meaninglist);
            if (userkey != null) {
                RefInfo ref = new RefInfo();
                ref.incrementRefCount();
                ref.setLastSearch(new Date());
                HashMap<String, RefInfo> refmap =
                    new HashMap<String, RefInfo>();
                refmap.put(userkey, ref);
                info.setRefmap(refmap);
            }
            info.setMemo(memo);
            makePersistentInTx(info);
        }
        return info;
    }

    synchronized public WordInfo update(WordInfo info) {
        begin();
        WordInfo storeinfo = getByKey(info.getKey());
        if (storeinfo != null) {
            storeinfo.setSpell(info.getSpell());
            storeinfo.setRefmap(info.getRefmap());
            storeinfo.setMeaninglist(info.getMeaninglist());
            storeinfo.setMemo(info.getMemo());
        }
        commit();
        return storeinfo;
    }

}
