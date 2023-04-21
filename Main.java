import Controller.RegisterLoginController;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String s = ("user create -(?<option1>.+) (?<input1>.+) -(?<option2>.+) (?<input2>.+) -(?<option3>.+) (?<input3>.+) -(?<option4>.+) (?<input4>.+) -(?<option5>.+) (?<input5>.+)");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Matcher matcher = Pattern.compile(s).matcher(str);
        RegisterLoginController controller = new RegisterLoginController();
        ArrayList<String> allOptions = new ArrayList<>();
        allOptions.add("i");
        allOptions.add("o");
        allOptions.add("u");
        allOptions.add("e");
        allOptions.add("a");
        if(!controller.checkAllOptionsExist(matcher, allOptions))
            System.out.println("incorrect format");
         if(!matcher.matches()) System.out.println("no found");
        else System.out.println(controller.getOptionsFromMatcher(matcher,"u", 5));
    }
}