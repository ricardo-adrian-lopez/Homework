package com.mobileapps.training.testfriday;

import java.util.Stack;

public class CorrectBrackets {

    public static void main(String args[]) {

        System.out.println(correctParenthesisBrackets("({})[]"));
        System.out.println(correctParenthesisBrackets("[]{]}["));
    }

    public static boolean correctParenthesisBrackets(String string) {
        Stack<Character> stack  = new Stack<>();
        for(int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if(c == '[' || c == '(' || c == '{' ) {
                stack.push(c); //Continue pushing until one is different
            } else if(c == ']') {
                if(stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else if(c == ')') {
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if(c == '}') {
                if(stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
