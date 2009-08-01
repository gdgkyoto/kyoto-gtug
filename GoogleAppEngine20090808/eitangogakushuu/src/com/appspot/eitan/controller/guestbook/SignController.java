package com.appspot.eitan.controller.guestbook;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;


import com.appspot.eitan.dao.GreetingDao;
import com.appspot.eitan.model.Greeting;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SignController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(SignController.class.getName());

    @Override
    public Navigation run() {
        GreetingDao dao = new GreetingDao();
        Greeting greeting = new Greeting();
        BeanUtil.copy(request, greeting);
        UserService service = UserServiceFactory.getUserService();
        greeting.setAuthor(service.getCurrentUser());
        dao.makePersistentInTx(greeting);
        return redirect(basePath);
    }
}
