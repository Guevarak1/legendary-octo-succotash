package org.example.datastructures;

import java.util.Stack;

class MinStack {
    Stack<StackNode> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.top();
        minStack.pop();
        minStack.getMin();
    }

    public void push(int val) {
        if (!stack.isEmpty()) {
            int currMinVal = stack.peek().minVal;
            if (val < currMinVal)
                stack.push(new StackNode(val, val));
            else
                stack.push(new StackNode(val, currMinVal));
        } else {
            stack.push(new StackNode(val, val));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().minVal;
    }
}

class StackNode {
    public int minVal;
    public int val;

    public StackNode(int val, int minVal) {
        this.val = val;
        this.minVal = minVal;
    }
}