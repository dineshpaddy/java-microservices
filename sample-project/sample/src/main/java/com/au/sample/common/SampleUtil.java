package com.au.sample.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleUtil {

    public static String convertDate(String dateToParse) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format1.parse(dateToParse);
        return format2.format(date).toString();
    }
}
