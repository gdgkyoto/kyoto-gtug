package com.appspot.eitan.model.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * �Q�Ə��
 * @author Shinji Otsubo
 *
 */
public class RefInfo implements Serializable{
    private static final long serialVersionUID = -8918135097760836862L;

//    enum EnumExamResult {POOR, AVERAGE, GOOD};   //  �����A���ʁA�ǂ� �̎O�i�K

    /** ×をあらわす評価 */
    public static final int POOR        = 1 ;

    /** △をあらわす評価 */
    public static final int AVERAGE     = 2 ;

    /** ○をあらわす評価 */
    public static final int GOOD        = 3 ;

    /** �Q�Ɖ� */
    public int refCount = 0;
    /** �ŏI������ */
    public Date lastSearch = null;
    /** �ŏI������ */
    public Date lastExam = null;

    public int getRefCount() {
        return refCount;
    }
    public void setRefCount(int refCount) {
        this.refCount = refCount;
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
     * �Q�ƊK�����C���N�������g����
     * @return
     */
    public int incrementRefCount(){
        if(refCount < Integer.MAX_VALUE) ++refCount;    //  �ꉞ�I�[�o�[�t���[�΍�
        return refCount;
    }

    public int examResult ;

    public int getExamResult() {
        return examResult;
    }
    public void setExamResult(int examResult) {
        this.examResult = examResult;
    }


//  /** �������� */
//  public EnumExamResult examResult = EnumExamResult.AVERAGE;
//
//  public EnumExamResult getExamResult() {
//      return examResult;
//  }
//  public void setExamResult(EnumExamResult examResult) {
//      this.examResult = examResult;
//  }

}
