package hiy.controller.hiy;

import hiy.service.CardService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class UploadController extends Controller {

    @Override
    public Navigation run() throws Exception {
        service.newCard(new RequestMap(request));

        return redirect("./");
    }

    private CardService service = new CardService();
}
