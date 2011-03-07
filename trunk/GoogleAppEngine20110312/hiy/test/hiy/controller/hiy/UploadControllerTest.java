package hiy.controller.hiy;

import hiy.model.Card;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.slim3.util.RequestLocator;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UploadControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.param("userID", "ctrlertest@gmail.com");
        tester.param("power", "123");
        byte[] bytes = new byte[5];
        FileItem imageFile = new FileItem( "test.png", "image/png", bytes);
        RequestLocator.get().setAttribute("image", imageFile);

        tester.start("/hiy/upload");
        UploadController controller = tester.getController();

        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("./"));

        Card stored = Datastore.query(Card.class).asSingle();
        assertThat(stored, is(notNullValue()));
        assertThat(stored.getUserID(), is("ctrlertest@gmail.com"));
        assertThat(stored.getPower(), is(123));
        assertThat(stored.getImage(), is(notNullValue()));

        byte[] storedBytes = stored.getImage().getBytes();
        assertThat(storedBytes.length, is(5));
    }
}
