package Controller;

import Model.*;
import View.Commands;

import java.util.Objects;
import java.util.regex.Matcher;

import static Controller.RegisterLoginController.getCurrentUser;
import static Controller.RegisterLoginController.getOptionsFromMatcher;

public class GameController {
    private MilitaryPerson selectedUnit;
    //TODO: make selected unit null after changing the player.
    private Game currentGame;
    private int numberOfPlayers = 2;
    private int shownMapX;
    private int shownMapY;
    
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
    
    public void setSelectedUnit(MilitaryPerson selectedUnit) {
        this.selectedUnit = selectedUnit;
    }
    
    public String selectUnit(Matcher matcher) {
        int x = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(x, y)) return "invalid location";
        for (Person person : currentGame.getMap().getCells()[x - 1][y - 1].getPeople()) {
            if (person instanceof MilitaryPerson && person.getKing().getUsername().equals(getCurrentUser().getUsername())) {
                selectedUnit = (MilitaryPerson) person;
                return "success";
            }
        }
        return "don't have";
    }
    
    public String moveUnit(Matcher matcher) {
        //TODO: specify materials where units can't go; in their way or in the destination
        //TODO: specify how long can the unit go
        int x = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(x, y)) return "invalid location";
        if (selectedUnit == null) return "no selected unit";
        selectedUnit.getLocation().removePerson(selectedUnit);
        currentGame.getMap().getCells()[x - 1][y - 1].addPerson(selectedUnit);
        return "success";
    }
    
    public boolean isLocationValid(int x, int y) {
        return x >= 0 && y >= 0 && x <= currentGame.getMap().getLength() && y <= currentGame.getMap().getWidth();
    }
    
    public String createUnit(Matcher matcher) {
        String type = RegisterLoginController.getOptionsFromMatcher(matcher, "t", 2);
        //TODO FOR HOORA: checking validity of type
        int count = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "c", 2)));
        MilitaryPerson givenUnit;
        if (givenUnit.getTrainingCost() * count > Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getInventory())
            return "not enough coins";
        boolean haveProducts = haveNeededProductsForUnit(givenUnit, count);
        if (!haveProducts) return "not enough products";
        if (count > Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getKingFreePeopleNumber())
            return "not enough people";
        //TODO: checking if we're in the appropriate building
        createUnitWithGivenUnit(givenUnit);
        return "success";
    }
    
    private boolean haveNeededProductsForUnit(MilitaryPerson givenUnit, int count) {
        for (Product neededProduct : givenUnit.getNeededProducts()) {
            boolean weHaveTheProduct = false;
            for (Product kingProduct : Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getKingProducts()) {
                if (kingProduct.getName().equals(neededProduct.getName()) && kingProduct.getCount() >= count) {
                    weHaveTheProduct = true;
                    break;
                }
            }
            if (!weHaveTheProduct) return false;
        }
        return true;
    }
    
    
    private void createUnitWithGivenUnit(MilitaryPerson givenUnit) {
        MilitaryPerson militaryPerson = new MilitaryPerson(getCurrentUser(), givenUnit.getType(), givenUnit.getNeededProducts(), givenUnit.getFirePower(),
                givenUnit.getDefendPower(), givenUnit.getSpeed(), givenUnit.getTrainingCost(), "standing");
        Objects.requireNonNull(getKingdomByKing(getCurrentUser())).addPerson(militaryPerson);
        //TODO: defining location of the building it should be in
    }
    
    public String showAPartOfMap(Matcher matcher) {
        shownMapX = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "x", 2)));
        shownMapY = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(shownMapX, shownMapY)) return "invalid location";
        Map smallMap = makeSmallMap(currentGame.getMap(), shownMapX, shownMapY);
        return MapController.showMap(smallMap);
    }
    
    private Map makeSmallMap(Map bigMap, int x, int y) {
        Map smallMap = new Map(3, 3);
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                smallMap.getCells()[i + 1][j + 1] = currentGame.getMap().getCells()[shownMapX + i][shownMapY + i];
        return smallMap;
    }
    
    public String moveOnMap(Matcher matcher) {
        int dx = Integer.parseInt(matcher.group("verticalNumber"));
        int dy = Integer.parseInt(matcher.group("horizontalNumber"));
        String verticalDirection = matcher.group("verticalDirection");
        String horizontalDirection = matcher.group("horizontalDirection");
        if (verticalDirection.equals("up")) dx = -dx;
        if (horizontalDirection.equals("left")) dy = -dy;
        if (!isLocationValid(shownMapX + dx, shownMapY + dy)) return "invalid location";
        shownMapY = shownMapY + dy;
        shownMapX = shownMapX + dx;
        return MapController.showMap(makeSmallMap(currentGame.getMap(), shownMapX, shownMapY));
    }
    
    public String pourOil(Matcher matcher) {
        String direction = matcher.group();
        int dx = getDXByDirection(direction), dy = getDYByDirection(direction);
        int x = getLocationXOrY(currentGame.getMap(), selectedUnit.getLocation(), 'x');
        int y = getLocationXOrY(currentGame.getMap(), selectedUnit.getLocation(), 'y');
        if (!isLocationValid(x + dx, y + dy)) return "invalid location";
        currentGame.getMap().getCells()[x][y].setHasOil(true);
        currentGame.getMap().getCells()[x + dx][y + dy].setHasOil(true);
        return "success";
    }
    
    private int getLocationXOrY(Map map, Cell location, char ch) {
        Cell[][] mapCells = map.getCells();
        for (int i = 0; i < mapCells.length; i++)
            for (int j = 0; j < mapCells.length; j++)
                if (mapCells[i][j].equals(location)) {
                    if (ch == 'x') return i;
                    return j;
                }
        return -1;
    }
    
    private int getDXByDirection(String direction) {
        if (direction.equals("up")) return -1;
        if (direction.equals("down")) return 1;
        return 0;
    }
    
    private int getDYByDirection(String direction) {
        if (direction.equals("left")) return -1;
        if (direction.equals("right")) return 1;
        return 0;
    }
    
    public String digTunnel(Matcher matcher) {
        int x = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(x, y)) return "invalid location";
        currentGame.getMap().getCells()[x][y].setHasTunnel(true);
        return "success";
    }
    
    
    public String setMode(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!isLocationValid(x, y)) return "invalid location";
        for (Person person : currentGame.getMap().getCells()[x][y].getPeople()) {
            if (person instanceof MilitaryPerson && person.getKing().getUsername().equals(getCurrentUser().getUsername())) {
                MilitaryPerson militaryPerson = (MilitaryPerson) person;
                militaryPerson.setMode(matcher.group("mode"));
                return "success";
            }
        }
        return "no person";
    }
    
    public String disbandUnit() {
        Cell[][] mapCells = currentGame.getMap().getCells();
        for (int i = 0; i < mapCells.length; i++) {
            for (int j = 0; j < mapCells[i].length; j++) {
                if (mapCells[i][j].getBuilding().getType().equals("house") && mapCells[i][j].getBuilding().getKing().getUsername().equals(getCurrentUser().getUsername())) {
                    String toGetMatcher = "move unit to -x " + i + " -y " + j;
                    if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("success"))
                        return "success";
                }
            }
        }
        return "can't go";
    }
    
    public String attackEnemy(Matcher matcher) {
        int enemyX = Integer.parseInt(matcher.group("x"));
        int enemyY = Integer.parseInt(matcher.group("y"));
        for (Person person : currentGame.getMap().getCells()[enemyX][enemyY].getPeople()) {
            if (!person.getKing().equals(getCurrentUser())) {
                String toGetMatcher = "move unit to -x " + enemyX + " -y " + enemyY;
                if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("success")) {
                    //TODO: make it clear that it is different from moving.
                    return "success";
                } else return "can't go";
            }
        }
        return "no enemy";
    }
    
    public String aerialAttack(Matcher matcher) {
        int enemyX = Integer.parseInt(matcher.group("x"));
        int enemyY = Integer.parseInt(matcher.group("y"));
        for (Person person : currentGame.getMap().getCells()[enemyX][enemyY].getPeople()) {
            if (!person.getKing().equals(getCurrentUser())) {
                if (selectedUnit.getType().equals("Archer") || selectedUnit.getType().equals("Crossbowmen") || selectedUnit.getType().equals("Archer Bow")) {
                    if (selectedUnit.getShootingRange() >= (Math.sqrt((double) enemyY * enemyY + enemyX * enemyX))) {
                        //TODO:...
                        return "success";
                    } else return "out of range";
                } else return "no shooter";
            }
        }
        return "no enemy";
    }
    
    public void fight(MilitaryPerson unit1, MilitaryPerson unit2) {
        if (unit1.getFirePower() > unit2.getDefendPower())
            Objects.requireNonNull(getKingdomByKing(unit2.getKing())).removePerson(unit2);
        if (unit2.getFirePower() > unit1.getDefendPower())
            Objects.requireNonNull(getKingdomByKing(unit1.getKing())).removePerson(unit1);
    }
    
    private Kingdom getKingdomByKing(User king) {
        for (Kingdom kingdom : currentGame.getKingdoms()) {
            if (kingdom.getKing().equals(king)) return kingdom;
        }
        return null;
    }
}