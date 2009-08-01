package com.appspot.eitan.controller.locale;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() {
        return forward("index.jsp");
    }
}
