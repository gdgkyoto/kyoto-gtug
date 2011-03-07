package hiy.controller.hiy;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MyCardsControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {

        tester.param("key", "abc");

        tester.start("/hiy/myCards");
        MyCardsController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/hiy/myCards.jsp"));

        assertThat(tester.requestScope("enemy"), is(notNullValue()));
        assertThat((String) tester.requestScope("enemy"), is("abc"));

        assertThat(tester.requestScope("myCards"), is(notNullValue()));
    }
}
