package org.example.problems;

import java.util.Stack;

public class StackProbs {
    public static void main(String[] args) {

        String s = "bxj##tw";
        String t = "bxj###tw";
//        System.out.println(backspaceCompare(s, t));
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
//        System.out.println(evalRPN(tokens));
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        dailyTemperatures(temps);
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

    //**********************
    //MEDIUMS
    //**********************
    public static int evalRPN(String[] tokens) {
        Stack<String> tokenStack = new Stack<>();
        for (String currentToken : tokens) {
            switch (currentToken) {
                case "+" -> {
                    int currPlus = Integer.parseInt(tokenStack.pop()) + Integer.parseInt(tokenStack.pop());
                    tokenStack.push(String.valueOf(currPlus));
                }
                case "-" -> {
                    int firstVal = Integer.parseInt(tokenStack.pop());
                    int secondVal = Integer.parseInt(tokenStack.pop());
                    int currMin = secondVal - firstVal;
                    tokenStack.push(String.valueOf(currMin));
                }
                case "*" -> {
                    int currMult = Integer.parseInt(tokenStack.pop()) * Integer.parseInt(tokenStack.pop());
                    tokenStack.push(String.valueOf(currMult));
                }
                case "/" -> {
                    int firstVal = Integer.parseInt(tokenStack.pop());
                    int secondVal = Integer.parseInt(tokenStack.pop());
                    int currDiv = secondVal / firstVal;
                    tokenStack.push(String.valueOf(currDiv));
                }
                default -> tokenStack.push(currentToken);
            }
        }

        return Integer.parseInt(tokenStack.pop());
    }

    //https://leetcode.com/problems/daily-temperatures/
    //monotonic decreasing stack problem
    //todo would need to look into this more
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] daysUntilWarmerWeather = new int[temperatures.length];
        Stack<TempNode> tempTracker = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int currTemp = temperatures[i];
            while (!tempTracker.isEmpty() && currTemp > tempTracker.peek().temp) {
                TempNode currTempNode = tempTracker.pop();
                daysUntilWarmerWeather[currTempNode.index] = i - currTempNode.index;
            }
            tempTracker.push(new TempNode(i, currTemp));
        }
        return daysUntilWarmerWeather;
    }
}

class TempNode {
    public int index;
    public int temp;

    public TempNode(int index, int temp) {
        this.index = index;
        this.temp = temp;
    }
}