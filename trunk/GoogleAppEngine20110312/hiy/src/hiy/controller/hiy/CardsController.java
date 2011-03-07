package hiy.controller.hiy;

import hiy.model.Card;
import hiy.service.CardService;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CardsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<Card> cards = service.getCards();
        requestScope("cards", cards);

        return forward("cards.jsp");
    }

    private CardService service = new CardService();
}
