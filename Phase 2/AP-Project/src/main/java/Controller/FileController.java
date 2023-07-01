package Controller;

import Model.*;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileController {
    
    public static ArrayList<String> readFileContent(String path) {
        ArrayList<String> content = new ArrayList<>();
        File Users = new File(path);
        try {
            FileReader fileReader = new FileReader(Users);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                content.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return content;
    }
    
    
    public static void writeToFileContent(String path, ArrayList<String> content, boolean isAppend) {
        File Users = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(Users, isAppend);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter writer = new PrintWriter(bufferedWriter);
            for (int i = 0; i < content.size(); i++) {
                writer.println(content.get(i));
            }
            writer.close();
        } catch (Exception e) {
        }
    }
    
    public static void initializeUsersFile() {
        File Users = new File("src/main/java/Database/Users.txt");
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        if (content.size() < 10) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--USERNAME--");
            initial.add("--PASSWORD--");
            initial.add("--NICKNAME--");
            initial.add("--EMAIL--");
            initial.add("--SLOGAN--");
            initial.add("--HIGHSCORE--");
            initial.add("--SECURITY QUESTION--");
            initial.add("--SECURITY ANSWER--");
            initial.add("--STAY LOGGED IN? (boolean)--");
            initial.add("--AVATAR PATH--");
            initial.add("_____________________________________________________");
            writeToFileContent("src/main/java/Database/Users.txt", initial, false);
        }
    }
    
    public static User getUserByUsername(String username) {
        User wantedUser;
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                wantedUser = new User(content.get(11 * i), content.get((11 * i) + 1), content.get((11 * i) + 1), content.get((11 * i) + 2)
                        , content.get((11 * i) + 3), content.get((11 * i) + 4));
                wantedUser.setAvatarPath(content.get((11 * i) + 9));
                wantedUser.setSecurityQuestion(content.get((11 * i) + 6));
                wantedUser.setSecurityAnswer(content.get((11 * i) + 7));
                return wantedUser;
            }
        }
        return null;
    }
    
    public static boolean isUserNameUnique(String username) {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isEmailUnique(String email) {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get((11 * i) + 3).equals(email)) {
                return false;
            }
        }
        return true;
    }
    
    public static User getFirstStayLoggedIn() {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get((11 * i) + 8).equals("true")) {
                return getUserByUsername(content.get(11 * i));
            }
        }
        return null;
    }
    
    public static void addUserToFile(User user) throws NoSuchAlgorithmException {
        ArrayList<String> content = new ArrayList<>();
        initializeUsersFile();
        content.add(user.getUsername());
        content.add(RegisterLoginController.passwordToSHA(user.getPassword()));
        content.add(user.getNickname());
        content.add(user.getEmail());
        content.add(user.getSlogan());
        content.add(String.valueOf(user.getHighScore()));
        content.add(user.getSecurityQuestion());
        content.add(user.getSecurityAnswer());
        content.add("false");
        content.add(user.getAvatarPath());
        content.add("_____________________________________________________");
        writeToFileContent("src/main/java/Database/Users.txt", content, true);
    }
    
    public static boolean isPasswordCorrect(String username, String password) throws NoSuchAlgorithmException {
        User user = getUserByUsername(username);
        password = RegisterLoginController.passwordToSHA(password);
        if (user.getPassword().equals(password))
            return true;
        return false;
    }
    
    public static boolean isAnswerCorrect(String username, String answer) {
        User user = getUserByUsername(username);
        if (user.getSecurityAnswer().equals(answer))
            return true;
        return false;
    }
    
    public static void addStayLoggedInForUser(String username, boolean isLoggedIn) {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                content.remove((11 * i) + 8);
                content.add(((11 * i) + 8), String.valueOf(isLoggedIn));
            }
        }
        writeToFileContent("src/main/java/Database/Users.txt", content, false);
    }
    
    public static void changePassword(String username, String password) throws NoSuchAlgorithmException {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                content.remove((11 * i) + 1);
                content.add(((11 * i) + 1), RegisterLoginController.passwordToSHA(password));
            }
        }
        writeToFileContent("src/main/java/Database/Users.txt", content, false);
    }
    
    public static void changeUsername(String username, String newUsername) throws NoSuchAlgorithmException {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                content.remove(11 * i);
                content.add((11 * i), newUsername);
            }
        }
        writeToFileContent("src/main/java/Database/Users.txt", content, false);
    }
    
    public static void changeNickname(String username, String nickname) throws NoSuchAlgorithmException {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                content.remove((11 * i) + 2);
                content.add(((11 * i) + 2), nickname);
            }
        }
        writeToFileContent("src/main/java/Database/Users.txt", content, false);
    }
    
    public static void changeEmail(String username, String email) throws NoSuchAlgorithmException {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                content.remove((11 * i) + 3);
                content.add(((11 * i) + 3), email);
            }
        }
        writeToFileContent("src/main/java/Database/Users.txt", content, false);
    }
    
    public static void changeSlogan(String username, String slogan) throws NoSuchAlgorithmException {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                content.remove((11 * i) + 4);
                content.add(((11 * i) + 4), slogan);
            }
        }
        writeToFileContent("src/main/java/Database/Users.txt", content, false);
    }
    
    public static void changeAvatar(String username, String avatar) throws NoSuchAlgorithmException {
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 0; i < (content.size() / 11); i++) {
            if (content.get(11 * i).equals(username)) {
                content.remove((11 * i) + 9);
                content.add(((11 * i) + 9), avatar);
            }
        }
        writeToFileContent("src/main/java/Database/Users.txt", content, false);
    }
    
    public static void initializeGamesFile() {
        File Games = new File("src/main/java/Database/Games.txt");
        ArrayList<String> content = readFileContent("src/main/java/Database/Games.txt");
        if (content.size() < 4) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--ALL PLAYERS(separated with ,)--");
            initial.add("--MAP TEMPLATE NUMBER--");
            initial.add("_____________________________________________________");
            writeToFileContent("src/main/java/Database/Games.txt", initial, false);
        }
    }
    
    public static void addGameToFile(Game game) {
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
        writeToFileContent("src/main/java/Database/Games.txt", content, true);
    }
    
    public static void initializeKingdomsFile() {
        File Kingdoms = new File("src/main/java/Database/Kingdoms.txt");
        ArrayList<String> content = readFileContent("src/main/java/Database/Kingdoms.txt");
        if (content.size() < 9) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--KING'S USERNAME--");
            initial.add("--INVENTORY--");
            initial.add("--KING'S BUILDINGS WITH LOCATION {testBuilding|x:X-y:Y,}--");
            initial.add("--KING'S PRODUCTS(SEPARATED WITH ,)--");
            initial.add("--KING'S PEOPLE WITH LOCATION {testPerson|x:X-y:Y,}--");
            initial.add("--KING'S POPULARITY FACTORS {factor1:amount, }--");
            initial.add("--KING'S ATTACK EQUIPMENTS(SEPARATED WITH ,)--");
            initial.add("_____________________________________________________");
            writeToFileContent("src/main/java/Database/Kingdoms.txt", initial, false);
        }
    }
    
    public static void addKingdomToFile(Kingdom kingdom) {
        initializeKingdomsFile();
        String tmp = "";
        ArrayList<String> content = new ArrayList<>();
        content.add(String.valueOf(kingdom.getGameId()));
        content.add(kingdom.getKing().getUsername());
        content.add(String.valueOf(kingdom.getInventory()));
        content.add("");
        for (int i = 0; i < kingdom.getKingProducts().size(); i++) {
            tmp += (kingdom.getKingProducts().get(i).getName() + ",");
        }
        content.add(tmp);
        content.add("");
        tmp = "";
        for (int i = 0; i < kingdom.getKingPopularityFactors().size(); i++) {
            tmp += (kingdom.getKingPopularityFactors().get(i).getName() + ":" +
                    kingdom.getKingPopularityFactors().get(i).getPopularityAmount() + ",");
        }
        content.add(tmp);
        content.add("");
        content.add("_____________________________________________________");
        writeToFileContent("src/main/java/Database/Kingdoms.txt", content, true);
    }
    
    public static void updateKingPeopleInFile(Person person, Kingdom kingdom, Game currentGame) {
        ArrayList<String> content = readFileContent("src/main/java/Database/Kingdoms.txt");
        for (int i = 0; i < (content.size() / 9); i++) {
            if (content.get(9 * i + 1).equals(kingdom.getKing().getUsername()) && Integer.parseInt(content.get(9 * i)) == currentGame.getGameId()) {
                String tmp = content.get(9 * i + 5);
                content.remove(9 * i + 5);
                tmp += (person.getType() + "|x:" + person.getLocation().getX() + "-y:" + person.getLocation().getY() + ",");
                content.add(9 * i + 5, tmp);
            }
        }
        writeToFileContent("src/main/java/Database/Kingdoms.txt", content, false);
    }
    
    public static ShopBuildings getShopBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("src/main/java/Database/ShopBuilding.txt");
        for (int i = 0; i < (content.size() / 6); i++) {
            if (content.get(6 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(6 * i + 2).split(":\\d-?").length; j++) {
                    Product product = getProductByName(content.get(6 * i + 2).split(":\\d-?")[j]);
                    if (j < content.get(7 * i + 2).split(":\\d-?").length - 1)
                        product.increaseCount(Integer.parseInt(content.get(6 * i + 2).split("-?[a-zA-Z]+:")[j]));
                    if (product != null)
                        neededProduct.add(product);
                }
                Building tmp = new Building(content.get(6 * i), content.get(6 * i + 1), neededProduct,
                        Integer.parseInt(content.get(6 * i + 3)), Integer.parseInt(content.get(6 * i + 4)));
                ShopBuildings shopBuilding = new ShopBuildings(tmp);
                return shopBuilding;
            }
        }
        return null;
    }
    
    public static TrainingBuildings getTrainingBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("src/main/java/Database/TrainingBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(7 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    Product product = getProductByName(content.get(7 * i + 2).split(":\\d-?")[j]);
                    if (j < content.get(7 * i + 2).split(":\\d-?").length - 1)
                        product.increaseCount(Integer.parseInt(content.get(7 * i + 2).split("-?[a-zA-Z]+:")[j + 1]));
                    if (product != null)
                        neededProduct.add(product);
                }
                Building tmp = new Building(content.get(7 * i), content.get(7 * i + 1), neededProduct,
                        Integer.parseInt(content.get(7 * i + 3)), Integer.parseInt(content.get(7 * i + 4)));
                TrainingBuildings trainingBuilding = new TrainingBuildings(tmp, null, Integer.parseInt(content.get(6 * i + 5)));
                return trainingBuilding;
            }
        }
        return null;
    }
    
    public static ProductionBuildings getProductionBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("src/main/java/Database/ProductionBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(7 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    Product product = getProductByName(content.get(7 * i + 2).split(":\\d-?")[j]);
                    if (j < content.get(7 * i + 2).split(":\\d-?").length - 1)
                        product.increaseCount(Integer.parseInt(content.get(7 * i + 2).split("-?[a-zA-Z]+:")[j + 1]));
                    if (product != null)
                        neededProduct.add(product);
                }
                Building tmp = new Building(content.get(7 * i), content.get(7 * i + 1), neededProduct,
                        Integer.parseInt(content.get(7 * i + 3)), Integer.parseInt(content.get(7 * i + 4)));
                ProductionBuildings productionBuilding = new ProductionBuildings(tmp, Integer.parseInt(content.get(7 * i + 5)));
                return productionBuilding;
            }
        }
        return null;
    }
    
    public static StorageBuildings getStorageBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("src/main/java/Database/StorageBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(7 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    Product product = getProductByName(content.get(7 * i + 2).split(":\\d-?")[j]);
                    if (j < content.get(7 * i + 2).split(":\\d-?").length - 1)
                        product.increaseCount(Integer.parseInt(content.get(7 * i + 2).split("-?[a-zA-Z]+:")[j]));
                    if (product != null)
                        neededProduct.add(product);
                }
                Building tmp = new Building(content.get(7 * i), content.get(7 * i + 1), neededProduct,
                        Integer.parseInt(content.get(7 * i + 3)), Integer.parseInt(content.get(7 * i + 4)));
                StorageBuildings storageBuildings = new StorageBuildings(tmp, Integer.parseInt(content.get(7 * i + 5)));
                return storageBuildings;
            }
        }
        return null;
    }
    
    public static OtherBuildings getOtherBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("src/main/java/Database/OtherBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(7 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    if (content.get(7 * i + 2).split(":\\d+-?")[j].equals("gold")) {
                    
                    }
                    Product product = getProductByName(content.get(7 * i + 2).split(":\\d+-?")[j]);
                    if (j < content.get(7 * i + 2).split(":\\d-?").length - 1)
                        product.increaseCount(Integer.parseInt(content.get(7 * i + 2).split("-?[a-zA-Z]+:")[j + 1]));
                    if (product != null)
                        neededProduct.add(product);
                }
                Building tmp = new Building(content.get(7 * i), content.get(7 * i + 1), neededProduct,
                        Integer.parseInt(content.get(7 * i + 3)), Integer.parseInt(content.get(7 * i + 4)));
                OtherBuildings otherBuilding = new OtherBuildings(tmp, Boolean.valueOf(content.get(7 * i + 5)));
                return otherBuilding;
            }
        }
        return null;
    }
    
    public static FightingBuildings getFightingBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("src/main/java/Database/FightingBuilding.txt");
        for (int i = 0; i < (content.size() / 8); i++) {
            if (content.get(8 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(8 * i + 2).split(":\\d-?").length; j++) {
                    Product product = getProductByName(content.get(8 * i + 2).split(":\\d-?")[j]);
                    if (j < content.get(8 * i + 2).split(":\\d-?").length - 1)
                        product.increaseCount(Integer.parseInt(content.get(8 * i + 2).split("-?[a-zA-Z]+:")[j + 1]));
                    if (product != null)
                        neededProduct.add(product);
                }
                Building tmp = new Building(content.get(8 * i), content.get(8 * i + 1), neededProduct,
                        Integer.parseInt(content.get(8 * i + 3)), Integer.parseInt(content.get(8 * i + 4)));
                FightingBuildings fightingBuilding = new FightingBuildings(tmp, Integer.parseInt(content.get(8 * i + 5)), Integer.parseInt(content.get(8 * i + 6)));
                return fightingBuilding;
            }
        }
        return null;
    }
    
    public static String getBuildingCategoryByType(String type) {
        String[] TrainingBuilding = new String[]{"barracks", "mercenary post", "enginner guild"};
        String[] ProductionBuilding = new String[]{"mill", "iron mine", "ox tether", "quarry", "woodcutter", "armourer",
                "blacksmith", "fletcher", "poleturner", "oil smelter", "stable", "apple orchard", "dairy farmer", "hops farmer",
                "hunter post", "wheat farmer", "bakery", "brewer"};
        String[] StorageBuilding = new String[]{"armoury", "stockpile", "granary"};
        String[] OtherBuilding = new String[]{"small stone gatehouse", "large stone gatehouse", "drawbridge", "inn",
                "hovel", "church", "catheral", "caged war dogs", "siege tent", "short wall", "high wall", "stairs",};
        String[] FightingBuilding = new String[]{"lookout tower", "perimeter tower", "defence turret", "square tower",
                "round tower"};
        String[] ShopBuilding = new String[]{"market"};
        for (String s : TrainingBuilding) {
            if (s.equals(type))
                return "TrainingBuildings";
        }
        for (String s : ProductionBuilding) {
            if (s.equals(type))
                return "ProductionBuildings";
        }
        for (String s : StorageBuilding) {
            if (s.equals(type))
                return "StorageBuildings";
        }
        for (String s : OtherBuilding) {
            if (s.equals(type))
                return "OtherBuildings";
        }
        for (String s : FightingBuilding) {
            if (s.equals(type))
                return "FightingBuildings";
        }
        for (String s : ShopBuilding) {
            if (s.equals(type))
                return "ShopBuildings";
        }
        return null;
    }
    
    public static MilitaryPerson getMilitaryPersonByType(String Type) {
        ArrayList<String> content = readFileContent("src/main/java/Database/MilitaryPerson.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(7 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 5).split(",").length; j++) {
                    Product product = getProductByName(content.get(7 * i + 5).split(",")[j]);
                    if (product != null)
                        neededProduct.add(product);
                }
                return new MilitaryPerson(content.get(7 * i), neededProduct, Integer.parseInt(content.get(7 * i + 1)),
                        Integer.parseInt(content.get(7 * i + 2)), Integer.parseInt(content.get(7 * i + 3)), content.get(7 * i + 4));
            }
        }
        return null;
    }
    
    public static Product getProductByName(String name) {
        ArrayList<String> content = readFileContent("src/main/java/Database/Product.txt");
        for (int i = 0; i < (content.size() / 6); i++) {
            if (content.get(6 * i).equals(name)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                Matcher matcher = Pattern.compile("\\d+").matcher(content.get(6 * i + 4));
                int j = 0;
                while (matcher.find()) {
                    Product product = getProductByName(content.get(6 * i + 4).split(":\\d-?")[j]);
                    if (product != null) {
                        product.increaseCount(Integer.parseInt(matcher.group()));
                        neededProduct.add(product);
                    }
                    j++;
                }
                Product product = new Product(content.get(6 * i), Integer.parseInt(content.get(6 * i + 1)),
                        Integer.parseInt(content.get(6 * i + 2)), content.get(6 * i + 3), neededProduct);
                return product;
            }
        }
        return null;
    }
    
    public static ArrayList<User> getAllUsers(String path) {
        ArrayList<User> allUsers = new ArrayList<>();
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 1; i < (content.size() / 11); i++) {
            allUsers.add(getUserByUsername(content.get(11 * i)));
        }
        return allUsers;
    }
    
    public static void initializeTradeRequestFile() {
        File TradeRequest = new File("src/main/java/Database/Trades.txt");
        ArrayList<String> content = readFileContent("src/main/java/Database/Trades.txt");
        if (content.size() < 7) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--ID--");
            initial.add("--GAME ID--");
            initial.add("--TRADE TYPE--");
            initial.add("--SENDER KING'S USERNAME--");
            initial.add("--RECEIVER KING'S USERNAME--");
            initial.add("--RESOURCE TYPE--");
            initial.add("--RESOURCE AMOUNT--");
            initial.add("--RESOURCE PRICE--");
            initial.add("--RESOURCE MESSAGE--");
            initial.add("_____________________________________________________");
            writeToFileContent("src/main/java/Database/Trades.txt", initial, false);
        }
    }
    
    public static int generateTradeId() {
        File TradeRequest = new File("src/main/java/Database/Trades.txt");
        ArrayList<String> content = readFileContent("src/main/java/Database/Trades.txt");
        int lineNumber = content.size();
        return (lineNumber / 7);
    }
    
    public static void addTradeToFile(Trade trade, Game currentGame) throws NoSuchAlgorithmException {
        initializeTradeRequestFile();
        ArrayList<String> content = new ArrayList<>();
        content.add(String.valueOf(trade.getId()));
        content.add(String.valueOf(currentGame.getGameId()));
        content.add(String.valueOf(trade.getTradeType()));
        content.add(trade.getSenderUsername());
        content.add(trade.getReceiverUsername());
        content.add(String.valueOf(trade.getResourceType()));
        content.add(String.valueOf(trade.getResourceAmount()));
        content.add(String.valueOf(trade.getPrice()));
        content.add(trade.getMessage());
        content.add("_____________________________________________________");
        writeToFileContent("src/main/java/Database/Trades.txt", content, true);
    }
    
    public static ArrayList<Trade> getAllTradesByKing(String receiverUsername, Game currentGame) {
        ArrayList<String> content = readFileContent("src/main/java/Database/Trades.txt");
        ArrayList<Trade> allTrades = new ArrayList<>();
        for (int i = 1; i < (content.size() / 10); i++) {
            if (content.get(10 * i + 4).equals(receiverUsername) && Integer.parseInt(content.get(10 * i + 1)) == currentGame.getGameId()) {
                Trade trade = new Trade(content.get(10 * i + 3), content.get(10 * i + 4), Integer.parseInt(content.get(10 * i)), content.get(10 * i + 2),
                        content.get(10 * i + 5), Integer.parseInt(content.get(10 * i + 6)), Integer.parseInt(content.get(10 * i + 7)),
                        content.get(10 * i + 8));
                allTrades.add(trade);
            }
        }
        return allTrades;
    }
    
    public static Trade getTradeByID(int tradeId, Game currentGame) {
        ArrayList<String> content = readFileContent("src/main/java/Database/Trades.txt");
        for (int i = 1; i < (content.size() / 10); i++) {
            if (Integer.parseInt(content.get(10 * i)) == tradeId && Integer.parseInt(content.get(10 * i + 1)) == currentGame.getGameId()) {
                Trade trade = new Trade(content.get(10 * i + 3), content.get(10 * i + 4), Integer.parseInt(content.get(10 * i)), content.get(10 * i + 2),
                        content.get(10 * i + 5), Integer.parseInt(content.get(10 * i + 6)), Integer.parseInt(content.get(10 * i + 7)),
                        content.get(10 * i + 8));
                return trade;
            }
        }
        return null;
    }
    
    public static ArrayList<Product> getAllProducts(Game currentGame) {
        ArrayList<Product> allProducts = new ArrayList<>();
        ArrayList<String> content = readFileContent("src/main/java/Database/Product.txt");
        for (int i = 1; i < (content.size() / 6); i++) {
            ArrayList<Product> neededProduct = new ArrayList<>();
            Matcher matcher = Pattern.compile("\\d+").matcher(content.get(6 * i + 4));
            int j = 0;
            while (matcher.find()) {
                Product product = getProductByName(content.get(6 * i + 4).split(":\\d-?")[j]);
                if (product != null) {
                    product.increaseCount(Integer.parseInt(matcher.group()));
                    neededProduct.add(product);
                }
                j++;
            }
            Product product = new Product(content.get(6 * i), Integer.parseInt(content.get(6 * i + 1)),
                    Integer.parseInt(content.get(6 * i + 2)), content.get(6 * i + 3), neededProduct);
            Product tmp = currentGame.getKingdomByKing(currentGame.turn.getCurrentKing().getUsername()).getKingProductByName(product.getName());
            if (tmp != null)
                product.setCount(tmp.getCount());
            allProducts.add(product);
        }
        return allProducts;
    }
    
    public static ArrayList<User> getAllUsersWithoutOwner(String path, String owner) {
        ArrayList<User> allUsers = new ArrayList<>();
        ArrayList<String> content = readFileContent("src/main/java/Database/Users.txt");
        for (int i = 1; i < (content.size() / 11); i++) {
            if (!content.get(11 * i).equals(owner)) {
                allUsers.add(getUserByUsername(content.get(11 * i)));
            }
        }
        return allUsers;
    }
}
