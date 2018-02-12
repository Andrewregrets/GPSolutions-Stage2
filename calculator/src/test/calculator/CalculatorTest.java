package calculator;

import calculator.exceptions.CalculatorArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate() {
        //tests from task
        assertEquals(6, new Calculator("2 + 2 * 2").calculate().intValue());
        assertEquals(1.75, new Calculator("(4 + 3) * 2 ^ -2").calculate().doubleValue());
        assertThrows(ArithmeticException.class, () ->
                new Calculator("5 + 1/0").calculate());
        assertEquals(1, new Calculator("((17 ^ 4 + 5 * 974 ^ 33 + 2.24 * 4.75)^0").calculate().intValue());
        assertEquals(1, new Calculator("((17 ^ 4 + 5 * 974 ^ 33 + 2,24 * 4.75)^0").calculate().intValue());
        assertThrows(CalculatorArgumentException.class, () ->
                new Calculator("4 2 * 3").calculate());
        assertThrows(CalculatorArgumentException.class, () ->
                new Calculator("4a * 5").calculate());

        ///
        assertEquals(7, new Calculator("3 + 2 * 2").calculate().intValue());
        //numbers and operations
        assertEquals(-167.0, new Calculator("31 - 22 * 9").calculate().doubleValue());
        //numbers, operations and braces with random, but acceptable spaces
        assertEquals(459.0, new Calculator(
                "(72- 5) * 7 +8 - 3^2 /5*(6-(1-5))").calculate().doubleValue());
    }
}