package Controller;

import Model.*;
import View.Commands;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Controller.RegisterLoginController.*;

public class GameController {
    public String[] legalColors = {"yellow", "purple", "pink", "orange", "white", "black", "cyan", "red"};
    private MilitaryPerson selectedUnit;
    private MilitaryPerson patrollingUnit;
    private boolean isPatrollingStopped = false;
    private Game currentGame;
    private int numberOfPlayers;
    private Cell cell;
    private int shownMapX;
    private int shownMapY;
    private Building selectedBuilding;
    public ArrayList<NaturalBlock> tree=new ArrayList<>();


    public String newGame(String line) {
        String resultMessage = "";
        String[] lineUsernames = line.split("-");
        ArrayList<String> usernames = new ArrayList<>();
        for(String s: lineUsernames)
            usernames.add(s);
        usernames.add(RegisterLoginController.getCurrentUser().getUsername());
        for (int i = 0; i < usernames.size(); i++) {
            if (FileController.getUserByUsername(usernames.get(i)) == null)
                resultMessage = ("New game creation failed! Username [" + usernames.get(i) + "] does not exist; please try again!");
        }
        if(hasRepeatedUsername(usernames) && resultMessage == "")
            resultMessage = "You have repeated usernames in the list; please try again!";
        else if (resultMessage.equals("")) {
            File Games = new File("src/main/java/Database/Games.txt");
            ArrayList<String> content = FileController.readFileContent("src/main/java/Database/Games.txt");
            int gameId = content.size() / 4 + 1;
            ArrayList<Kingdom> kingdoms = new ArrayList<>();
            for (int i = 0; i < usernames.size(); i++) {
                Kingdom newKingdom = new Kingdom(FileController.getUserByUsername(usernames.get(i)), gameId);
                FileController.addKingdomToFile(newKingdom);
                kingdoms.add(newKingdom);
            }
            Game game = new Game(gameId, kingdoms);
            currentGame = game;
            FileController.addGameToFile(game);
            resultMessage = "New game created successfully! Game's ID: " + gameId;
        }
        return resultMessage;
    }

    public boolean hasRepeatedUsername(ArrayList<String> usernames) {
        for(int i = 0; i<usernames.size(); i++) {
            for(int j = i+1; j<usernames.size(); j++) {
                if(usernames.get(i).equals(usernames.get(j)))
                    return true;
            }
        }
        return false;
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
        cell = currentGame.getMap().getCellByLocation(x, y);
        if (!isLocationValid(x, y))
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
        if (!isLocationValid(x1, y1) || !isLocationValid(x2, y2))
            return "Invalid location!";
        for (int i = x1; i <= x2; i++)
            for (int j = y1; j <= y2; j++)
                if (currentGame.getMap().getCellByLocation(i, j).getBuilding() != null)
                    hasBuilding = true;
        if (!hasBuilding) {
            for (int i = x1; i <= x2; i++)
                for (int j = y1; j <= y2; j++)
                    currentGame.getMap().getCells()[i][j] = new Cell(i, j, matcher.group("type"));
            return "Cells texture changed successfully";
        }
        return "You can't change the texture of a cell with a building on it!";
    }

