package com.appspot.eitan.controller.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.model.WordInfo;

public class SelectExamWordController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(SelectExamWordController.class.getName());

    @Override
    public Navigation run() {

        if (!validate()) {
            return forward(basePath);
        }

        UserInfo userInfo = (UserInfo) sessionScope("loginUser");
        List<WordInfo> wordList = userInfo.getWordList();

        // セッションに単語リストをセット
        sessionScope("wordList", wordList);

        List<String> wordKeyList    = new ArrayList<String>();
        for(int i = 0 ; i < wordList.size();i++){
            WordInfo wi = wordList.get(i);
            String key  = wi.getKey();
            wordKeyList.add(key);
        }
        sessionScope("wordKeyList",wordKeyList);

        // 各種情報をリクエストにセット
        int index = 1;
        requestScope("word", wordList.get(0));
        requestScope("index", index);

        return forward("exam.jsp");
    }

    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("searchCount", v.integerType("検索回数は数字のみ"));
        v.add("wordCount", v.integerType("単語数は数字のみ"));
        return v.validate();
    }
}
