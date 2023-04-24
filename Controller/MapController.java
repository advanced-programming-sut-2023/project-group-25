package Controller;


import Model.Cell;
import Model.Map;

public class MapController {
    int length, weigh;

    public MapController(int x, int y) {
        this.length = x;
        this.weigh = y;
    }

    public void initializeMapTemplate1() {
        Cell[][] map = new Cell[length][weigh];

        initializeCastlesLocation(map);
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = length / 6; i <= (2 * length) / 6; i++)
            for (int j = weigh / 6; j <= (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (4 * weigh) / 6; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = 0; i < length / 6; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = 0; j < weigh / 6; j++)
                map[i][j].setMaterial("rockLand");
        for (int i = (2 * length) / 6; i < (4 * length) / 6; i++)
            for (int j = (5 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("rockLand");
    }

    public void initializeMapTemplate2() {
        Cell[][] map = new Cell[length][weigh];

        initializeCastlesLocation(map);
        initializeIronLandsTemplate2(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = length / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * weigh) / 6 - 5; j < (3 * weigh) / 6 + 5; j++)
                map[i][j].setMaterial("sea");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * weigh) / 6; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * weigh) / 6; j < (5 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
    }

    private void initializeIronLandsTemplate2(Cell[][] map) {
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (2 * weigh) / 6; j < (3 * weigh) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = length / 6; i < (2 * length) / 6; i++)
            for (int j = (3 * weigh) / 6 + 5; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (2 * weigh) / 6; j < (3 * weigh) / 6 - 5; j++)
                map[i][j].setMaterial("ironLand");
        for (int i = (4 * length) / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * weigh) / 6 + 5; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("ironLand");
    }

    public void initializeMapTemplate3() {
        Cell[][] map = new Cell[length][weigh];

        initializeCastlesLocation(map);
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (4 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("sea");
        for (int i = (3 * length) / 6 - 5; i < (3 * length) / 6 + 5; i++)
            for (int j = (2 * weigh) / 6; j < (4 * weigh) / 6; j++)
                map[i][j].setMaterial("lowWater");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - 5; i++)
            for (int j = (4 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = 0; j < (2 * weigh) / 6; j++)
                map[i][j].setMaterial("grass");
        for (int i = (3 * length) / 6 + 5; i < (4 * length) / 6; i++)
            for (int j = (4 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("grass");
    }

    public void initializeCastlesLocation(Cell[][] map) {
        for (int i = 0; i < length / 6; i++)
            for (int j = 0; j < weigh / 6; j++)
                map[i][j].setMaterial("castle1");
        for (int i = 0; i < length / 6; i++)
            for (int j = (5 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("castle2");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = 0; j < weigh / 6; j++)
                map[i][j].setMaterial("castle3");
        for (int i = (5 * length) / 6; i < length; i++)
            for (int j = (5 * weigh) / 6; j < weigh; j++)
                map[i][j].setMaterial("castle4");
    }
}
