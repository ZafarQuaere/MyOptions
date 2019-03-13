package com.test;


import java.util.Base64;

/**
 * Created by parasmani.sharma on 08/02/2017.
 */

public class Encode {

    public static void main(String arg[])
    {
        String str = "amitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamitamit";

        byte[] bytes = str.getBytes();
        //String encodedString = Base64.encodeToString(bytes, 0);
        Base64.Encoder encodedString = Base64.getEncoder();
        byte[] st = encodedString.encode(bytes);

        System.out.println( "data >> " +  st.toString());
    }


}



