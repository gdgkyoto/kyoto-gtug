package hiy.controller.hiy;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class BattleController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("battle.jsp");
    }
}
