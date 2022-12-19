package org.example.problems;

import java.util.Stack;

public class StackProbs {
    public static void main(String[] args) {

        String s = "bxj##tw";
        String t = "bxj###tw";
        System.out.println(backspaceCompare(s, t));
    }

    //https://leetcode.com/problems/valid-parentheses/
    //remember to check for empty data structure, check all possible conditions. like else return false lines
    //String s = "]";
    private static boolean isValidParenthesis(String s) {
        Stack<Character> stringStack = new Stack<>();
        char[] chars = s.toCharArray();

        for (char currChar : chars) {
            if (currChar == '(' || currChar == '{' || currChar == '[') {
                stringStack.push(currChar);
            } else if (!stringStack.isEmpty()) {
                char topChar = stringStack.peek();
                if (topChar == '(' && currChar == ')') stringStack.pop();
                else if (topChar == '{' && currChar == '}') stringStack.pop();
                else if (topChar == '[' && currChar == ']') stringStack.pop();
                else return false;
            } else return false;
        }

        return stringStack.isEmpty();
    }

    //https://leetcode.com/problems/backspace-string-compare/
    //s = "ab#c", t = "ad#c" => true
    public static boolean backspaceCompare(String s, String t) {
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();

        for (char c : sCharArr) {
            if (c != '#')
                sStack.push(c);
                //edge case if more than 1 # before we add more chars to our stack
            else if (!sStack.isEmpty())
                sStack.pop();
        }

        for (char c : tCharArr) {
            if (c != '#')
                tStack.push(c);
                //edge case if more than 1 # before we add more chars to our stack
            else if (!tStack.isEmpty())
                tStack.pop();
        }

        while (!sStack.isEmpty() && !tStack.isEmpty()) {
            if (sStack.pop() != tStack.pop()) return false;
        }


        return sStack.isEmpty() && tStack.isEmpty();
    }

}
