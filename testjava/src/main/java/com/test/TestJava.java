package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class TestJava {
    public static void main(String args[]) {

        //System.out.println(isValidPassword("1111_@123"));;

        //String test = "i am test";
        //System.out.println("\"" + test + "\"");

        //System.out.println(getBirthYear("02-17-1984"));

        //testSplit("978901$9780101$DEL,BOM$DEL-BOM/BOM-DEL/$2$1");

        //testComparator();
        //StringBuilder s = readFromAssets();

        // System.out.println(getHTML("DEL New Delhi, India - Indira Gandhi Intl - All Airports", "de"));

        //work in in only, work in us only
        int out[] = new int[2];
        out[0] = 1;
        out[1] = 0;


        if (out != null) {
            if (out[0] == 1 && getISO().equals("i")) {
                System.out.println("showing i:)");
            } else if (out[1] == 1 && getISO().equals("u")) {
                System.out.println("showing u:)");
            } else {
                System.out.println("hiding :)");
            }
        } else {
            System.out.println("showing");
        }


    }

    public static String getISO() {
        return "i";
    }

    public static StringBuilder readFromAssets() {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL("http://stackoverflow.com/questions/15050499/android-custom-view-redraw");
            //URL url = new URL("http://stackoverflow.com/questions/24852219/android-buildscript-repositories-jcenter-vs-mavencentral");

            reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //System.out.println(mLine);
                builder.append(mLine);
            }
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        System.out.println(builder.toString());
        return builder;
    }

    public static boolean isValidPassword(String password) {

        int size = password.length();
        int pos_lastLetter = size - 1;
        if (password.length() > 5 && password.length() < 20) {
            if (Character.isDigit(password.charAt(0)) || Character.isLetter(password.charAt(0))) {
                if (Character.isDigit(password.charAt(pos_lastLetter)) || Character.isLetter(password.charAt(pos_lastLetter))) {
                    for (int i = 0; i < password.length(); i++) {
                        if (Character.isLetter(password.charAt(i)) || Character.isDigit(password.charAt(i)) || password.charAt(i) == '@' || password.charAt(i) == '.' || password.charAt(i) == '_' || password.charAt(i) == '-') {

                        } else {
                            return false;
                        }
                    }
                    return true;
                } else {
                    //System.out.println("Last Letter of password should be alpha or numeric");
                    return false;
                }
            } else {
                //System.out.println("First Letter of password should be alpha or numeric");
                return false;
            }
        } else {
            //System.out.println("Enter a valid 6-20 character password using only alphabet (a-z), number (0-9), underscore, hyphen, @ or period. The first and the last character must be an alphabet or a number. Passwords must match.");
            return false;
        }
    }


    private static String getHTML(String txtSuggestion, String txtEntered) {

        StringBuilder finalText = new StringBuilder(txtSuggestion);
        try {
            String[] split = txtSuggestion.toLowerCase().split(txtEntered.toLowerCase());
            int count = 0;
            System.out.println(split.length - 1);
            for (int index = 0; index < split.length; index++) {
                int indexOf = txtSuggestion.toLowerCase().indexOf(txtEntered.toLowerCase(), count);
                System.out.println("indexOf : " + indexOf);
                if (indexOf == -1) {
                    System.out.println("getHTML >> " + txtSuggestion + "/" + txtEntered + " > " + finalText.toString());
                    return finalText.toString();
                }
                String temp = txtSuggestion.substring(indexOf, indexOf + txtEntered.length());
                System.out.println("temp : " + temp);
                String re = "<font color='red'>" + temp + "</font>";
                System.out.println(finalText.toString());
                finalText.replace(index == 0 ? indexOf : indexOf + re.length() - 2, indexOf + txtEntered.length() + +re.length() - 2, re);

                System.out.println(finalText.toString());
                if (index == 0) {
                    //return "";
                }


                count = indexOf + txtEntered.length();
                System.out.println("count: " + count);

            }
        } catch (Exception e) {
            System.out.println("getHTML >> " + txtSuggestion + "/" + txtEntered + " > " + txtSuggestion);
            return txtSuggestion;
        }
        System.out.println("getHTML >> " + txtSuggestion + "/" + txtEntered + " > " + finalText.toString());
        return finalText.toString();
    }

    private static void testComparator() {
        ArrayList<PriceData> list = new ArrayList<>();

        PriceData d1 = new PriceData();
        d1.setPrice(10);
        d1.setName("Orange");
        list.add(d1);

        PriceData d2 = new PriceData();
        d2.setPrice(5);
        d2.setName("Apple");
        list.add(d2);

        PriceData d3 = new PriceData();
        d3.setPrice(15);
        d3.setName("mango");
        list.add(d3);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Before sorting : " + list.get(i).getPrice());
        }

        /*Collections.sort(list, new Comparator<PriceData>() {
            @Override
            public int compare(PriceData o1, PriceData o2) {
                return o1.toString().compareTo(o2.toString());

            }
        });*/

        Collections.sort(list, new Comparator<PriceData>() {
            @Override
            public int compare(PriceData o1, PriceData o2) {
                if (o1.getPrice() == o2.getPrice()) {
                    return 0;
                } else if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                } else {
                    return -1;
                }

            }
        });


        for (int i = 0; i < list.size(); i++) {
            System.out.println("After sorting : " + list.get(i).getPrice());
        }
    }

    public static String getBirthYear(String dob) {
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date date = sf.parse(dob);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return String.format("%02d", (cal.get(Calendar.MONTH) + 1));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void testSplit(String string) {
        String[] ary = string.split("\\$");
        System.out.println(ary[0]);
    }

}
