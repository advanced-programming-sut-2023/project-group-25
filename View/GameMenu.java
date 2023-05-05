package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Model.User;

import java.util.Scanner;
import java.util.regex.Matcher;

import static View.Commands.getMatcher;

public class GameMenu {
    private final ChangeMenuController changeMenuController;
    private final GameController gamecontroller;
    private User currentUser;
    private Matcher matcher;
    private String input;
    
    public GameMenu(ChangeMenuController changeMenuController) {
        this.changeMenuController = changeMenuController;
        this.gamecontroller = changeMenuController.getGameController();
    }
    
    public String run(Scanner scanner) {
        while (true) {
            input = scanner.nextLine();
            if ((matcher = getMatcher(input, Commands.BACK)) != null) {
                return "mainMenu";
            } else if ((matcher = getMatcher(input, Commands.SELECT_UNIT)) != null) {
                switch (gamecontroller.selectUnit(matcher)) {
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
                switch (gamecontroller.moveUnit(matcher)) {
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
                switch (gamecontroller.createUnit(matcher)) {
                    //TODO: complete method
                }
            } else if ((matcher = getMatcher(input, Commands.ATTACK_ENEMY)) != null) {
                switch (gamecontroller.attackEnemy(matcher)) {
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
            } else if ((matcher = getMatcher(input,Commands.AERIAL_ATTACK)) != null) {
                switch (gamecontroller.aerialAttack(matcher)) {
                
                }
            } else if ((matcher = getMatcher(input, Commands.SHOW_MAP)) != null) {
                String result = gamecontroller.showAPartOfMap(matcher);
                if (result.equals("invalid location")) System.out.println("You have entered invalid location!");
                else System.out.println(result);
            } else if ((matcher = getMatcher(input, Commands.MOVE_ON_MAP)) != null) {
                String result = gamecontroller.moveOnMap(matcher);
                if (result.equals("invalid location")) System.out.println("You have entered invalid location!");
                else System.out.println(result);
            } else if ((matcher = getMatcher(input, Commands.POUR_OIL)) != null) {
                if (gamecontroller.pourOil(matcher).equals("invalid location"))
                    System.out.println("You have entered invalid direction!");
                else System.out.println("Oil has been poured successfully!");
            } else if ((matcher = getMatcher(input, Commands.DIG_TUNNEL)) != null) {
                if (gamecontroller.digTunnel(matcher).equals("invalid location"))
                    System.out.println("You have entered invalid location!");
                else System.out.println("Tunnel has been dug successfully!");
            } else if ((matcher = getMatcher(input, Commands.FILL_MOAT)) != null) {
                switch (gamecontroller.fillMoat(matcher)) {
                    case "success":
                        System.out.println("Moat has been filled successfully!");
                        break;
                    case "invalid location":
                        System.out.println("You have entered invalid location!");
                        break;
                    case "no moat":
                        System.out.println("There is no moat in this location!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.DIG_MOAT)) != null) {
                switch (gamecontroller.digMoat(matcher)) {
                    case "invalid location":
                        System.out.println("You have entered invalid location!");
                        break;
                    case "has moat":
                        System.out.println("A moat already exists in this cell!");
                        break;
                    case "has building":
                        System.out.println("You can't dig a moat under a building!");
                        break;
                    case "has water":
                        System.out.println("You can't dig a moat under water!");
                        break;
                    case "success":
                        System.out.println("The moat has been dug successfully!");
                        break;
                }
            } else if ((matcher = getMatcher(input, Commands.SET_MODE)) != null) {
                switch (gamecontroller.setMode(matcher)) {
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
                if (gamecontroller.disbandUnit().equals("can't go")) System.out.println("You can't dis band this unit!");
                else System.out.println("Unit is disbanded successfully!");
            }
        }
    }
    
}