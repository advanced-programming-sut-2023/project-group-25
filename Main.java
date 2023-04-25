import Controller.ChangeMenuController;
import Controller.RegisterLoginController;
import Model.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        ChangeMenuController controller = new ChangeMenuController();
        controller.run(scanner);
    }
}