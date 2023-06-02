package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

public class MainController {
    public static String getOptionsFromMatcher(Matcher matcher, String option, int numberOfOptions) {
        for (int i = 0; i < numberOfOptions; i++) {
            if (matcher.group(("option" + (i + 1))).equals(option))
                return matcher.group(("input" + (i + 1)));
        }
        return null;
    }

    public static boolean checkAllOptionsExist(Matcher matcher, ArrayList<String> allOptions) {
        ArrayList<String> matcherExistingOptions = new ArrayList<>();
        for (int i = 0; i < allOptions.size(); i++) {
            matcherExistingOptions.add(matcher.group(("option" + (i + 1))));
        }
        Collections.sort(matcherExistingOptions);
        Collections.sort(allOptions);
        if (matcherExistingOptions.equals(allOptions))
            return true;
        else
            return false;
    }
}
