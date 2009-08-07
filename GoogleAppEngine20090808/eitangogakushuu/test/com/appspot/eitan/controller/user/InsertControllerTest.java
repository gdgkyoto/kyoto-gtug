package com.appspot.eitan.controller.user;

import org.slim3.tester.JDOControllerTestCase;

public class InsertControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/user/insert");
        InsertController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/user/", getNextPath());
    }
}
