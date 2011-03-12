package hiy.controller.hiy;

import hiy.model.Card;
import hiy.service.CardService;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class CardsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        List<Card> cards;
       // if ( user != null ) {
       //     cards = service.getCardsWithout( user.getUserId() );
       // } else {
            cards = service.getCards();
       // }
        requestScope("cards", cards);

        return forward("cards.jsp");
    }

    private CardService service = new CardService();
}
