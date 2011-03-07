package hiy.controller.hiy;

import hiy.meta.CardMeta;
import hiy.model.Card;
import hiy.service.CardService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CardImageController extends Controller {

    @Override
    public Navigation run() throws Exception {

        try {
            Card card = this.service.getCard( asKey( meta.key ) );
            show( "card.png", card.getImage().getBytes() );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return null;
    }

    private CardService service = new CardService();
    private CardMeta meta = new CardMeta();
}
