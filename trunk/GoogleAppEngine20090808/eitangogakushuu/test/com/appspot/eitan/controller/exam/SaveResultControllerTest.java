package com.appspot.eitan.controller.exam;

import org.slim3.tester.JDOControllerTestCase;

public class SaveResultControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/exam/SaveResult");
        SaveResultController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/exam/SaveResult.jsp", getNextPath());
    }
}
