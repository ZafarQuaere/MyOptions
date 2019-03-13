package com.decode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zafar.imam on 26-07-2017.
 */

public class DateDemo {

    private static Date time;

    public static void main(String arg[]){

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        System.out.println("calendar.getTime() : "+calendar.getTime());
         time = calendar.getTime();

       // System.out.println(" time.getDay() : "+ time.getDay()+" \n time.getMonth() : "+ time.getMonth()+" \n time.getYear() : "+ time.getYear());
        //System.out.println(calendar.)
        dateFormat();

    }

    private static void dateFormat() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        System.out.println(dateFormat.format(time));

        String stringDate = dateFormat.format(time);

    }
}
