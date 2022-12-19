package org.example.datastructures;

import java.util.Stack;

//Queue (FIFO) Stack (LIFO)

//https://leetcode.com/problems/implement-queue-using-stacks/
public class MyQueue {
    public Stack<Integer> readStack;
    public Stack<Integer> writeStack;

    public MyQueue() {
        readStack = new Stack<>();
        writeStack = new Stack<>();
    }

    public void push(int x) {
        writeStack.push(x);
    }

    public int pop() {
        while (!writeStack.isEmpty()) {
            readStack.push(writeStack.pop());
        }
        int res = readStack.pop();
        while (!readStack.isEmpty()) {
            writeStack.push(readStack.pop());
        }
        return res;
    }

    public int peek() {
        while (!writeStack.isEmpty()) {
            readStack.push(writeStack.pop());
        }
        int res = readStack.peek();
        while (!readStack.isEmpty()) {
            writeStack.push(readStack.pop());
        }
        return res;
    }

    public boolean empty() {
        return writeStack.isEmpty();
    }
}
