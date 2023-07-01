package Controller;

import View.FirstPage;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

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

    public static BackgroundImage setFirstPageBackground(String path) {
        System.out.println(path);
        Image image = new Image(FirstPage.class.getResource(path).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true));
        return backgroundImage;
    }

}
