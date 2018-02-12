package textfilter.matcher;

public class EqualsMatcher implements Matcher {
    @Override
    public boolean matches(String strToCheck, String reference) {
        return strToCheck.equals(reference);
    }
}