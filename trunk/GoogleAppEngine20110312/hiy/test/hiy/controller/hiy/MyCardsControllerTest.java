package hiy.controller.hiy;

import hiy.model.Card;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MyCardsControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {

        tester.environment.setEmail( "tester@gmail.com" );

        Card card = new Card();
        card.setUser("test1");
         Datastore.put(card);
        tester.param("key", Datastore.keyToString( card.getKey() ) );

        tester.start("/hiy/myCards");
        MyCardsController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/hiy/myCards.jsp"));

        assertThat(tester.requestScope("enemy"), is(notNullValue()));

        Card enemy = (Card) tester.requestScope("enemy");
        assertThat( enemy.getUser(), is( "test1" ) );

        assertThat(tester.requestScope("myCards"), is(notNullValue()));
    }
}
