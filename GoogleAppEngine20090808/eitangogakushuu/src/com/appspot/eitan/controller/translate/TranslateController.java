package com.appspot.eitan.controller.translate;

import java.util.List;
import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.appspot.eitan.model.WordInfo;
import com.appspot.eitan.model.beans.Meaning;
import com.appspot.eitan.search.SmartFmItemSearcher;


public class TranslateController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(TranslateController.class.getName());

    @Override
    public Navigation run() {
        if (!validate()) {
            return forward(basePath);
        }
        String spell = requestScope("spell");
        
        /* TODO Bigtable�ւ̎Q�Ɖ񐔖₢���킹�i��؂��񂨊肢���܂��j*/
        
        SmartFmItemSearcher searcher = new SmartFmItemSearcher();
        List<Meaning> result = searcher.search(spell);
        
        /* TODO Bigtable�ւ̎Q�Ɖ񐔂̓o�^�i��؂��񂨊肢���܂��j*/
        
        WordInfo wordInfo = new WordInfo();
        wordInfo.setSpell(spell);
        wordInfo.setMeaninglist(result);
        
        requestScope("wordInfo", wordInfo);
        
        return forward("confirm.jsp");
    }
    
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("spell", v.required());
        return v.validate();
    }
}
