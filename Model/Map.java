package Model;

public class Map {
    private Map[3]templateMaps=new Map[];
    private Cell[][] cells;
    private int height;
    private int width;


    public Map(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Cell getCellByLocation(int width, int height) {
        return null;
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
