package Controller;

import Model.*;
import View.Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;

import static Controller.RegisterLoginController.getCurrentUser;
import static Controller.RegisterLoginController.getOptionsFromMatcher;

public class GameController {
    private final RegisterLoginController registerLoginController = new RegisterLoginController();
    private final MainController mainController = new MainController();
    private MilitaryPerson selectedUnit;
    //TODO: make selected unit null after changing the player.
    private Game currentGame;
    private int numberOfPlayers;
    private Cell cell;
    private Turn turn;
    private int shownMapX;
    private int shownMapY;

    private Building selectedBuilding;

    public void initializeGamesFile() {
        File Games = new File("Games.txt");
        ArrayList<String> content = mainController.readFileContent("Games.txt");
        if (content.size() < 4) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--ALL PLAYERS(separated with ,)--");
            initial.add("--MAP TEMPLATE NUMBER--");
            initial.add("_____________________________________________________");
            mainController.writeToFileContent("Games.txt", initial, false);
        }
    }

    public void addGameToFile(Game game) {
        initializeGamesFile();
        ArrayList<String> content = new ArrayList<>();
        content.add(String.valueOf(game.getGameId()));
        String usernames = "";
        for (int i = 0; i < game.getKingdoms().size(); i++) {
            usernames += (game.getKingdoms().get(i).getKing().getUsername() + "-");
        }
        content.add(usernames);
        content.add(String.valueOf(game.getMapTemplateNumber()));
        content.add("_____________________________________________________");
        mainController.writeToFileContent("Games.txt", content, true);
    }

    public void initializeKingdomsFile() {
        File Kingdoms = new File("Kingdoms.txt");
        ArrayList<String> content = mainController.readFileContent("Kingdoms.txt");
        if (content.size() < 9) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--KING'S USERNAME--");
            initial.add("--INVENTORY--");
            initial.add("--JOBLESS COUNTER--");
            initial.add("--KING'S BUILDINGS WITH LOCATION {testBuilding|x:X-y:Y,}--");
            initial.add("--KING'S PRODUCTS(SEPARATED WITH ,)--");
            initial.add("--KING'S PEOPLE WITH LOCATION {testPerson|x:X-y:Y,}--");
            initial.add("--KING'S POPULARITY FACTORS {factor1:amount, }--");
            initial.add("--KING'S ATTACK EQUIPMENTS(SEPARATED WITH ,)--");
            initial.add("_____________________________________________________");
            mainController.writeToFileContent("Kingdoms.txt", initial, false);
        }
    }

    public void addKingdomToFile(Kingdom kingdom) {
        initializeKingdomsFile();
        ArrayList<String> content = new ArrayList<>();
        content.add(String.valueOf(kingdom.getGameId()));
        content.add(kingdom.getKing().getUsername());
        content.add(String.valueOf(kingdom.getInventory()));
        content.add(String.valueOf(kingdom.getJoblessCounter()));
        content.add("null");
        content.add("null");
        content.add("null");
        content.add("null");
        content.add("null");
        content.add("_____________________________________________________");
        mainController.writeToFileContent("Kingdoms.txt", content, true);
    }

    /*public User getGameByID(String id) {
        Game wantedGame;
        ArrayList<String> content =  mainController.readFileContent("Games.txt");
        for (int i = 0; i < (content.size() / 4); i++) {
            if (content.get(4 * i).equals(id)) {
                String[] allPlayersUsername = content.get((10 * i) + 1).split(",");

                ArrayList<Kingdom>
                wantedGame = new Game(content.get(10 * i), content.get((10 * i) + 1), content.get((10 * i) + 1), content.get((10 * i) + 2)
                        , content.get((10 * i) + 3), content.get((10 * i) + 4));
                wantedUser.setSecurityQuestion(content.get((10 * i) + 6));
                wantedUser.setSecurityAnswer(content.get((10 * i) + 7));
                return wantedUser;
            }
        }
        return null;
    }*/

    public String newGame(String line) {
        String resultMessage = "";
        String[] usernames = line.split("-");
        for (int i = 0; i < usernames.length; i++) {
            if (registerLoginController.getUserByUsername(usernames[i]) == null)
                resultMessage = ("New game creation failed! Username [" + usernames[i] + "] does not exist!");
        }
        if (resultMessage.equals("")) {
            File Games = new File("Games.txt");
            ArrayList<String> content = mainController.readFileContent("Games.txt");
            int gameId = content.size() / 4 + 1;
            ArrayList<Kingdom> kingdoms = new ArrayList<>();
            for (int i = 0; i < usernames.length; i++) {
                Kingdom newKingdom = new Kingdom(registerLoginController.getUserByUsername(usernames[i]), gameId);
                addKingdomToFile(newKingdom);
                kingdoms.add(newKingdom);
            }
            Game game = new Game(gameId, kingdoms);
            currentGame = game;
            addGameToFile(game);
            resultMessage = "New game created successfully! Game's ID: " + gameId;
        }
        return resultMessage;
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
            cell.setMaterial(matcher.group("type"));
            return "Cells texture changed successfully";
        }
        return "You can't change the texture of a cell with a building on it!";
    }

    public String clearCell(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        cell = currentGame.getMap().getCellByLocation(x, y);
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
        NaturalBlock naturalBlock = new NaturalBlock(type, "Tree");
        if (!isLocationValid(x, y))
            return "Invalid input!";
        if (cell.getMaterial().equals("water") || cell.getMaterial().equals("sea"))
            return "You can't drop a tree in this location!";
        cell.addNaturalBlocks(naturalBlock);
        return type + " added successfully";
    }


    public String dropBuilding(int x, int y, Cell cell, String type) {
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        //Building building = new Building(savedBuilding, cell, currentKingdom);
        //TODO:read saved building info from file by Type of the building
        if (isLocationValid(x, y))
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
        //cell.setBuilding(building);
        return type + " added successfully";
    }

    public String dropUnit(int x, int y, Cell cell, String type, String count) {
        //TODO:
        return null;
    }

    public String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        cell = currentGame.getMap().getCellByLocation(x, y);
        if (!isLocationValid(x, y))
            return "Invalid input!";
        if (cell.getBuilding() == null)
            return "There is no building in this location";
        if (!cell.getBuilding().getKing().equals(currentKingdom.getKing()))
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
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        result.append("PopularityFactors: \n");
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            result.append(popularityFactor.getName()).append("\n");
        }
        return result.toString();
    }

    public String showPopularity() {
        int result = 0;
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            result += popularityFactor.getPopularityAmount();
        }
        return "Popularity: " + result;
    }

    public String showFoodList() {
        StringBuilder result = new StringBuilder();
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        ArrayList<Product> products = new ArrayList<>(currentKingdom.getKingProducts());
        result.append("Apple: ").append(showEachFood(products, "apple"));
        result.append("Apple: ").append(showEachFood(products, "meat"));
        result.append("Apple: ").append(showEachFood(products, "bread"));
        result.append("Apple: ").append(showEachFood(products, "cheese"));
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

    public void ratePopularityFactor(Matcher matcher) {
        if (matcher.group("popularityFactor").equals("food"))
            rateFood(matcher.group("rateNumber"));
        if (matcher.group("popularityFactor").equals("fear"))
            rateFear(matcher.group("rateNumber"));
        if (matcher.group("popularityFactor").equals("tax"))
            rateTax(matcher.group("rateNumber"));
    }

    private void rateTax(String rateNumber) {
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("tax")) {
                if (Integer.parseInt(rateNumber) <= 0)
                    popularityFactor.setRate(Integer.parseInt(rateNumber) * (-2) + 1);
                else if (Integer.parseInt(rateNumber) <= 4)
                    popularityFactor.setRate(Integer.parseInt(rateNumber) * (-2));
                else if (Integer.parseInt(rateNumber) <= 8)
                    popularityFactor.setRate(Integer.parseInt(rateNumber) * (-4) + 8);
            }
        }
    }

    private void rateFear(String rateNumber) {
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("fear"))
                popularityFactor.setRate(Integer.parseInt(rateNumber));
        }
    }

    private void rateFood(String rateNumber) {
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKingdom.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("food"))
                popularityFactor.setRate(Integer.parseInt(rateNumber) * 4);
            //TODO:give 0.5x + 1 amount of food to people
        }
    }

    public String showPopularityFactorRate(Matcher matcher) {
        StringBuilder result = new StringBuilder();
        int rate = 0;
        Kingdom currentKingdom = getKingdomByKing(turn.getCurrentKing());
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
        int x = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "x", 2)));
        int y = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "y", 2)));
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
//        if (givenUnit.getTrainingCost() * count > Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getInventory())
//            return "not enough coins";
//        boolean haveProducts = haveNeededProductsForUnit(givenUnit, count);
//        if (!haveProducts) return "not enough products";
//        if (count > Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getKingFreePeopleNumber())
//            return "not enough people";
//        //TODO: checking if we're in the appropriate building
//        createUnitWithGivenUnit(givenUnit);
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
        if (!isLocationValid(shownMapX, shownMapY)) {
            shownMapX = 0;
            shownMapY = 0;
            return "invalid location";
        }
        String type = getOptionsFromMatcher(matcher, "t", 2);
        int count = Integer.parseInt(Objects.requireNonNull(getOptionsFromMatcher(matcher, "c", 2)));

        return null;
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
        if (shownMapX == 0 && shownMapY == 0) return "haven't chosen";
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

    public String buildEquipment(Matcher matcher) {
        String equipmentName = matcher.group("equipmentName");
        AttackEquipment attackEquipment = null;
        if (!selectedUnit.getType().equals("engineer")) return "not engineer";
        for (Product neededMaterials : attackEquipment.getUsedMaterials()) {
            boolean weHaveNeededMaterials = false;
            for (Product kingProduct : Objects.requireNonNull(getKingdomByKing(getCurrentUser())).getKingProducts()) {
                if (neededMaterials.getCount() <= kingProduct.getCount()) weHaveNeededMaterials = true;
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

    public String repair() {
        Building savedBuilding; //read from file
        Kingdom kingdom = getKingdomByKing(turn.getCurrentKing());
        if(selectedBuilding==null)
            return "You haven't selected a building yet";
        if (selectedBuilding.getHitPoint() < savedBuilding.getHitPoint()) {
            for (Product product : kingdom.getKingProducts()) {
                if (product.getName().equals("stone")) {
                    for (Product buildingCost : selectedBuilding.getBuildingCosts())
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
                                if (!person.getKing().equals(turn.getCurrentKing()))
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
        selectedBuilding=null;
        return "You have repaired " + selectedBuilding.getType();
    }
}