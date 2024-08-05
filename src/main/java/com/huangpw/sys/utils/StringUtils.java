package com.huangpw.sys.utils;

/**
 * 字符串操作的公共方法
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * 为空返回true,不为空返回false
     * @param msg
     * @return
     */
    public static boolean isEmpty(String msg) {
        return "".equals(msg) || msg == null;
    }
}
