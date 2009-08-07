package com.appspot.eitan.util;

import javax.servlet.http.HttpServletRequest;

import com.appspot.eitan.model.UserInfo;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * �F�؂̂��߂̃��[�e�B���e�B�N���X
 * 
 * @author toyoshima
 */
public class AuthenticationUtil {

    private static AuthenticationUtil instance = new AuthenticationUtil();

    private AuthenticationUtil() {
    }

    public static AuthenticationUtil instance() {
        return instance;
    }

    public String createLink(HttpServletRequest req) {
        UserService userService = UserServiceFactory.getUserService();

        String result = null;
        if (userService.isUserLoggedIn()) {
            String email = userService.getCurrentUser().getEmail();
            result =
                email
                    + " | <a href=\""
                    + userService.createLogoutURL(req.getRequestURI())
                    + "\">logout</a>";
        } else {
            result =
                "<a href=\""
                    + userService.createLoginURL(req.getRequestURI())
                    + "\">login</a>";
        }

        return result;
    }

    public boolean isLogin() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.isUserLoggedIn();
    }

    public String getLoginUserEmail() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.getCurrentUser().getEmail();
    }
}
