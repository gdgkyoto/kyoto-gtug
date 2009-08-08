package com.appspot.eitan.model.beans;

import java.io.Serializable;

/**
 * �P���
 * @author Shinji Otsubo
 *
 */
public class Meaning implements Serializable {
    private static final long serialVersionUID = -4504952574361249686L;
    
    //   http://www.sk.cs.chubu.ac.jp/~joy/japanese/hinshi/00-hinshi.html ���11��� �����A����͎��Ԃ��Ȃ��̂� �S��OTHER�Ƃ���
    public static enum WordCategory {
        /** meishi */
        NOUN,
        /** daimeishi */
        PRONOUN,
        /** doushi */
        VERB,
        /** keiyoushi */
        ADJECTIVE,
        /** hukushi */
        ADVERB,
        /** zenchishi */
        PREPOSITION,
        /** setuzokushi */
        CONJUNCTION,
        /** kandoushi */
        INTERJECTION,
        /** ku */
        PHRASE,
        /** sonota */
        OTHER;
        
        public static WordCategory fromString(String s) {
            if (s.equals("Noun"))         return NOUN;
            if (s.equals("Pronoun"))      return PRONOUN;
            if (s.equals("Verb"))         return VERB;
            if (s.equals("Adjective"))    return ADJECTIVE;
            if (s.equals("Adverb"))       return ADVERB;
            if (s.equals("Preposition"))  return PREPOSITION;   
            if (s.equals("Conjunction"))  return CONJUNCTION;       
            if (s.equals("Interjection")) return INTERJECTION;  
            if (s.equals("Phrase"))       return PHRASE;
            return OTHER;
        }
    }


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
