package com.music_demo.function;


import java.text.SimpleDateFormat;
import java.util.Date;

public class dataFormat {
    private final static Date date = new Date();

    //数字格式
    public static Integer IntegerFormat() {
        String time = "" + date.getYear() + date.getMonth() + date.getDay();
        return Integer.valueOf(time);
    }

    //格式
    public static String dateFormat(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    //转换数据库时间
    public static java.sql.Date sqlTransition() {
        return new java.sql.Date(date.getTime());
    }

    //获取当前的时间剩余的秒数
    public static int getResidue() {
        int s = (date.getHours() * 60 + date.getMinutes()) * 60 + date.getSeconds();
        return s;
    }

    //获取当前月份
    public static int getMonth() {
        return date.getMonth();
    }

    //获取当前年份
    public static int getYeay() {
        return date.getYear();
    }
}
