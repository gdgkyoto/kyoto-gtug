package com.appspot.eitan.controller.user;

import org.slim3.tester.JDOControllerTestCase;

public class AddwordControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/user/addword");
        AddwordController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/user/", getNextPath());
    }
}
