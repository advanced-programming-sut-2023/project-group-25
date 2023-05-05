package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController;
import Controller.RegisterLoginController;
import Model.Game;
import Model.Map;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final ChangeMenuController changeMenuController;
    private final RegisterLoginController registerLoginController;
    private final GameController gameController;
    private final MapController mapController;
    private Matcher matcher;
    private String input;
    private Map map;
    
    public MapMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.gameController = changeMenuController.getGameController();
        this.mapController = changeMenuController.getMapController();
    }
    
    public String run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("MAP MENU"));
        mapController.setNumberOfCastles(gameController.getNumberOfPlayers());
        int mapLength = -1, mapWidth = -1;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.BACK)) != null) {
                return "mainMenu";
            } else if ((matcher = Commands.getMatcher(input, Commands.ENTER_LENGTH_AND_WIDTH)) != null) {
                mapLength = Integer.parseInt(matcher.group("length"));
                mapWidth = Integer.parseInt(matcher.group("width"));
            } else if ((matcher = Commands.getMatcher(input, Commands.SHOW_MAP_BEFORE_STARTING_THE_GAME)) != null) {
                showTemplates();
            } else if ((matcher = Commands.getMatcher(input, Commands.CHOOSE_MAP)) != null) {
                int chosenMapNumber = Integer.parseInt(matcher.group("number"));
                if (mapLength < 0) System.out.println("You should first initialize the size of the map");
                else {
                    initializeTemplateMaps(mapLength, mapWidth);
                    map = Map.getTemplateMaps()[chosenMapNumber - 1];
                    //gameController.setCurrentGame(new Game(map));
                }
            }
        }
    }
    
    private void showTemplates() {
        initializeTemplateMaps(12, 12);
        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + ".");
            System.out.println(mapController.showMap(Map.getTemplateMaps()[i]) + "\n");
        }
    }
    
    private void initializeTemplateMaps(int length, int width) {
        mapController.initializeMapTemplate1(length, width);
        mapController.initializeMapTemplate2(length, width);
        mapController.initializeMapTemplate3(length, width);
    }
}