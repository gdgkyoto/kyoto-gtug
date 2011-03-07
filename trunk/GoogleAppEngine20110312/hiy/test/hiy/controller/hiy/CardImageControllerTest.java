package hiy.controller.hiy;

import hiy.model.Card;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.google.appengine.api.datastore.Blob;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CardImageControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {

        Card card = new Card();
        card.setUser("test1");
        card.setImage( new Blob( new byte[] { 1, 2, 3 } ) );
        Datastore.put(card);
        tester.param("key", Datastore.keyToString( card.getKey() ) );

        tester.start("/hiy/cardImage");
        CardImageController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
