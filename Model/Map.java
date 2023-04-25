package Model;

public class Map {
    
    private static Map[] templateMaps = new Map[3];
    
    private int length;
    private int width;
    private Cell[][] cells;
    
    public Map(int length, int width) {
        this.length = length;
        this.width = width;
        cells = new Cell[length][width];
    }
    
    public Cell getCellByLocation(int x, int y) {
        return cells[x][y];
    }
    
    public Cell[][] getCells() {
        return cells;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getWidth() {
        return width;
    }
    
    public static Map[] getTemplateMaps() {
        return templateMaps;
    }
    
    public static void setTemplateMap(int index, Map templateMap) {
        templateMaps[index] = templateMap;
    }
}
