package com.dail.util;

import org.jsoup.Jsoup;

/**
 * Created by Roger on 2016/12/13.
 */
public class StrUtil {

    public static String filterTag(String content) {
        return Jsoup.parse(content).text();
    }

    public static String getShortContent(String text, int maxLength) {
        text = filterTag(text);
        return text != null && text.length() > maxLength ? text.substring(0, maxLength) :text;
    }
}
