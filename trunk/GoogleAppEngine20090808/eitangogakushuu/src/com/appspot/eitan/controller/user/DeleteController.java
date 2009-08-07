package com.appspot.eitan.controller.user;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.UserInfoDao;
import com.appspot.eitan.model.UserInfo;

public class DeleteController extends Controller {
    private UserInfoDao _userdao = new UserInfoDao();

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(DeleteController.class.getName());

    @Override
    public Navigation run() {
        UserInfo info = _userdao.find(key(), version());
        _userdao.deletePersistentInTx(info);
        return redirect(basePath);
    }
}
