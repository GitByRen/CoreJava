package com.important.Util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public final static String EMPTY_STRING = "";

    public static void main(String[] args) {
        System.out.println(myTrim(" asd dd "));
        System.out.println("          ");
        System.out.println("********************");
        System.out.println(reverseArray("abcdefg".toCharArray(), 2, 5));
        System.out.println("********************");
        System.out.println(getTime("abkkcadkabkeasdbab", "ab"));
        System.out.println("********************");
        System.out.println(getSubstr("abcwerhelloadf", "dfsahellos"));
    }

    /**
     * 判断是否为Null或空
     * 
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        return (null == obj || EMPTY_STRING.equals(obj));
    }

    // 1.模拟一个trim()方法
    public static String myTrim(String str) {
        int start = 0;
        int last = str.length() - 1;
        // start < last 避免全是空
        while (start < last && str.charAt(start) == ' ') {
            start++;
        }
        while (start < last && str.charAt(last) == ' ') {
            last--;
        }
        return str.substring(start, last);
    }

    // 2.反转字符串"abcdefg"反转为"abfedcg"
    public static String reverseArray(char[] c, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
        }
        return new String(c);
    }

    // 3.获取一个字符串在另一个字符串中出现的次数"abkkcadkabkeasdbab"
    public static int getTime(String src, String dest) {
        int count = 0;
        int index = 0;
        while ((index = src.indexOf(dest)) != -1) {
            count++;
            src = src.substring(index + dest.length());
        }
        return count;
    }

    // 4.获取两个字符串中最大的相同子串
    public static List<String> getSubstr(String src, String dest) {
        String maxStr = (src.length() > dest.length()) ? src : dest;
        String minStr = (src.length() < dest.length()) ? src : dest;
        int len = minStr.length();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int x = 0, y = len - i; y <= len; x++, y++) {
                String str = minStr.substring(x, y);
                if (maxStr.contains(str)) {
                    list.add(str);
                }
            }
            if (list.size() != 0) {
                return list;
            }
        }
        return null;
    }
}
