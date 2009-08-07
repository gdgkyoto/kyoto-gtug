package com.appspot.eitan.controller.translate;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.util.AuthenticationUtil;

public class IndexController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    @Override
    public Navigation run() {
        String link = AuthenticationUtil.instance().createLink(request);
        requestScope("authenticationLink", link);
        
        return forward("index.jsp");
    }
}
