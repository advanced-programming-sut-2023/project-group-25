package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController;
import Controller.RegisterLoginController;
import Model.Map;
import Model.Turn;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final RegisterLoginController registerLoginController;
    private final GameController gameController;
    private final MapController mapController;
    private Turn turn;
    private Matcher matcher;
    private String input;
    private Map map;
    
    public MapMenu(ChangeMenuController changeMenuController) {
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.gameController = changeMenuController.getgameController();
        this.mapController = changeMenuController.getMapController();
    }
    
    public String run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("MAP MENU"));
        mapController.setNumberOfCastles(gameController.getCurrentGame().getKingdoms().size());
        chooseColorRun(scanner);
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
                    Turn.setTurnCounter(0);
                    turn.setCurrentKing(RegisterLoginController.getCurrentUser());
                    return "main menu";
                }
            } else {
                System.out.println("invalid command!");
            }
        }
    }
    
    private void chooseColorRun(Scanner scanner) {
        System.out.println("Please enter the colors of the players in order separated with '-' from the list below:");
        for (int i = 0; i < gameController.legalColors.length - 1; i++) {
            System.out.print(gameController.legalColors[i] + "-");
        }
        System.out.println(gameController.legalColors[gameController.legalColors.length - 1]);
        
        String colorsStr;
        outer: while (true) {
            colorsStr = scanner.nextLine();
            switch (gameController.setKingdomColors(colorsStr)) {
                case "few colors":
                    System.out.println("The number of colors you entered is fewer than needed! Try again:");
                    break;
                case "too many colors":
                    System.out.println("The number of colors you entered is too many! Try again:");
                    break;
                case "bad color":
                    System.out.println("You have entered an invalid color! Try again:");
                    break;
                case "repeated color":
                    System.out.println("You cannot assign one color to two or more kingdoms! Try again:");
                    break;
                case "success":
                    System.out.println("Colors are assigned to players successfully!");
                    break outer;
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