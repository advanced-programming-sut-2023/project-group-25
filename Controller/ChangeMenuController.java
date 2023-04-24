package Controller;

import View.*;

import java.util.Scanner;

public class ChangeMenuController {
    private final RegisterLoginMenu registerLoginMenu = new RegisterLoginMenu(this);
    private final MainMenu mainMenu = new MainMenu(this);
    private final GameMenu gameMenu = new GameMenu(this);
    private final ProfileMenu profileMenu = new ProfileMenu(this);
    private final TradeMenu tradeMenu = new TradeMenu(this);
    private final MapMenu mapMenu = new MapMenu(this);
    private static RegisterLoginController registerLoginController = new RegisterLoginController();
    private static MainController mainController = new MainController();
    private static GameController gameController = new GameController();
    private static ProfileController profileController = new ProfileController();
    private static TradeController tradeController = new TradeController();
<<<<<<< HEAD
=======
    private static MapController mapController = new MapController();
>>>>>>> 39b74a1157e0efba13342a3a03b774832c7ab250
    
    public RegisterLoginController getRegisterLoginController() {
        return registerLoginController;
    }
    
    public MainController getMainController() {
        return mainController;
    }
    
    public GameController getGameController() {
        return gameController;
    }
    
    public ProfileController getProfileController() {
        return profileController;
    }
    
    public TradeController getTradeController() {
        return tradeController;
    }
    
    public void run(Scanner scanner) {
        if (registerLoginMenu.run(scanner).equals("exit")) return;
        //registerLoginMenu has returned mainMenu.
        switch (mainMenu.run(scanner)) {
            case "logout":
                if (registerLoginMenu.run(scanner).equals("exit")) return;
                break;
            case "game menu":
                gameMenu.run(scanner);
                break;
            case "map menu":
                mapMenu.run(scanner);
                break;
            case "profile menu":
                profileMenu.run(scanner);
                break;
            case "trade menu":
            
        }
    }
}
