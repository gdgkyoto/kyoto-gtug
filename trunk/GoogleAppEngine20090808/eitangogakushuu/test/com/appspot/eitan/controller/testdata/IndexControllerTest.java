package com.appspot.eitan.controller.testdata;

import org.slim3.tester.JDOControllerTestCase;

public class IndexControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/testdata/");
        IndexController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/testdata/", getNextPath());
    }
}
