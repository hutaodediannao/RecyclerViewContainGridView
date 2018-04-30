package com.hutao.app.imageprivewproject.util;

/**
 * Created by Administrator on 2018/4/29.
 */

public class StringUtil {

    public static boolean isEntryOrNull(String str) {
        if (null == str || str.length() == 0) {
            return true;
        }
        return false;
    }

}
