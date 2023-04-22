package Model;

public class Map {
//    private Map[3]templateMaps;
    private final Cell[][] cells;
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

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

}
