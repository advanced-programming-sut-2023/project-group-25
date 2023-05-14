import Controller.ChangeMenuController;


import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        ChangeMenuController controller = new ChangeMenuController();
        controller.run(scanner);
    }
}