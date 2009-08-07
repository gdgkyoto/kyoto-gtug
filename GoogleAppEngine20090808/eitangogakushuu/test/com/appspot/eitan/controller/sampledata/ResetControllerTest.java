package com.appspot.eitan.controller.sampledata;

import org.slim3.tester.JDOControllerTestCase;

public class ResetControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/sampledata/reset");
        ResetController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/sampledata/", getNextPath());
    }
}
