import Controller.RegisterLoginController;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        RegisterLoginController controller = new RegisterLoginController();
        String s = ("user create -(?<option1>.+) (?<input1>.+) -(?<option2>.+) (?<input2>.+) -(?<option3>.+) (?<input3>.+) -(?<option4>.+) (?<input4>.+) -(?<option5>.+) (?<input5>.+)");
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i<100; i++)
            System.out.println(controller.generateRandomPassword());

    }
}