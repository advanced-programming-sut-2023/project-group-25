package Controller;

import View.*;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ChangeMenuController {
    private static final RegisterLoginController registerLoginController = new RegisterLoginController();
    private static final MainController mainController = new MainController();
    private static final GameController gameController = new GameController();
    private static final ProfileController profileController = new ProfileController();
    private static final TradeController tradeController = new TradeController();
    private static final MapController mapController = new MapController(gameController);
    private final RegisterLoginMenu registerLoginMenu = new RegisterLoginMenu(this);
    private final MainMenu mainMenu = new MainMenu(this);
    private final GameMenu gameMenu = new GameMenu(this);
    private final ProfileMenu profileMenu = new ProfileMenu(this);
    private final TradeMenu tradeMenu = new TradeMenu(this);
    private final MapMenu mapMenu = new MapMenu(this);

    public RegisterLoginController getRegisterLoginController() {
        return registerLoginController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public GameController getgameController() {
        return gameController;
    }

    public ProfileController getProfileController() {
        return profileController;
    }

    public TradeController getTradeController() {
        return tradeController;
    }

    public MapController getMapController() {
        return mapController;
    }

    public void run(Scanner scanner) throws NoSuchAlgorithmException {
        if (registerLoginMenu.run(scanner).equals("exit")) return;
        //registerLoginMenu has returned mainMenu.
        while (true) {
            switch (mainMenu.run(scanner)) {
                case "logout":
                    if (registerLoginMenu.run(scanner).equals("exit")) return;
                    break;
                case "game menu":
                    gameMenu.run(scanner);
                    break;
                case "map menu":
                    mapMenu.run(scanner);
                    gameMenu.run(scanner);
                    break;
                case "profile menu":
                    profileMenu.run(scanner);
                    break;
                case "trade menu":
                    tradeMenu.run(scanner);
                    break;
            }
        }
    }
}
