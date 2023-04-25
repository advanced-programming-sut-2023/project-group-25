package Model;

public class Map {

    private Map[] templateMaps = new Map[3];
    private Cell[][] cells;
    private int length;
    private int width;

    public Map(int length, int width) {
        this.length = length;
        this.width = width;
        cells=new Cell[length][width];
    }
    public Cell getCellByLocation(int x, int y) {
        return cells[x][y];
    }

    public Cell[][] getCells() {
        return cells;
    }
    public void setTemplateMaps(Map[] templateMaps) {
        this.templateMaps = templateMaps;
    }
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Map[] getTemplateMaps() {
        return templateMaps;
    }
}
