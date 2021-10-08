package com.string;

public class FISGlobal {

    boolean isOriginal = true;
    public static String str = "Hello";
    public static String origStr = "";
    public static int n1 = 2, n2 = 3;
    public static int count = 0;

    public static void main(String[] arg) {
        FISGlobal sc = new FISGlobal();
//        findEquals();
        int count = sc.stringManip(str, n1, n2);
        System.out.println(count);
    }

    private int stringManip(String str, int n1, int n2) {
        if (isOriginal) {
            origStr = str;
            System.out.println(origStr);
            isOriginal = false;
        }
        String harStr = str.substring(n1, str.length());
        String sejStr = str.substring(0, n2-1);
        System.out.println("harString: "+harStr+"  sejStr: "+sejStr);
        String newStr = harStr + sejStr;
        System.out.println(newStr);
        count++;
        if (!origStr.equals(newStr)){
            stringManip(newStr,n1,n2);
        }
        return count;
    }


    private static void findEquals() {
        if (!"Hello".equals("lloHe")) {
            System.out.println("Hello" +"equals()"+ "lloHe");
        }
        if (!"Hello".equals("Hello")) {
            System.out.println("Hello equals() Hello");
        }
        if ("Hello" == "lloHe"){
            System.out.println("Hello" +" == "+ "lloHe");
        }
    }
}
