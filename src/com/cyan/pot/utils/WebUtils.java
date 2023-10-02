package com.cyan.pot.utils;

import java.time.LocalDateTime;

/**
    该工具类与文件上传有关
 */
public class WebUtils {
    public static final String FURN_IMG_DIRECTORY = "resources/images/product-image/";

    public static String getLocalDatePath() {
        LocalDateTime ldt = LocalDateTime.now();
        int year = ldt.getYear();
        int month = ldt.getMonthValue();   //获取月份的值
        int dayOfMonth = ldt.getDayOfMonth();
        String localDatePath = year + "/" + month + "/" + dayOfMonth + "/";

        return localDatePath;
    }
}
