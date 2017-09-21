package com.netease.yuedu.snail.common.utils;

import org.joda.time.DateTime;

public final class DateUtil {
    private DateUtil() {
    }

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String toString(Long timeToken) {
        return toString(timeToken, DATE_TIME_FORMAT);
    }

    public static String toString(Long timeToken, String format) {
        DateTime dateTime = new DateTime(timeToken);
        return dateTime.toString(format);
    }

}
