package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by zafar.imam on 15-11-2016.
 */

public class DateFormat {

    public static String dateString = "FEB-01-1977";
    public static String formattedDate;

    public static void main(String[] arg) {
        String getDateFormat = dateFormat(dateString);
        System.out.println("User Format :" + dateString);

        System.out.println("Returned Value is : " + getReturnedNumber());

    }

    private static int getReturnedNumber() {
        try {
            return 2;
        } finally {
            return 1;
        }
    }

    public static String dateFormat(String dateString) {
        SimpleDateFormat userFormat = new SimpleDateFormat("MMM-dd-yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM yyyy");

        try {
            formattedDate = myFormat.format(userFormat.parse(dateString));
            System.out.println("My Format :" + formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }
}
