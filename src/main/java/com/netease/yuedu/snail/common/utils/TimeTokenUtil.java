package com.netease.yuedu.snail.common.utils;

public final class TimeTokenUtil {

    private TimeTokenUtil() {
    }

    public static String createTimeToken() {
        return DateUtil.toString(System.currentTimeMillis(), "yyyyMMddHHmmssSSS");
    }
}
