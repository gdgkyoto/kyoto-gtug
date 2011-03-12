package hiy.controller.hiy;

import hiy.service.CardService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class UploadController extends Controller {

    @Override
    public Navigation run() throws Exception {

        System.out.println( "upload ");
        System.out.println( " color : " + request.getAttribute( "color" ) );
        System.out.println( " power : " + request.getAttribute( "power" ) );
        System.out.println( " userID : " + request.getAttribute( "userID" ) );
        System.out.println( " userName : " + request.getAttribute( "userName" ) );

        service.newCard(new RequestMap(request));

        return redirect("./");
    }

    private CardService service = new CardService();
}
