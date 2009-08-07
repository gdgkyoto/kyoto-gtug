package com.appspot.eitan.controller.user;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.UserInfoDao;

public class IndexController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    @Override
    public Navigation run() {
        UserInfoDao userdao = new UserInfoDao();
        requestScope("userList",userdao.findAll());
        return forward("index.jsp");
    }
}
