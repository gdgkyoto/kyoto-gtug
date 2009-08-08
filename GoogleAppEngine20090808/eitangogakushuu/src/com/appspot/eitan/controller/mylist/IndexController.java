package com.appspot.eitan.controller.mylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.model.WordInfo;
import com.appspot.eitan.model.beans.RefInfo;
import com.appspot.eitan.model.beans.WordDispInfo;

public class IndexController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    @Override
    public Navigation run() {
        UserInfo user = (UserInfo)request.getSession().getAttribute("loginUser");
        if (user == null)
            return forward(basePath);
        
        List<WordInfo> wordinfolist = user.getWordList();

        List<WordDispInfo> worddispinfolist = new ArrayList<WordDispInfo>(); 
        for (WordInfo wi : wordinfolist) {
            String spell = wi.getSpell();
            RefInfo refInfo = wi.getRefmap().get(user.getKey());
            int count = refInfo.refCount;
            int publicCount = wi.getPublicCount();
            int status = refInfo.examResult;
            worddispinfolist.add(new WordDispInfo(count, publicCount, spell, status));
        }
        
        requestScope("worddispinfolist", worddispinfolist);
        
        return forward("index.jsp");
    }
}

class ComparatorBySpell implements Comparator<WordInfo> {

    @Override
    public int compare(WordInfo o1, WordInfo o2) {
        return o1.getSpell().compareTo(o2.getSpell());
    }
}
