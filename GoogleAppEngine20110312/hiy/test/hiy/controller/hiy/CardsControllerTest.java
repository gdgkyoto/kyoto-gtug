package hiy.controller.hiy;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CardsControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.environment.setEmail( "tester@gmail.com" );

        tester.start("/hiy/cards");
        CardsController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/hiy/cards.jsp"));

        assertThat(tester.requestScope("cards"), is(notNullValue()));
    }
}
