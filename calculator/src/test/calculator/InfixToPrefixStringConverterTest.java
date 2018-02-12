package calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class InfixToPrefixStringConverterTest {

    @Test
    void infixToPostfix() {
        //digits and operations
        assertEquals(Arrays.asList("3 2 2 * +".split(" ")), InfixToPrefixStringConverter.convert("3 + 2 * 2"));
        //numbers and operations
        assertEquals(Arrays.asList("31 22 9 * -".split(" ")), InfixToPrefixStringConverter.convert("31 - 22 * 9"));
        //numbers, operations and braces with random, but acceptable spaces
        assertEquals(Arrays.asList("72 5 - 7 * 8 + 3 2 ^ 7 / 8 1 5 - - * -".split(" ")), InfixToPrefixStringConverter.convert(
                "(72- 5) * 7 +8 - 3^2 /7*(8-(1-5))"));
        //negative number
        assertEquals(Arrays.asList("4 3 + 2 -2 ^ *".split(" ")), InfixToPrefixStringConverter.convert("(4 + 3) * 2 ^ -2"));
    }
}