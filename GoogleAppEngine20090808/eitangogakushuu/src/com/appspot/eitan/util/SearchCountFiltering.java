package com.appspot.eitan.util;

import com.appspot.eitan.model.WordInfo;

public class SearchCountFiltering implements Filtering {
    private String loginKey;
    private int searchCount;

    public SearchCountFiltering(String loginKey, int searchCount) {
        this.loginKey = loginKey;
        this.searchCount = searchCount;
    }

    @Override
    public boolean execute(Object obj) {
        WordInfo w = (WordInfo) obj;
        int refCount = w.getRefmap().get(loginKey).refCount;
        return refCount >= searchCount;
    }

}
