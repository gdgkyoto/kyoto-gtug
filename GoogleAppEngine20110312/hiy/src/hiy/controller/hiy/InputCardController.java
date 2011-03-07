package hiy.controller.hiy;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class InputCardController extends Controller {

    @Override
    public Navigation run() throws Exception {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        requestScope( "userID", user.getUserId() );
        requestScope( "userName", user.getNickname() );

        return forward("inputCard.jsp");
    }
}
