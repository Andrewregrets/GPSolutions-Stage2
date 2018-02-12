package textfilter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.printf("Program parameters: \n%s\n", Arrays.stream(args).collect(Collectors.joining("\n")));
        System.out.println("Input lines: (input empty line to finish the program).");

        System.out.println(StringFilter.getResultString(System.in, args));
    }
}
