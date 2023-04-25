package Controller;


import Model.Cell;
import Model.Map;

public class MapController {
    int length, width;
    

    public void initializeMapTemplate1(int length, int width) {
        Cell[][] map = new Cell[length][width];

        initializeCastlesLocation(map);
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (2 * this.width) / 6; j < (4 * this.width) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = length / 6; i <= (2 * length) / 6; i++)
            for (int j = this.width / 6; j <= (2 * this.width) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (4 * this.width) / 6; j < (5 * this.width) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = 0; i < length / 6; i++)
            for (int j = (2 * this.width) / 6; j < (4 * this.width) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (2 * this.width) / 6; j < (4 * this.width) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = 0; j < this.width / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (5 * this.width) / 6; j < this.width; j++)
                map[i][j].setMaterial("rockLand");
    }

    public void initializeMapTemplate2() {
        Cell[][] map = new Cell[length][width];

        initializeCastlesLocation(map);
        initializeIronLandsTemplate2(map);
        initializeRockLandsTemplate2(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (5 * width) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = length / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * width) / 6 - 5; j < (3 * width) / 6 + 5; j++)
                map[i][j].setMaterial("sea");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map[i][j].setMaterial("grass");
    }

    private void initializeRockLandsTemplate2(Cell[][] map) {
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map[i][j].setMaterial("rockLand");
    }

    private void initializeIronLandsTemplate2(Cell[][] map) {
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map[i][j].setMaterial("ironLand");
    }

    public void initializeMapTemplate3() {
        Cell[][] map = new Cell[length][width];

        initializeCastlesLocation(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (4 * width) / 6; j < width; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map[i][j].setMaterial("lowWater");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * width) / 6; j < width; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < width; j++)
                map[i][j].setMaterial("grass");
    }

    public void initializeCastlesLocation(Cell[][] map) {
        for (int i = 0; i < length / 6; i++)
            for (int j = 0; j < width / 6; j++)
                map[i][j].setMaterial("castle1");
        for (int i = 0; i < length / 6; i++)
            for (int j = (5 * width) / 6; j < width; j++)
                map[i][j].setMaterial("castle2");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = 0; j < width / 6; j++)
                map[i][j].setMaterial("castle3");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (5 * width) / 6; j < width; j++)
                map[i][j].setMaterial("castle4");
    }



    public static final String BACKGROUND_RESET = "\033[0m";             //reset
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";   // LIGHT BLUE
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// LIGHT RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// LIGHT GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// LIGHT YELLOW
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    
    
    //extra colors:
//    public static final String BLACK_BACKGROUND = "\033[40m";   // BLACK
//    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
//    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
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
                    case "ironLand":
                        mapView += RED_BACKGROUND + "#" + BACKGROUND_RESET;
                        break;
                    case "rockLand":
                        mapView += BLACK_BACKGROUND_BRIGHT + "#" + BACKGROUND_RESET;
                        break;
                    case "sea":
                        mapView += BLUE_BACKGROUND + "#" + BACKGROUND_RESET;
                        break;
                    case "lowWater":
                        mapView += BLUE_BACKGROUND_BRIGHT + "#" + BACKGROUND_RESET;
                        break;
                    case "grass":
                        mapView += GREEN_BACKGROUND_BRIGHT + "#" + BACKGROUND_RESET;
                        break;
                    case "jolge":
                        mapView += GREEN_BACKGROUND + "#" + BACKGROUND_RESET;
                        break;
                    case "castle":
                        mapView += PURPLE_BACKGROUND + "#" + BACKGROUND_RESET;
                        break;
                    case "beach":
                        mapView += YELLOW_BACKGROUND_BRIGHT + "#" + BACKGROUND_RESET;
                        break;
                    default:
                        mapView += YELLOW_BACKGROUND + "#" + BACKGROUND_RESET;
                        break;
                }
            }
        }
        return mapView;
    }
}
