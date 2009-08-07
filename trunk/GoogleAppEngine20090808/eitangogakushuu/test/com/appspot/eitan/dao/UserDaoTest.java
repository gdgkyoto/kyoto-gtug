package com.appspot.eitan.dao;

import org.slim3.tester.JDOTestCase;

public class UserDaoTest extends JDOTestCase {

    public void test() throws Exception {
        UserDao userDao = new UserDao();
        assertNotNull(userDao);
    }
}
