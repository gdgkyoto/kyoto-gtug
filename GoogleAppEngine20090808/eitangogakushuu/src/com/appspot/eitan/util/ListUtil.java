package com.appspot.eitan.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    private static ListUtil instance = new ListUtil();

    private ListUtil() {
    }

    public <T> List<T> filter(List<T> list, Filtering filter) {
        List<T> result = new ArrayList<T>();

        for (T t : list) {
            if (filter.execute(t)) {
                result.add(t);
            }
        }

        return result;
    }

    public static ListUtil instance() {
        return instance;
    }
}
