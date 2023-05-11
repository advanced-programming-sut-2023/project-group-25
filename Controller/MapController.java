package Controller;


import Model.Cell;
import Model.Map;


public class MapController {
    
    public static final String BACKGROUND_RESET = "\033[0m";                //reset
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";      // LIGHT BLUE
    public static final String RED_BACKGROUND = "\033[48;5;88m";            // DARK RED
    public static final String RED_BACKGROUND_BRIGHT = "\033[48;5;9m";       // LIGHT RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";     // LIGHT GREEN
    public static final String BROWN_BACKGROUND_BRIGHT = "\033[0;103m";     // LIGHT BROWN
    public static final String BROWN_BACKGROUND = "\033[43m";               // DARK BROWN
    public static final String GRAY_BACKGROUND = "\033[48;5;8m";            // GRAY
    public static final String BLUE_BACKGROUND = "\033[44m";                // DARK BLUE
    public static final String GREEN_BACKGROUND = "\033[42m";               // DARK GREEN
    public static final String PURPLE_BACKGROUND = "\033[48;5;93m";         // PURPLE
    public static final String BLACK_BACKGROUND = "\033[48;5;16m";          // BLACK
    private static final String CYAN_BACKGROUND = "\033[46m";               // CYAN
    private static final String WHITE_BACKGROUND = "\033[15m";              // WHITE
    private static final String YELLOW_BACKGROUND_BRIGHT = "\033[48;5;226m";//YELLOW BRIGHT
    private static final String PINK_BACKGROUND = "\033[48;5;213m";          //PINK
    private static final String ORANGE_BACKGROUND = "\033[48;5;208m";       //ORANGE
    private static GameController gameController = null;
    int numberOfCastles;
    
