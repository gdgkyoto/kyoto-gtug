package com.appspot.eitan.model;

import org.slim3.tester.JDOTestCase;

public class UserTest extends JDOTestCase {

    public void test() throws Exception {
        User user = new User();
        assertNotNull(user);
    }
}
