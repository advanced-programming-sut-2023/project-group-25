package View;

import Controller.*;
import Model.Kingdom;
import Model.Trade;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private final ChangeMenuController changeMenuController;
    private final TradeController tradeController;
    private final GameController gameController;
    private final RegisterLoginController registerLoginController;

    private Matcher matcher;
    private String input;

    public TradeMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.tradeController = changeMenuController.getTradeController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.gameController = changeMenuController.getgameController();
    }

    public void run(Scanner scanner) throws NoSuchAlgorithmException {
        FileController.initializeTradeRequestFile();
        System.out.println(registerLoginController.showCurrentMenuName("TRADE MENU"));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.BACK)) != null) {
                return;
            } else if (input.equals("send request")) {
                Kingdom requestReceiver;
                System.out.println("Here is the list of all of the kings; please choose one of them to send a request:");
                System.out.print(tradeController.showAllKingsUsernames());
                System.out.print("Type the king's username: ");
                while (true) {
                    input = scanner.nextLine();
                    requestReceiver = gameController.getCurrentGame().getKingdomByKing(input);
                    if (requestReceiver == null) {
                        System.out.print("Invalid username; please try again: ");
                    } else {
                        break;
                    }
                }
                String username = input;
                System.out.println("Type your request details:");
                while (true) {
                    input = scanner.nextLine();
                    if ((matcher = Commands.getMatcher(input, Commands.TRADE_REQUEST)) != null) {
                        break;
                    } else {
                        System.out.println("Invalid format for request; please try again: ");
                    }
                }
                ArrayList<String> allOptions = new ArrayList<>() {{
                    add("t");
                    add("a");
                    add("p");
                    add("m");
                }};
                while (true) {
                    String resultMessage = tradeController.createTradeRequest(allOptions, matcher, username);
                    if (input.equals("cancel")) {
                        System.out.println("New game creation canceled!");
                        break;
                    } else if (!resultMessage.equals("success")) {
                        System.out.println(resultMessage);
                    } else {
                        System.out.println("Trade request sent successfully!");
                        break;
                    }
                    input = scanner.nextLine();
                }
            } else if ((matcher = Commands.getMatcher(input, Commands.TRADE_LIST)) != null) {
                System.out.print(tradeController.showAllTrades());
            } else if ((matcher = Commands.getMatcher(input, Commands.TRADE_ACCEPT)) != null) {
                ArrayList<String> allOptions = new ArrayList<>() {{
                    add("i");
                    add("m");
                }};
                System.out.println(tradeController.acceptTrade(matcher,allOptions));
            } else
                System.out.println("Invalid command!");
        }
    }
}

