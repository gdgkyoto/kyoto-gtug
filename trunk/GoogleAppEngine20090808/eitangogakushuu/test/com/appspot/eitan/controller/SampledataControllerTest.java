package com.appspot.eitan.controller;

import org.slim3.tester.JDOControllerTestCase;

public class SampledataControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/sampledata");
        SampledataController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/sampledata.jsp", getNextPath());
    }
}
