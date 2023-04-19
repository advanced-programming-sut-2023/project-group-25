package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    ;

    private String regex;

    private Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands commands) {
        Matcher matcher = Pattern.compile(commands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
