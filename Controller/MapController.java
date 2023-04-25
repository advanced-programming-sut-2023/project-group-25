package Controller;


import Model.Cell;
import Model.Map;


public class MapController {
    int length, width;
    
    
    public void initializeMapTemplate1(int length, int width) {
        Map map = new Map(length,width);
        
        initializeCastlesLocation(map);
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("sea");
        
        for (int i = length / 6; i <= (2 * length) / 6; i++)
            for (int j = width / 6; j <= (2 * width) / 6; j++){
                map.getCells()[i][j] = new Cell("grass");
            }
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = 0; i < length / 6; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("ironLand");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("ironLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = 0; j < width / 6; j++)
                map.getCells()[i][j] = new Cell("rockLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (5 * width) / 6; j < width; j++)
                map.getCells()[i][j] = new Cell("rockLand");
        
        setDefaultLand(length, width, map);
        
        Map.setTemplateMap(0,map);
    }
    
    private static void setDefaultLand(int length, int width, Map map) {
        for (int i = 0; i< length; i++) {
            for (int j = 0; j< width; j++) {
                if (map.getCells()[i][j] != null) continue;
                map.getCells()[i][j] = new Cell("land");
            }
        }
    }
    
    public void initializeMapTemplate2(int length,int width) {
        Map map = new Map(length,width);
        
        initializeCastlesLocation(map);
        initializeIronLandsTemplate2(map);
        initializeRockLandsTemplate2(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("sea");
        for (int i = length / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * width) / 6 - 5; j < (3 * width) / 6 + 5; j++)
                map.getCells()[i][j] = new Cell("sea");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        
        
        setDefaultLand(length, width, map);
        Map.setTemplateMap(1,map);
    }
    
    private void initializeRockLandsTemplate2(Map map) {
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map.getCells()[i][j] = new Cell("rockLand");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("rockLand");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map.getCells()[i][j] = new Cell("rockLand");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("rockLand");
    }
    
    private void initializeIronLandsTemplate2(Map map) {
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map.getCells()[i][j] = new Cell("ironLand");
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - 5; j++)
                map.getCells()[i][j] = new Cell("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * width) / 6 + 5; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("ironLand");
    }
    
    public void initializeMapTemplate3(int length, int width) {
        Map map = new Map(length,width);
        
        initializeCastlesLocation(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (4 * width) / 6; j < width; j++)
                map.getCells()[i][j] = new Cell("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("lowWater");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * width) / 6; j < width; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < width; j++)
                map.getCells()[i][j] = new Cell("grass");
        
        setDefaultLand(length, width, map);
        
        Map.setTemplateMap(2,map);
    }
    
    public void initializeCastlesLocation(Map map) {
        for (int i = 0; i < length / 6; i++)
            for (int j = 0; j < width / 6; j++)
                map.getCells()[i][j] = new Cell("castle1");
        for (int i = 0; i < length / 6; i++)
            for (int j = (5 * width) / 6; j < width; j++)
                map.getCells()[i][j] = new Cell("castle2");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = 0; j < width / 6; j++)
                map.getCells()[i][j] = new Cell("castle3");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (5 * width) / 6; j < width; j++)
                map.getCells()[i][j] = new Cell("castle4");
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
        StringBuilder mapView = new StringBuilder();
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapWidth; j++) {
                switch (map.getCells()[i][j].getmaterial()) {
                    case "ironLand":
                        mapView.append(RED_BACKGROUND + "###" + BACKGROUND_RESET);
                        break;
                    case "rockLand":
                        mapView.append(BLACK_BACKGROUND_BRIGHT + "###" + BACKGROUND_RESET);
                        break;
                    case "sea":
                        mapView.append(BLUE_BACKGROUND + "###" + BACKGROUND_RESET);
                        break;
                    case "lowWater":
                        mapView.append(BLUE_BACKGROUND_BRIGHT + "###" + BACKGROUND_RESET);
                        break;
                    case "grass":
                        mapView.append(GREEN_BACKGROUND_BRIGHT + "###" + BACKGROUND_RESET);
                        break;
                    case "jolge":
                        mapView.append(GREEN_BACKGROUND + "###" + BACKGROUND_RESET);
                        break;
                    case "castle":
                        mapView.append(PURPLE_BACKGROUND + "###" + BACKGROUND_RESET);
                        break;
                    case "beach":
                        mapView.append(YELLOW_BACKGROUND_BRIGHT + "###" + BACKGROUND_RESET);
                        break;
                    default:
                        mapView.append(YELLOW_BACKGROUND + "###" + BACKGROUND_RESET);
                        break;
                }
            }
            mapView.append("\n");
        }
        return mapView.toString();
    }
}
