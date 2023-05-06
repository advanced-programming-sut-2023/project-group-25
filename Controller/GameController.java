package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.io.File;
import java.security.NoSuchAlgorithmException;

public class GameController {
    private final RegisterLoginController registerLoginController = new RegisterLoginController();
    private final MainController mainController = new MainController();
    private Game currentGame;
    private int numberOfPlayers = 2;
    private Map map;
    private Cell cell;
    private Turn turn;

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
            addGameToFile(game);
            resultMessage = "New game created successfully! Game's ID: " + gameId;
        }
        return resultMessage;
    }

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
            result = dropBuilding(x, y, cell, type);
        } else if (matcher.group("object").equals("unit")) {
            result = dropUnit(x, y, cell, type, matcher.group("count"));
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


    public String dropBuilding(int x, int y, Cell cell, String type) {
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        Building building = new Building(savedBuilding,cell,currentKing);
        //TODO:read saved building info from file by Type of the building
        if (x < 0 || y < 0)
            return "Invalid input!";
        if (cell.getMaterial().equals("water") || cell.getMaterial().equals("sea")||cell.getBuilding()==null)
            return "You can't have a building in this location!";
        //TODO:some building are for specific grounds, CHECK IT
        cell.setBuilding(building);
        return type + " added successfully";
    }

    public String dropUnit(int x, int y, Cell cell, String type, String count) {
        //TODO:
        return null;
    }

    public String selectBuilding(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        cell = map.getCellByLocation(x, y);
        if (x < 0 || y < 0)
            return "Invalid input!";
        if(cell.getBuilding()==null)
            return "There is no building in this location";
        if(!cell.getBuilding().getKing().equals(currentKing))
            return "This building doesn't belong to you";
        //TODO: save the selected building to do sth in it
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
            result.append(popularityFactor.getName()).append("\n");
        }
        return result.toString();
    }

    public String showPopularity() {
        int result = 0;
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKing.getKingPopularityFactors()) {
            result += popularityFactor.getPopularityAmount();
        }
        return "Popularity: " + result;
    }

    public String showFoodList() {
        StringBuilder result = new StringBuilder();
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        ArrayList<Product> products = new ArrayList<>(currentKing.getKingProduct());
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
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKing.getKingPopularityFactors()) {
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
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKing.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("fear"))
                popularityFactor.setRate(Integer.parseInt(rateNumber));
        }
    }

    private void rateFood(String rateNumber) {
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKing.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals("food"))
                popularityFactor.setRate(Integer.parseInt(rateNumber) * 4);
            //TODO:give 0.5x + 1 amount of food to people
        }
    }

    public String showPopularityFactorRate(Matcher matcher) {
        StringBuilder result = new StringBuilder();
        int rate = 0;
        Kingdom currentKing = currentGame.getKingdomByKing(turn.getCurrentKing());
        for (PopularityFactor popularityFactor : currentKing.getKingPopularityFactors()) {
            if (popularityFactor.getName().equals(matcher.group("popularityFactor"))) {
                rate = popularityFactor.getRate();
                break;
            }
        }
        result.append(matcher.group("popularityFactor")).append(": ").append(String.valueOf(rate));
        return result.toString();
    }
}
