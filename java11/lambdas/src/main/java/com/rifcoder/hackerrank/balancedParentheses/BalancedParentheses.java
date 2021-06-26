package com.rifcoder.hackerrank.balancedParentheses;

import java.util.Stack;

public class BalancedParentheses {
    private static final char OPEN_PARENTHESES = '(';
    private static final char CLOSE_PARENTHESES = ')';

    public static boolean ensureBalanced(String input) {
        if (input.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (ensureOpenBracket(c)) {
                stack.push(c);
            } else if (stack.isEmpty()) {
                return false;
            } else if (ensureClosedBracket(stack.peek(), c)) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    private static boolean ensureOpenBracket(char c) {
        return c == OPEN_PARENTHESES;
    }

    private static boolean ensureClosedBracket(char lastBracket, char c) {
        return c == CLOSE_PARENTHESES && lastBracket == OPEN_PARENTHESES;
    }
}
