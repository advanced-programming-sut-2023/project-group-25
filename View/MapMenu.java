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


        System.out.println("Enter your length and width:");
        int mapLength = scanner.nextInt();
        int mapWidth = scanner.nextInt();
        map = new Map(mapLength,mapWidth);
        
        for (Map templateMap : map.getTemplateMaps()) {
            System.out.println(mapController.showMap(templateMap) + "\n");
        }
        while (true) {
            input = scanner.nextLine();
            if ((matcher = Commands.getMatcher(input, Commands.BACK)).find()) {
                return "mainMenu";
            }

            else if ((matcher = Commands.getMatcher(input,Commands.CHOOSE_MAP)).find()) {
                int chosenMapNumber = Integer.parseInt(matcher.group("number"));
                map = map.getTemplateMaps()[chosenMapNumber];
                game = new Game(map);
            }
        }
    }
}