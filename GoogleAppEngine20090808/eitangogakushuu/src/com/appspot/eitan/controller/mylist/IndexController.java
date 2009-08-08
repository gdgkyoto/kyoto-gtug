package com.appspot.eitan.controller.mylist;

import java.util.List;
import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.model.WordInfo;

public class IndexController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    @Override
    public Navigation run() {
        UserInfo user = (UserInfo)request.getSession().getAttribute("loginUser");
        if (user != null) {
            List<WordInfo> wordinfolist = user.getWordList();
            requestScope("wordinfolist", wordinfolist);
        }
        return forward("index.jsp");
    }
}
