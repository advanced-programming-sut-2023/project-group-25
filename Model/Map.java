package Model;

public class Map {
<<<<<<< HEAD
    private Map[] templateMaps=new Map[3];
=======
<<<<<<< HEAD
    private Map[] templateMaps = new Map[3];
>>>>>>> 0b96c219f23dc27ab6f5cfcf779fd88369f5e910
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
    public void setTemplateMaps(Map[] templateMaps) {
        this.templateMaps = templateMaps;
=======
<<<<<<< HEAD
=======
    public int getLength() {
        return length;
>>>>>>> 0b96c219f23dc27ab6f5cfcf779fd88369f5e910
    }


>>>>>>> 39b74a1157e0efba13342a3a03b774832c7ab250
}
