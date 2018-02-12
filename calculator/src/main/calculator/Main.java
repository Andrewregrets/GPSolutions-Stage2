package calculator;

import calculator.exceptions.CalculatorArgumentException;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringToProcess;
        BigDecimal result = null;

        while (true) {
            stringToProcess = scanner.nextLine();
            if(stringToProcess.isEmpty()) {
                System.out.println("Goodbye!");
                return;
            }
            try {
                compute(stringToProcess);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        System.out.println(result);
    }

    public static BigDecimal compute(String computeString) throws CalculatorArgumentException{
        Calculator calculator = new Calculator(computeString);
        return calculator.calculate();
    }
}
