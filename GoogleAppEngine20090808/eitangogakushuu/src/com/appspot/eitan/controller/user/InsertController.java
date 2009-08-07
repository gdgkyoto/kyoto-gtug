package com.appspot.eitan.controller.user;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.appspot.eitan.dao.UserInfoDao;

public class InsertController extends Controller {
    private UserInfoDao _userdao = new UserInfoDao();

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(InsertController.class.getName());

    @Override
    public Navigation run() {
        if (!validate()) {
            return forward(basePath);
        }
        String email = requestScope("email");
        _userdao.insert(email);
        return redirect(basePath);
    }
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("email", v.required());
        return v.validate();
    }
}
