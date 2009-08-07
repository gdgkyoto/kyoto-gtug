package com.appspot.eitan.model.beans;

import java.io.Serializable;

import com.google.appengine.api.datastore.Text;

/**
 * �P���
 * @author Shinji Otsubo
 *
 */
public class Meaning implements Serializable {
    private static final long serialVersionUID = -4504952574361249686L;

    enum WordCategory {OTHER}; //   http://www.sk.cs.chubu.ac.jp/~joy/japanese/hinshi/00-hinshi.html ���11��� �����A����͎��Ԃ��Ȃ��̂� �S��OTHER�Ƃ���


    /** �i�� */
    public WordCategory category = WordCategory.OTHER;

    /** �a�� */
    public Text jptext;
}
