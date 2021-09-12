package com.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by amit on 28-11-2016.
 */
public class StringTest
{
    public static void main(String args[])
    {
        int[] out = check();
        for(int index = 0; index < out.length; ++index) {
            System.out.println("value at " + index + " is : " + out[index]);
        }
        if(out != null) {
            if(out[0] == 1) {
                if(out[1] == 1) {
                    System.out.println("showing in only :)");
                } else if(out[2] == 1) {
                    System.out.println("showing us only :)");
                } else {
                    System.out.println("hiding :)");
                }
            } else {
                System.out.println("showing all:)");
            }
        } else {
            System.out.println("showing all");
        }
    }

    private static int[] check() {
        System.out.println("check");
        String[] ins = new String[]{"--Any help--", "--Any suggestions--", "--Thanks in advance--"};
        int[] outs = new int[ins.length];
        StringBuilder s = readFromAssets();
        System.out.println("" + s);

        for(int index = 0; index < outs.length; index++) {
            System.out.println("index is : " + s.indexOf(ins[index]));
            System.out.println("" + (s.indexOf(ins[index]) > -1));
            outs[index] = s.indexOf(ins[index]) > -1?1:0;
        }

        return outs;
    }

    private static StringBuilder readFromAssets() {
        System.out.println("readFromAssets");
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            URL e = new URL("http://stackoverflow.com/questions/40820950/how-to-work-on-tempogone1-4");
            reader = new BufferedReader(new InputStreamReader(e.openStream(), "UTF-8"));

            String mLine;
            while((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }
        } catch (IOException var12) {
            System.out.println(var12.toString());
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }
        return builder;
    }
}
