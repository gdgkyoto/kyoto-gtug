package hiy.controller.hiy;

import hiy.meta.CardMeta;
import hiy.model.Card;
import hiy.service.CardService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CardImageController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Card card = this.service.getCard( asKey( meta.key ) );
        show( "card.png", card.getImage().getBytes() );

        return null;
    }

    private CardService service = new CardService();
    private CardMeta meta = new CardMeta();
}
