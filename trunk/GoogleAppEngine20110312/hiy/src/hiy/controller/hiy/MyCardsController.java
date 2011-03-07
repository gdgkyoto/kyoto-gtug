package hiy.controller.hiy;

import hiy.model.Card;
import hiy.service.CardService;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MyCardsController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Card enemy = service.getCard( asKey( "key" ) );
        requestScope( "enemy", enemy );

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        List<Card> cards = service.getCardsWith( user.getNickname() );
        requestScope("myCards", cards);


        return forward("myCards.jsp");
    }

    private CardService service = new CardService();
}
