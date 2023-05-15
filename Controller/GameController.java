package Controller;

import Model.*;
import Model.Map;
import View.Commands;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Controller.RegisterLoginController.*;

public class GameController {
    public String[] legalColors = {"yellow", "purple", "pink", "orange", "white", "black", "cyan", "red"};
    public ArrayList<NaturalBlock> tree = new ArrayList<>();
    private Person selectedUnit;
    private MilitaryPerson patrollingUnit;
    private boolean isPatrollingStopped = false;
    private Game currentGame;
    private int numberOfPlayers;
    private Cell cell;
    private int shownMapX;
    private int shownMapY;
    private Building selectedBuilding;


    private static String checkTheNeededProducts(ArrayList<Product> product1, Kingdom currentKingdom) {
        for (Product neededProduct : product1) {
            boolean weHaveIt = false;
            for (Product kingProduct : currentKingdom.getKingProducts()) {
                if (neededProduct.getName().equals(kingProduct.getName())) {
                    weHaveIt = true;
                    if (neededProduct.getCount() > kingProduct.getCount()) {
                        return "You don't have enough " + neededProduct.getName();
                    }
                }
            }
            if (!weHaveIt) return "You don't have any " + neededProduct.getName();
        }
        return "ok";
    }


    public String newGame(String line) {
        String resultMessage = "";
        String[] lineUsernames = line.split("-");
        ArrayList<String> usernames = new ArrayList<>();
        usernames.addAll(Arrays.asList(lineUsernames));
        usernames.add(RegisterLoginController.getCurrentUser().getUsername());
        for (int i = 0; i < usernames.size(); i++) {
            if (FileController.getUserByUsername(usernames.get(i)) == null)
                resultMessage = ("New game creation failed! Username [" + usernames.get(i) + "] does not exist; please try again!");
        }
        if (hasRepeatedUsername(usernames) && resultMessage == "")
            resultMessage = "You have repeated usernames in the list; please try again!";
        else if (resultMessage.equals("")) {
            File Games = new File("src/main/java/Database/Games.txt");
            ArrayList<String> content = FileController.readFileContent("src/main/java/Database/Games.txt");
            int gameId = content.size() / 4 + 1;
            ArrayList<Kingdom> kingdoms = new ArrayList<>();
            kingdoms = createKingdomsInitially(kingdoms, usernames, gameId);
            Game game = new Game(gameId, kingdoms);
            currentGame = game;
            resultMessage = "New game created successfully! Game's ID: " + gameId;
        }
        return resultMessage;
    }
    
    public ArrayList<Kingdom> createKingdomsInitially(ArrayList<Kingdom> kingdoms, ArrayList<String> usernames, int gameId) {
        for (int i = 0; i < usernames.size(); i++) {
            Kingdom newKingdom = new Kingdom(FileController.getUserByUsername(usernames.get(i)), gameId);
            ArrayList<Product> products = new ArrayList<>();
            Product bread = FileController.getProductByName("bread");
            bread.setCount(8);
            products.add(bread);
            Product wood = FileController.getProductByName("wood");
            wood.setCount(100);
            products.add(wood);
            Product stone = FileController.getProductByName("stone");
            stone.setCount(50);
            products.add(stone);
            newKingdom.setKingProducts(products);
            FileController.addKingdomToFile(newKingdom);
            kingdoms.add(newKingdom);
        }
        return kingdoms;
    }


    public void addJoblessWorkers() {

        for (int i = 0; i < 8; i++) {
            WorkerPerson workerPerson = new WorkerPerson(currentGame.turn.getCurrentKing(), "jobless", null);
            getKingdomByKing(currentGame.turn.getCurrentKing()).setKingPeople(workerPerson);
        }
    }

