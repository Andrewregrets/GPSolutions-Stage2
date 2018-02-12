package textfilter.matcher;

public interface Matcher {
    boolean matches(String strToCheck, String reference);
}
