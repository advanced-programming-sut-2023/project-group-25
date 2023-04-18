package Model;

public class Map {
    private Map[3]templateMaps;
    private Cell[][] cells;
    private int height;
    private int width;


    public Map(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Cell getCellByLocation(int width, int height) {
        return cells[width][height];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
