package com.string;

/**
 * Created by amit on 16-06-2017.
 */
public class ReverseString
{
    public static void main(String args[])
    {
        System.out.println(getReverse("(ReverseThisString)"));
    }

    private static String getReverse(String string)
    {
        char val[] = new char[string.length()];
        for (int index = 0; index < string.length(); index++)
        {
            if(val[index] != 0)
            {
                break;
            }
            val[index] = string.charAt(string.length() - 1 - index);
            val[string.length() - 1 - index] = string.charAt(index);
            System.out.println("loop count : " + (index + 1) + ", " + (new String(val)));
        }
        return new String(val);
    }
}
