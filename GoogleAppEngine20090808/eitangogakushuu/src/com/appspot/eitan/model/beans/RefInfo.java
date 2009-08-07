package com.appspot.eitan.model.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 参照情報
 * @author Shinji Otsubo
 *
 */
public class RefInfo implements Serializable{
    private static final long serialVersionUID = -8918135097760836862L;

    enum EnumExamResult {POOR, AVERAGE, GOOD};   //  悪い、普通、良い の三段階

    /** 参照回数 */
    public int refCount = 0;
    /** 試験結果 */
    public EnumExamResult examResult = EnumExamResult.AVERAGE;
    /** 最終検索日時 */
    public Date lastSearch = null;
    /** 最終試験日時 */
    public Date lastExam = null;

    public int getRefCount() {
        return refCount;
    }
    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }
    public EnumExamResult getExamResult() {
        return examResult;
    }
    public void setExamResult(EnumExamResult examResult) {
        this.examResult = examResult;
    }
    public Date getLastSearch() {
        return lastSearch;
    }
    public void setLastSearch(Date lastSearch) {
        this.lastSearch = lastSearch;
    }
    public Date getLastExam() {
        return lastExam;
    }
    public void setLastExam(Date lastExam) {
        this.lastExam = lastExam;
    }

    /**
     * 参照階数をインクリメントする
     * @return
     */
    public int incrementRefCount(){
        if(refCount < Integer.MAX_VALUE) ++refCount;    //  一応オーバーフロー対策
        return refCount;
    }

}
