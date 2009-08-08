package com.appspot.eitan.controller.exam;

import org.slim3.tester.JDOControllerTestCase;

public class SelectExamWordControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/exam/selectExamWord");
        SelectExamWordController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/exam/", getNextPath());
    }
}
