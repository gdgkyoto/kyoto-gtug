package com.appspot.eitan.controller.user;

import org.slim3.tester.JDOControllerTestCase;

public class AddControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/user/add");
        AddController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/user/", getNextPath());
    }
}
