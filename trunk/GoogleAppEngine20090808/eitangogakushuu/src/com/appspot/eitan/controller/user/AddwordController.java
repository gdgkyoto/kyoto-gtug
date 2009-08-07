package com.appspot.eitan.controller.user;

import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.UserInfoDao;
import com.appspot.eitan.model.UserInfo;

public class AddwordController extends Controller {
    UserInfoDao _userdao = new UserInfoDao();

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(AddwordController.class.getName());

    @Override
    public Navigation run() {

        String spell = String.valueOf(Math.random() * 300);
        UserInfo info = _userdao.addWodKey(key(),spell);

        HashSet<String> wordkeyset = info.getWordkeyset();
        System.out.println("----- " + wordkeyset.size());
        for(Iterator<String> iter = wordkeyset.iterator(); iter.hasNext();){
            String wordkey = iter.next();
            System.out.println(wordkey);
        }
        System.out.println("-----");
        return redirect(basePath);
    }
}
