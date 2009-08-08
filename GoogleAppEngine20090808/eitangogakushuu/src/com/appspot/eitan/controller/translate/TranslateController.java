package com.appspot.eitan.controller.translate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.appspot.eitan.dao.UserInfoDao;
import com.appspot.eitan.dao.WordInfoDao;
import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.model.WordInfo;
import com.appspot.eitan.model.beans.Meaning;
import com.appspot.eitan.model.beans.RefInfo;
import com.appspot.eitan.search.SmartFmItemSearcher;


public class TranslateController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(TranslateController.class.getName());

    private WordInfoDao _worddao = new WordInfoDao();
    private UserInfoDao _userdao = new UserInfoDao();

    @Override
    public Navigation run() {
        if (!validate()) {
            return forward(basePath);
        }
        String spell = requestScope("spell");

        WordInfo wordInfo = _worddao.getBySpell(spell);
        if(wordInfo == null){
            wordInfo = new WordInfo();
            SmartFmItemSearcher searcher = new SmartFmItemSearcher();
            List<Meaning> meaninglist = searcher.search(spell);
            wordInfo.setSpell(spell);
            wordInfo.setMeaninglist(meaninglist);
            wordInfo = _worddao.insert(spell,meaninglist,null,null);
        }
        UserInfo loginUser = (UserInfo)request.getSession().getAttribute("loginUser");
        if(loginUser != null){
            HashMap<String, RefInfo> refmap = wordInfo.getRefmap();
            if(refmap == null){
                refmap = new HashMap<String, RefInfo>();
                wordInfo.setRefmap(refmap);
            }
            RefInfo refInfo = refmap.get(loginUser.getKey());
            if(refInfo == null){
                refInfo = new RefInfo();
                refmap.put(loginUser.getKey(),refInfo);
            }
            refInfo.incrementRefCount();
            refInfo.lastSearch = new Date();
            wordInfo = _worddao.update(wordInfo);

            _userdao.addWordKey(loginUser.getKey(),wordInfo.getKey());
        }
        requestScope("wordInfo", wordInfo);

        return forward("confirm.jsp");
    }

    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("spell", v.required());
        return v.validate();
    }
}
