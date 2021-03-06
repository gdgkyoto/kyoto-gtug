package com.appspot.eitan.controller.locale;

import org.slim3.controller.Controller;
import org.slim3.controller.ControllerConstants;
import org.slim3.controller.Navigation;

public class ChangeController extends Controller {

    @Override
    public Navigation run() {
        sessionScope(ControllerConstants.LOCALE_KEY, requestScope("locale"));
        return redirect(basePath);
    }
}
