package com.appspot.eitan.model.beans;

import java.io.Serializable;

/**
 * �P���
 * @author Shinji Otsubo
 *
 */
public class Meaning implements Serializable {
    private static final long serialVersionUID = -4504952574361249686L;

    public static enum WordCategory {OTHER}; //   http://www.sk.cs.chubu.ac.jp/~joy/japanese/hinshi/00-hinshi.html ���11��� �����A����͎��Ԃ��Ȃ��̂� �S��OTHER�Ƃ���


    /** �i�� */
    private WordCategory category = WordCategory.OTHER;

    /** �a�� */
    private String jptext;

    public Meaning(String mean,WordCategory wc){
        this.jptext = mean;
        this.category = wc;

    }

    public WordCategory getCategory() {
        return category;
    }

    public String getJptext() {
        return jptext;
    }

}
