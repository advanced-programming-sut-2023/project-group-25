package Model;

public class Map {
<<<<<<< HEAD
    private Map[] templateMaps = new Map[3];
    private Cell[][] cells;
=======
//    private Map[3]templateMaps;
    private final Cell[][] cells;
    private int length;
    private int width;
>>>>>>> 39b74a1157e0efba13342a3a03b774832c7ab250


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

<<<<<<< HEAD
=======
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

>>>>>>> 39b74a1157e0efba13342a3a03b774832c7ab250
}
