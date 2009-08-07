package com.appspot.eitan.dao;

import java.util.HashSet;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import org.slim3.jdo.SelectQuery;

import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.model.UserInfoMeta;
import org.slim3.jdo.GenericDao;

public class UserInfoDao extends GenericDao<UserInfo> {

    private static final UserInfoMeta m = new UserInfoMeta();

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(UserInfoDao.class.getName());

    public UserInfoDao() {
        super(UserInfo.class);
    }

    public UserInfoDao(PersistenceManager pm) {
        super(UserInfo.class, pm);
    }

    public UserInfo getByEmail(String email){
        return from().where(m.email.eq(email.toLowerCase())).getFirstResult();
    }

    synchronized public UserInfo insert(String email){
        UserInfo obj = getByEmail(email);
        if(obj != null) return obj;

        UserInfo info = new UserInfo();
        info.setEmail(email);
        makePersistentInTx(info);
        return info;
    }

    synchronized public UserInfo addWodKey(String userkey, String wordkey){
        begin();
        UserInfo info = find(userkey);
        if(info != null){
            HashSet<String> newset = null;
            HashSet<String> wordkeyset = info.getWordkeyset();
            if(wordkeyset != null){
                newset = new HashSet<String>(wordkeyset);
            }else{
                newset = new HashSet<String>();
            }
            newset.add(wordkey);
            info.setWordkeyset(newset);
        }
        commit();
        return info;
    }

    @Override
    protected SelectQuery<UserInfo> from() {
        return new SelectQuery<UserInfo>(pm, m.getModelClass());
    }

}
