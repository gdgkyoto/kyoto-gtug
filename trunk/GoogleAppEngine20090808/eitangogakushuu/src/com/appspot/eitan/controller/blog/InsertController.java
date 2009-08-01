package com.appspot.eitan.controller.blog;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.BeanUtil;

import com.appspot.eitan.dao.BlogDao;
import com.appspot.eitan.model.Blog;


public class InsertController extends Controller {

    private BlogDao dao = new BlogDao();

    @Override
    public Navigation run() {
        if (!validate()) {
            return forward("create");
        }
        Blog blog = new Blog();
        BeanUtil.copy(request, blog);
        dao.makePersistentInTx(blog);
        return redirect(basePath);
    }

    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("title", v.required());
        v.add("content", v.required());
        return v.validate();
    }
}