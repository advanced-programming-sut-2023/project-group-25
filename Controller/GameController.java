package Controller;

import Model.*;

import java.util.Random;
import java.util.regex.Matcher;

public class GameController {
    private Game currentGame;
    private int numberOfPlayers = 2;
    private Map map;
    private Cell cell;
    private Turn turn;


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        map = currentGame.getMap();
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

    public String clearCell(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = map.getCellByLocation(x, y);
        int templateNumber = currentGame.getMapTemplateNumber();
        Map originalMap = Map.getTemplateMaps()[templateNumber];
        String originalMaterial = originalMap.getCellByLocation(x, y).getMaterial();
        cell.setBuilding(null);
        cell.getPeople().removeAll(cell.getPeople());
        cell.getNaturalBlocks().removeAll(cell.getNaturalBlocks());
        cell.setMaterial(originalMaterial);
        return "Cell cleared successfully";
    }

    public String dropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = map.getCellByLocation(x, y);
        String direction = matcher.group("direction");
        NaturalBlock naturalBlock = new NaturalBlock("Rock", "Rock");
        if (x < 0 || y < 0 || !direction.matches("^[n|r|s|e|w]$"))
            return "Invalid input!";
        if (direction.matches("r")) {
            String chars = "swen";
            Random rnd = new Random();
            direction = String.valueOf(chars.charAt(rnd.nextInt(chars.length())));
        }
        naturalBlock.setDirection(direction);
        cell.addNaturalBlocks(naturalBlock);
        return "Rock added successfully";
    }

    public String dropObject(Matcher matcher) {
        String result = null;
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        cell = map.getCellByLocation(x, y);
        if (matcher.group("object").equals("tree")) {
            result = dropTree(x, y, cell, type);
        } else if (matcher.group("object").equals("building")) {
            result = dropBuilding(cell, type);
        } else if (matcher.group("object").equals("unit")) {
            result = dropUnit(cell, type, matcher.group("count"));
        }
        return result;
    }

    public String dropTree(int x, int y, Cell cell, String type) {
        NaturalBlock naturalBlock = new NaturalBlock(type, "Tree");
        if (x < 0 || y < 0)
            return "Invalid input!";
        if (cell.getMaterial().equals("water") || cell.getMaterial().equals("sea"))
            return "You can't drop a tree in this location!";
        cell.addNaturalBlocks(naturalBlock);
        return type + " added successfully";
    }


    public String dropBuilding(Cell cell, String type) {
        return null;
    }

    public String dropUnit(Cell cell, String type, String count) {
        return null;
    }

    public String showDetails(Matcher matcher) {
        int x, y;
        StringBuilder result = new StringBuilder();
        if (matcher.group("option1").equals("x")) {
            x = Integer.parseInt(matcher.group("input1"));
            y = Integer.parseInt(matcher.group("input2"));
        } else {
            y = Integer.parseInt(matcher.group("input1"));
            x = Integer.parseInt(matcher.group("input2"));
        }

        cell = map.getCellByLocation(x, y);
        result.append("Texture: ").append(cell.getMaterial()).append("\nBuilding: ").append(cell.getBuilding())
                .append("\nNumber of units: ").append(cell.getPeople().size()).append("\n");
        for (Person person : cell.getPeople()) {
            result.append(person.getType());
        }
        return result.toString();
    }

    public String showPopularityFactors() {
        StringBuilder result = new StringBuilder();
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        result.append("PopularityFactors: \n");
        for (PopularityFactor popularityFactor : currentKing.getKingPopularityFactors()) {
            result.append(popularityFactor).append("\n");
        }
        return result.toString();
    }

    public String showPopularity() {
        int result = 0;
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKing.getKingPopularityFactors()) {
            result += popularityFactor.getRate();
        }
        return "Popularity: " + result;
    }

    

}
