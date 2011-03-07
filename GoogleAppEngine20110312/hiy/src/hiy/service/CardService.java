package hiy.service;

import hiy.model.Card;
import hiy.meta.CardMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;


public class CardService {

    public Card newCard(Map<String, Object> input) {
        Card card = new Card();

        card.setUserID( (String) input.get("userID") );
        card.setUserName( (String) input.get("userName") );
        card.setPower( Integer.parseInt( (String) input.get("power") ) );
        FileItem imageFile = (FileItem) input.get("image");
        if ( imageFile != null ) {
            card.setImage( new Blob( imageFile.getData() ) );
        }

        Transaction tx = Datastore.beginTransaction();
        Datastore.put(card);
        tx.commit();

        return card;
    }

    public List<Card> getCards() {
        return Datastore.query(meta).asList();
    }

    public List<Card> getCardsWith( String userID ) {
        List<Card> cardsAll = Datastore.query(meta).asList();

        List<Card> cards = new ArrayList<Card>();
        for ( Card card : cardsAll ) {
            if ( card.getUserID().equals( userID ) ) {
                cards.add( card );
            }
        }

        return cards;
    }

    public List<Card> getCardsWithout( String userID ) {
        List<Card> cardsAll = Datastore.query(meta).asList();

        List<Card> cards = new ArrayList<Card>();
        for ( Card card : cardsAll ) {
            if ( !card.getUserID().equals( userID ) ) {
                cards.add( card );
            }
        }

        return cards;
    }

    public Card getCard( Key key ) {
        return Datastore.get(meta, key);
    }

    private CardMeta meta = new CardMeta();
}
