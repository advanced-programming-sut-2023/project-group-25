package Model;

public class Map {
    private Map[] templateMaps = new Map[3];
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

}
