package com.appspot.eitan.controller;

import org.slim3.tester.JDOControllerTestCase;

public class UserControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/user");
        UserController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/user.jsp", getNextPath());
    }
}
