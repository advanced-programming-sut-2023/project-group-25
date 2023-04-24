package Controller;

<<<<<<< HEAD

import Model.Cell;
import Model.Map;

public class MapController {
    int length, weigh;
    public MapController(int x, int y) {
        this.length = x;
        this.weigh = y;
    }

    public void initializeMapTemplate1() {
        Cell[][] map = new Cell[length][weigh];

        initializeCastlesLocation(map);
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = length / 6; i <= (2 * length) / 6; i++)
            for (int j = weigh / 6; j <= (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (4 * weigh) / 6; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = 0; i < length / 6; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = 0; j < weigh / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (5 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("rockLand");
    }

    public void initializeMapTemplate2() {
        Cell[][] map = new Cell[length][weigh];

        initializeCastlesLocation(map);
        initializeIronLandsTemplate2(map);
        initializeRockLandsTemplate2(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = length / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * weigh) / 6 - 5; j < (3 * weigh) / 6 + 5; j++)
                map[i][j].setMaterial("sea");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * weigh) / 6; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * weigh) / 6; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
    }

    private void initializeRockLandsTemplate2(Cell[][] map) {
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (2 * weigh) / 6; j < (3 * weigh) / 6 - 5; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (3 * weigh) / 6 + 5; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (2 * weigh) / 6; j < (3 * weigh) / 6 - 5; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (3 * weigh) / 6 + 5; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("rockLand");
    }

    private void initializeIronLandsTemplate2(Cell[][] map) {
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (2 * weigh) / 6; j < (3 * weigh) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (3 * weigh) / 6 + 5; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (2 * weigh) / 6; j < (3 * weigh) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * weigh) / 6 + 5; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
    }

    public void initializeMapTemplate3() {
        Cell[][] map = new Cell[length][weigh];

        initializeCastlesLocation(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (4 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("lowWater");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("grass");
    }

    public void initializeCastlesLocation(Cell[][] map) {
        for (int i = 0; i < length / 6; i++)
            for (int j = 0; j < weigh / 6; j++)
                map[i][j].setMaterial("castle1");
        for (int i = 0; i < length / 6; i++)
            for (int j = (5 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("castle2");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = 0; j < weigh / 6; j++)
                map[i][j].setMaterial("castle3");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (5 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("castle4");
    }
}
=======
import Model.Map;

public class MapController {
    public static final String BACKGROUND_RESET = "\033[0m";             //reset
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";   // LIGHT BLUE
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// LIGHT RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// LIGHT GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// LIGHT YELLOW
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    
    //extra colors:
//    public static final String BLACK_BACKGROUND = "\033[40m";   // BLACK
//    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
//    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
//    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
//    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
//    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
//    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
//    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
//    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    
    public String showMap(Map map) {
        int mapLength = map.getLength();
        int mapWidth = map.getWidth();
        String mapView = "";
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapWidth; j++) {
                switch (map.getCells()[i][j].getMaterial()) {
                    case "land":
                        mapView += YELLOW_BACKGROUND_BRIGHT + "######\n######\n######\n" + BACKGROUND_RESET;
                        break;
                    case "water":
                        mapView += BLUE_BACKGROUND_BRIGHT + "######\n######\n######\n" + BACKGROUND_RESET;
                        break;
                    case "grass":
                        mapView += GREEN_BACKGROUND_BRIGHT + "######\n######\n######\n" + BACKGROUND_RESET;
                        break;
                    case "castle":
                        mapView += RED_BACKGROUND + "######\n######\n######\n" + BACKGROUND_RESET;
                        break;
                }
            }
        }
        return mapView;
    }
}
>>>>>>> 0b96c219f23dc27ab6f5cfcf779fd88369f5e910
