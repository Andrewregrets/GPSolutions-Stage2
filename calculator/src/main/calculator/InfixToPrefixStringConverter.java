package calculator;/* Java implementation to convert infix expression to postfix*/
// Note that here we use Stack class for Stack operations

import calculator.exceptions.CalculatorArgumentException;

import java.util.*;

class InfixToPrefixStringConverter {
    // A utility function to return precedence of a given operator
    // Higher returned value means higher precedence
    static int scanIndex = 0;
    private static boolean numberIsExpected;

    static int getPrecedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    private static String getDigitsFromPositionInString(String string) {
        StringBuilder digits = new StringBuilder();
        skipSpaces(string);
        if (string.charAt(scanIndex) == '-') {
            digits.append('-');
            ++scanIndex;
            skipSpaces(string);
        }
        while (scanIndex < string.length() && Character.isDigit(string.charAt(scanIndex))) {
            digits.append(string.charAt(scanIndex));
            ++scanIndex;
        }
        if (scanIndex >= string.length()) {
            --scanIndex;
            return digits.toString();
        }
        if ((string.charAt(scanIndex) == '.' || string.charAt(scanIndex) == ',')) {
            digits.append(".");
            ++scanIndex;
            while (scanIndex < string.length() && Character.isDigit(string.charAt(scanIndex))) {
                digits.append(string.charAt(scanIndex));
                ++scanIndex;
            }
        }
        --scanIndex;
        numberIsExpected = false;
        return digits.toString();
    }

    private static void skipSpaces(String string) {
        while (string.charAt(scanIndex)== ' ')
            ++scanIndex;
    }

    static List<String> convert(String infixExpression) {
        numberIsExpected = true;

        List<String> postfixTokens = new LinkedList<>();

        Stack<Character> operationParenthesisStack = new Stack<>();

        for (scanIndex = 0; scanIndex < infixExpression.length(); ++scanIndex) {
            char c = infixExpression.charAt(scanIndex);
            if (Character.isDigit(c)) {
                if(!numberIsExpected)
                    throw new CalculatorArgumentException("Invalid expression. Two numbers in a row.");
                postfixTokens.add(getDigitsFromPositionInString(infixExpression));
            } else if (c == '(')
                operationParenthesisStack.push(c);
            else if (c == ')') {
                while (!operationParenthesisStack.isEmpty() && operationParenthesisStack.peek() != '(')
                    postfixTokens.add(String.valueOf(operationParenthesisStack.pop()));

                if (!operationParenthesisStack.isEmpty() && operationParenthesisStack.peek() != '(')
                    throw new CalculatorArgumentException("Invalid Expression. Number of '(' isn't equal to number of ')'"); // invalid expression
                else
                    operationParenthesisStack.pop();
            } else if (c == ' ')
                continue;
                //check negatives
            else if (c == '-' && previousTokenIsNotNumber(infixExpression, scanIndex)) {
                postfixTokens.add(getDigitsFromPositionInString(infixExpression));
            }
            // an operator is encountered
            else {
                int precedence = getPrecedence(c);
                if (precedence < 0)
                    throw new CalculatorArgumentException(String.format("Invalid symbol %c", c));
                while (!operationParenthesisStack.isEmpty() && precedence <= getPrecedence(operationParenthesisStack.peek()))
                    postfixTokens.add(String.valueOf(operationParenthesisStack.pop()));
                operationParenthesisStack.push(c);
                numberIsExpected = true;
            }
        }

        // pop all the operators from the stack
        while (!operationParenthesisStack.isEmpty())
            postfixTokens.add(String.valueOf(operationParenthesisStack.pop()));

        return postfixTokens;
    }

    private static boolean previousTokenIsNotNumber(String stringToTraverse, int currentPosition) {
        --currentPosition;
        while (stringToTraverse.charAt(currentPosition) == ' ' && currentPosition > -1)
            --currentPosition;
        if (currentPosition == -1 || !Character.isDigit(stringToTraverse.charAt(currentPosition)))
            return true;
        return false;
    }
}