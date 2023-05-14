package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController;
import Controller.RegisterLoginController;
import Model.User;

import java.security.NoSuchAlgorithmException;
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
    private TradeMenu tradeMenu;
    
    public GameMenu(ChangeMenuController changeMenuController) {
        this.gameController = changeMenuController.getgameController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.mapController = changeMenuController.getMapController();
        this.tradeMenu = new TradeMenu(changeMenuController);
    }
    
    public String run(Scanner scanner) throws NoSuchAlgorithmException {
        System.out.println(registerLoginController.showCurrentMenuName("GAME MENU"));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = getMatcher(input, Commands.BACK)) != null) {
                return "main menu";
            } else if ((matcher = getMatcher(input, Commands.SELECT_UNIT)) != null) {
                System.out.println(gameController.selectUnit(matcher));
            } else if ((matcher = getMatcher(input, Commands.MOVE_UNIT)) != null) {
                System.out.println(gameController.moveUnit(matcher));
            } else if ((matcher = getMatcher(input, Commands.PATROL)) != null) {
                System.out.println(gameController.patrol(matcher));
            } else if ((matcher = getMatcher(input, Commands.CREATE_UNIT)) != null) {
                System.out.println(gameController.createUnit(matcher));
            } else if ((matcher = getMatcher(input, Commands.ATTACK_ENEMY)) != null) {
                System.out.println(gameController.attackEnemy(matcher));
            } else if ((matcher = getMatcher(input, Commands.AERIAL_ATTACK)) != null) {
                System.out.println(gameController.aerialAttack(matcher));
            } else if ((matcher = getMatcher(input, Commands.SHOW_MAP)) != null) {
                System.out.println(gameController.showAPartOfMap(matcher));
            } else if ((matcher = getMatcher(input, Commands.MOVE_ON_MAP)) != null) {
                System.out.println(gameController.moveOnMap(matcher));
            } else if ((matcher = getMatcher(input, Commands.POUR_OIL)) != null) {
                System.out.println(gameController.pourOil(matcher));
            } else if ((matcher = getMatcher(input, Commands.DIG_TUNNEL)) != null) {
                System.out.println(gameController.digTunnel(matcher));
            } else if ((matcher = getMatcher(input, Commands.SET_MODE)) != null) {
                System.out.println(gameController.setMode(matcher));
            } else if ((matcher = getMatcher(input, Commands.DISBAND)) != null) {
                System.out.println(gameController.disbandUnit());
            } else if ((matcher = getMatcher(input, Commands.BUILD_EQUIPMENT)) != null) {
                System.out.println(gameController.buildEquipment(matcher));
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
            } else if ((matcher = getMatcher(input, Commands.SHOW_DETAILS)) != null) {
                System.out.print(gameController.showDetails(matcher));
            } else if ((matcher = getMatcher(input, Commands.SELECT_BUILDING)) != null) {
                System.out.println(gameController.selectBuilding(matcher));
            } else if ((matcher = getMatcher(input, Commands.REPAIR)) != null) {
                System.out.println(gameController.repair());
            } else if ((matcher = getMatcher(input, Commands.SHOW_POPULARITY_FACTORS)) != null) {
                System.out.print(gameController.showPopularityFactors());
            } else if ((matcher = getMatcher(input, Commands.SHOW_POPULARITY)) != null) {
                System.out.println(gameController.showPopularity());
            } else if ((matcher = getMatcher(input, Commands.SHOW_FOOD_LIST)) != null) {
                System.out.println(gameController.showFoodList());
            } else if ((matcher = getMatcher(input, Commands.RATE_POPULARITY_FACTOR)) != null) {
                gameController.ratePopularityFactor(matcher);
            } else if ((matcher = getMatcher(input, Commands.SHOW_POPULARITY_FACTOR_RATE)) != null) {
                System.out.println(gameController.showPopularityFactorRate(matcher));
            } else if ((matcher = getMatcher(input, Commands.NEXT_TURN)) != null) {
                gameController.nextTurn();
            } else if ((matcher = getMatcher(input, Commands.ENTER_TRADE_MENU)) != null) {
                tradeMenu.run(scanner);
                System.out.println(registerLoginController.showCurrentMenuName("GAME MENU"));
            } else if ((matcher = getMatcher(input, Commands.FETCH_OIL)) != null) {
                System.out.println(gameController.fetchOil());
            } else if ((matcher = getMatcher(input, Commands.BURN_OIL)) != null) {
                System.out.println(gameController.burnOil());
            } else if ((matcher = getMatcher(input, Commands.PRODUCE_SOURCE)) != null) {
                System.out.println(gameController.produceSource(matcher));
            } else if ((matcher = getMatcher(input, Commands.PRODUCE_FOOD)) != null) {
                System.out.println(gameController.produceFood(matcher));
            } else if ((matcher = getMatcher(input, Commands.PRODUCE_EQUIPMENT)) != null) {
                System.out.println(gameController.produceEquipment(matcher));
            } else System.out.println("invalid command!");
        }
    }
}