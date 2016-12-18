package com.dail.util;

import com.google.common.base.Strings;
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

    /**
     * 过滤特殊字符
     * @param str
     * @return
     */
    public static String filtersql(String str){
        if (Strings.isNullOrEmpty(str)){
            return "";
        }
        str=str.replaceAll("\\\\","\\\\\\\\\\\\\\\\");
        str=str.replaceAll("'","\\\\'");
        str=str.replaceAll("_","\\\\_");
        str=str.replaceAll("%", "\\\\%");
        str=str.replaceAll("\"","\\\\\"");
        return str;
    }
}
