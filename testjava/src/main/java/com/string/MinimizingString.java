package com.string;

import java.util.HashSet;
import java.util.Set;

public class MinimizingString {

    static Set<String> myset = new HashSet<String>();

    public static void main(String[] arg) {
        String s = "aabccabba";
//        int minStringLength = minimizeString(s);
        int minStringLength = stringReduction(s);
        System.out.println(minStringLength);
        int strReduct = stringRed(s);
        System.out.println(strReduct);
    }

    private static int stringRed(String str) {
        int i = 0;
        StringBuilder s = new StringBuilder(str);
        while (i < s.length() && s.length() > 1) {
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                s.deleteCharAt(i);
                s.deleteCharAt(i);
                if (i > 0) {
                    i--;
                }
            }
            i++;
        }
        System.out.println((s.length() > 0) ? s : "Empty String");
        return s.length();
    }

    private static int minimizeString(String s) {
        System.out.println("Main String " + s + " Length: " + s.length());
        // divide the string into two
        String left = s.substring(0, s.length() / 2 + 1);
        String right = s.substring(s.length() / 2, s.length());
        System.out.println(left + " " + right);

        //now append both String
        String appended = right + left;
        System.out.println("appended String: " + appended);
        if (right.charAt(right.length() - 1) == left.charAt(0)) {

        }
        return 0;
    }

    static int stringReduction(String a) {
        int result = 1;
        if (isSame(a)) {
            return a.length();
        }
        for (int i = 0; i < a.length() - 1; i++) {
            if (a.charAt(i) != a.charAt(i + 1)) {
                char newChar = requiredLetter(a.charAt(i), a.charAt(i + 1));
                //replace a[i] and a[i+1] to newChar;
                String s = String.valueOf(a.charAt(i)) + String.valueOf(a.charAt(i + 1));
                StringBuilder build = new StringBuilder(a);
                StringBuilder update = new StringBuilder(build.toString());
                update.replace(i, i + 2, String.valueOf(newChar));
                a = update.toString();
                if (myset.contains(a) == true) {
                    continue;
                }

                result = stringReduction(a);
                if (result != 1) {
                    a = build.toString();
                    myset.add(a);
                } else {
                    return result;
                }
            }
        }
        return result;
    }

    static char requiredLetter(char c, char d) {
        char ch = 'a';
        if ((c == 'a' && d == 'b') || (c == 'b' && d == 'a'))
            ch = 'c';
        if ((c == 'b' && d == 'c') || (c == 'c' && d == 'b'))
            ch = 'a';

        if ((c == 'a' && d == 'c') || (c == 'c' && d == 'a'))
            ch = 'b';
        return ch;
    }

    static boolean isSame(String x) {
        boolean same = true;
        for (int i = 0; i < x.length() - 1; i++) {
            if (x.charAt(i) != x.charAt(i + 1))
                return false;
        }
        return same;
    }
}
