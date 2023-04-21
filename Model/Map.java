package Model;

public class Map {
    private Map[3]templateMaps;
    private Cell[][] cells;


    public Map(int height, int width) {
        cells=new Cell[height][width];
    }

    public Cell getCellByLocation(int x, int y) {
        return cells[x][y];
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
