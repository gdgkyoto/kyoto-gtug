package com.appspot.eitan.controller.user;

import org.slim3.tester.JDOControllerTestCase;

public class DeleteControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/user/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/user/", getNextPath());
    }
}
