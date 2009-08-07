package com.appspot.eitan.controller.sampledata;

import java.util.List;
import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.UserInfoDao;
import com.appspot.eitan.model.UserInfo;

public class ResetController extends Controller {
    private UserInfoDao _userdao = new UserInfoDao();


    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ResetController.class.getName());

    @Override
    public Navigation run() {

        //  UserInfo
        List<UserInfo> userList = _userdao.findAll();
        if(!userList.isEmpty()){
            _userdao.begin();
            _userdao.deletePersistentAll(userList);
            _userdao.commit();
        }

        //  WordInfo


        return redirect(basePath);
    }
}