    public String clearCell(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int templateNumber = currentGame.getMapTemplateNumber();
        int length = currentGame.getMap().getLength();
        int width = currentGame.getMap().getWidth();
        MapController.initializeMapTemplate1(length, width);
        MapController.initializeMapTemplate2(length, width);
        MapController.initializeMapTemplate3(length, width);
        Map originalMap = Map.getTemplateMaps()[templateNumber];
        String originalMaterial = originalMap.getCellByLocation(x, y).getMaterial();
        currentGame.getMap().getCells()[x][y] = new Cell(x, y, originalMaterial);
//        currentGame.getMap().getCells()[x][y].setBuilding(null);
//        currentGame.getMap().getCells()[x][y].getPeople().removeAll(currentGame.getMap().getCellByLocation(x,y).getPeople());
//        currentGame.getMap().getCells()[x][y].getNaturalBlocks().removeAll(currentGame.getMap().getCellByLocation(x,y).getNaturalBlocks());
        return "Cell cleared successfully";
    }
    public void initializeTrees(){
        NaturalBlock block1 = new NaturalBlock("desertBush","tree");
        NaturalBlock block2 = new NaturalBlock("cherryPalm","tree");
        NaturalBlock block3 = new NaturalBlock("oliveTree","tree");
        NaturalBlock block4 = new NaturalBlock("coconutPalm","tree");
        NaturalBlock block5 = new NaturalBlock("date","tree");
        tree.add(block1);
        tree.add(block2);
        tree.add(block3);
        tree.add(block4);
        tree.add(block5);
    }

