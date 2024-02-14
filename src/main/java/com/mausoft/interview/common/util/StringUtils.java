package com.mausoft.interview.common.util;

public class StringUtils {
    public static void reverse(StringBuilder str, int start, int end) {
        while (start < end) {
            char tmp = str.charAt(start);
            str.setCharAt(start, str.charAt(end));
            str.setCharAt(end, tmp);
            start++;
            end--;
        }
    }
}