package hiy.service;

import hiy.meta.CardMeta;
import hiy.model.Card;

import java.util.List;
import java.util.Map;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Transaction;


public class CardService {

    public Card newCard(Map<String, Object> input) {
        Card card = new Card();

        card.setUser( (String) input.get("user") );
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

    private CardMeta meta = new CardMeta();
}
