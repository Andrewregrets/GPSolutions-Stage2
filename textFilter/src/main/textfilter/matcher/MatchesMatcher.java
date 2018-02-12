package textfilter.matcher;

public class MatchesMatcher implements Matcher {
    @Override
    public boolean matches(String strToCheck, String reference) {
        return strToCheck.matches(reference);
    }
}