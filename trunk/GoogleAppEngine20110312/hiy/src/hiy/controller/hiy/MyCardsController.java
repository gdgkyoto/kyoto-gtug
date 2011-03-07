package hiy.controller.hiy;

import hiy.model.Card;
import hiy.service.CardService;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class MyCardsController extends Controller {

    @Override
    public Navigation run() throws Exception {

        requestScope("enemy", service.getCard( asKey( "key" )));

        List<Card> cards = service.getCardsWithout( asKey( "key" ) );
        requestScope("myCards", cards);


        return forward("myCards.jsp");
    }

    private CardService service = new CardService();
}
