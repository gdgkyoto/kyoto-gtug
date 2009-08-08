package com.appspot.eitan.controller.translate;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class RegistController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(RegistController.class.getName());

    @Override
    public Navigation run() {
        return forward("regist.jsp");
    }
}
