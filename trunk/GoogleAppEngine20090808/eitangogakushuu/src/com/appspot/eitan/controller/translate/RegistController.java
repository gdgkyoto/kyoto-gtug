package com.appspot.eitan.controller.translate;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.appspot.eitan.dao.WordInfoDao;
import com.appspot.eitan.model.WordInfo;
import com.google.appengine.api.datastore.Text;

public class RegistController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(RegistController.class.getName());

    private WordInfoDao _wordinfodao = new WordInfoDao();
    
    @Override
    public Navigation run() {
        if (!validate()) {
            return forward(basePath);
        }
        
        String key = requestScope("wordinfokey");
        WordInfo wordInfo = _wordinfodao.getByKey(key);
        if (wordInfo != null) {
            wordInfo.setMemo(getMemo());
            _wordinfodao.update(wordInfo);
        }
        return forward("regist.jsp");
    }
    
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("memo", v.required());
        return v.validate();
    }
    
    private Text getMemo()
    {
        String memo = requestScope("memo");
        return new Text(memo);
    }
}
