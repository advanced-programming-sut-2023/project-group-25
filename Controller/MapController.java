package Controller;


import Model.Cell;
import Model.Map;

public class MapController {
    int x, y;

    public MapController(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void initializeMapTemplate1() {
        Cell[][] map = new Cell[x][y];

        initializeCastlesLocation(map);
        for (int i = (2 * x) / 6; i < (4 * x) / 6; i++)
            for (int j = (2 * y) / 6; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = x / 6; i <= (2 * x) / 6; i++)
            for (int j = y / 6; j <= (2 * y) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (4 * x) / 6; i < (5 * x) / 6; i++)
            for (int j = (4 * y) / 6; j < (5 * y) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = 0; i < x / 6; i++)
            for (int j = (2 * y) / 6; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (5 * x) / 6; i < x; i++)
            for (int j = (2 * y) / 6; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (2 * x) / 6; i < (4 * x) / 6; i++)
            for (int j = 0; j < y / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * x) / 6; i < (4 * x) / 6; i++)
            for (int j = (5 * y) / 6; j < y; j++)
                map[i][j].setMaterial("rockLand");
    }

    public void initializeMapTemplate2() {
        Cell[][] map = new Cell[x][y];

        initializeCastlesLocation(map);
        for (int i = (3 * x) / 6 - 5; i < (3 * x) / 6 + 5; i++)
            for (int j = 0; j < (5 * y) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = x / 6; i < (5 * x) / 6; i++)
            for (int j = (3 * y) / 6 - 5; j < (3 * y) / 6 + 5; j++)
                map[i][j].setMaterial("sea");
        for (int i = x / 6; i < (2 * x) / 6; i++)
            for (int j = (2 * y) / 6; j < (3 * y) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = x / 6; i < (2 * x) / 6; i++)
            for (int j = (3 * y) / 6 + 5; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * x) / 6; i < (5 * x) / 6; i++)
            for (int j = (2 * y) / 6; j < (3 * y) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * x) / 6; i < (5 * x) / 6; i++)
            for (int j = (3 * y) / 6 + 5; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (2 * x) / 6; i < (3 * x) / 6 - 5; i++)
            for (int j = (2 * y) / 6; j < (3 * y) / 6 - 5; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * x) / 6; i < (3 * x) / 6 - 5; i++)
            for (int j = (3 * y) / 6 + 5; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (3 * x) / 6 + 5; i < (4 * x) / 6; i++)
            for (int j = (2 * y) / 6; j < (3 * y) / 6 - 5; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (3 * x) / 6 + 5; i < (4 * x) / 6; i++)
            for (int j = (3 * y) / 6 + 5; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * x) / 6; i < (3 * x) / 6 - 5; i++)
            for (int j = 0; j < (2 * y) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * x) / 6; i < (3 * x) / 6 - 5; i++)
            for (int j = (4 * y) / 6; j < (5 * y) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * x) / 6 + 5; i < (4 * x) / 6; i++)
            for (int j = 0; j < (2 * y) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * x) / 6 + 5; i < (4 * x) / 6; i++)
            for (int j = (4 * y) / 6; j < (5 * y) / 6; j++)
                map[i][j].setMaterial("grass");
    }

    public void initializeMapTemplate3() {
        Cell[][] map = new Cell[x][y];

        initializeCastlesLocation(map);
        for (int i = (3 * x) / 6 - 5; i < (3 * x) / 6 + 5; i++)
            for (int j = 0; j < (2 * y) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * x) / 6 - 5; i < (3 * x) / 6 + 5; i++)
            for (int j = (4 * y) / 6; j < y; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * x) / 6 - 5; i < (3 * x) / 6 + 5; i++)
            for (int j = (2 * y) / 6; j < (4 * y) / 6; j++)
                map[i][j].setMaterial("water");
        for (int i = (2 * x) / 6; i < (3 * x) / 6 - 5; i++)
            for (int j = 0; j < (2 * y) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * x) / 6; i < (3 * x) / 6 - 5; i++)
            for (int j = (4 * y) / 6; j < y; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * x) / 6 + 5; i < (4 * x) / 6; i++)
            for (int j = 0; j < (2 * y) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * x) / 6 + 5; i < (4 * x) / 6; i++)
            for (int j = (4 * y) / 6; j < y; j++)
                map[i][j].setMaterial("grass");
    }

    public void initializeCastlesLocation(Cell[][] map) {
        for (int i = 0; i < x / 6; i++)
            for (int j = 0; j < y / 6; j++)
                map[i][j].setMaterial("castle1");
        for (int i = 0; i < x / 6; i++)
            for (int j = (5 * y) / 6; j < y; j++)
                map[i][j].setMaterial("castle2");
        for (int i = (5 * x) / 6; i < x; i++)
            for (int j = 0; j < y / 6; j++)
                map[i][j].setMaterial("castle3");
        for (int i = (5 * x) / 6; i < x; i++)
            for (int j = (5 * y) / 6; j < y; j++)
                map[i][j].setMaterial("castle4");
    }
}
