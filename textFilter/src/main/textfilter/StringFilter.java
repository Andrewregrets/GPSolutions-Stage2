package textfilter;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import textfilter.matcher.*;

class LineVerifier {
    static boolean satisfies(Matcher matcher, String strToCheck, String... references) {
        String[] words = strToCheck.split("\\W+");
        for (String word : words)
            for (String reference : references)
                if (matcher.matches(word, reference))
                    return true;
        return false;
    }
}

public class StringFilter {

    /**
     * Checks if {@code args} should be used as {@code regex} in {@code Pattern.matches(regex, str)}
     * in textFilter program
     */

    public static boolean shouldBeUsedInMatch(String... args) {
        boolean isRegex = true;

        if (args.length != 1)
            return false;

        if (args[0].trim().isEmpty())
            return false;

        try {
            Pattern.compile(args[0]);
        } catch (PatternSyntaxException e) {
            isRegex = false;
        }

        return isRegex;
    }

    public static String getResultString(InputStream inputStream, String... args) {
        Matcher matcher;

        if (StringFilter.shouldBeUsedInMatch(args))
            matcher = new MatchesMatcher();
        else {
            matcher = new EqualsMatcher();
            args = String.join(" ", args).split("\\W+");
            if (args.length < 1)
                return "";
        }

        StringBuilder resultStringBuilder = new StringBuilder();

        Scanner scanner = new Scanner(inputStream);
        String lineToCheck;

        while (true) {
            lineToCheck = scanner.nextLine();

            if (lineToCheck.isEmpty())
                break;

            if (LineVerifier.satisfies(matcher, lineToCheck, args))
                resultStringBuilder.append(lineToCheck).append("\n");
        }
        return resultStringBuilder.toString();
    }
}
