package hiy.controller.hiy;

import hiy.model.Card;
import hiy.service.CardService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class BattleController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Card myCard = service.getCard( asKey( "key" ) );
        Card enemy = service.getCard( asKey( "enemy" ) );

        requestScope( "myCard", myCard );
        requestScope( "enemy", enemy );

        return forward("battle.jsp");
    }

    private CardService service = new CardService();
}