    public String dropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = currentGame.getMap().getCellByLocation(x, y);
        String direction = matcher.group("direction");
        NaturalBlock naturalBlock = new NaturalBlock("Rock", "Rock");
        if (!isLocationValid(x, y) || !direction.matches("^[n|r|s|e|w]$"))
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
        cell = currentGame.getMap().getCellByLocation(x, y);
        if (matcher.group("object").equals("tree")) {
            result = dropTree(x, y, cell, type);
        } else if (matcher.group("object").equals("building")) {
            result = dropBuilding(x, y, cell, type);
        } else if (matcher.group("object").equals("unit")) {
            result = dropUnit(x, y, cell, type, matcher.group("count"));
        }
        return result;
    }

    public String dropTree(int x, int y, Cell cell, String type) {
        boolean check=false;
        NaturalBlock naturalBlock = new NaturalBlock(type, "Tree");
        if (!isLocationValid(x, y))
            return "Invalid input!";
        if (cell.getMaterial().equals("water") || cell.getMaterial().equals("sea"))
            return "You can't drop a tree in this location!";
        for(NaturalBlock trees:tree){
            if(trees.getName().equals(type)){
                check=true;
            }
        }
        if(!check) return "This type of tree doesn't exist";
        cell.addNaturalBlocks(naturalBlock);
        return type + " added successfully";
    }


    public String dropBuilding(int x, int y, Cell cell, String type) {
        Building savedBuilding = null;
        String category = FileController.getBuildingCategoryByType(type);
        savedBuilding = getBuilding(type, savedBuilding, category);
        Building building = new Building(savedBuilding);

        if (!isLocationValid(x, y))
            return "Invalid input!";
        if ((type.equals("iron mine")) && (!cell.getMaterial().equals("ironLand")))
            return "Invalid ground type for " + type;
        else if ((type.equals("hops farmer") || type.equals("wheat farmer") || (type.equals("apple orchard")))
                && (!cell.getMaterial().equals("grass")))
            return "Invalid ground type for " + type;
        else if ((type.equals("quarry")) && (!cell.getMaterial().equals("rockLand")))
            return "Invalid ground type for " + type;
        else if (cell.getMaterial().equals("water") || cell.getMaterial().equals("sea") || cell.getBuilding() != null)
            return "You can't have a building in this location!";
        for (Product neededProduct : building.getBuildingNeededProducts()) {
            for (Product product : getKingdomByKing(currentGame.turn.getCurrentKing()).getKingProducts()) {
                if (neededProduct.getName().equals(product.getName())) {
                    if (neededProduct.getCount() >= product.getCount()) {
                        product.setCount(product.getCount() - neededProduct.getCount());
                        cell.setBuilding(building);
                        building.setKing(currentGame.turn.getCurrentKing());
                        return type + " added successfully";
                    } else return "You don't have enough products to drop " + type;
                }
            }
        }
        return null;
    }

    private Building getBuilding(String type, Building savedBuilding, String category) {
        if (category.equals("TrainingBuildings")) {
            savedBuilding = FileController.getTrainingBuildingByType(type);
        } else if (category.equals("ProductionBuildings")) {
            savedBuilding = FileController.getProductionBuildingByType(type);
        } else if (category.equals("StorageBuildings")) {
            savedBuilding = FileController.getStorageBuildingByType(type);
        } else if (category.equals("OtherBuildings")) {
            savedBuilding = FileController.getOtherBuildingByType(type);
        } else if (category.equals("FightingBuildings")) {
            savedBuilding = FileController.getFightingBuildingByType(type);
        } else if (category.equals("ShopBuildings")) {
            savedBuilding = FileController.getShopBuildingByType(type);
        }
        return savedBuilding;
    }

    public String dropUnit(int x, int y, Cell cell, String type, String countStr) {
        int count = Integer.parseInt(countStr);
        if (!isLocationValid(x, y)) return "You have entered invalid location!";
        ArrayList<Person> unusedUnits = Objects.requireNonNull(getKingdomByKing(currentGame.turn.getCurrentKing())).getKingUnusedUnits();
        ArrayList<Person> unusedUnitsCopy = new ArrayList<>(unusedUnits);
        for (Person unit : unusedUnitsCopy) {
            if (unit.getType().equals(type)) {
                cell.addPerson(unit);
                unit.setLocation(cell);
                unusedUnits.remove(unit);
                count--;
                if (count == 0) break;
            }
        }
        if (count > 0) return "You don't have enough " + type + "s!";
        return "Unit added successfully!";
    }

    public String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = currentGame.getMap().getCellByLocation(x, y);
        if (!isLocationValid(x, y))
            return "Invalid input!";
        if (Pattern.compile("castle\\d").matcher(cell.getMaterial()).find())
            return "You cannot select a castle!";
        if (cell.getBuilding() == null)
            return "There is no building in this location";
        if (!cell.getBuilding().getKing().equals(currentGame.turn.getCurrentKing()))
            return "This building doesn't belong to you";
        selectedBuilding = cell.getBuilding();
        return "selected";
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

        cell = currentGame.getMap().getCellByLocation(x, y);

        Map map = new Map(1, 1);
        map.getCells()[0][0] = new Cell(cell.getMaterial());
        result.append(MapController.showMap(map));

        result.append("Texture: ").append(cell.getMaterial()).append("\nBuilding: ");
        if (cell.getBuilding() != null) result.append(cell.getBuilding());
        else result.append("no building");
        result.append("\nNumber of units: ").append(cell.getPeople().size()).append("\n");
        for (Person person : cell.getPeople()) {
            result.append(person.getType());
        }
        return result.toString();
    }

    public String showPopularityFactors() {
        StringBuilder result = new StringBuilder();
        Kingdom currentKingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        result.append("PopularityFactors: \n");
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            result.append(popularityFactor.getName()).append(popularityFactor.getPopularityAmount()).append("\n");
        }
        return result.toString();
    }

    public String showPopularity() {
        int result = 0;
        Kingdom currentKingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            result += popularityFactor.getPopularityAmount();
        }
        return "Popularity: " + result;
    }

    public String showFoodList() {
        StringBuilder result = new StringBuilder();
        Kingdom currentKingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        ArrayList<Product> products = new ArrayList<>(currentKingdom.getKingProducts());
        result.append("Apple: ").append(showEachFood(products, "apple")).append("\n");
        result.append("Meat: ").append(showEachFood(products, "meat")).append("\n");
        result.append("Bread: ").append(showEachFood(products, "bread")).append("\n");
        result.append("Cheese: ").append(showEachFood(products, "cheese"));
        return result.toString();
    }

    private int showEachFood(ArrayList<Product> products, String food) {
        int count = 0;
        for (Product product : products) {
            if (product.getName().equals(food))
                count++;
        }
        return count;
    }

    public String ratePopularityFactor(Matcher matcher) {
        if (matcher.group("popularityFactor").equals("food"))
            return rateFood(matcher.group("rateNumber"));
        if (matcher.group("popularityFactor").equals("fear"))
            return rateFear(matcher.group("rateNumber"));
        if (matcher.group("popularityFactor").equals("tax"))
            return rateTax(matcher.group("rateNumber"));
        return null;
    }

    private String rateTax(String rateNumber) {
        int rate = Integer.parseInt(rateNumber);
        Kingdom currentKingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("tax")) {
                if (!(rate >= 4 && rate <= 8))
                    return "Invalid rate";
                if (rate <= 0)
                    popularityFactor.setPopularityAmount(rate * (-2) + 1);
                else if (rate <= 4)
                    popularityFactor.setPopularityAmount(rate * (-2));
                else if (rate <= 8)
                    popularityFactor.setPopularityAmount(rate * (-4) + 8);
                popularityFactor.setRate(rate);
            }
        }
        return "success";
    }

    private String rateFear(String rateNumber) {
        int rate = Integer.parseInt(rateNumber);
        Kingdom currentKingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("fear")) {
                if (!(rate >= -5 && rate <= 5))
                    return "Invalid rate";
                popularityFactor.setPopularityAmount(rate);
                popularityFactor.setRate(rate);
            }
        }
        return "success";
    }

    private String rateFood(String rateNumber) {
        int rate = Integer.parseInt(rateNumber);
        Kingdom currentKingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("food")) {
                if (!(rate >= -2 && rate <= 2))
                    return "Invalid rate";
                popularityFactor.setPopularityAmount(rate * 4);
                popularityFactor.setRate(rate);
            }
            //TODO:give 0.5x + 1 amount of food to people
            //TODO:give error to invalid numbers
        }
        return "success";
    }

    public String showPopularityFactorRate(Matcher matcher) {
        StringBuilder result = new StringBuilder();
        int rate = 0;
        Kingdom currentKingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals(matcher.group("popularityFactor"))) {
                rate = popularityFactor.getRate();
                break;
            }
        }
        result.append(matcher.group("popularityFactor")).append(": ").append(String.valueOf(rate));
        return result.toString();
    }

    public void setSelectedUnit(MilitaryPerson selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public String selectUnit(Matcher matcher) {
        int x = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(x, y)) return "You have entered invalid location!";
        for (Person person : currentGame.getMap().getCells()[x][y].getPeople()) {
            if (person instanceof MilitaryPerson && person.getKing().getUsername().equals(getCurrentUser().getUsername())) {
                selectedUnit = (MilitaryPerson) person;
                return "Unit is selected successfully!";
            }
        }
        return "You don't have any people in this location!";
    }

    public String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(x, y)) return "You have entered invalid location!";
        if (selectedUnit == null) return "You haven't selected a unit!";
        if (selectedUnit.equals(patrollingUnit)) isPatrollingStopped = true;
        List<Cell> pathCells = PathFinder.findPath(selectedUnit.getLocation(), currentGame.getMap().getCellByLocation(x, y), currentGame.getMap());
        if (pathCells.size() == 1) return "The path is blocked!";
        if (pathCells.size() > selectedUnit.getMovingRange()) return "This move is out of the range of the unit!";
        for (Cell cell : pathCells) {
            removeAndAddInMoving(selectedUnit, cell.getX(), cell.getY());
        }
        return "Unit has been moved successfully!";
    }

    public boolean isLocationValid(int x, int y) {
        return x >= 0 && y >= 0 && x <= currentGame.getMap().getLength() && y <= currentGame.getMap().getWidth();
    }

    public String createUnit(Matcher matcher) {
        String type = RegisterLoginController.getOptionsFromMatcher(matcher, "t", 2);
        int count = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "c", 2)));
        MilitaryPerson givenUnit = FileController.getMilitaryPersonByType(type);
        if (givenUnit == null) return "You have entered an invalid type of unit!";
        Kingdom kingdom = getKingdomByKing(getCurrentUser());
        if (givenUnit.getTrainingCost() * count > Objects.requireNonNull(kingdom).getInventory())
            return "You don't have enough coins!";
        boolean haveProducts = haveNeededProductsForUnit(givenUnit, count);
        if (!haveProducts) return "You don't have needed products to create this unit!";

        if (count > Objects.requireNonNull(kingdom).getJoblessCounter())
            return "You don't have enough number of people to be trained!";
        if (selectedBuilding == null) return "You should first select the related building to create a unit!";
        if (!unitMatchesSelectedBuilding(givenUnit)) return "You can't create this unit in this building!";
        for (int i = 0; i < count; i++) {
            createUnitWithGivenUnit(givenUnit);
            kingdom.reduceJoblessCounter();
        }
        return "Unit created successfully!";
    }

    private boolean unitMatchesSelectedBuilding(MilitaryPerson givenUnit) {
        return ((givenUnit.getNationality().equals("European") && selectedBuilding.getType().equals("barracks")) ||
                (givenUnit.getNationality().equals("Arab") && selectedBuilding.getType().equals("mercenary post")));
    }


    private void createUnitWithGivenUnit(MilitaryPerson givenUnit) {
        MilitaryPerson militaryPerson = new MilitaryPerson(getCurrentUser(), givenUnit.getType(), givenUnit);
        Objects.requireNonNull(getKingdomByKing(getCurrentUser())).addPerson(militaryPerson);
        Objects.requireNonNull(getKingdomByKing(currentGame.turn.getCurrentKing())).addUnusedUnit(militaryPerson);
        //TODO: defining location of the building it should be in
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


    public String showAPartOfMap(Matcher matcher) {
        shownMapX = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "x", 2)));
        shownMapY = Integer.parseInt(Objects.requireNonNull(RegisterLoginController.getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(shownMapX, shownMapY)) {
            shownMapX = 0;
            shownMapY = 0;
            return "You have entered invalid location!";
        }
        Map smallMap = makeSmallMap(currentGame.getMap(), shownMapX, shownMapY);
        assert smallMap != null;
        return MapController.showMap(smallMap);
    }

    private Map makeSmallMap(Map bigMap, int x, int y) {
        Map smallMap = new Map(3, 3);
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (!isLocationValid(shownMapX + i, shownMapY + j)) return null;
                smallMap.getCells()[i + 1][j + 1] = currentGame.getMap().getCells()[shownMapX + i][shownMapY + j];
            }
        return smallMap;
    }

    public String moveOnMap(Matcher matcher) {
        if (shownMapX == 0 && shownMapY == 0) return "You haven't chosen a location yet!";
        int dx = Integer.parseInt(matcher.group("verticalNumber"));
        int dy = Integer.parseInt(matcher.group("horizontalNumber"));
        String verticalDirection = matcher.group("verticalDirection");
        String horizontalDirection = matcher.group("horizontalDirection");
        if (verticalDirection.equals("up")) dx = -dx;
        if (horizontalDirection.equals("left")) dy = -dy;
        if (!isLocationValid(shownMapX + dx, shownMapY + dy)) return "You have entered invalid location!";
        shownMapY = shownMapY + dy;
        shownMapX = shownMapX + dx;
        return MapController.showMap(Objects.requireNonNull(makeSmallMap(currentGame.getMap(), shownMapX, shownMapY)));
    }

    public String pourOil(Matcher matcher) {
        String direction = matcher.group();
        int dx = getDXByDirection(direction), dy = getDYByDirection(direction);
        int x = getLocationXOrY(currentGame.getMap(), selectedUnit.getLocation(), 'x');
        int y = getLocationXOrY(currentGame.getMap(), selectedUnit.getLocation(), 'y');
        if (!isLocationValid(x + dx, y + dy)) return "You have entered invalid direction!";
        currentGame.getMap().getCells()[x][y].setHasOil(true);
        currentGame.getMap().getCells()[x + dx][y + dy].setHasOil(true);
        return "Oil has been poured successfully!";
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
        if (!isLocationValid(x, y)) return "You have entered invalid location!";
        currentGame.getMap().getCells()[x][y].setHasTunnel(true);
        return "Tunnel has been dug successfully!";
    }


    public String setMode(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!isLocationValid(x, y)) return "You have entered invalid location!";
        for (Person person : currentGame.getMap().getCells()[x][y].getPeople()) {
            if (person instanceof MilitaryPerson && person.getKing().getUsername().equals(getCurrentUser().getUsername())) {
                MilitaryPerson militaryPerson = (MilitaryPerson) person;
                militaryPerson.setMode(matcher.group("mode"));
                return "Changed mode successfully!";
            }
        }
        return "You have no military person in this location!";
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
                    return "Selected unit attacked successfully!";
                } else return "Selected unit can't got to this location!";
            }
        }
        return "There's no enemy in this location!";
    }

    public String aerialAttack(Matcher matcher) {
        int enemyX = Integer.parseInt(matcher.group("x"));
        int enemyY = Integer.parseInt(matcher.group("y"));
        for (Person person : currentGame.getMap().getCells()[enemyX][enemyY].getPeople()) {
            if (!person.getKing().equals(getCurrentUser())) {
                if (selectedUnit.getType().equals("Archer") || selectedUnit.getType().equals("Crossbowmen") || selectedUnit.getType().equals("Archer Bow")) {
                    if (selectedUnit.getShootingRange() >= (Math.sqrt((double) enemyY * enemyY + enemyX * enemyX))) {
                        currentGame.getMap().getCells()[enemyX][enemyY].addToKingdomsWithArrows(currentGame.getKingdomByKing(currentGame.turn.getCurrentKing()));
                        return "You made the aerial attack successfully!";
                    } else return "The enemy you want to attack is out of range of the selected unit!";
                } else return "Selected unit is not able to shoot!";
            }
        }
        return "There is no enemy in this location!";
    }

    public void fight(MilitaryPerson unit1, MilitaryPerson unit2) {
        if (unit1.getFirePower() > unit2.getDefendPower())
            Objects.requireNonNull(getKingdomByKing(unit2.getKing())).removePerson(unit2);
        if (unit2.getFirePower() > unit1.getDefendPower())
            Objects.requireNonNull(getKingdomByKing(unit1.getKing())).removePerson(unit1);
    }

    private Kingdom getKingdomByKing(User king) {
        for (Kingdom kingdom : currentGame.getKingdoms()) {
            if (kingdom.getKing().getUsername().equals(king.getUsername())) return kingdom;
        }
        return null;
    }

    public String buildEquipment(Matcher matcher) {
        String equipmentName = matcher.group("equipmentName");
        AttackEquipment attackEquipment = null;
        if (!selectedUnit.getType().equals("engineer")) return "not engineer";
        for (Product neededMaterials : attackEquipment.getUsedMaterials()) {
            boolean weHaveNeededMaterials = false;
            for (Product kingProduct : Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getKingProducts()) {
                if (neededMaterials.getCount() <= kingProduct.getCount()) {
                    weHaveNeededMaterials = true;
                    break;
                }
            }
            if (!weHaveNeededMaterials) return "don't have materials";
        }
        createEquipmentWithGivenEquipment(attackEquipment);
        return "success";
    }

    private void createEquipmentWithGivenEquipment(AttackEquipment gitvenEquipment) {
        AttackEquipment attackEquipment = new AttackEquipment(gitvenEquipment.getName(),
                gitvenEquipment.getUsedMaterials(), getCurrentUser(), selectedUnit);
        getKingdomByKing(getCurrentUser()).addAttackEquipment(attackEquipment);
    }

    public String setKingdomColors(String colorsStr) {
        String[] colors = colorsStr.split("-");
        if (colors.length < currentGame.getKingdoms().size()) return "few colors";
        if (colors.length > currentGame.getKingdoms().size()) return "too many colors";
        if (!isAColorRepeated(colors)) return "repeated color";
        for (int i = 0; i < colors.length; i++) {
            if (!isColorLegal(colors[i])) return "bad color";
            currentGame.setColorOfKingdom(i, colors[i]);
        }
        return "success";
    }

    private boolean isColorLegal(String color) {
        for (String legalColor : legalColors) {
            if (color.equals(legalColor)) return true;
        }
        return false;
    }

    private boolean isAColorRepeated(String[] colors) {
        for (int i = 0; i < colors.length; i++) {
            for (int j = i + 1; j < colors.length; j++) {
                if (colors[i].equals(colors[j])) return false;
            }
        }
        return true;
    }

    public String patrol(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if (!isLocationValid(x1 -1, y1-1) || !isLocationValid(x2-1, y2-1)) return "You have entered invalid location!";
        patrollingUnit = selectedUnit;
        if (patrollingUnit == null) return "You should first select a unit to patrol!";
        if ((Math.abs(x1 - x2) + Math.abs(y1 - y2)) > selectedUnit.getMovingRange()) return "The distance is out of the unit's moving range!";
        while (!isPatrollingStopped) {
            removeAndAddInMoving(patrollingUnit, x2-1, y2-1);
            removeAndAddInMoving(patrollingUnit, x1-1, y1-1);
        }
        return "The unit patrolling!";
    }

    private void removeAndAddInMoving(MilitaryPerson unit, int x, int y) {
        patrollingUnit.getLocation().removePerson(unit);
        Cell destination = currentGame.getMap().getCells()[x][y];
        destination.addPerson(unit);
        unit.setLocation(destination);
    }

    public String repair() {
        Building savedBuilding = null;
        String category = FileController.getBuildingCategoryByType(selectedBuilding.getType());
        savedBuilding = getBuilding(selectedBuilding.getType(), savedBuilding, category);
        Kingdom kingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        if (selectedBuilding == null)
            return "You haven't selected a building yet";
        if (selectedBuilding.getHitPoint() < savedBuilding.getHitPoint()) {
            for (Product product : kingdom.getKingProducts()) {
                if (product.getName().equals("stone")) {
                    for (Product buildingCost : selectedBuilding.getBuildingNeededProducts())
                        if (buildingCost.getName().equals("stone")) {
                            if (product.getCount() != buildingCost.getCount())
                                return "You don't have enough stone!";
                        }
                } else {
                    Cell selectedBuildingLocation = selectedBuilding.getLocation();
                    int x = selectedBuildingLocation.getX();
                    int y = selectedBuildingLocation.getY();
                    for (int i = x - 1; i < x + 1; i++) {
                        for (int j = y - 1; j < y + 1; j++) {
                            for (Person person : currentGame.getMap().getCells()[i][j].getPeople()) {
                                if (!person.getKing().equals(currentGame.turn.getCurrentKing()))
                                    return "You can't repair a building while being under attack";
                            }
                        }
                    }
                }
            }
        } else if (selectedBuilding.getHitPoint() == savedBuilding.getHitPoint()) {
            return selectedBuilding.getType() + " doesn't need to be repaired";
        }
        selectedBuilding.setHitPoint(savedBuilding.getHitPoint());
        selectedBuilding = null;
        return "You have repaired " + selectedBuilding.getType();
    }

    public void nextTurn() {
        ArrayList<Kingdom> allGameUsers = new ArrayList<>(currentGame.getKingdoms());
        currentGame.turn.setCurrentKing(allGameUsers.get(Turn.getTurnCounter() % allGameUsers.size()).getKing());
        Turn.setTurnCounter(Turn.getTurnCounter() + 1);
        selectedBuilding = null;
        selectedUnit = null;
        patrollingUnit = null;
        isPatrollingStopped = false;
    }
}