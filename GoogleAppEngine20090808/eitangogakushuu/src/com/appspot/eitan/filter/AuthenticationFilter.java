package com.appspot.eitan.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.appspot.eitan.dao.UserInfoDao;
import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.util.AuthenticationUtil;

public class AuthenticationFilter implements Filter {
    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(AuthenticationFilter.class.getName());

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        
        HttpSession session = ((HttpServletRequest) req).getSession();

        AuthenticationUtil auth = AuthenticationUtil.instance();
        if (auth.isLogin()) { // ログイン済みなら、HttpSessionにUserInfoを設定する
            UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
            if (loginUser == null) { // HttpSessionになかったので、追加
                UserInfoDao dao = new UserInfoDao();

                String loginUserEmail = auth.getLoginUserEmail();
                UserInfo userInfo = dao.getByEmail(loginUserEmail);
                if (userInfo == null) { // そもそも永続化されてなかったので、insert実行
                    userInfo = dao.insert(loginUserEmail);
                    logger.info("初めてのアクセスだったため、UserInfo永続化：" + loginUserEmail);
                }

                session.setAttribute("loginUser", userInfo); // 保存
            }
        } else {
            // ログインしていないのに、HttpSessionに残っていると困る事になるかもしれないので、一応毎回消す
            session.removeAttribute("loginUser");
        }
        
        chain.doFilter(req, res);
    }
}