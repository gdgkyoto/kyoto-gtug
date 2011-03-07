package hiy.controller.hiy;

import hiy.model.Card;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {

        Card card = new Card();
        card.setUser("test1");

        Datastore.put(card);

        tester.param( "key",  Datastore.keyToString(card.getKey() ) );
        tester.param( "enemy",  Datastore.keyToString(card.getKey() ) );

        tester.start("/hiy/battle");
        BattleController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/hiy/battle.jsp"));
    }
}
