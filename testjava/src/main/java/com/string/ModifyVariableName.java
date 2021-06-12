package com.string;

public class ModifyVariableName {
    public static void main(String[] arg) {
        String input = "this_is_var";
        String input1 = "thisIsVar";
        String output = modifyVarNameCtoJava(input);
        System.out.println("input is : " + input + " output is " + output);
        String output1 = modifyVarNameJavatoC(input1);
        System.out.println("input is : " + input1 + " output is " + output1);
    }

    private static String modifyVarNameJavatoC(String input) {
        if (input.isEmpty())
            return input;
        String result = "";
        char[] charArr = input.toCharArray();
        for (char c : charArr) {
            if (Character.isUpperCase(c)) {
                result = result + "_";
            }
            result = result + c;
        }

        return result.toLowerCase();
    }

    private static String modifyVarNameCtoJava(String input) {
        if (input.isEmpty()) {
            return input;
        }

        String result = "";
        char[] charArr = input.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '_') {
                i++;
                char chr = Character.toUpperCase(charArr[i]);
                result += chr;
            } else {
                result += charArr[i];
            }
        }

        return result;
    }
}
