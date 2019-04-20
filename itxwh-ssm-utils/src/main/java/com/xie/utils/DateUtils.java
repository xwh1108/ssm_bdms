package com.xie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateToString(Date date, String patt){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        Date parse = simpleDateFormat.parse(str);
        return parse;

    }
}
