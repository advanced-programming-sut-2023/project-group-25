package View;

import Controller.ChangeMenuController;
import Controller.RegisterLoginController;
import Controller.TradeController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private final ChangeMenuController changeMenuController;
    private final TradeController tradeController;
    RegisterLoginController registerLoginController;

    private Matcher matcher;
    private String input;
    
    public TradeMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.tradeController = changeMenuController.getTradeController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
    }
    
    public void run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("TRADE MENU"));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input,Commands.BACK)) != null) {
                return;
            } else if(input.equals("send request")) {
                System.out.println("Here is the list of all of the kings; please choose one of them to send a request:");
                System.out.print(tradeController.showAllKingsUsernames());
            }
        }
    }
    
}
