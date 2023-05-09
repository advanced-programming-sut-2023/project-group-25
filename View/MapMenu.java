package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController;
import Controller.RegisterLoginController;
import Model.Map;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final RegisterLoginController registerLoginController;
    private final GameController gameController;
    private final MapController mapController;
    
    public MapMenu(ChangeMenuController changeMenuController) {
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.gameController = changeMenuController.getgameController();
        this.mapController = changeMenuController.getMapController();
    }
    
    public String run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("MAP MENU"));
        mapController.setNumberOfCastles(gameController.getCurrentGame().getKingdoms().size());
        int mapLength = -1, mapWidth = -1;
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher;
            if (Commands.getMatcher(input, Commands.BACK) != null) {
                return "mainMenu";
            } else if ((matcher = Commands.getMatcher(input, Commands.ENTER_LENGTH_AND_WIDTH)) != null) {
                System.out.println("Map size is initialized successfully!");
                mapLength = Integer.parseInt(matcher.group("length"));
                mapWidth = Integer.parseInt(matcher.group("width"));
            } else if (Commands.getMatcher(input, Commands.SHOW_MAP_BEFORE_STARTING_THE_GAME) != null) {
                showTemplates();
            } else if ((matcher = Commands.getMatcher(input, Commands.CHOOSE_MAP)) != null) {
                int chosenMapNumber = Integer.parseInt(matcher.group("number"));
                if (mapLength < 0) System.out.println("You should first initialize the size of the map");
                else {
                    initializeTemplateMaps(mapLength, mapWidth);
                    Map map = Map.getTemplateMaps()[chosenMapNumber - 1];
                    System.out.println("Your map is built successfully!");
                    gameController.getCurrentGame().setMap(map);
                    gameController.getCurrentGame().setMapTemplateNumber(chosenMapNumber - 1);
                    gameController.addGameToFile(gameController.getCurrentGame());
                    return "main menu";
                }
            } else {
                System.out.println("invalid command!");
            }
        }
    }
    
    private void showTemplates() {
        initializeTemplateMaps(13, 13);
        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + ".");
            System.out.println(MapController.showMap(Map.getTemplateMaps()[i]) + "\n");
        }
    }
    
    private void initializeTemplateMaps(int length, int width) {
        mapController.initializeMapTemplate1(length, width);
        mapController.initializeMapTemplate2(length, width);
        mapController.initializeMapTemplate3(length, width);
    }
}