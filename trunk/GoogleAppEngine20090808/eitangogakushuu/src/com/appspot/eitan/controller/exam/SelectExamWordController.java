package com.appspot.eitan.controller.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.model.WordInfo;
import com.appspot.eitan.util.Filtering;
import com.appspot.eitan.util.ListUtil;
import com.appspot.eitan.util.SearchCountFiltering;
import com.appspot.eitan.util.StatusFiltering;

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

        List<Filtering> filterList = new ArrayList<Filtering>();

        Integer searchCount = asInteger("searchCount");
        if (searchCount != null) {
            filterList.add(new SearchCountFiltering(
                userInfo.getKey(),
                searchCount));
        }

        String[] parameterValues = request.getParameterValues("status");
        if (parameterValues != null) {
            int[] numAry = new int[parameterValues.length];
            for (int i = 0; i < parameterValues.length; i++) {
                numAry[i] = Integer.valueOf(parameterValues[i]);
            }

            filterList.add(new StatusFiltering(userInfo.getKey(), searchCount));
        }

        for (Filtering f : filterList) {
            wordList = ListUtil.instance().filter(wordList, f);
        }

        Integer wordCount = asInteger("wordCount");
        if (wordCount != null) {
            if (wordList.size() > wordCount) {
                wordList = wordList.subList(0, wordCount);
            }
        }

        // セッションに単語リストをセット
        sessionScope("wordList", wordList);

        List<String> wordKeyList = new ArrayList<String>();
        for (int i = 0; i < wordList.size(); i++) {
            WordInfo wi = wordList.get(i);
            String key = wi.getKey();
            wordKeyList.add(key);
        }
        sessionScope("wordKeyList", wordKeyList);

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
