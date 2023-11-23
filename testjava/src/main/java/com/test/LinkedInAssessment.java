package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LinkedInAssessment {

    static int count = 0;
    public static void main(String[] arg) {
       /* if (count < 3){ // it will print Hi 3 times.
            count++;
            main(null);
        } else {
            return;
        }
        System.out.println("Hi ");*/

        arrayDecInit();
        anotherExample();
        llExample();
        arrExample();
//        tryCatchEg();
    }

    private static void tryCatchEg() {
        // this try catch will print A,D as we cannot catch error, we are catching exception.
        /*try {
            System.out.println("A");
            badMethod();
            System.out.println("B");
        } catch (Exception ex){
            System.out.println("C");
        } finally {
            System.out.println("D");
        }*/

        // here this will not compile as Exception catching all type of exception, AE is not reachable
       /* try {
            System.out.println("Hello World");
        }catch (Exception e){
            System.out.println("e");
        } catch (ArithmeticException ae){
            System.out.println("ae");
        } finally {
            System.out.println("!");
        }*/

       /* try {
            Scanner scanner = new Scanner(System.in);
            scanner.next();
            System.out.println("try");
        }
        catch (IOException ie){
            System.out.println("");
        }catch (IllegalArgumentException ie){
            System.out.println("ie");
        }*//*catch (IllegalArgumentException | IOException e){

        }*/
    }

    private static void badMethod() {
        throw new Error();
    }

    private static void arrExample() {
        int[] samples = {8,5,3,1};
        System.out.println(samples[2]);
        char myChar = "Zafar".charAt(3);
        System.out.println(myChar);
        // what stmt is true if nifty is String
        System.out.println("nifty".getClass().getSimpleName() == "String");
//        System.out.println("nifty".getType().equals("String"));
        System.out.println("nifty" instanceof  String);
//        System.out.println("nifty" .getType() ==  String);
    }

    private static void llExample() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(55);
        ll.add(100);
        ll.add(10);
        System.out.println(ll);
    }

    private static void arrayDecInit() {
//        ArrayList words = Arrays.asList("Hello", "World"); // not valid
//        ArrayList<String> words1 = new ArrayList<String>() {"Hello","World"}; // not valid
//        ArrayList<String> words2 = {"Hello", "World"}; // not valid
        ArrayList<String> words3 = new ArrayList<>(Arrays.asList("Hello", "World")); // Valid

    }

    private static void anotherExample() {
        String[] arr = new String[]{"A","B","C"};
        List<String> list = Arrays.asList(arr);
        List<String> list2 = new ArrayList<>(Arrays.asList(arr));
        List<String> list3 = new ArrayList<>(Arrays.asList("A", new String("B"),"C"));
        System.out.println(list.equals(list2)); // true
        System.out.println(list.equals(list3)); // true
    }
}