    public MapController(GameController gameController) {
        MapController.gameController = gameController;
    }
    
    
    private static void setDefaultLand(int length, int width, Map map) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (map.getCells()[i][j] != null) continue;
                map.getCells()[i][j] = new Cell(i, j, "land");
            }
        }
    }
    
    public static String showMap(Map map) {
        int mapLength = map.getLength();
        int mapWidth = map.getWidth();
        StringBuilder mapView = new StringBuilder();
        
        for (int i = 0; i < mapLength; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < mapWidth; j++) {
                    String material = map.getCells()[i][j].getMaterial();
                    switch (material) {
                        case "ironLand":
                            mapView.append(RED_BACKGROUND + "#####|" + BACKGROUND_RESET);
                            break;
                        case "rockLand":
                            mapView.append(GRAY_BACKGROUND + "#####|" + BACKGROUND_RESET);
                            break;
                        case "sea":
                            mapView.append(BLUE_BACKGROUND + "#####|" + BACKGROUND_RESET);
                            break;
                        case "lowWater":
                            mapView.append(BLUE_BACKGROUND_BRIGHT + "#####|" + BACKGROUND_RESET);
                            break;
                        case "grass":
                            mapView.append(GREEN_BACKGROUND_BRIGHT + "#####|" + BACKGROUND_RESET);
                            break;
                        case "jolge":
                            mapView.append(GREEN_BACKGROUND + "#####|" + BACKGROUND_RESET);
                            break;
                        case "land":
                            mapView.append(BROWN_BACKGROUND + "#####|" + BACKGROUND_RESET);
                            break;
                        case "beach":
                            mapView.append(BROWN_BACKGROUND_BRIGHT + "#####|" + BACKGROUND_RESET);
                            break;
                        default:
                            mapView.append(getBackGroundColorString(material.replace("castle", "")))
                                    .append("#####|").append(BACKGROUND_RESET);
                            break;
                        
                    }
                }
                mapView.append("\n");
            }
        }
        
        return mapView.toString();
    }
    
    private static String getBackGroundColorString(String indexStr) {
        int index = Integer.parseInt(indexStr);
        switch (gameController.getCurrentGame().getKingdoms().get(index).getColor()) {
            case "yellow":
                return YELLOW_BACKGROUND_BRIGHT;
            case "purple":
                return PURPLE_BACKGROUND;
            case "pink":
                return PINK_BACKGROUND;
            case "orange":
                return ORANGE_BACKGROUND;
            case "white":
                return WHITE_BACKGROUND;
            case "black":
                return BLACK_BACKGROUND;
            case "cyan":
                return CYAN_BACKGROUND;
            case "red":
                return RED_BACKGROUND_BRIGHT;
            default:
                return BACKGROUND_RESET;
        }
    }
    
    public void setNumberOfCastles(int numberOfCastles) {
        this.numberOfCastles = numberOfCastles;
    }
    
    public void initializeMapTemplate1(int length, int width) {
        Map map = new Map(length, width);
        
        initializeCastlesLocation(map, length, width);
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                
                map.getCells()[i][j] = new Cell("sea");
        for (int i = length / 3; i <= length / 3; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (4 * length) / 6; i <= (4 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = length / 6; i < length / 3; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("ironLand");
        for (int i = (4 * length) / 6 + length / 8; i < 4 * length / 6 + length / 8 * 3; i++)
            
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "ironLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            
            for (int j = width / 6; j < width / 4; j++)
                map.getCells()[i][j] = new Cell("rockLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (3 * width) / 4; j < 5 * width / 6; j++)
                map.getCells()[i][j] = new Cell("rockLand");
        
        
        setDefaultLand(length, width, map);
        Map.setTemplateMap(0, map);
    }
    
    public void initializeMapTemplate2(int length, int width) {
        Map map = new Map(length, width);
        
        initializeCastlesLocation(map, length, width);
        initializeIronLandsTemplate2(map, length, width);
        initializeRockLandsTemplate2(map, length, width);
        for (int i = (3 * length) / 6 - length / 8; i < (3 * length) / 6 + length / 8; i++)
            for (int j = width / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "sea");
        for (int i = length / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * width) / 6 - width / 8; j < (3 * width) / 6 + width / 8; j++)
                map.getCells()[i][j] = new Cell(i, j, "sea");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = width / 6; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = width / 6; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");
        
        
        setDefaultLand(length, width, map);
        Map.setTemplateMap(1, map);
    }
    
    private void initializeRockLandsTemplate2(Map map, int length, int width) {
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - width / 8; j++)
                map.getCells()[i][j] = new Cell(i, j, "rockLand");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = (3 * width) / 6 + length / 8; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "rockLand");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - width / 8; j++)
                map.getCells()[i][j] = new Cell(i, j, "rockLand");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = (3 * width) / 6 + width / 8; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "rockLand");
    }
    
    private void initializeIronLandsTemplate2(Map map, int length, int width) {
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - width / 8; j++)
                map.getCells()[i][j] = new Cell(i, j, "ironLand");
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (3 * width) / 6 + width / 8; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (2 * width) / 6; j < (3 * width) / 6 - width / 8; j++)
                map.getCells()[i][j] = new Cell(i, j, "ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * width) / 6 + width / 8; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "ironLand");
    }
    
    public void initializeMapTemplate3(int length, int width) {
        Map map = new Map(length, width);
        
        initializeCastlesLocation(map, length, width);
        for (int i = (3 * length) / 6 - length / 8; i < (3 * length) / 6 + length / 8; i++)
            for (int j = width / 6; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("sea");
        for (int i = (3 * length) / 6 - length / 8; i < (3 * length) / 6 + length / 8; i++)
            for (int j = (4 * width) / 6; j < width - width / 6; j++)
                map.getCells()[i][j] = new Cell("sea");
        for (int i = (3 * length) / 6 - length / 8; i < (3 * length) / 6 + length / 8; i++)
            for (int j = (2 * width) / 6; j < (4 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "lowWater");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = width / 6; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = (4 * width) / 6; j < width - width / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = width / 6; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < width - width / 6; j++)
                map.getCells()[i][j] = new Cell("grass");
        
        setDefaultLand(length, width, map);
        
        Map.setTemplateMap(2, map);
    }
    
    public void initializeCastlesLocation(Map map, int length, int width) {
        int[][] castlePositions = new int[8][2];
        castlePositions[0][0] = 0;
        castlePositions[0][1] = 0;
        castlePositions[1][0] = length - 1;
        castlePositions[1][1] = width - 1;
        castlePositions[2][0] = 0;
        castlePositions[2][1] = width - 1;
        castlePositions[3][0] = length - 1;
        castlePositions[3][1] = 0;
        castlePositions[4][0] = 0;
        castlePositions[4][1] = width / 2;
        castlePositions[5][0] = length - 1;
        castlePositions[5][1] = width / 2;
        castlePositions[6][0] = length / 2;
        castlePositions[6][1] = 0;
        castlePositions[7][0] = length / 2;
        castlePositions[7][1] = width - 1;
        
        for (int i = 0; i < numberOfCastles; i++)
            map.getCells()[castlePositions[i][0]][castlePositions[i][1]] = new Cell(castlePositions[i][0], castlePositions[i][1], "castle" + i);
    }
    
    //extra colors:
//    public static final String BLACK_BACKGROUND = "\033[40m";   // BLACK
//    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
//    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
//    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
//    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
//    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE


}
