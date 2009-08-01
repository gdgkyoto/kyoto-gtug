package com.appspot.eitan.controller.blog;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.BlogDao;
import com.appspot.eitan.model.Blog;


public class DeleteController extends Controller {

    private BlogDao dao = new BlogDao();

    @Override
    public Navigation run() {
        Blog blog = dao.find(key(), version());
        dao.deletePersistentInTx(blog);
        return redirect(basePath);
    }
}