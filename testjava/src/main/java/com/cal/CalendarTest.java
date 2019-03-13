package com.cal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by amit on 13-04-2017.
 */
public class CalendarTest
{
    public static void main(String args[]) {
        try
        {
            Calendar cal = Calendar.getInstance(Locale.ITALIAN);

            SimpleDateFormat f = new SimpleDateFormat("MMM", Locale.ITALY);
            Date may = f.parse("MAY");

            System.out.println("Converted : " + may);
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }


    }
}