    public boolean hasRepeatedUsername(ArrayList<String> usernames) {
        for (int i = 0; i < usernames.size(); i++) {
            for (int j = i + 1; j < usernames.size(); j++) {
                if (usernames.get(i).equals(usernames.get(j)))
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
        return "Cell cleared successfully";
    }

    public void initializeTrees() {
        NaturalBlock block1 = new NaturalBlock("desertBush", "tree");
        NaturalBlock block2 = new NaturalBlock("cherryPalm", "tree");
        NaturalBlock block3 = new NaturalBlock("oliveTree", "tree");
        NaturalBlock block4 = new NaturalBlock("coconutPalm", "tree");
        NaturalBlock block5 = new NaturalBlock("date", "tree");
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
        if (!isLocationValid(x - 1, y - 1)) return "Invalid location!";
        cell = currentGame.getMap().getCellByLocation(x - 1, y - 1);
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
        boolean check = false;
        NaturalBlock naturalBlock = new NaturalBlock(type, "Tree");
        if (!isLocationValid(x, y))
            return "Invalid input!";
        if (cell.getMaterial().equals("water") || cell.getMaterial().equals("sea"))
            return "You can't drop a tree in this location!";
        for (NaturalBlock trees : tree) {
            if (trees.getName().equals(type)) {
                check = true;
            }
        }
        if (!check) return "This type of tree doesn't exist";
        cell.addNaturalBlocks(naturalBlock);
        return type + " added successfully";
    }

    public String dropBuilding(int x, int y, Cell cell, String type) {
        String category = FileController.getBuildingCategoryByType(type);
        Building savedBuilding = getBuilding(type, category);
        Building building = new Building(savedBuilding);
        Building building1 = new Building(type, building.getCategory(), building.getBuildingNeededProducts(), building.getWorkerCounter(), building.getHitPoint());
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
        return buildBuilding(building1, cell, type);
    }

    private String buildBuilding(Building building, Cell cell, String type) {
        for (Product neededProduct : building.getBuildingNeededProducts()) {
            Kingdom kingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
            for (Product product : getKingdomByKing(currentGame.turn.getCurrentKing()).getKingProducts()) {
                if (neededProduct.getName().equals(product.getName())) {
                    if (neededProduct.getCount() <= product.getCount()) {
                        product.setCount(product.getCount() - neededProduct.getCount());
                        cell.setBuilding(building);
                        building.setLocation(cell);
                        building.setKing(currentGame.turn.getCurrentKing());
                        currentGame.getKingdomByKing(currentGame.turn.getCurrentKing().getUsername()).getKingBuildings().add(building);
                        return type + " added successfully";
                    } else return "You don't have enough " + neededProduct.getName() + " to drop " + type;
                }
            }
            return "You don't have any " + neededProduct + "!";
        }
        cell.setBuilding(building);
        building.setLocation(cell);
        building.setKing(currentGame.turn.getCurrentKing());
        currentGame.getKingdomByKing(currentGame.turn.getCurrentKing().getUsername()).getKingBuildings().add(building);
        return type + " added successfully";
    }

    private Building getBuilding(String type, String category) {
        Building savedBuilding = null;
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
        ArrayList<Person> unusedUnits = Objects.requireNonNull(getKingdomByKing(currentGame.turn.getCurrentKing()).getKingUnusedUnits());
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

    public String
    selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = currentGame.getMap().getCellByLocation(x - 1, y - 1);
        if (!isLocationValid(x - 1, y - 1))
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
        result.append("Popularity Factors: \n");
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            result.append(popularityFactor.getName()).append(": ").append(popularityFactor.getPopularityAmount()).append("\n");
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
                if (!(rate >= -3 && rate <= 8)) return "Invalid rate";
                if (rate <= 0) popularityFactor.setPopularityAmount(rate * (-2) + 1);
                else if (rate <= 4) popularityFactor.setPopularityAmount(rate * (-2));
                else popularityFactor.setPopularityAmount(rate * (-4) + 8);
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
                if (!(rate >= -5 && rate <= 5)) return "Invalid rate";
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
        int x = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "y", 2)));
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
        int x = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(x-1, y-1)) return "You have entered invalid location!";
        if (selectedUnit == null) return "You haven't selected a unit!";
        if (selectedUnit.equals(patrollingUnit)) isPatrollingStopped = true;
        List<Cell> pathCells = PathFinder.findPath(selectedUnit.getLocation(), currentGame.getMap().getCells()[x-1][y-1], currentGame.getMap());
        if (pathCells.size() == 1) return "The path is blocked!";
        if (pathCells.size() > ((MilitaryPerson) selectedUnit).getMovingRange())
            return "This move is out of the range of the unit!";
        for (Cell cell : pathCells) {
            removeAndAddInMoving((MilitaryPerson) selectedUnit, cell.getX(), cell.getY());
        }
        return "Unit has been moved successfully!";
    }

    public boolean isLocationValid(int x, int y) {
        return x >= 0 && y >= 0 && x <= currentGame.getMap().getLength() && y <= currentGame.getMap().getWidth();
    }

    public String createUnit(Matcher matcher) {
        String type = MainController.getOptionsFromMatcher(matcher, "t", 2);
        int count = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "c", 2)));
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
        shownMapX = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "x", 2)));
        shownMapY = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(shownMapX - 1, shownMapY - 1)) {
            shownMapX = 0;
            shownMapY = 0;
            return "You have entered invalid location!";
        }
        Map smallMap = makeSmallMap(shownMapX, shownMapY);
        assert smallMap != null;
        return MapController.showMap(smallMap);
    }

    private Map makeSmallMap(int x, int y) {
        Map smallMap = new Map(3, 3);
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (!isLocationValid(shownMapX + i - 1, shownMapY + j - 1)) return null;
                smallMap.getCells()[i + 1][j + 1] = currentGame.getMap().getCells()[shownMapX + i - 1][shownMapY + j - 1];
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
        return MapController.showMap(Objects.requireNonNull(makeSmallMap(shownMapX, shownMapY)));
    }

    public String pourOil(Matcher matcher) {
        String direction = matcher.group();
        int dx = getDXByDirection(direction), dy = getDYByDirection(direction);
        int x = getLocationXOrY(currentGame.getMap(), selectedUnit.getLocation(), 'x');
        int y = getLocationXOrY(currentGame.getMap(), selectedUnit.getLocation(), 'y');
        if (!isLocationValid(x + dx - 1, y + dy - 1)) return "You have entered invalid direction!";
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
        int x = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(MainController.getOptionsFromMatcher(matcher, "y", 2)));
        if (!isLocationValid(x - 1, y - 1)) return "You have entered invalid location!";
        for (Person kingPerson : currentGame.getKingdomByKing(currentGame.turn.getCurrentKing().getUsername()).getKingPeople()) {
            if (kingPerson instanceof WorkerPerson && ((WorkerPerson) kingPerson).getWorkerPlace() == null) {
                ((WorkerPerson) kingPerson).setWorkerPlace(new Building("tunnel", "", null, 0, 0));
                MilitaryPerson originalSelected = (MilitaryPerson) selectedUnit;
                selectedUnit = kingPerson;
                String toGetMatcher = "move unit to -x " + x + " -y " + y;
                if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("Unit has been moved successfully!")) {
                    selectedUnit = originalSelected;
                    return "Tunnel is dug successfully!";
                }
            }
        }
        currentGame.getMap().getCells()[x - 1][y - 1].setHasTunnel(true);
        return "Tunnel has been dug successfully!";
    }

    public String setMode(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!isLocationValid(x - 1, y - 1)) return "You have entered invalid location!";
        for (Person person : currentGame.getMap().getCells()[x - 1][y - 1].getPeople()) {
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
                if (mapCells[i][j].getBuilding().getType().equals("house") && mapCells[i][j].getBuilding()
                        .getKing().getUsername().equals(getCurrentUser().getUsername())) {
                    String toGetMatcher = "move unit to -x " + i + " -y " + j;
                    if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("Unit has been moved successfully!"))
                        return "Unit is disbanded successfully!";
                }
            }
        }
        return "You can't disband this unit!";
    }

    public String attackEnemy(Matcher matcher) {
        int enemyX = Integer.parseInt(matcher.group("x"));
        int enemyY = Integer.parseInt(matcher.group("y"));
        for (Person person : currentGame.getMap().getCells()[enemyX][enemyY].getPeople()) {
            if (!person.getKing().equals(getCurrentUser())) {
                String toGetMatcher = "move unit to -x " + enemyX + " -y " + enemyY;
                if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("Unit has been moved successfully!")) {
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
                if (selectedUnit.getType().equals("Archer") || selectedUnit.getType().equals("Crossbowmen")
                        || selectedUnit.getType().equals("Archer Bow")) {
                    if (((MilitaryPerson) selectedUnit).getShootingRange() >= (Math.sqrt((double) enemyY * enemyY + enemyX * enemyX))) {
                        currentGame.getMap().getCells()[enemyX][enemyY].addToKingdomsWithArrows(currentGame
                                .getKingdomByKing(currentGame.turn.getCurrentKing().getUsername()));
                        return "You made the aerial attack successfully!";
                    } else return "The enemy you want to attack is out of range of the selected unit!";
                } else return "Selected unit is not able to shoot!";
            }
        }
        return "There is no enemy in this location!";
    }

    private Kingdom getKingdomByKing(User king) {
        for (Kingdom kingdom : currentGame.getKingdoms()) {
            if (kingdom.getKing().getUsername().equals(king.getUsername())) return kingdom;
        }
        return null;
    }

    public String buildEquipment(Matcher matcher) {
        String equipmentName = matcher.group("equipmentName");
        Product product = FileController.getProductByName(equipmentName);
        if (!selectedUnit.getType().equals("engineer")) return "not engineer";
        for (Product neededMaterials : product.getUsedMaterials()) {
            boolean weHaveNeededMaterials = false;
            for (Product kingProduct : Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getKingProducts()) {
                if (neededMaterials.getCount() <= kingProduct.getCount()) {
                    weHaveNeededMaterials = true;
                    break;
                }
            }
            if (!weHaveNeededMaterials) return "don't have materials";
        }
        createEquipmentWithGivenEquipment(product);
        return "success";
    }

    private void createEquipmentWithGivenEquipment(Product givenProduct) {
        AttackEquipment attackEquipment = new AttackEquipment(givenProduct.getName(),
                givenProduct.getUsedMaterials(), getCurrentUser(), (MilitaryPerson) selectedUnit);
        Objects.requireNonNull(getKingdomByKing(currentGame.turn.getCurrentKing())).addAttackEquipment(attackEquipment);
    }

    public String setKingdomColors(String colorsStr) {
        String[] colors = colorsStr.split("-");
        if (colors.length < currentGame.getKingdoms().size()) return "few colors";
        if (colors.length > currentGame.getKingdoms().size()) {
            System.out.println("" + colorsStr + currentGame.getKingdoms().size());
            return "too many colors";
        }
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
        if (!isLocationValid(x1 - 1, y1 - 1) || !isLocationValid(x2 - 1, y2 - 1))
            return "You have entered invalid location!";
        patrollingUnit = (MilitaryPerson) selectedUnit;
        if (patrollingUnit == null) return "You should first select a unit to patrol!";
        if ((Math.abs(x1 - x2) + Math.abs(y1 - y2)) > ((MilitaryPerson) selectedUnit).getMovingRange())
            return "The distance is out of the unit's moving range!";
        while (!isPatrollingStopped) {
            removeAndAddInMoving(patrollingUnit, x2 - 1, y2 - 1);
            removeAndAddInMoving(patrollingUnit, x1 - 1, y1 - 1);
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
        String category = FileController.getBuildingCategoryByType(selectedBuilding.getType());
        Building savedBuilding = getBuilding(selectedBuilding.getType(), category);
        Kingdom kingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        if (selectedBuilding == null)
            return "You haven't selected a building yet";
        if (selectedBuilding.getHitPoint() < savedBuilding.getHitPoint()) {
            String result = checkResourses(kingdom, selectedBuilding);
            if (result != null)
                return result;
        } else if (selectedBuilding.getHitPoint() == savedBuilding.getHitPoint()) {
            return selectedBuilding.getType() + " doesn't need to be repaired";
        }
        selectedBuilding.setHitPoint(savedBuilding.getHitPoint());
        selectedBuilding = null;
        return "You have repaired " + selectedBuilding.getType();
    }

    private String checkResourses(Kingdom kingdom, Building selectedBuilding) {
        for (Product product : kingdom.getKingProducts()) {
            if (product.getName().equals("stone")) {
                for (Product buildingCost : selectedBuilding.getBuildingNeededProducts())
                    if (buildingCost.getName().equals("stone"))
                        if (product.getCount() != buildingCost.getCount())
                            return "You don't have enough stone!";
            } else {
                Cell selectedBuildingLocation = selectedBuilding.getLocation();
                int x = selectedBuildingLocation.getX();
                int y = selectedBuildingLocation.getY();
                for (int i = x - 1; i < x + 1; i++)
                    for (int j = y - 1; j < y + 1; j++)
                        for (Person person : currentGame.getMap().getCells()[i][j].getPeople())
                            if (!person.getKing().equals(currentGame.turn.getCurrentKing()))
                                return "You can't repair a building while being under attack";
            }
        }
        return null;
    }

    private void fight() {
        //TODO: this method can be more effective
        Cell[][] cells = currentGame.getMap().getCells().clone();
        for (int i = 0; i < currentGame.getMap().getLength(); i++) {
            for (int j = 0; j < currentGame.getMap().getWidth(); j++) {
                for (Person person1 : cells[i][j].getPeople()) {
                    for (Person person2 : cells[i][j].getPeople()) {
                        if (person1 instanceof MilitaryPerson && person2 instanceof MilitaryPerson
                                && !person1.getKing().equals(person2.getKing())) {
                            MilitaryPerson unit1 = (MilitaryPerson) person1, unit2 = (MilitaryPerson) person2;
                            if (unit1.getFirePower() > unit2.getDefendPower()) {
                                currentGame.getMap().getCells()[i][j].removePerson(unit2);
                                Objects.requireNonNull(getKingdomByKing(unit2.getKing())).removePerson(unit2);
                            }
                            if (unit2.getFirePower() > unit1.getDefendPower()) {
                                currentGame.getMap().getCells()[i][j].removePerson(unit1);
                                Objects.requireNonNull(getKingdomByKing(unit1.getKing())).removePerson(unit1);
                            }
                        }
                    }
                }
            }
        }

    }

    private void arrowsAct() {
        Cell[][] cells = currentGame.getMap().getCells();
        for (int i = 0; i < currentGame.getMap().getLength(); i++) {
            for (int j = 0; j < currentGame.getMap().getWidth(); j++) {
                ArrayList<Kingdom> kingdomsWithArrowsCopy = cells[i][j].getKingdomsWithArrowsHere();
                for (Kingdom kingdom1 : kingdomsWithArrowsCopy) {
                    for (Person person : cells[i][j].getPeople()) {
                        if (!kingdom1.getKing().equals(person.getKing())) {
                            Objects.requireNonNull(getKingdomByKing(person.getKing())).removePerson(person);
                            cells[i][j].removePerson(person);
                        }
                    }
                }
            }
        }
    }

    private void hittingBuildings() {
        ArrayList<Kingdom> allGameUsers = new ArrayList<>(currentGame.getKingdoms());
        for (Kingdom kingdom : allGameUsers) {
            for (Building building : kingdom.getKingBuildings()) {
                int x = building.getLocation().getX();
                int y = building.getLocation().getY();
                for (int i = x - 8; i <= x + 8; i++) {
                    for (int j = y - 8; j <= y + 8; j++) {
                        for (Person person : currentGame.getMap().getCells()[i][j].getPeople()) {
                            if (person instanceof MilitaryPerson && !person.getKing().equals(kingdom.getKing())) {
                                building.setHitPoint(building.getHitPoint() - ((MilitaryPerson) person).getFirePower());
                            }
                        }
                    }
                }
            }
        }
    }

    private void divideFood() {
        boolean check1 = false, check2 = false;
        int peopleCounter = 0, rate, foodCounter = getNumberOfFoods();
        int population = getKingdomByKing(currentGame.turn.getCurrentKing()).getKingPeople().size();
        for (PopularityFactor popularityFactor : getKingdomByKing(currentGame.turn.getCurrentKing()).getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("food")) {
                rate = popularityFactor.getRate();
                double foodGivingRate = ((0.5 * rate) + 1);
                if ((foodCounter / foodGivingRate >= population))
                    check1 = true;
                if ((foodCounter / foodGivingRate) - 4 >= population)
                    check2 = true;
                for (Product product : getKingdomByKing(currentGame.turn.getCurrentKing()).getKingProducts()) {
                    if (product.getName().equals("apple") || product.getName().equals("meat") ||
                            product.getName().equals("bread") || product.getName().equals("cheese")) {
                        if (check2 && !check1)
                            for (int i = 0; i < product.getCount() - 1; i++) {
                                peopleCounter++;
                                product.setCount(product.getCount() - foodGivingRate);
                            }
                        else if (check1 && !check2)
                            for (int i = 0; i < product.getCount(); i++) {
                                peopleCounter++;
                                product.setCount(product.getCount() - foodGivingRate);
                            }
                        if (peopleCounter == population)
                            break;
                    }
                }
            }
        }
    }

    private int getNumberOfFoods() {
        int foodCount = 0;
        for (Product product : getKingdomByKing(currentGame.turn.getCurrentKing()).getKingProducts()) {
            if (product.getName().equals("apple") || product.getName().equals("meat") ||
                    product.getName().equals("bread") || product.getName().equals("cheese")) {
                foodCount += product.getCount();
            }
        }
        return foodCount;
    }

    private void changePopulation() {
        /*Kingdom kingdom = getKingdomByKing(currentGame.turn.getCurrentKing());
        String popularity = showPopularity();
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(popularity);
        int popularityAmount = 0;
        if (matcher.find())
            popularityAmount = Integer.parseInt(matcher.group());
        if (popularityAmount <= 0)
            kingdom.setJoblessCounter(1);
        else if (popularityAmount <= 10)
            kingdom.setJoblessCounter(3);
        else if (popularityAmount <= 20)
            kingdom.setJoblessCounter(6);
        else
            kingdom.setJoblessCounter(8);*/
    }

    private void getTax() {
        int peopleCounter = 0;
        int population = getKingdomByKing(currentGame.turn.getCurrentKing()).getKingPeople().size();
        int rate;
        double currentInventory = getKingdomByKing(currentGame.turn.getCurrentKing()).getInventory();
        for (PopularityFactor popularityFactor : getKingdomByKing(currentGame.turn.getCurrentKing()).getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("tax")) {
                if (currentInventory == 0)
                    popularityFactor.setRate(0);
                rate = popularityFactor.getRate();
                double TaxGivingRate = generateTaxGivingRate(rate);
                for (int i = 0; i < population; i++) {
                    if (peopleCounter == population)
                        break;
                    peopleCounter++;
                    getKingdomByKing(currentGame.turn.getCurrentKing()).setInventory(currentInventory + TaxGivingRate);
                }

            }
        }

    }

    private double generateTaxGivingRate(int rate) {
        double result = 0;
        if (rate >= 1 && rate <= 8)
            result = (0.2 * rate) + 0.4;
        else if (rate >= -1 && rate < 1)
            result = (0.6 * rate);
        else if (rate >= -3 && rate < -1)
            result = (0.2 * rate) - 0.4;
        return result;
    }

    public void nextTurn() {
        ArrayList<Kingdom> allGameUsers = new ArrayList<>(currentGame.getKingdoms());
        currentGame.turn.setCurrentKing(allGameUsers.get(Turn.getTurnCounter() % allGameUsers.size()).getKing());
        Turn.setTurnCounter(Turn.getTurnCounter() + 1);
        selectedBuilding = null;
        selectedUnit = null;
        patrollingUnit = null;
        isPatrollingStopped = false;
        fight();
        arrowsAct();
        hittingBuildings();
        divideFood();
        getTax();
        changePopulation();
        //TODO: mode of units
    }

    public String fetchOil() {
        if (selectedUnit == null || !(selectedUnit instanceof MilitaryPerson) || !selectedUnit.getType().equals("Engineer"))
            return "You should first select an engineer!";
        Cell[][] cells = currentGame.getMap().getCells();
        for (int i = 0; i < currentGame.getMap().getLength(); i++) {
            for (int j = 0; j < currentGame.getMap().getWidth(); j++) {
                Building building = cells[i][j].getBuilding();
                if (building.getType().equals("oil smelter") && building.getKing().getUsername().equals(currentGame.turn.getCurrentKing().getUsername())) {
                    String toGetMatcher = "move unit to -x " + i + " -y " + j;
                    if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("Unit has been moved successfully!")) {
                        ((MilitaryPerson) selectedUnit).setHasOil(true);
                        toGetMatcher = "move unit to -x " + selectedUnit.getLocation().getX() + " -y " + selectedUnit.getLocation().getY();
                        moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT));
                        return "Oil is fetched successfully!";
                    } else return "Engineer cannot go to the oil smelter!";
                }
            }
        }
        return "You don't have an oil smelter!";
    }

    public String burnOil() {
        if (selectedUnit == null || !(selectedUnit instanceof MilitaryPerson) || !selectedUnit.getType().equals("Slaves"))
            return "You should first select a slave!";
        Cell[][] cells = currentGame.getMap().getCells();
        boolean thereIsAtLeastOne = false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x = selectedUnit.getLocation().getX() + i;
                int y = selectedUnit.getLocation().getY() + j;
                if (isLocationValid(x, y) && cells[x][y].hasOil()) {
                    thereIsAtLeastOne = true;
                    cells[x][y].destroyBuildingCompletely();
                    cells[x][y].killPeopleCompletely();
                    cells[x][y].setHasOil(false);
                }
            }
        }
        if (!thereIsAtLeastOne) return "There isn't any cell around the selected slave that has oil!";
        return "The slave burnt the oil he could successfully!";
    }

    public String produceSource(Matcher matcher) {
        String type = matcher.group(1);
        int count = Integer.parseInt(matcher.group(2));
        String neededBuildingType = getNeededBuilding(type);
        String storageBuildingType = getStorageBuilding(type);
        if (neededBuildingType == null) return "You have entered invalid type!";
        if (!(type.equals("hop") || type.equals("iron") || type.equals("stone") || type.equals("wood") || type.equals("flour") || type.equals("wheat"))) {
            return "You have entered invalid type for source!";
        }
        Kingdom currentKingdom = currentGame.getKingdomByKing(currentGame.turn.getCurrentKing().getUsername());
        boolean thereIsFreeWorker = false;
        for (Person kingPerson : currentKingdom.getKingPeople()) {
            if (kingPerson instanceof WorkerPerson && ((WorkerPerson) kingPerson).getWorkerPlace() == null) {
                thereIsFreeWorker = true;
                for (Building kingBuilding : currentKingdom.getKingBuildings()) {
                    int i = kingBuilding.getLocation().getX();
                    int j = kingBuilding.getLocation().getY();
                    for (Building storageBuilding : currentKingdom.getKingBuildings()) {
                        if (storageBuilding.getType().equals(storageBuildingType)) {
                            String toGetMatcher = "move unit to -x " + i + " -y " + j;
                            Person realSelectedUnit = selectedUnit;
                            selectedUnit = kingPerson;
                            if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("Unit has been moved successfully!")) {
                                ((WorkerPerson) selectedUnit).setWorkerPlace
                                        (new Building("hop", "", null, 0, 0));
                                toGetMatcher = "move unit to -x " + storageBuilding.getLocation().getX()
                                        + " -y" + storageBuilding.getLocation().getY();
                                if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT))
                                        .equals("Unit has been moved successfully!")) {
                                    ((WorkerPerson) selectedUnit).setWorkerPlace(new Building("hop", "", null, 0, 0));
                                    toGetMatcher = "move unit to -x " + storageBuilding.getLocation().getX() + " -y" + storageBuilding.getLocation().getY();
                                    if (moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("Unit has been moved successfully!")) {
                                        ((WorkerPerson) selectedUnit).setWorkerPlace(null);
                                        selectedUnit = realSelectedUnit;
                                        Product product = FileController.getProductByName(type);
                                        assert product != null;
                                        Product product1 = new Product
                                                (type, product.getCost(), product.getPrice(), "source", null);
                                        product1.setCount(count);
                                        currentKingdom.getKingProducts().add(product1);
                                        return type + " is produced successfully!";
                                    }
                                    selectedUnit = realSelectedUnit;
                                    return "The worker cannot go to the " + storageBuildingType;
                                }
                                selectedUnit = realSelectedUnit;
                            }
                        }

                    }
                }
            }
        }
        if (thereIsFreeWorker) return "There is no " + neededBuildingType + " you can access!";
        return "You have no free workers now";
    }

    private String getNeededBuilding(String type) {
        switch (type) {
            case "iron":
            case "wood":
            case "stone":
                return "stockpile";
            case "meat":
                return "granary";
            case "bread":
                return "granary";
            case "cheese":
                return "granary";
            case "ale":
                return "granary";
            case "apple":
                return "granary";
            case "hop":
                return "stockpile";
            case "wheat":
                return "stockpile";
            case "flour":
                return "stockpile";
            case "spear":
                return "armoury";
            case "bow":
                return "armoury";
            case "mace":
                return "armoury";
            case "crossbow":
                return "armoury";
            case "pike":
                return "armoury";
            case "sword":
                return "armoury";
            case "leatherArmor":
                return "armoury";
            case "metalArmor":
                return "armoury";
        }
        return null;
    }


    public String produceEquipment(Matcher matcher) {
        String type = matcher.group();
        String neededBuildingType = getNeededBuilding(type);
        Product product = FileController.getProductByName(type);
        Kingdom currentKingdom = currentGame.getKingdomByKing(currentGame.turn.getCurrentKing().getUsername());
        assert product != null;
        ArrayList<Product> neededThings = product.getUsedMaterials();
        String hasNeededProducts = checkTheNeededProducts(neededThings, currentKingdom);
        if (!hasNeededProducts.equals("ok")) return hasNeededProducts;
        for (Product neededProduct : neededThings) {
            boolean weHaveIt = false;

            for (Product kingProduct : currentKingdom.getKingProducts()) {
                if (neededProduct.getName().equals(kingProduct.getName())) {
                    weHaveIt = true;
                    if (neededProduct.getCount() > kingProduct.getCount()) {
                        return "You don't have enough " + neededProduct.getName();
                    }
                }

            }
            if (!weHaveIt) return "You don't have any " + neededProduct.getName();
        }

        if (product.getCost() > currentKingdom.getInventory()) return "You don't have enough coins";
        for (Building kingBuilding : currentKingdom.getKingBuildings()) {
            if (kingBuilding.getType().equals(neededBuildingType)) {
                for (Product neededProduct : neededThings) {
                    for (Product kingProduct : currentKingdom.getKingProducts()) {
                        if (neededProduct.getName().equals(kingProduct.getName())) {
                            kingProduct.setCount(kingProduct.getCount() - neededProduct.getCount());
                        }
                    }
                }
                currentKingdom.setInventory(currentKingdom.getInventory() - product.getCost());
                return product.getName() + " is produced successfully!";
            }
        }
        return "You don't have a " + neededBuildingType;
    }

    public String produceFood(Matcher matcher) {
        String type = matcher.group(1);
        int count = Integer.parseInt(matcher.group(2));

        Kingdom currentKingdom = currentGame.getKingdomByKing(currentGame.turn.getCurrentKing().getUsername());
        String neededBuildingType = getNeededBuilding(type);
        String storageBuildingType = getStorageBuilding(type);
        for (Building kingBuilding : currentKingdom.getKingBuildings()) {
            if (kingBuilding.getType().equals(neededBuildingType)) {
                for (Building storageBuilding : currentKingdom.getKingBuildings()) {
                    if (storageBuilding.getType().equals(storageBuildingType)) {
                        Product product = FileController.getProductByName(type);
                        assert product != null;
                        Product product1 = new Product(type, product.getCost(), product.getPrice(), product.getCategory(), product.getUsedMaterials());
                        product1.setCount(count);
                        String hasNeededProducts = checkTheNeededProducts(product1.getUsedMaterials(), currentKingdom);
                        if (!hasNeededProducts.equals("ok")) return hasNeededProducts;
                        Person realSelectedUnit = selectedUnit;
                        selectedUnit = kingBuilding.getWorkerPeople().get(0);
                        String toGetMatcher = "move unit to -x " + storageBuilding.getLocation().getX()
                                + " -y " + storageBuilding.getLocation().getY();
                        if (!moveUnit(Commands.getMatcher(toGetMatcher, Commands.MOVE_UNIT)).equals("Unit has been moved successfully!")) {
                            selectedUnit = realSelectedUnit;
                            return "There is no access by " + neededBuildingType + " workers to the " + storageBuildingType;
                        }

                        currentKingdom.getKingProducts().add(product1);
                        selectedUnit = realSelectedUnit;
                        return type + " is produced successfully!";
                    }
                }
                return "You don't have any " + storageBuildingType + "s!";
            }
        }
        return "You don't have any " + neededBuildingType + "s!";
    }

    private String getStorageBuilding(String type) {
        switch (type) {
            case "iron":
            case "wood":
            case "stone":
                return "stockpile";
            case "meat":
                return "granary";
            case "bread":
                return "granary";
            case "cheese":
                return "granary";
            case "ale":
                return "granary";
            case "apple":
                return "granary";
            case "hop":
                return "stockpile";
            case "wheat":
                return "stockpile";
            case "flour":
                return "stockpile";
            case "spear":
                return "armoury";
            case "bow":
                return "armoury";
            case "mace":
                return "armoury";
            case "crossbow":
                return "armoury";
            case "pike":
                return "armoury";
            case "sword":
                return "armoury";
            case "leatherArmor":
                return "armoury";
            case "metalArmor":
                return "armoury";
        }
        return null;
    }
}