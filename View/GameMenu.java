package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController;
import Controller.RegisterLoginController;
import Model.User;

import java.util.Scanner;
import java.util.regex.Matcher;

import static View.Commands.getMatcher;

public class GameMenu {
    private final RegisterLoginController registerLoginController;
    private final GameController gameController;
    private final MapController mapController;
    private User currentUser;
    private Matcher matcher;
    private String input;
    
    public GameMenu(ChangeMenuController changeMenuController) {
        this.gameController = changeMenuController.getgameController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.mapController = changeMenuController.getMapController();
    }
    
    public String run(Scanner scanner) {
        System.out.println(registerLoginController.showCurrentMenuName("GAME MENU"));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = getMatcher(input, Commands.BACK)) != null) {
                return "mainMenu";
            } else if ((matcher = getMatcher(input, Commands.SELECT_UNIT)) != null) {
                switch (gameController.selectUnit(matcher)) {
                    case "success":
                        System.out.println("Unit is selected successfully!");
                        break;
                    case "don't have":
                        System.out.println("You don't have any people in this location!");
                        break;
                    case "invalid location":
                        System.out.println("You have entered invalid location!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.MOVE_UNIT)) != null) {
                switch (gameController.moveUnit(matcher)) {
                    case "success":
                        System.out.println("Unit has been moved successfully!");
                        break;
                    case "no selected unit":
                        System.out.println("You haven't selected a unit!");
                        break;
                    case "invalid location":
                        System.out.println("You have entered invalid location!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.CREATE_UNIT)) != null) {
                switch (gameController.createUnit(matcher)) {
                    case "not enough coins":
                        System.out.println("You don't have enough coins!");
                        break;
                    case "not enough products":
                        System.out.println("You don't have needed products to create this unit!");
                        break;
                    case "not enough people":
                        System.out.println("You don't have enough number of people to be trained!");
                        break;
                    case "invalid type for building":
                        System.out.println("You can't create this unit in this building!");
                        break;
                    case "success":
                        System.out.println("Unit created successfully!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.ATTACK_ENEMY)) != null) {
                switch (gameController.attackEnemy(matcher)) {
                    case "success":
                        System.out.println("Selected unit attacked successfully!");
                        break;
                    case "can't go":
                        System.out.println("Selected unit can't got to this location!");
                        break;
                    case "no enemy":
                        System.out.println("There's no enemy in this location!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.AERIAL_ATTACK)) != null) {
                switch (gameController.aerialAttack(matcher)) {
                    case "no shooter":
                        System.out.println("Selected unit is not able to shoot!");
                        break;
                    case "out of range":
                        System.out.println("The enemy you want to attack is out of range of the selected unit!");
                        break;
                    case "no enemy":
                        System.out.println("There is no enemy in this location!");
                        break;
                    case "success":
                        System.out.println("You made the aerial attack successfully!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.SHOW_MAP)) != null) {
                String result = gameController.showAPartOfMap(matcher);
                if (result.equals("invalid location")) System.out.println("You have entered invalid location!");
                else System.out.println(result);
            } else if ((matcher = getMatcher(input, Commands.MOVE_ON_MAP)) != null) {
                String result = gameController.moveOnMap(matcher);
                if (result.equals("haven't chosen")) System.out.println("You haven't chosen a location yet!");
                else if (result.equals("invalid location")) System.out.println("You have entered invalid location!");
            } else if ((matcher = getMatcher(input, Commands.POUR_OIL)) != null) {
                if (gameController.pourOil(matcher).equals("invalid location"))
                    System.out.println("You have entered invalid direction!");
                else System.out.println("Oil has been poured successfully!");
            } else if ((matcher = getMatcher(input, Commands.DIG_TUNNEL)) != null) {
                if (gameController.digTunnel(matcher).equals("invalid location"))
                    System.out.println("You have entered invalid location!");
                else System.out.println("Tunnel has been dug successfully!");
            } else if ((matcher = getMatcher(input, Commands.SET_MODE)) != null) {
                switch (gameController.setMode(matcher)) {
                    case "invalid location":
                        System.out.println("You have entered invalid location!");
                        break;
                    case "no person":
                        System.out.println("You have no military person in this location!");
                        break;
                    case "success":
                        System.out.println("Changed mode successfully!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.DISBAND)) != null) {
                if (gameController.disbandUnit().equals("can't go"))
                    System.out.println("You can't dis band this unit!");
                else System.out.println("Unit is disbanded successfully!");
            } else if ((matcher = getMatcher(input, Commands.BUILD_EQUIPMENT)) != null) {
                switch (gameController.buildEquipment(matcher)) {
                    case "success":
                        System.out.println("Equipment is built successfully!");
                        break;
                    
                }
            } else if (input.matches("show map")) {
                System.out.println(MapController.showMap(gameController.getCurrentGame().getMap()));
            } else if ((matcher = getMatcher(input, Commands.BLOCK_SET_TEXTURE)) != null) {
                System.out.println(gameController.setCellMaterial(matcher));
            } else if ((matcher = getMatcher(input, Commands.RECTANGLE_SET_TEXTURE)) != null) {
                System.out.println(gameController.setCellBlockMaterial(matcher));
            } else if ((matcher = getMatcher(input, Commands.CLEAR_BLOCK)) != null) {
                System.out.println(gameController.clearCell(matcher));
            } else if ((matcher = getMatcher(input, Commands.DROP_ROCK)) != null) {
                System.out.println(gameController.dropRock(matcher));
            } else if ((matcher = getMatcher(input, Commands.DROP_OBJECT)) != null) {
                System.out.println(gameController.dropObject(matcher));
            } else if ((matcher = getMatcher(input, Commands.SELECT_BUILDING)) != null) {
                System.out.println(gameController.selectBuilding(matcher));
            } else if ((matcher = getMatcher(input, Commands.SHOW_DETAILS)) != null) {
                System.out.println(gameController.showDetails(matcher));
            } else if ((matcher = getMatcher(input, Commands.SHOW_POPULARITY_FACTORS)) != null) {
                System.out.println(gameController.showPopularityFactors());
            } else if ((matcher = getMatcher(input, Commands.SHOW_POPULARITY)) != null) {
                System.out.println(gameController.showPopularity());
            } else if ((matcher = getMatcher(input, Commands.SHOW_FOOD_LIST)) != null) {
                System.out.println(gameController.showFoodList());
            } else if ((matcher = getMatcher(input, Commands.RATE_POPULARITY_FACTOR)) != null) {
                gameController.ratePopularityFactor(matcher);
            } else if ((matcher = getMatcher(input, Commands.SHOW_POPULARITY_FACTOR_RATE)) != null) {
                System.out.println(gameController.showPopularityFactorRate(matcher));
            } //else if ((matcher = getMatcher(input,Commands.REPAIR))!=null) {
            //System.out.println(gameController.repair());
            else System.out.println("invalid command!");
        }
    }
}