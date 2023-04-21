package View;

import Controller.ChangeMenuController;
import Controller.TradeController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private final ChangeMenuController changeMenuController;
    private final TradeController tradeController;
    private Matcher matcher;
    private String input;
    
    public TradeMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.tradeController = changeMenuController.getTradeController();
    }
    
    public String run(Scanner scanner) {
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input,Commands.BACK)).find()) {
                return "main menu";
            }
        }
    }
    
}
