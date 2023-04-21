package Controller;

import View.*;

import java.util.Scanner;

public class ChangeMenuController {
    private final RegisterLoginMenu registerLoginMenu = new RegisterLoginMenu(this);
    private final MainMenu mainMenu = new MainMenu(this);
    private final GameMenu gameMenu = new GameMenu(this);
//    private final ProfileMenu profileMenu = new ProfileMenu(this);
//    private final TradeMenu tradeMenu = new TradeMenu(this);
    private final MapMenu mapMenu = new MapMenu(this);
    private final RegisterLoginController registerLoginController = new RegisterLoginController();
    private final MainController mainController = new MainController();
    private final GameController gameController = new GameController();
    
    public RegisterLoginController getRegisterLoginController() {
        return registerLoginController;
    }
    
    public MainController getMainController() {
        return mainController;
    }
    
    public GameController getGameController() {
        return gameController;
    }
    
    public void run(Scanner scanner) {
        if (registerLoginMenu.run(scanner).equals("exit")) return;
        //registerLoginMenu has returned mainMenu.
        switch (mainMenu.run(scanner)) {
            case "logout":
                if (registerLoginMenu.run(scanner).equals("exit")) return;
                break;
            case "gameMenu":
                gameMenu.run(scanner);
                break;
            case "mapMenu":
                mapMenu.run(scanner);
                break;
            case "profileMenu":
        }
    }
}
