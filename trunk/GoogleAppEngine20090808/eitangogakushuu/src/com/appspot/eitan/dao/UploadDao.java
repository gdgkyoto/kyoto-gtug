package com.appspot.eitan.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.slim3.jdo.GenericDao;
import org.slim3.jdo.SelectQuery;

import com.appspot.eitan.model.Upload;
import com.appspot.eitan.model.UploadMeta;

public class UploadDao extends GenericDao<Upload> {

    private static final UploadMeta m = new UploadMeta();

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(UploadDao.class.getName());

    public UploadDao() {
        super(Upload.class);
    }

    public UploadDao(PersistenceManager pm) {
        super(Upload.class, pm);
    }

    @Override
    protected SelectQuery<Upload> from() {
        return new SelectQuery<Upload>(pm, m.getModelClass());
    }
}
