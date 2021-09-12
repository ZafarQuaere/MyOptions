package com.string;

public class StringAnagram {

    public static void main(String[] arg) {
        String a = "aabcca";
        String b = "cbacba";
        checkAnagram(a,b);
    }

    private static void checkAnagram(String a, String b) {
        System.out.println("String 1: "+a+"\nString 2: "+b);
        if (a.length() != b.length()) {
            System.out.println("Not an Anagram");
            return;
        }

        int[] cArr = new int[256];

        for (char c : a.toCharArray()){
            int valueOfChar = (int)c;
            cArr[valueOfChar]++;
        }

        for (char c: b.toCharArray()) {
            int valueOfChar = (int) c;
            cArr[valueOfChar]--;
        }

        for (int i = 0; i < 256; i++) {
            if (cArr[i] != 0) {
                System.out.println(a + " and "+b +" are not anagram, difference is :"+(char)i);
            }
        }
    }
}
