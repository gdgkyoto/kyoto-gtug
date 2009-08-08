package com.appspot.eitan.controller.exam;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.WordInfoDao;
import com.appspot.eitan.model.UserInfo;
import com.appspot.eitan.model.WordInfo;
import com.appspot.eitan.model.beans.RefInfo;

public class SaveResultController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(SaveResultController.class.getName());

    @Override
    public Navigation run() {

        UserInfo userInfo   = (UserInfo)sessionScope("loginUser");

        // jspからのsubmit情報を取得
        String score        = requestScope("score");        // 評価
        int scoreValue      = (Integer.parseInt(score));
        String wordKey      = requestScope("wordKey");      // Wordのキー
        String indexStr     = requestScope("index");        // テストしたリストのindex
        int index           = Integer.parseInt(indexStr);


        // テスト結果を更新
        WordInfoDao wid              = new WordInfoDao();
        WordInfo wordInfo            = wid.getByKey(wordKey);
        Map<String, RefInfo> refMap  = wordInfo.getRefmap();
        RefInfo refInfo              = refMap.get(userInfo.getKey());
        refInfo.setExamResult(scoreValue);
        wid.update(wordInfo);

        // 次のテスト内容
        List<String> wordKeyList    = (List<String>)request.getSession().getAttribute("wordKeyList");

        String nextWordKey  = wordKeyList.get(index);
        WordInfo nextwordInfo  =wid.getByKey(nextWordKey);

        requestScope("word",nextwordInfo);
        int nextIndex   = index+1;
        requestScope("index",nextIndex);

        if(nextIndex == wordKeyList.size()){

            // 最後の画面に今回の結果とか出せたらいいと思う。
            // 今回の結果 = 最新の結果
            return forward("finishExam.jsp");
        }
        return forward("exam.jsp");
    }
}
