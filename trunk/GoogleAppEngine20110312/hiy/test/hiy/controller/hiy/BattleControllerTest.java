package hiy.controller.hiy;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/hiy/battle");
        BattleController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/hiy/battle.jsp"));
    }
}
