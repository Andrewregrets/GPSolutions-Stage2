package calculator;

import calculator.exceptions.CalculatorArgumentException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    List<String> prefixTokenList;

    public Calculator(String expressionString) throws CalculatorArgumentException {
        validateSymbols(expressionString);
        prefixTokenList = InfixToPrefixStringConverter.convert(expressionString);
    }

    public BigDecimal calculate() {
        Stack<BigDecimal> stack = new Stack<>();

        List<String> operandsAndOperatorsList = new LinkedList<>(prefixTokenList);

        for (String token :
                operandsAndOperatorsList) {
            if (!token.matches("\\D"))
                stack.push(new BigDecimal(token));
            else if (token.matches("[\\^*/+-]")) {
                BigDecimal secondOperand = stack.pop();
                BigDecimal firstOperand = stack.pop();
                switch (token) {
                    case "^":
                        stack.push(firstOperand.pow(secondOperand.intValue(), MathContext.DECIMAL64));
                        break;
                    case "*":
                        stack.push(firstOperand.multiply(secondOperand));
                        break;
                    case "/":
                        stack.push(firstOperand.divide(secondOperand));
                        break;
                    case "+":
                        stack.push(firstOperand.add(secondOperand));
                        break;
                    case "-":
                        stack.push(firstOperand.subtract(secondOperand));
                        break;
                }
            }

        }
        return stack.pop();
    }

    private static void validateSymbols(String operationString) throws CalculatorArgumentException {
        Pattern pattern = Pattern.compile("[^\\d\\+\\-\\*/\\^()., ]");
        Matcher matcher = pattern.matcher(operationString);
        if (matcher.find())
            throw new CalculatorArgumentException("Detected Unsupported symbols:" + matcher.group() +
                    "\nSupported symbols are: 0-9, +,-,*,/,^(,)");
    }
}
