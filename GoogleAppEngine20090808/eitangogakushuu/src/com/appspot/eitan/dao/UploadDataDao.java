package com.appspot.eitan.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.slim3.jdo.GenericDao;
import org.slim3.jdo.SelectQuery;

import com.appspot.eitan.model.UploadData;
import com.appspot.eitan.model.UploadDataMeta;

public class UploadDataDao extends GenericDao<UploadData> {

    private static final UploadDataMeta m = new UploadDataMeta();

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(UploadDataDao.class.getName());

    public UploadDataDao() {
        super(UploadData.class);
    }

    public UploadDataDao(PersistenceManager pm) {
        super(UploadData.class, pm);
    }

    @Override
    protected SelectQuery<UploadData> from() {
        return new SelectQuery<UploadData>(pm, m.getModelClass());
    }
}