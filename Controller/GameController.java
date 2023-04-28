package Controller;

import Model.Cell;
import Model.Game;
import Model.Map;

import java.util.regex.Matcher;

public class GameController {
    private Game currentGame;
    private int numberOfPlayers = 2;
    private Map map;
    private Cell cell;


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public String setCellMaterial(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = map.getCellByLocation(x, y);
        if (x < 0 || y < 0)
            return "Invalid location!";
        else if (cell.getBuilding() != null)
            return "You can't change the texture of a cell with a building on it!";
        cell.setMaterial(matcher.group("type"));
        return "Cell's texture changed successfully";
    }

    public String setCellBlockMaterial(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        boolean hasBuilding = false;
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0)
            return "Invalid location!";
        for (int i = x1; i <= x2; i++)
            for (int j = y1; j <= y2; j++)
                if (map.getCellByLocation(i, j).getBuilding() != null)
                    hasBuilding = true;
        if (!hasBuilding) {
            cell.setMaterial(matcher.group("type"));
            return "Cells texture changed successfully";
        }
        return "You can't change the texture of a cell with a building on it!";
    }

}
