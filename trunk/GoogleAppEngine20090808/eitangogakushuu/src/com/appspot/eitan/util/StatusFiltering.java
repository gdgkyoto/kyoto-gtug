package com.appspot.eitan.util;

import com.appspot.eitan.model.WordInfo;

public class StatusFiltering implements Filtering {

    private String loginKey;
    private int[] status;

    public StatusFiltering(String loginUserKey, int... status) {
        this.loginKey = loginUserKey;
        this.status = status;
    }

    @Override
    public boolean execute(Object t) {
        WordInfo w = (WordInfo) t;
        int examResult = w.getRefmap().get(loginKey).examResult;

        boolean found = false;
        for (int i : status) {
            if (examResult == i) {
                found = true;
                break;
            }
        }

        return found;
    }

}
