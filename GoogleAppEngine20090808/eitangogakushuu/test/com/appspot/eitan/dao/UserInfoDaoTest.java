package com.appspot.eitan.dao;

import org.slim3.tester.JDOTestCase;

public class UserInfoDaoTest extends JDOTestCase {

    public void test() throws Exception {
        UserInfoDao userInfoDao = new UserInfoDao();
        assertNotNull(userInfoDao);
    }
}
