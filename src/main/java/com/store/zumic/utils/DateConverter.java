package com.store.zumic.utils;

import java.text.ParseException;
import java.time.LocalDate;


public class DateConverter {

    public static LocalDate StringToDateConverter(String date) throws ParseException {
//
        LocalDate newDate = LocalDate.parse(date);
        return newDate;
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
//        LocalDate newDate = formatter.parse(date);
        //return newDate;
    }


}
