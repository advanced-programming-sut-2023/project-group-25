package Controller;

import Model.*;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
        File Users = new File("Users.txt");
        ArrayList<String> content = readFileContent("Users.txt");
        if (content.size() < 9) {
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
            initial.add("_____________________________________________________");
            writeToFileContent("Users.txt", initial, false);
        }
    }

    public static User getUserByUsername(String username) {
        User wantedUser;
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                wantedUser = new User(content.get(10 * i), content.get((10 * i) + 1), content.get((10 * i) + 1), content.get((10 * i) + 2)
                        , content.get((10 * i) + 3), content.get((10 * i) + 4));
                wantedUser.setSecurityQuestion(content.get((10 * i) + 6));
                wantedUser.setSecurityAnswer(content.get((10 * i) + 7));
                return wantedUser;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers(String path) {
        ArrayList<User> allUsers = new ArrayList<>();
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            allUsers.add(getUserByUsername(content.get(10 * i)));
        }
        return allUsers;
    }

    public static boolean isUserNameUnique(String username) {
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmailUnique(String email) {
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get((10 * i) + 3).equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static User getFirstStayLoggedIn() {
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get((10 * i) + 8).equals("true")) {
                return getUserByUsername(content.get(10 * i));
            }
        }
        return null;
    }

    public static void addUserToFile(User user) throws NoSuchAlgorithmException {
        ArrayList<String> content = new ArrayList<>();
        content.add(user.getUsername());
        content.add(RegisterLoginController.passwordToSHA(user.getPassword()));
        content.add(user.getNickname());
        content.add(user.getEmail());
        content.add(user.getSlogan());
        content.add(String.valueOf(user.getHighScore()));
        content.add(user.getSecurityQuestion());
        content.add(user.getSecurityAnswer());
        content.add("false");
        content.add("_____________________________________________________");
        writeToFileContent("Users.txt", content, true);
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
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                content.remove((10 * i) + 8);
                content.add(((10 * i) + 8), String.valueOf(isLoggedIn));
            }
        }
        writeToFileContent("Users.txt", content, false);
    }

    public static void changePassword(String username, String password) throws NoSuchAlgorithmException {
        ArrayList<String> content = readFileContent("Users.txt");
        for (int i = 0; i < (content.size() / 10); i++) {
            if (content.get(10 * i).equals(username)) {
                content.remove((10 * i) + 1);
                content.add(((10 * i) + 1), RegisterLoginController.passwordToSHA(password));
            }
        }
        writeToFileContent("Users.txt", content, false);
    }

    public static void initializeGamesFile() {
        File Games = new File("Games.txt");
        ArrayList<String> content = readFileContent("Games.txt");
        if (content.size() < 4) {
            ArrayList<String> initial = new ArrayList<>();
            initial.add("--GAME ID--");
            initial.add("--ALL PLAYERS(separated with ,)--");
            initial.add("--MAP TEMPLATE NUMBER--");
            initial.add("_____________________________________________________");
            writeToFileContent("Games.txt", initial, false);
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
        writeToFileContent("Games.txt", content, true);
    }

    public static void initializeKingdomsFile() {
        File Kingdoms = new File("Kingdoms.txt");
        ArrayList<String> content = readFileContent("Kingdoms.txt");
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
            writeToFileContent("Kingdoms.txt", initial, false);
        }
    }

    public static void addKingdomToFile(Kingdom kingdom) {
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
        writeToFileContent("Kingdoms.txt", content, true);
    }

    public static ShopBuildings getShopBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("ShopBuilding.txt");
        for (int i = 0; i < (content.size() / 6); i++) {
            if (content.get(6 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(6 * i + 2).split(":\\d-?").length; j++) {
                    Product product = Product.getProductByName(content.get(6 * i + 2).split(":\\d-?")[j]);
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
        ArrayList<String> content = readFileContent("TrainingBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(6 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    Product product = Product.getProductByName(content.get(7 * i + 2).split(":\\d-?")[j]);
                    product.increaseCount(Integer.parseInt(content.get(7 * i + 2).split("-?[a-zA-Z]+:")[j]));
                    if (product != null)
                        neededProduct.add(product);
                }
                Building tmp = new Building(content.get(7 * i), content.get(7 * i + 1), neededProduct,
                        Integer.parseInt(content.get(7 * i + 3)), Integer.parseInt(content.get(7 * i + 4)));
                //TODO: output military people not available in the file!
                TrainingBuildings trainingBuilding = new TrainingBuildings(tmp, null, Integer.parseInt(content.get(6 * i + 5)));
                return trainingBuilding;
            }
        }
        return null;
    }

    public static ProductionBuildings getProductionBuildingByType(String Type) {
        ArrayList<String> content = readFileContent("ProductionBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(7 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    Product product = Product.getProductByName(content.get(7 * i + 2).split(":\\d-?")[j]);
                    product.increaseCount(Integer.parseInt(content.get(7 * i + 2).split("-?[a-zA-Z]+:")[j]));
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
        ArrayList<String> content = readFileContent("StorageBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(6 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    Product product = Product.getProductByName(content.get(7 * i + 2).split(":\\d-?")[j]);
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
        ArrayList<String> content = readFileContent("OtherBuilding.txt");
        for (int i = 0; i < (content.size() / 7); i++) {
            if (content.get(7 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(7 * i + 2).split(":\\d-?").length; j++) {
                    Product product = Product.getProductByName(content.get(7 * i + 2).split(":\\d-?")[j]);
                    product.increaseCount(Integer.parseInt(content.get(7 * i + 2).split("-?[a-zA-Z]+:")[j]));
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
        ArrayList<String> content = readFileContent("FightingBuilding.txt");
        for (int i = 0; i < (content.size() / 8); i++) {
            if (content.get(8 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(8 * i + 2).split(":\\d-?").length; j++) {
                    Product product = Product.getProductByName(content.get(8 * i + 2).split(":\\d-?")[j]);
                    product.increaseCount(Integer.parseInt(content.get(8 * i + 2).split("-?[a-zA-Z]+:")[j]));
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
        String[] ProductionBuilding = new String[]{",ill", "iron mine", "ox tether", "quarry", "woodcutter", "armourer",
                "blacksmith", "fletcher", "poleturner", "oil smelter", "stable", "apple orchard", "diary farmer", "hops farmer",
                "hunter post", "wheat farmer", "bakery", "brewer"};
        String[] StorageBuilding = new String[]{"armoury", "stockpile", "granary"};
        String[] OtherBuilding = new String[]{"small stone gatehouse", "large stone gatehouse", "drawbridge", "inn",
                "hovel", "church", "catheral", "caged war dogs"};
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
        ArrayList<String> content = readFileContent("MilitaryPerson.txt");
        for (int i = 0; i < (content.size() / 5); i++) {
            if (content.get(6 * i).equals(Type)) {
                ArrayList<Product> neededProduct = new ArrayList<>();
                for (int j = 0; j < content.get(6 * i + 4).split(",").length; j++) {
                    Product product = Product.getProductByName(content.get(6 * i + 4).split(",")[j]);
                    if (product != null)
                        neededProduct.add(product);
                }
                return new MilitaryPerson(content.get(6 * i), neededProduct, Integer.parseInt(content.get(6 * i + 1)),
                        Integer.parseInt(content.get(6 * i + 2)), Integer.parseInt(content.get(6 * i + 3)), content.get(6 * i + 4));
            }
        }
        return null;
    }
}