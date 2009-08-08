package com.appspot.eitan.controller.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.model.WordInfo;
import com.appspot.eitan.model.beans.Meaning;

public class SelectExamWordController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(SelectExamWordController.class.getName());

    @Override
    public Navigation run() {

        WordInfo word2 = new WordInfo();
        word2.setSpell("diet");
        Meaning meaning2 = new Meaning("食事",Meaning.WordCategory.OTHER);
        Meaning meaning3 = new Meaning("国会",Meaning.WordCategory.OTHER);
        List<Meaning> meaningList2 = new ArrayList<Meaning>();
        meaningList2.add(meaning2);
        meaningList2.add(meaning3);
        word2.setMeaninglist(meaningList2);

        // スタブの部分（ここから）
        WordInfo word1 = new WordInfo();
        word1.setSpell("apple");
        Meaning meaning1 = new Meaning("りんご",Meaning.WordCategory.OTHER);
        List<Meaning> meaningList1 = new ArrayList<Meaning>();
        meaningList1.add(meaning1);
        word1.setMeaninglist(meaningList1);


        // スタブの部分（ここまで）

        List<WordInfo> wordList = new ArrayList<WordInfo>();
        wordList.add(word2);
        wordList.add(word1);


        // セッションに単語リストをセット
        sessionScope("wordList",wordList);

        //各種情報をリクエストにセット
        int index = 1;
        requestScope("word",wordList.get(0));
        requestScope("index",index);

        return forward("exam.jsp");
    }
}
