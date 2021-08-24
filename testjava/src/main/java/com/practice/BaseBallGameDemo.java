package com.practice;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
/*
Int = new score
+ = new score which is sum of previous 2 score
D = new score which is double of previous score
C = invalidate previous score, guarantee there is previous score.

 */
public class BaseBallGameDemo {
    public static void main(String[] arg){
//        Scanner sc = new Scanner(System.in);
//        String[] ops = sc.nextLine().split(" ");
        String[] ops = new String[]{"5","2","C","D","+"};
        System.out.println(findSolution(ops));
        System.out.println(calPoints(ops));
    }

    private static int findSolution(String[] ops) {
//        int result = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (String op: ops) {
            switch (op){
                case "+" :
                    int x = stack.pop();
                    int y = stack.pop();
                    stack.push(y);
                    stack.push(x);
                    stack.push(x+y);
                    break;
                case "D" :
                    stack.push(stack.peek()*2);
                    break;
                case "C" :
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.valueOf(op));
            }
        }
        int sum = 0;
        while (!stack.empty()){
            sum = sum + stack.pop();
        }
        return sum;
    }

    public static int calPoints(String[] ops) {
        int sum = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (String s : ops) {
            if (s.equals("C")) {
                sum -= list.removeLast();
            } else if (s.equals("D")) {
                list.add(list.peekLast() * 2);
                sum += list.peekLast();
            } else if (s.equals("+")) {
                list.add(list.peekLast() + list.get(list.size() - 2));
                sum += list.peekLast();
            } else {
                list.add(Integer.parseInt(s));
                sum += list.peekLast();
            }
        }
        return sum;
    }
}
