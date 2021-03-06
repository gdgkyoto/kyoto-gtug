package hiy.service;

import hiy.model.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.google.appengine.api.datastore.Blob;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CardServiceTest extends AppEngineTestCase {

    private CardService service = new CardService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("userID", "1231234");
        input.put("userName", "test@gmail.com");
        input.put("power", "110");
        byte[] bytes = { 1, 2, 3, 4, 5 };
        FileItem imageFile = new FileItem( "test.png", "image/png", bytes);
        input.put("image", imageFile);

        Card card = service.newCard(input);
        assertThat(card, is(notNullValue()));
        Card stored = Datastore.get(Card.class, card.getKey());

        assertThat(stored.getUserID(), is("1231234"));
        assertThat(stored.getUserName(), is("test@gmail.com"));
        assertThat(stored.getPower(), is(110));
        assertThat(stored.getImage(), is(notNullValue()));

        byte[] storedBytes = stored.getImage().getBytes();
        assertThat(storedBytes[0], is((byte) 1));
        assertThat(storedBytes[1], is((byte) 2));
        assertThat(storedBytes[2], is((byte) 3));
        assertThat(storedBytes[3], is((byte) 4));
        assertThat(storedBytes[4], is((byte) 5));
   }

    @Test
    public void testGetCards() throws Exception {
        Card card = new Card();
        card.setUserID("test1");

        Datastore.put(card);
        List<Card> cards = service.getCards();

        assertThat(cards.size(), is(1));
        assertThat(cards.get(0).getUserID(), is("test1"));

    }

    @Test
    public void testGetCard() throws Exception {
        Card card = new Card();
        card.setUserID("test2");

        byte[] bytes = { 1, 3, 5, 9, 12 };
        card.setImage( new Blob( bytes ) );

        Datastore.put(card);
        Card stored = service.getCard( card.getKey() );

        assertThat(stored, is(notNullValue()));
        assertThat(stored.getUserID(), is("test2"));

        byte[] storedBytes = stored.getImage().getBytes();
        assertThat(storedBytes, is(notNullValue()));
        assertThat(storedBytes.length, is(5));
    }
}
