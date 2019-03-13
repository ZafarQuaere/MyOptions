package com.decode;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by amit on 02-12-2016.
 */
public class DecodeTest
{
    public static void main(String args[])
    {
        /*try {
            String encodedURL = URLEncoder.encode("http://www.google.com", "UTF-8");
            System.out.println(encodedURL);

            String decodedURL = URLDecoder.decode(encodedURL, "UTF-8");
            System.out.println(decodedURL);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        String s1 = test1("https://www.google.com", true);
        System.out.println(s1);

        String s2 = test1(s1, false);
        System.out.println(s2);

    }

    public static String test1(String input, boolean encode)
    {
        char ary[] = new char[input == null ? 0 : input.length()];
        for (int index = 0; index < ary.length; index++) {
            ary[index] = (char)((int)input.charAt(index) + (encode ? 123 : -123));
        }
        return new String(ary);
    }

}
