package com.appspot.eitan.controller.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.UploadDao;


public class IndexController extends Controller {

    private UploadDao dao = new UploadDao();

    @Override
    public Navigation run() {
        requestScope("uploadList", dao.findAll());
        return forward("index.jsp");
    }
}
