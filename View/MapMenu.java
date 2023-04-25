package View;

import Controller.ChangeMenuController;
import Controller.MapController;
import Model.Game;
import Model.Map;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final ChangeMenuController changeMenuController;
    private final MapController mapController;
    private Matcher matcher;
    private String input;
    private Map map;
    private Game game;
    
    public MapMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.mapController = changeMenuController.getMapController();
    }
    
    public String run(Scanner scanner) {
        
        initializeTemplateMaps(40, 40);
        
        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1);
            System.out.println(mapController.showMap(Map.getTemplateMaps()[i]) + "\n");
        }
        
        System.out.println("Enter your length and width:");
        int mapLength = scanner.nextInt();
        int mapWidth = scanner.nextInt();
        map = new Map(mapLength, mapWidth);
        
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.BACK)) != null) {
                return "mainMenu";
            } else if ((matcher = Commands.getMatcher(input, Commands.CHOOSE_MAP)) != null) {
                int chosenMapNumber = Integer.parseInt(matcher.group("number"));
                initializeTemplateMaps(mapLength, mapWidth);
                map = Map.getTemplateMaps()[chosenMapNumber - 1];
                //game = new Game(map);
            }
        }
    }
    
    private void initializeTemplateMaps(int length, int width) {
        mapController.initializeMapTemplate1(length, width);
        mapController.initializeMapTemplate2(length, width);
        mapController.initializeMapTemplate3(length, width);
    }
}