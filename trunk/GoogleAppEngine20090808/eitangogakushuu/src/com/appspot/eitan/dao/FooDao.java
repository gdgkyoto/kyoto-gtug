package com.appspot.eitan.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.slim3.jdo.GenericDao;
import org.slim3.jdo.SelectQuery;

import com.appspot.eitan.model.Foo;
import com.appspot.eitan.model.FooMeta;

public class FooDao extends GenericDao<Foo> {

    private static final FooMeta m = new FooMeta();

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(FooDao.class.getName());

    public FooDao() {
        super(Foo.class);
    }

    public FooDao(PersistenceManager pm) {
        super(Foo.class, pm);
    }

    @Override
    protected SelectQuery<Foo> from() {
        return new SelectQuery<Foo>(pm, m.getModelClass());
    }

}
