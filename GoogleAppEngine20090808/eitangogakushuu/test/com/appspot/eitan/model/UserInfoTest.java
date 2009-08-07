package com.appspot.eitan.model;

import org.slim3.tester.JDOTestCase;

public class UserInfoTest extends JDOTestCase {

    public void test() throws Exception {
        UserInfo userInfo = new UserInfo();
        assertNotNull(userInfo);
    }
}
