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
        if (auth.isLogin()) { // ���O�C���ς݂Ȃ�AHttpSession��UserInfo��ݒ肷��
            UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
            if (loginUser == null) { // HttpSession�ɂȂ������̂ŁA�ǉ�
                UserInfoDao dao = new UserInfoDao();

                String loginUserEmail = auth.getLoginUserEmail();
                UserInfo userInfo = dao.getByEmail(loginUserEmail);
                if (userInfo == null) { // ���������i��������ĂȂ������̂ŁAinsert���s
                    userInfo = dao.insert(loginUserEmail);
                    logger.info("���߂ẴA�N�Z�X���������߁AUserInfo�i�����F" + loginUserEmail);
                }

                session.setAttribute("loginUser", userInfo); // �ۑ�
            }
        } else {
            // ���O�C�����Ă��Ȃ��̂ɁAHttpSession�Ɏc���Ă���ƍ��鎖�ɂȂ邩������Ȃ��̂ŁA�ꉞ�������
            session.removeAttribute("loginUser");
        }
        
        chain.doFilter(req, res);
    }
}