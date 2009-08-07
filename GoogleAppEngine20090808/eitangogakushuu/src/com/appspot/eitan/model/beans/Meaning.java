package com.appspot.eitan.model.beans;

import java.io.Serializable;

import com.google.appengine.api.datastore.Text;

/**
 * 単語訳
 * @author Shinji Otsubo
 *
 */
public class Meaning implements Serializable {
    private static final long serialVersionUID = -4504952574361249686L;

    enum WordCategory {OTHER}; //   http://www.sk.cs.chubu.ac.jp/~joy/japanese/hinshi/00-hinshi.html より11種類 だが、今回は時間がないので 全てOTHERとする


    /** 品詞 */
    public WordCategory category = WordCategory.OTHER;

    /** 和訳 */
    public Text jptext;
}
