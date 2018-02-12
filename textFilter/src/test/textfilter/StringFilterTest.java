package textfilter;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class StringFilterTest {

    @Test
    void canBeUsedInMatch() {
        assertEquals(true, StringFilter.shouldBeUsedInMatch("^ab.+"));
        assertEquals(true, StringFilter.shouldBeUsedInMatch(new String[]{"^ab.+"}));
        assertEquals(false, StringFilter.shouldBeUsedInMatch(new String[]{"^ab.", "+"}));
        assertEquals(true, StringFilter.shouldBeUsedInMatch(new String[]{"abcd"}));
    }

    @Test
    void getResultString() {
        // tests from task
        test1();
        test2();
        test3();
        test4();
        testAsterisk();
    }

    private void test1() {
        String[] args = new String[]{"abcd"};
        String input = "abcdf;\n" +
                "abcd abc;\n" +
                "bcd adbc abc;\n" +
                "ghij abcd acdf;\n" +
                "ab ac ad;\n" +
                "\n";
        String expectedOutput = "abcd abc;\n" +
                "ghij abcd acdf;\n";

        testGetString(args, input, expectedOutput);
    }

    private void test2() {
        String[] args = new String[]{"abcd", "abc"};
        String input = "abcdf;\n" +
                "abcd abc;\n" +
                "bcd adbc abc;\n" +
                "ghij abcd acdf;\n" +
                "ab ac ad;\n" +
                "\n";
        String expectedOutput = "abcd abc;\n" +
                "bcd adbc abc;\n" +
                "ghij abcd acdf;\n";

        testGetString(args, input, expectedOutput);
    }

    private void test3() {
        String[] args = new String[]{"^ab.+"};
        String input = "abcdf;\n" +
                "abcd abc;\n" +
                "bcd adbc abc;\n" +
                "ghij abcd acdf;\n" +
                "ab ac ad;\n" +
                "\n";
        String expectedOutput = "abcdf;\n" +
                "abcd abc;\n" +
                "bcd adbc abc;\n" +
                "ghij abcd acdf;\n";

        testGetString(args, input, expectedOutput);
    }

    private void test4() {
        String[] args = new String[]{"pqrst"};

        String input = "abcdf;\n" +
                "abcd abc;\n" +
                "bcd adbc abc;\n" +
                "ghij abcd acdf;\n" +
                "ab ac ad;\n" +
                "\n";
        String expectedOutput = "";

        testGetString(args, input, expectedOutput);
    }

    private void testAsterisk() {
        String[] args = new String[]{"*"};
        String input = "abc*df;\n" +
                "* abc;\n" +
                "bcd adbc abc;\n" +
                "ghij abcd acdf;\n" +
                "ab *\tac ad;\n" +
                "\n";
        String expectedOutput = "";

        testGetString(args, input, expectedOutput);
    }

    private void testGetString(String[] args, String input, String expectedOutput) {
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        assertEquals(expectedOutput, StringFilter.getResultString(stream, args));
    }
}