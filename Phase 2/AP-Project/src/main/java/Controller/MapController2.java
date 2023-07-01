package Controller;

import Model.Cell;
import Model.*;
import Model.Map;
import View.FirstPage;
import View.GameGraphics;
import View.MainMenuGraphics;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

import static Controller.MapController.*;
import static View.GameGraphics.selectedBuilding;
import static View.GameGraphics.toCreateUnitImageView;
import static java.util.Map.entry;

public class MapController2 {
    public static String clickedBuildingToDrop = null;
    //    public static String clickedUnitToCreate = null;
    private static int stableCount = 0;
    private static int horseCount = 0;
    private static GameController gameController = FirstPage.changeMenuController.getGameController();
    public final Label miniMapShowingLabel = new Label();
    private final Label miniMapLabel = new Label();
    private final ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
    private final ImageView popularityMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/popularityMenu.png"))));
    private final ImageView back = new ImageView(new Image(String.valueOf(getClass().getResource("/images/back.png"))));
    private final ImageView imageIcon1 = new ImageView(new Image(String.valueOf(getClass().getResource("/images/i1.png"))));
    private final ImageView imageIcon2 = new ImageView(new Image(String.valueOf(getClass().getResource("/images/i2.png"))));
    private final ImageView imageIcon3 = new ImageView(new Image(String.valueOf(getClass().getResource("/images/i3.png"))));
    private final ImageView imageIcon4 = new ImageView(new Image(String.valueOf(getClass().getResource("/images/i4.png"))));
    private final ImageView imageIcon5 = new ImageView(new Image(String.valueOf(getClass().getResource("/images/i5.png"))));
    private final ImageView imageIcon6 = new ImageView(new Image(String.valueOf(getClass().getResource("/images/i6.png"))));
    private final ImageView barracks = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/TrainingBuildings/barracks0.png"))));
    private final ImageView mercenary = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/TrainingBuildings/mercenary post0.png"))));
    private final ImageView armoury = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/StorageBuildings/armoury0.png"))));
    private final ImageView stairs = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/stairs.png"))));
    private final ImageView shortWall = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/short wall.png"))));
    private final ImageView highWall = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/high wall.png"))));
    private final ImageView towers = new ImageView(new Image(String.valueOf(getClass().getResource("/images/towers.png"))));
    private final ImageView militaryBuildings = new ImageView(new Image(String.valueOf(getClass().getResource("/images/militaryBuilding.png"))));
    private final ImageView gatehouse = new ImageView(new Image(String.valueOf(getClass().getResource("/images/gatehouse.png"))));
    private final ImageView stockpile = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/StorageBuildings/stockpile.png"))));
    private final ImageView woodCutter = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/woodcutter.png"))));
    private final ImageView quarry = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/quarry.png"))));
    private final ImageView oxTether = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/ox tether.png"))));
    private final ImageView ironMine = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/iron mine.png"))));
    private final ImageView market = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ShopBuildings/market0.png"))));
    private final ImageView appleOrchard = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/apple orchard.png"))));
    private final ImageView dairyFarmer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/dairy farmer.png"))));
    private final ImageView hopsFarmer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/hops farmer.png"))));
    private final ImageView wheatFarmer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/wheat farmer.png"))));
    private final ImageView hovel = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/hovel.png"))));
    private final ImageView church = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/church.png"))));
    private final ImageView catheral = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/catheral.png"))));
    private final ImageView armourer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/armourer.png"))));
    private final ImageView blacksmith = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/blacksmith.png"))));
    private final ImageView fletcher = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/fletcher.png"))));
    private final ImageView poleturner = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/poleturner.png"))));
    private final ImageView granary = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/StorageBuildings/granary0.png"))));
    private final ImageView bakery = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/bakery.png"))));
    private final ImageView brewer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/brewer.png"))));
    private final ImageView mill = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/mill.gif"))));
    private final ImageView inn = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/inn.png"))));
    private final ImageView lookoutTower = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/FightingBuildings/lookout tower.png"))));
    private final ImageView defenceTower = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/FightingBuildings/defence turret.png"))));
    private final ImageView perimeterTower = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/FightingBuildings/perimeter tower.png"))));
    private final ImageView roundTower = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/FightingBuildings/round tower.png"))));
    private final ImageView squareTower = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/FightingBuildings/square tower.png"))));
    private final ImageView smallGate = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/small stone gatehouse.png"))));
    private final ImageView largeGate = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/large stone gatehouse.png"))));
    private final ImageView cage = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/OtherBuildings/caged war dogs.png"))));
    private final ImageView pit = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/FightingBuildings/pits.png"))));
    private final ImageView engineerGuild = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/TrainingBuildings/engineers guild0.png"))));
    private final ImageView stable = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/stable.png"))));
    private final ImageView oilSmelter = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/oil smelter.png"))));
    private final ImageView churchMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/church.png"))));
    private final ImageView catheralMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/cathedral.png"))));
    private final ImageView armouryMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/Armoury.png"))));
    private final ImageView woodcutterMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/woodcutter.png"))));
    private final ImageView cageMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/caged war dog.png"))));
    private final ImageView killingPitMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/killing pit.png"))));
    private final ImageView stableMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/stables.png"))));
    private final ImageView stockpileMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/stockpile.png"))));
    private final ImageView appleOrchardMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/apple orchard.png"))));
    private final ImageView dairyFarmMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/dairy farm.png"))));
    private final ImageView hopsFarmMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/hops farm.png"))));
    private final ImageView hovelMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/hovel.png"))));
    private final ImageView ironMineMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/iron mine.png"))));
    private final ImageView oxTetherMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/ox tether.png"))));
    private final ImageView quarryMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/quarry.png"))));
    private final ImageView wheatFarmMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/wheat farm.png"))));
    private final ImageView bakeryMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/bakery.png"))));
    private final ImageView breweryMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/brewery.png"))));
    private final ImageView granaryMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/granary.png"))));
    private final ImageView innMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/inn.png"))));
    private final ImageView millMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/mill.png"))));
    private final ImageView fletcherMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/fletcher.png"))));
    private final ImageView armorerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/armorer.png"))));
    private final ImageView blacksmithMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/blacksmith.png"))));
    private final ImageView poleturnerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/poleturner.png"))));

    private final ImageView mainMarketMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/market.png"))));
    private final ImageView backMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/back.png"))));
    private final ImageView foodMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/food.png"))));
    private final ImageView sourcesMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/sources.png"))));
    private final ImageView weaponsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/weapns.png"))));
    private final ImageView totalMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/t.png"))));
    private final ImageView tradeFoodMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/trade food.png"))));
    private final ImageView tradeResourcesMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/trade materials.png"))));
    private final ImageView tradeWeaponsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/trade weapons.png"))));
    private final ImageView allMarketMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/all.png"))));
    private final ImageView sellBuyMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/buy sell panel.png"))));
    private final ImageView meatMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/meat.png"))));
    private final ImageView cheeseMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/cheese.png"))));
    private final ImageView hopMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/hop.png"))));
    private final ImageView wheatMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/g.png"))));
    private final ImageView breadMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/bread.png"))));
    private final ImageView appleMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/apple.png"))));
    private final ImageView bearMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/bear.png"))));
    private final ImageView flowerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/flower.png"))));
    private final ImageView woodMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/wood.png"))));
    private final ImageView rockMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/rock.png"))));
    private final ImageView ironMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/iron.png"))));
    private final ImageView bowMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/bow.png"))));
    private final ImageView crossbowMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/crossbow.png"))));
    private final ImageView leatherMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/leatherArmour.png"))));
    private final ImageView metalMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/metalarmour.png"))));
    private final ImageView maceMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/mace.png"))));
    private final ImageView pikeMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/pike.png"))));
    private final ImageView swordMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/sword.png"))));
    private final ImageView spearMarket = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/spear.png"))));
    
    private final ImageView bowMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/bow.png"))));
    private final ImageView crossbowMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/crossbow.png"))));
    private final ImageView leatherMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/leather.png"))));
    private final ImageView maceMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/mace.png"))));
    private final ImageView metalMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/metal.png"))));
    private final ImageView pikeMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/pike.png"))));
    private final ImageView spearMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/spear.png"))));
    private final ImageView swordMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/sword.png"))));
    private final ImageView monk = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/monk.png"))));
    private final ImageView arabianUnitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
    private final ImageView archerBow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/ArcherBow.png"))));
    private final ImageView assassin = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Assassins.png"))));
    private final ImageView horseArcher = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/HorseArchers.png"))));
    private final ImageView arabianSwordsmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/ArabianSwordsmen.png"))));
    private final ImageView fireThrowers = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/FireThrowers.png"))));
    private final ImageView slave = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Slaves.png"))));
    private final ImageView slinger = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Slingers.png"))));
    private final ImageView enginnerUnitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
    private final ImageView engineer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Engineer.png"))));
    private final ImageView ladderman = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Laddermen.png"))));
    private final ImageView tunneler = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Tunneler.png"))));
    private final ImageView europeanUnitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/unitMenu.png"))));
    private final ImageView archer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Archer.png"))));
    private final ImageView crossbowmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Crossbowmen.png"))));
    private final ImageView knight = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Knight.png"))));
    private final ImageView macemen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Macemen.png"))));
    private final ImageView pikemen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Pikemen.png"))));
    private final ImageView spearmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Spearmen.png"))));
    private final ImageView swordsmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Swordsmen.png"))));
    private final ImageView bow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/bow.png"))));
    private final ImageView crossbow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/crossbow.png"))));
    private final ImageView leatherArmour = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/leatherArmour.png"))));
    private final ImageView mace = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/mace.png"))));
    private final ImageView metalArmour = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/metalarmour.png"))));
    private final ImageView pike = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/pike.png"))));
    private final ImageView spear = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/spear.png"))));
    private final ImageView sword = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/sword.png"))));
    private final ImageView horse = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/horse.png"))));
    private final ImageView redMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/redMenu.png"))));
    private final ImageView stop = new ImageView(new Image(String.valueOf(getClass().getResource("/images/stop.png"))));
    private final ImageView briefing = new ImageView(new Image(String.valueOf(getClass().getResource("/images/briefing.png"))));
    private final ImageView delete = new ImageView(new Image(String.valueOf(getClass().getResource("/images/delete.png"))));
    private final ImageView undo = new ImageView(new Image(String.valueOf(getClass().getResource("/images/undo.png"))));
    private final ImageView options = new ImageView(new Image(String.valueOf(getClass().getResource("/images/option.png"))));
    private final ImageView briefingMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/briefingMenu.png"))));
    private final ImageView greenMask = new ImageView(new Image(String.valueOf(getClass().getResource("/images/green.png"))));
    private final ImageView yellowMask = new ImageView(new Image(String.valueOf(getClass().getResource("/images/yellow.png"))));
    private final ImageView redMask = new ImageView(new Image(String.valueOf(getClass().getResource("/images/red.png"))));
    private final HashMap<String, Integer> hasGivenHandler = new HashMap<>();
    Text foodNumber = new Text();
    private Button backToGame = new Button("Back To Game");
    private Button exit = new Button("Exit Game");
    private Button resume = new Button("Resume game");
    private boolean isTheFirstTime = false;
    private int edgeLength = 70;
    private int shownX;
    private int shownY;
    private int miniMapShowingX = 1401;
    private int miniMapShowingY = 729;
    private Map map;
    private Pane pane;
    private Scene scene;
    private Stage stage;
    private Button first = new Button("-2");
    private Button second = new Button("-1");
    private Button third = new Button("0");
    private Button forth = new Button("1");
    private Button fifth = new Button("2");

    public static void initializeMapTemplate(int length, int width) {
        Map map = new Map(length, width);
        initializeCastlesLocation(map, length, width);
        initializeIronLandsTemplate2(map, length, width);
        initializeRockLandsTemplate2(map, length, width);
        for (int i = (3 * length) / 6 - length / 8; i < (3 * length) / 6 + length / 8; i++)
            for (int j = width / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "sea");
        for (int i = length / 6; i < (5 * length) / 6; i++)
            for (int j = (3 * width) / 6 - width / 8; j < (3 * width) / 6 + width / 8; j++)
                map.getCells()[i][j] = new Cell(i, j, "sea");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = width / 6; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");
        for (int i = (2 * length) / 6; i < (3 * length) / 6 - length / 8; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = width / 6; j < (2 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");
        for (int i = (3 * length) / 6 + length / 8; i < (4 * length) / 6; i++)
            for (int j = (4 * width) / 6; j < (5 * width) / 6; j++)
                map.getCells()[i][j] = new Cell(i, j, "grass");

        setDefaultLand(length, width, map);
        Map.setTemplateMap(1, map);
    }

    public static void initializeCastlesLocation(Map map, int length, int width) {
        Building castle = new Building("castle", "otherBuilding", null, 0, 10000);
        int[][] castlePositions = new int[8][2];
        castlePositions[0][0] = 2;
        castlePositions[0][1] = 2;
        castlePositions[1][0] = length - 3;
        castlePositions[1][1] = width - 3;
        castlePositions[2][0] = 2;
        castlePositions[2][1] = width - 3;
        castlePositions[3][0] = length - 3;
        castlePositions[3][1] = 2;
        castlePositions[4][0] = 2;
        castlePositions[4][1] = width / 2;
        castlePositions[5][0] = length - 3;
        castlePositions[5][1] = width / 2;
        castlePositions[6][0] = length / 2;
        castlePositions[6][1] = 2;
        castlePositions[7][0] = length / 2;
        castlePositions[7][1] = width - 3;
        for (int i = 0; i < gameController.getCurrentGame().getNumberOfPlayers(); i++) {
            map.getCells()[castlePositions[i][0]][castlePositions[i][1]] = new Cell(castlePositions[i][0]
                    , castlePositions[i][1], "land");
            map.getCells()[castlePositions[i][0]][castlePositions[i][1]].setBuilding(castle);
            castle.setKing(gameController.getCurrentGame().getKingdoms().get(i).getKing());
            gameController.getCurrentGame().getKingdoms().get(i).setMainCastleLocation(map.getCells()
                    [castlePositions[i][0]][castlePositions[i][1]]);
            if (!gameController.getCurrentGame().isFirstLoaded())
                for (int j = 0; j < 8; j++) {
                    addJoblessInitially(gameController.getCurrentGame().getKingdoms().get(i));
                }
        }
        //gameController.getCurrentGame().setFirstLoaded(true);
    }

    public int getMiniMapShowingX() {
        return miniMapShowingX;
    }

    public void setMiniMapShowingX(int miniMapShowingX) {
        this.miniMapShowingX = miniMapShowingX;
    }

    public int getMiniMapShowingY() {
        return miniMapShowingY;
    }

    public void setMiniMapShowingY(int miniMapShowingY) {
        this.miniMapShowingY = miniMapShowingY;
    }

    public int getShownX() {
        return shownX;
    }

    public void setShownX(int shownX) {
        this.shownX = shownX;
    }

    public int getShownY() {
        return shownY;
    }

    public void setShownY(int shownY) {
        this.shownY = shownY;
    }

    public String loadMapToShow(Scene scene, Stage stage, Pane pane, Map map, int x, int y, int edgeLength) {
        gameController = new GameController();

        //capacity: 31 x 16 (x50 pixels)
        if (edgeLength < 40 || edgeLength >= 100 || !isLocationAppropriateToShow(x, y, map, edgeLength)) {
//            System.out.println("returned");
            return "bad location";
        }

//        System.out.println("loading");

        this.edgeLength = edgeLength;
        this.shownX = x;
        this.shownY = y;
        this.map = map;
        this.pane = pane;
        this.scene = scene;
        this.stage = stage;

        int xCounter = 0, yCounter = 0;

        for (int i = x - 11 * 70 / edgeLength; i < x + 12 * 70 / edgeLength + 1; i++) {
            for (int j = y - 5 * 70 / edgeLength; j < y + 7 * 70 / edgeLength + 2; j++) {
                assert map != null;
                if (i >= map.getLength() || j >= map.getWidth()) continue;
                Cell cell = map.getCells()[i][j];

                showBackgrounds(pane, cell.getMaterial(), xCounter, yCounter, edgeLength);

                for (NaturalBlock naturalBlock : cell.getNaturalBlocks()) {
                    showNaturalBlock(pane, i, j, naturalBlock);
                }

                showBuilding(pane, i, j, cell.getBuilding());

                showPeople(pane, map, i, j);

                yCounter++;
            }
            xCounter++;
            yCounter = 0;
        }

        Background background = new Background(MainController.setFirstPageBackground("/images/miniMap.png"));
        miniMapLabel.setBackground(background);
        miniMapLabel.setPrefHeight(135);
        miniMapLabel.setPrefWidth(135);
        miniMapLabel.setLayoutX(1401);
        miniMapLabel.setLayoutY(729);
        miniMapLabel.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-style: solid;");
        miniMapLabel.toFront();


        miniMapShowingLabel.setStyle("-fx-background-color: #fff0; -fx-border-style: solid;" +
                " -fx-border-width: 2; -fx-border-color: white;");
        miniMapShowingLabel.setPrefWidth(20);
        miniMapShowingLabel.setPrefHeight(20);
        miniMapShowingLabel.setLayoutX(miniMapShowingX);
        miniMapShowingLabel.setLayoutY(miniMapShowingY);
        miniMapShowingLabel.toFront();

        EventHandler<MouseEvent> ccc = ev -> {
            double xScene = ev.getX();
            double yScene = ev.getY();
            if (xScene >= 0 && xScene <= 1528.8 && yScene >= 2.4 && yScene <= 692) {
                setArabianUnits(false);
                setEngineer(false);
                monk.setVisible(false);
                setAllMenus();
                setEuropeanUnits(false);
                setWeapons(false);
                setGranaryButtons(false);
                foodNumber.setVisible(false);
            }
        };
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, ccc);

        setSizeMenuIcons(imageView, 0, 670);
        setSizeIcons(imageIcon1, 500, 815, 50, 50);
        setSizeIcons(back, 500, 790, 35, 35);
        setSizeIcons(redMenu, 1364, 729, 137, 37);
        setSizeMenuIcons(stop, 1370, 735);
        setSizeMenuIcons(briefing, 1375, 770);
        setSizeMenuIcons(delete, 1375, 800);
        setSizeMenuIcons(undo, 1375, 830);
        stop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stopHandler());
        briefing.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> briefingHandler());
        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> deleteHandler(event));
        undo.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> undoHandler());
        setTextForPopularity();

        setMenuIcon1();
        setTowers();
        setMilitaryBuildings();
        setGatehouse();
        imageIcon1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(true);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            event.consume();
        });

        setSizeIcons(imageIcon2, 550, 825, 40, 40);
        setMenuIcon2();
        imageIcon2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(true);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            event.consume();
        });

        setSizeIcons(imageIcon3, 600, 822, 45, 40);
        setMenuIcon3();
        imageIcon3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(true);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            event.consume();
        });

        setSizeIcons(imageIcon4, 650, 822, 45, 40);
        setMenuIcon4();
        imageIcon4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(true);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            event.consume();
        });

        setSizeIcons(imageIcon5, 700, 825, 40, 40);
        setMenuIcon5();
        imageIcon5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(true);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            event.consume();
        });

        setSizeIcons(imageIcon6, 750, 825, 40, 40);
        setMenuIcon6();
        imageIcon6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(true);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            event.consume();
        });

        towers.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(true);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            back.setVisible(true);
            popularityMenu.setVisible(false);
            event.consume();
        });

        militaryBuildings.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(true);
            setImagesIconGatehouse(false);
            back.setVisible(true);
            popularityMenu.setVisible(false);
            event.consume();
        });

        gatehouse.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(true);
            back.setVisible(true);
            popularityMenu.setVisible(false);
        });
        setPopularityMenu(pane);

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(true);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            event.consume();
        });
        
        backMarket.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addBackForMarket());
        Button nextTurn = new Button("Next Turn");
        nextTurn.setStyle("-fx-background-color:#FC9303;-fx-text-fill: black;-fx-border-color: black;");
        nextTurn.setLayoutX(1464);
        nextTurn.setLayoutY(701);
        nextTurn.toFront();
        pane.getChildren().add(nextTurn);
        nextTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            gameController.nextTurn();
            event.consume();
        });

        setEuropeanUnitsSize();
        setArabianUnitsSize();
        setEngineerSize();
        setSizeUnits(monk, "monk", 750, 720);
        addWeapons();
        makeHorse();
        addGranaryButton();

        if (!isTheFirstTime) {
            setArabianUnits(false);
            setEngineer(false);
            monk.setVisible(false);
            setAllMenus();
            setGranaryButtons(false);
            setEuropeanUnits(false);
            setWeapons(false);
            foodNumber.setVisible(false);
            setImagesIcons1(true);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            setImagesIconTowers(false);
            setImagesIconMilitaryBuildings(false);
            setImagesIconGatehouse(false);
            popularityMenu.setVisible(false);
            setSizeIcons(imageIcon6, 750, 825, 40, 40);
            
            setAllMenusSize();
            pane.getChildren().addAll(imageView, imageIcon1, imageIcon2, imageIcon3, imageIcon4, imageIcon5, imageIcon6
                    , barracks, mercenary, armoury, stairs, shortWall, highWall
                    , towers, militaryBuildings, gatehouse, back, smallGate, largeGate, cage, pit, engineerGuild, stable, oilSmelter
                    , lookoutTower, defenceTower, perimeterTower, roundTower, squareTower
                    , stockpile, woodCutter, quarry, ironMine, oxTether, market, appleOrchard, wheatFarmer, hopsFarmer, dairyFarmer
                    , hovel, church, catheral, catheralMenu, monk, poleturner, armourer, blacksmith, fletcher
                    , granary, bakery, brewer, mill, inn, popularityMenu, fletcherMenu, blacksmithMenu, armorerMenu, poleturnerMenu
                    , archerBow, assassin, arabianSwordsmen, fireThrowers, horseArcher, slave, slinger
                    , arabianUnitsMenu, europeanUnitsMenu, archer, crossbowmen, knight, macemen, pikemen, spearmen, swordsmen
                    , bow, crossbow, leatherArmour, mace, metalArmour, pike, spear, sword, horse
                    , maceMenu, swordMenu, bowMenu, crossbowMenu, metalMenu, leatherMenu, pikeMenu, spearMenu
                    , churchMenu, armouryMenu, hovelMenu, woodcutterMenu, cageMenu, killingPitMenu
                    , stableMenu, stockpileMenu, appleOrchardMenu, dairyFarmMenu, hopsFarmMenu, wheatFarmMenu
                    , quarryMenu, ironMineMenu, oxTetherMenu, innMenu, millMenu, bakeryMenu, breweryMenu, granaryMenu
                    , first, second, third, forth, fifth, foodNumber
                    , miniMapLabel, miniMapShowingLabel, exit, resume, briefingMenu, backToGame
                    , mainMarketMenu, foodMenu, sourcesMenu, weaponsMenu, totalMenu, options
                    , tradeFoodMenu, tradeResourcesMenu, tradeWeaponsMenu, allMarketMenu, sellBuyMenu
                    , woodMenu, ironMenu, rockMenu, backMarket, redMenu, stop, briefing, undo, delete
                    , meatMenu, cheeseMenu, appleMenu, hopMenu, bearMenu, wheatMenu, flowerMenu, breadMenu
                    , bowMarket, crossbowMarket, leatherMarket, metalMarket, maceMarket, pikeMarket, spearMarket, swordMarket);
            isTheFirstTime = true;
        }


        stage.show();
        return "success";
    }

    private void setTextForPopularity() {
        int inventory = (int) gameController.getCurrentGame().getKingdomByKing(gameController.getCurrentGame().turn.getCurrentKing().getUsername()).getInventory();
        Text coins = new Text(String.valueOf(inventory));
        coins.setLayoutX(1040);
        coins.setLayoutY(790);
        Text popularity = new Text(gameController.showPopularity());
        popularity.setLayoutX(1050);
        popularity.setLayoutY(770);
        int allpeople = gameController.getCurrentGame().getKingdomByKing(gameController.getCurrentGame().turn.getCurrentKing().getUsername()).getKingPeople().size();
        Text population = new Text(String.valueOf(allpeople));
        population.setLayoutX(1050);
        population.setLayoutY(810);
        pane.getChildren().addAll(coins, population, popularity);
    }

    private void deleteHandler(MouseEvent event) {
//        if ()
//        int cellX = getXLocationByPixel(event.getX() / edgeLength);
//        int cellY = getYLocationByPixel(event.getY() / edgeLength);
//        Cell cell = gameController.getCurrentGame().getMap().getCells()[cellX / edgeLength][cellY / edgeLength];
//        cell.setBuilding(null);
//        pane.getChildren().remove(GameGraphics.lastBuildingImage);
    }

    private void undoHandler() {
        int cellX = (int) ((GameGraphics.lastBuildingDropped.getLocation().getX() - shownX + (float) 11 * 70 / edgeLength) * edgeLength);
        int cellY = (int) ((GameGraphics.lastBuildingDropped.getLocation().getY() - shownY + (float) 5 * 70 / edgeLength) * edgeLength);
        Cell cell = gameController.getCurrentGame().getMap().getCells()[cellX / edgeLength][cellY / edgeLength];
        cell.setBuilding(null);
        pane.getChildren().remove(GameGraphics.lastBuildingImage);
        loadMapToShow(scene, stage, pane, map, shownX, shownY, edgeLength);
    }

    private void briefingHandler() {
        briefingMenu.setVisible(true);
        briefingMenu.toFront();
        backToGame.setVisible(true);
        backToGame.toFront();
        backToGame.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        backToGame.setFont(Font.font(12));
        backToGame.setLayoutX(680);
        backToGame.setLayoutY(498);
        Text text = new Text();
        StringBuilder users = new StringBuilder();
        ArrayList<Kingdom> allUsers = new ArrayList<>(gameController.getCurrentGame().getKingdoms());
        for (Kingdom kingdom : allUsers) users.append(kingdom.getKing().getUsername()).append("\n");
        text.setLayoutX(640);
        text.setLayoutY(400);
        text.setText(users.toString());
        text.toFront();
        pane.getChildren().add(text);
        backToGame.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            briefingMenu.setVisible(false);
            backToGame.setVisible(false);
        });
    }

    private void stopHandler() {
        options.toFront();
        options.setVisible(true);
        resume.setVisible(true);
        resume.toFront();
        exit.setVisible(true);
        exit.toFront();
        resume.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        exit.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        resume.setFont(Font.font(15));
        exit.setFont(Font.font(15));
        resume.setLayoutX(670);
        resume.setLayoutY(400);
        exit.setLayoutX(685);
        exit.setLayoutY(450);
        resume.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            options.setVisible(false);
            resume.setVisible(false);
            exit.setVisible(false);
        });
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                new MainMenuGraphics().start(FirstPage.stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void addBackForMarket() {
        mainMarketMenu.setVisible(true);
        foodMenu.setVisible(true);
        sourcesMenu.setVisible(true);
        weaponsMenu.setVisible(true);
        totalMenu.setVisible(true);
        backMarket.setVisible(false);
        tradeFoodMenu.setVisible(false);
        tradeResourcesMenu.setVisible(false);
        tradeWeaponsMenu.setVisible(false);
        allMarketMenu.setVisible(false);
        sellBuyMenu.setVisible(false);
        addFoodsToList(false);
        addSourceToList(false);
        addWeaponsToList(false);
    }
    
    private void setSizeMenuIcons(ImageView icon, int x, int y) {
        icon.setLayoutX(x);
        icon.setLayoutY(y);
        icon.toFront();
    }

    private void addGranaryButton() {
        first.setLayoutX(860);
        first.setLayoutY(800);
        first.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        second.setLayoutX(840);
        second.setLayoutY(770);
        second.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        third.setLayoutX(875);
        third.setLayoutY(740);
        third.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        forth.setLayoutX(915);
        forth.setLayoutY(770);
        forth.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        fifth.setLayoutX(890);
        fifth.setLayoutY(800);
        fifth.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
    }

    private void setGranaryButtons(boolean check) {
        first.setVisible(check);
        second.setVisible(check);
        third.setVisible(check);
        forth.setVisible(check);
        fifth.setVisible(check);
    }

    private void setBuildingMenu(Pane pane) {
        ImageView towerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/towerMenu.png"))));
        ImageView gatehouseMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/gatehouseMenu.png"))));
        setSizeMenuIcons(towerMenu, 0, 670);
        setSizeMenuIcons(gatehouseMenu, 0, 670);
        Text text = new Text(selectedBuilding.getType());
        String category = FileController.getBuildingCategoryByType(selectedBuilding.getType());
        Building savedBuilding = gameController.getBuilding(selectedBuilding.getType(), category);
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(selectedBuilding.getHitPoint() / savedBuilding.getHitPoint());
        progressBar.setStyle("-fx-accent: green");
        progressBar.setLayoutX(890);
        progressBar.setLayoutY(760);
        Button repair = new Button("repair");
        repair.setLayoutX(820);
        repair.setLayoutY(760);
        repair.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        if (selectedBuilding.getType().equals("lookout tower") || selectedBuilding.getType().equals("perimeter tower")
                || selectedBuilding.getType().equals("square tower") || selectedBuilding.getType().equals("defence turret")
                || selectedBuilding.getType().equals("round tower")) {
            text.toFront();
            text.setLayoutX(650);
            text.setLayoutY(780);
            text.setFont(Font.font(20));
            pane.getChildren().addAll(towerMenu, progressBar, repair, text);
        } else if (selectedBuilding.getType().equals("small stone gatehouse") || selectedBuilding.getType().equals("large stone gatehouse"))
            pane.getChildren().addAll(gatehouseMenu, progressBar, repair);
        else if (selectedBuilding.getType().equals("mercenary post")) setArabianUnits(true);
        else if (selectedBuilding.getType().equals("barracks")) {
            setEuropeanUnits(true);
            setWeapons(true);
        } else if (selectedBuilding.getType().equals("engineers guild")) setEngineer(true);
        else if (selectedBuilding.getType().equals("church")) churchMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("catheral")) {
            catheralMenu.setVisible(true);
            monk.setVisible(true);
        } else if (selectedBuilding.getType().equals("armoury")) setArmoury();
        else if (selectedBuilding.getType().equals("hovel")) hovelMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("market")) setMarketMenu();
        else if (selectedBuilding.getType().equals("granary")) setGranary();
        else if (selectedBuilding.getType().equals("woodcutter")) woodcutterMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("caged war dogs")) cageMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("pits")) killingPitMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("stable")) setStable();
        else if (selectedBuilding.getType().equals("stockpile")) stockpileMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("apple orchard")) appleOrchardMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("dairy farmer")) dairyFarmMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("hops farmer")) hopsFarmMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("wheat farmer")) wheatFarmMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("quarry")) quarryMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("iron mine")) ironMineMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("ox tether")) oxTetherMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("inn")) innMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("mill")) millMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("bakery")) bakeryMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("brewer")) breweryMenu.setVisible(true);
        else if (selectedBuilding.getType().equals("poleturner")) {
            poleturnerMenu.setVisible(true);
            pikeMenu.setVisible(true);
            spearMenu.setVisible(true);
        } else if (selectedBuilding.getType().equals("armourer")) {
            armorerMenu.setVisible(true);
            metalMenu.setVisible(true);
            leatherMenu.setVisible(true);
        } else if (selectedBuilding.getType().equals("blacksmith")) {
            blacksmithMenu.setVisible(true);
            maceMenu.setVisible(true);
            swordMenu.setVisible(true);
        } else if (selectedBuilding.getType().equals("fletcher")) {
            fletcherMenu.setVisible(true);
            bowMenu.setVisible(true);
            crossbowMenu.setVisible(true);
        }
        repair.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            gameController.repair(savedBuilding, selectedBuilding);
        });
    }

    private void setMarketMenu() {
        //TODO: shop HOORA
        mainMarketMenu.setVisible(true);
        foodMenu.setVisible(true);
        sourcesMenu.setVisible(true);
        weaponsMenu.setVisible(true);
        totalMenu.setVisible(true);
        foodMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> enterFoodMenu());
        sourcesMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> enterSourceMenu());
        weaponsMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> enterWeaponsMenu());
        totalMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> enterTotalMenu());
//        sellBuyMenu.setVisible(true);
    }
    
    private void enterTotalMenu() {
        backMarket.setVisible(true);
        mainMarketMenu.setVisible(false);
        foodMenu.setVisible(false);
        sourcesMenu.setVisible(false);
        weaponsMenu.setVisible(false);
        totalMenu.setVisible(false);
        tradeFoodMenu.setVisible(false);
        tradeResourcesMenu.setVisible(false);
        tradeWeaponsMenu.setVisible(false);
        allMarketMenu.setVisible(true);
        sellBuyMenu.setVisible(false);
        addFoodsToList(false);
        addSourceToList(false);
        //TODO: HOORA: text for each in format "buy/sell" price
    }
    
    private void enterWeaponsMenu() {
        backMarket.setVisible(true);
        mainMarketMenu.setVisible(false);
        foodMenu.setVisible(false);
        sourcesMenu.setVisible(false);
        weaponsMenu.setVisible(false);
        totalMenu.setVisible(false);
        tradeFoodMenu.setVisible(false);
        tradeResourcesMenu.setVisible(false);
        tradeWeaponsMenu.setVisible(true);
        allMarketMenu.setVisible(false);
        sellBuyMenu.setVisible(false);
        addFoodsToList(false);
        addSourceToList(false);
        addWeaponsToList(true);
    }
    
    private void enterSourceMenu() {
        backMarket.setVisible(true);
        mainMarketMenu.setVisible(false);
        foodMenu.setVisible(false);
        sourcesMenu.setVisible(false);
        weaponsMenu.setVisible(false);
        totalMenu.setVisible(false);
        tradeFoodMenu.setVisible(false);
        tradeResourcesMenu.setVisible(true);
        tradeWeaponsMenu.setVisible(false);
        allMarketMenu.setVisible(false);
        sellBuyMenu.setVisible(false);
        addFoodsToList(false);
        addSourceToList(true);
        addWeaponsToList(false);
    }
    
    private void enterFoodMenu() {
        backMarket.setVisible(true);
        mainMarketMenu.setVisible(false);
        foodMenu.setVisible(false);
        sourcesMenu.setVisible(false);
        weaponsMenu.setVisible(false);
        totalMenu.setVisible(false);
        tradeFoodMenu.setVisible(true);
        tradeResourcesMenu.setVisible(false);
        tradeWeaponsMenu.setVisible(false);
        allMarketMenu.setVisible(false);
        sellBuyMenu.setVisible(false);
        addFoodsToList(true);
        addSourceToList(false);
        addWeaponsToList(false);
    }
    
    private void addWeaponsToList(boolean check) {
        bowMarket.setVisible(check);
        crossbowMarket.setVisible(check);
        leatherMarket.setVisible(check);
        metalMarket.setVisible(check);
        maceMarket.setVisible(check);
        pikeMarket.setVisible(check);
        swordMarket.setVisible(check);
        spearMarket.setVisible(check);
    }
    
    private void addSourceToList(boolean check) {
        woodMenu.setVisible(check);
        rockMenu.setVisible(check);
        ironMenu.setVisible(check);
    }
    
    private void addFoodsToList(boolean check) {
        meatMenu.setVisible(check);
        cheeseMenu.setVisible(check);
        appleMenu.setVisible(check);
        hopMenu.setVisible(check);
        bearMenu.setVisible(check);
        wheatMenu.setVisible(check);
        flowerMenu.setVisible(check);
        breadMenu.setVisible(check);
    }

    private void setGranary() {
        granaryMenu.setVisible(true);
        setGranaryButtons(true);
        foodNumber.setVisible(true);
        foodNumber.setText(gameController.getNumberOfFoods() + " units of food.");
        first.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gameController.rateFood("-2"));
        second.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gameController.rateFood("-1"));
        third.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gameController.rateFood("0"));
        forth.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gameController.rateFood("1"));
        fifth.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gameController.rateFood("2"));
        foodNumber.setLayoutX(500);
        foodNumber.setLayoutY(800);
        foodNumber.toFront();
        foodNumber.setFont(Font.font(20));
    }

    private void setStable() {
        stableMenu.setVisible(true);
        stableCount++;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                System.out.println("Performing action...");
                horseCount++;
                count++;
                if (count >= 4) timer.cancel();
            }
        };
        timer.schedule(task, 0, 3000);
    }

    private void makeHorse() {
        if (horseCount < stableCount * 4) {
            while (horseCount < stableCount * 4) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    int count = 0;

                    @Override
                    public void run() {
                        System.out.println("Performing action...");
                        horseCount++;
                        count++;
                        if (count >= 4) timer.cancel();
                    }
                };
                timer.schedule(task, 0, 3000);
            }
        }
    }

    private void setArmoury() {
        armouryMenu.setVisible(true);
        setSizeMenuIcons(pikeMenu, 600, 760);
        setSizeMenuIcons(spearMenu, 650, 760);
        setSizeMenuIcons(metalMenu, 700, 760);
        setSizeMenuIcons(leatherMenu, 750, 760);
        setSizeMenuIcons(swordMenu, 800, 760);
        setSizeMenuIcons(maceMenu, 850, 760);
        setSizeMenuIcons(bowMenu, 900, 760);
        setSizeMenuIcons(crossbowMenu, 950, 760);
        pikeMenu.setVisible(true);
        spearMenu.setVisible(true);
        metalMenu.setVisible(true);
        leatherMenu.setVisible(true);
        swordMenu.setVisible(true);
        maceMenu.setVisible(true);
        bowMenu.setVisible(true);
        crossbowMenu.setVisible(true);
    }

    private void setAllMenusSize() {
        setSizeMenuIcons(fletcherMenu, 0, 670);
        setSizeMenuIcons(blacksmithMenu, 0, 670);
        setSizeMenuIcons(armorerMenu, 0, 670);
        setSizeMenuIcons(poleturnerMenu, 0, 670);
        setSizeMenuIcons(catheralMenu, 0, 670);
        setSizeMenuIcons(churchMenu, 0, 670);
        setSizeMenuIcons(cageMenu, 0, 670);
        setSizeMenuIcons(granaryMenu, 0, 670);
        setSizeMenuIcons(woodcutterMenu, 0, 670);
        setSizeMenuIcons(armouryMenu, 0, 670);
        setSizeMenuIcons(hovelMenu, 0, 670);
        setSizeMenuIcons(killingPitMenu, 0, 670);
        setSizeMenuIcons(stableMenu, 0, 670);
        setSizeMenuIcons(stockpileMenu, 0, 670);
        setSizeMenuIcons(appleOrchardMenu, 0, 670);
        setSizeMenuIcons(dairyFarmMenu, 0, 670);
        setSizeMenuIcons(hopsFarmMenu, 0, 670);
        setSizeMenuIcons(quarryMenu, 0, 670);
        setSizeMenuIcons(wheatFarmMenu, 0, 670);
        setSizeMenuIcons(ironMineMenu, 0, 670);
        setSizeMenuIcons(oxTetherMenu, 0, 670);
        setSizeMenuIcons(innMenu, 0, 670);
        setSizeMenuIcons(millMenu, 0, 670);
        setSizeMenuIcons(bakeryMenu, 0, 670);
        setSizeMenuIcons(breweryMenu, 0, 670);
        setSizeMenuIcons(mainMarketMenu, 0, 670);
        setSizeMenuIcons(tradeFoodMenu, 0, 670);
        setSizeMenuIcons(tradeResourcesMenu, 0, 670);
        setSizeMenuIcons(tradeWeaponsMenu, 0, 670);
        setSizeMenuIcons(allMarketMenu, 0, 670);
        setSizeMenuIcons(sellBuyMenu, 0, 670);
        setSizeMenuIcons(foodMenu, 650, 760);
        setSizeMenuIcons(sourcesMenu, 700, 760);
        setSizeMenuIcons(weaponsMenu, 750, 760);
        setSizeMenuIcons(totalMenu, 820, 760);
        setSizeMenuIcons(meatMenu, 500, 760);
        setSizeMenuIcons(cheeseMenu, 550, 760);
        setSizeMenuIcons(appleMenu, 600, 760);
        setSizeMenuIcons(hopMenu, 650, 760);
        setSizeMenuIcons(bearMenu, 700, 760);
        setSizeMenuIcons(wheatMenu, 750, 760);
        setSizeMenuIcons(flowerMenu, 800, 760);
        setSizeMenuIcons(breadMenu, 850, 760);
        setSizeMenuIcons(woodMenu, 650, 760);
        setSizeMenuIcons(rockMenu, 700, 760);
        setSizeMenuIcons(ironMenu, 750, 760);
        setSizeIcons(bowMarket, 500, 760, 30, 30);
        setSizeIcons(crossbowMarket, 550, 760, 30, 30);
        setSizeIcons(leatherMarket, 600, 760, 30, 30);
        setSizeIcons(metalMarket, 650, 760, 30, 30);
        setSizeIcons(maceMarket, 700, 760, 30, 30);
        setSizeIcons(pikeMarket, 750, 760, 30, 30);
        setSizeIcons(spearMarket, 800, 760, 30, 30);
        setSizeIcons(swordMarket, 850, 760, 30, 30);
        setSizeIcons(backMarket, 500, 790, 35, 35);
        setSizeMenuIcons(pikeMenu, 750, 760);
        setSizeMenuIcons(spearMenu, 800, 760);
        setSizeMenuIcons(metalMenu, 750, 760);
        setSizeMenuIcons(leatherMenu, 800, 760);
        setSizeMenuIcons(swordMenu, 750, 760);
        setSizeMenuIcons(maceMenu, 800, 760);
        setSizeMenuIcons(bowMenu, 750, 760);
        setSizeMenuIcons(crossbowMenu, 800, 760);
        setSizeMenuIcons(foodMenu, 750, 760);
        setSizeMenuIcons(options, 500, 250);
        setSizeMenuIcons(briefingMenu, 500, 250);
    }

    private void setAllMenus() {
        options.setVisible(false);
        armouryMenu.setVisible(false);
        fletcherMenu.setVisible(false);
        blacksmithMenu.setVisible(false);
        armorerMenu.setVisible(false);
        poleturnerMenu.setVisible(false);
        catheralMenu.setVisible(false);
        churchMenu.setVisible(false);
        hovelMenu.setVisible(false);
        cageMenu.setVisible(false);
        woodcutterMenu.setVisible(false);
        killingPitMenu.setVisible(false);
        stableMenu.setVisible(false);
        stockpileMenu.setVisible(false);
        appleOrchardMenu.setVisible(false);
        dairyFarmMenu.setVisible(false);
        hopsFarmMenu.setVisible(false);
        oxTetherMenu.setVisible(false);
        ironMineMenu.setVisible(false);
        quarryMenu.setVisible(false);
        wheatFarmMenu.setVisible(false);
        innMenu.setVisible(false);
        millMenu.setVisible(false);
        bakeryMenu.setVisible(false);
        breweryMenu.setVisible(false);
        granaryMenu.setVisible(false);
        mainMarketMenu.setVisible(false);
        tradeFoodMenu.setVisible(false);
        tradeResourcesMenu.setVisible(false);
        tradeWeaponsMenu.setVisible(false);
        allMarketMenu.setVisible(false);
        sellBuyMenu.setVisible(false);
        foodMenu.setVisible(false);
        sourcesMenu.setVisible(false);
        weaponsMenu.setVisible(false);
        totalMenu.setVisible(false);
        meatMenu.setVisible(false);
        cheeseMenu.setVisible(false);
        appleMenu.setVisible(false);
        hopMenu.setVisible(false);
        bearMenu.setVisible(false);
        wheatMenu.setVisible(false);
        flowerMenu.setVisible(false);
        breadMenu.setVisible(false);
        woodMenu.setVisible(false);
        rockMenu.setVisible(false);
        ironMenu.setVisible(false);
        bowMarket.setVisible(false);
        crossbowMarket.setVisible(false);
        leatherMarket.setVisible(false);
        metalMarket.setVisible(false);
        maceMarket.setVisible(false);
        pikeMarket.setVisible(false);
        swordMarket.setVisible(false);
        spearMarket.setVisible(false);
        backMarket.setVisible(false);
        resume.setVisible(false);
        exit.setVisible(false);
        briefingMenu.setVisible(false);
        backToGame.setVisible(false);
        redMask.setVisible(false);
        yellowMask.setVisible(false);
        greenMask.setVisible(false);

        pikeMenu.setVisible(false);
        spearMenu.setVisible(false);
        metalMenu.setVisible(false);
        leatherMenu.setVisible(false);
        swordMenu.setVisible(false);
        maceMenu.setVisible(false);
        bowMenu.setVisible(false);
        crossbowMenu.setVisible(false);
    }

    private void setPopularityMenu(Pane pane) {
        //TODO: .... popularity numbers and mask icons for each (we don't have turn yet)
//        TextField popularityAmount=new TextField(gameController.showPopularity());
        setSizeMenuIcons(popularityMenu, 0, 670);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            double menuX = mouseEvent.getX();
            double menuY = mouseEvent.getY();
            if (menuX >= 1032 && menuX <= 1089 && menuY >= 86 && menuY <= 138) {
                popularityMenu.setVisible(true);
                back.setVisible(true);
                back.toFront();

                mouseEvent.consume();
            }
        });
    }

    private void setGatehouse() {
        setSizeBuildingIcons("small stone gatehouse", smallGate, 550, 750, 70, 70);
        setSizeBuildingIcons("large stone gatehouse", largeGate, 630, 750, 70, 70);
        setSizeBuildingIcons("caged war dogs", cage, 720, 750, 50, 50);
        setSizeBuildingIcons("pits", pit, 790, 770, 40, 40);
    }

    private void setMilitaryBuildings() {
        setSizeBuildingIcons("engineers guild", engineerGuild, 550, 750, 70, 70);
        setSizeBuildingIcons("stable", stable, 630, 750, 70, 70);
        setSizeBuildingIcons("oil smelter", oilSmelter, 720, 750, 70, 70);
    }

    private void setTowers() {
        setSizeBuildingIcons("lookout tower", lookoutTower, 550, 740, 90, 70);
        setSizeBuildingIcons("defence turret", defenceTower, 610, 750, 70, 70);
        setSizeBuildingIcons("perimeter tower", perimeterTower, 680, 750, 70, 70);
        setSizeBuildingIcons("round tower", roundTower, 750, 750, 70, 70);
        setSizeBuildingIcons("square tower", squareTower, 830, 750, 70, 50);
    }

    private void setImagesIconGatehouse(boolean check) {
        back.setVisible(check);
        smallGate.setVisible(check);
        largeGate.setVisible(check);
        cage.setVisible(check);
        pit.setVisible(check);
    }

    private void setImagesIconMilitaryBuildings(boolean check) {
        back.setVisible(check);
        engineerGuild.setVisible(check);
        stable.setVisible(check);
        oilSmelter.setVisible(check);

    }

    private void setImagesIconTowers(boolean check) {
        back.setVisible(check);
        lookoutTower.setVisible(check);
        defenceTower.setVisible(check);
        perimeterTower.setVisible(check);
        roundTower.setVisible(check);
        squareTower.setVisible(check);
    }
    
    public boolean isLocationAppropriateToShow(int x, int y, Map map, int edgeLength) {
        return x - 11 * 70 / edgeLength >= 0 && y - 7 * 50 / edgeLength >= 0 && x + 16 * 50 / edgeLength <= map.getWidth()
                && y + 8 * 50 / edgeLength <= map.getLength();
    }

    private void setMenuIcon6() {
        setSizeBuildingIcons("granary", granary, 500, 750, 70, 70);
        setSizeBuildingIcons("bakery", bakery, 580, 750, 70, 70);
        setSizeBuildingIcons("brewer", brewer, 665, 750, 70, 70);
        setSizeBuildingIcons("mill", mill, 750, 750, 70, 50);
        setSizeBuildingIcons("inn", inn, 810, 750, 70, 70);
    }

    private void setSizeBuildingIcons(String name, ImageView photo, int x, int y, int height, int width) {
        photo.setLayoutX(x);
        photo.setLayoutY(y);
        photo.setFitHeight(height);
        photo.setFitWidth(width);
        photo.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = name);
        photo.toFront();
    }

    private void setImagesIcons6(boolean check) {
        granary.setVisible(check);
        bakery.setVisible(check);
        brewer.setVisible(check);
        mill.setVisible(check);
        inn.setVisible(check);
    }

    private void setMenuIcon5() {
        setSizeBuildingIcons("fletcher", fletcher, 500, 750, 70, 70);
        setSizeBuildingIcons("poleturner", poleturner, 590, 750, 70, 70);
        setSizeBuildingIcons("blacksmith", blacksmith, 670, 750, 70, 70);
        setSizeBuildingIcons("armourer", armourer, 750, 750, 70, 70);
    }

    private void setImagesIcons5(boolean check) {
        poleturner.setVisible(check);
        fletcher.setVisible(check);
        blacksmith.setVisible(check);
        armourer.setVisible(check);
    }

    private void setMenuIcon4() {
        setSizeBuildingIcons("hovel", hovel, 500, 750, 70, 70);
        setSizeBuildingIcons("church", church, 580, 750, 70, 70);
        setSizeBuildingIcons("catheral", catheral, 660, 750, 80, 80);
    }

    private void setImagesIcons4(boolean check) {
        hovel.setVisible(check);
        church.setVisible(check);
        catheral.setVisible(check);
    }

    private void setMenuIcon3() {
        setSizeBuildingIcons("apple orchard", appleOrchard, 500, 750, 70, 70);
        setSizeBuildingIcons("dairy farmer", dairyFarmer, 580, 750, 70, 70);
        setSizeBuildingIcons("hops farmer", hopsFarmer, 670, 750, 70, 70);
        setSizeBuildingIcons("wheat farmer", wheatFarmer, 750, 750, 70, 70);
    }

    private void setImagesIcons3(boolean check) {
        appleOrchard.setVisible(check);
        dairyFarmer.setVisible(check);
        hopsFarmer.setVisible(check);
        wheatFarmer.setVisible(check);
    }

    private void setMenuIcon2() {
        setSizeBuildingIcons("stockpile", stockpile, 500, 750, 70, 70);
        setSizeBuildingIcons("woodcutter", woodCutter, 580, 750, 70, 70);
        setSizeBuildingIcons("quarry", quarry, 690, 750, 70, 70);
        setSizeBuildingIcons("ox tether", oxTether, 750, 740, 50, 50);
        setSizeBuildingIcons("iron mine", ironMine, 800, 750, 70, 70);
        setSizeBuildingIcons("market", market, 880, 750, 70, 70);
    }

    private void setMenuIcon1() {
        setSizeBuildingIcons("barracks", barracks, 500, 750, 70, 70);
        setSizeBuildingIcons("mercenary post", mercenary, 580, 750, 70, 70);
        setSizeBuildingIcons("armoury", armoury, 640, 750, 90, 100);
        setSizeBuildingIcons("stairs", stairs, 750, 760, 60, 20);
        setSizeBuildingIcons("short wall", shortWall, 800, 770, 50, 20);
        setSizeBuildingIcons("high wall", highWall, 850, 750, 70, 20);

        setSizeIcons(towers, 900, 740, 35, 35);
        setSizeIcons(militaryBuildings, 940, 740, 35, 35);
        setSizeIcons(gatehouse, 900, 780, 35, 35);
    }

    private void setSizeIcons(ImageView photo, int x, int y, int height, int width) {
        photo.setLayoutX(x);
        photo.setLayoutY(y);
        photo.setFitHeight(height);
        photo.setFitWidth(width);
        photo.toFront();
    }

    private void setImagesIcons2(boolean check) {
        stockpile.setVisible(check);
        woodCutter.setVisible(check);
        quarry.setVisible(check);
        oxTether.setVisible(check);
        ironMine.setVisible(check);
        market.setVisible(check);
    }

    private void setImagesIcons1(boolean check) {
        barracks.setVisible(check);
        mercenary.setVisible(check);
        armoury.setVisible(check);
        stairs.setVisible(check);
        highWall.setVisible(check);
        shortWall.setVisible(check);
        towers.setVisible(check);
        militaryBuildings.setVisible(check);
        gatehouse.setVisible(check);

    }

    private void setEuropeanUnitsSize() {
        setSizeMenuIcons(europeanUnitsMenu, 0, 670);
        setSizeUnits(archer, "Archer", 502, 750);
        setSizeUnits(crossbowmen, "Crossbowmen", 572, 750);
        setSizeUnits(knight, "Knight", 640, 750);
        setSizeUnits(macemen, "Macemen", 710, 750);
        setSizeUnits(pikemen, "Pikemen", 780, 750);
        setSizeUnits(spearmen, "Spearmen", 840, 750);
        setSizeUnits(swordsmen, "Swordsmen", 910, 760);
    }

    private void setEuropeanUnits(boolean check) {
        europeanUnitsMenu.setVisible(check);
        archer.setVisible(check);
        crossbowmen.setVisible(check);
        knight.setVisible(check);
        macemen.setVisible(check);
        pikemen.setVisible(check);
        spearmen.setVisible(check);
        swordsmen.setVisible(check);
    }

    private void addWeapons() {
        setSizeIcons(bow, 502, 830, 40, 50);
        setSizeIcons(crossbow, 552, 830, 40, 50);
        setSizeIcons(leatherArmour, 602, 830, 40, 50);
        setSizeIcons(mace, 652, 830, 40, 50);
        setSizeIcons(metalArmour, 702, 830, 40, 50);
        setSizeIcons(pike, 752, 830, 40, 50);
        setSizeIcons(spear, 802, 830, 40, 50);
        setSizeIcons(sword, 852, 830, 40, 50);
        setSizeIcons(horse, 902, 830, 40, 50);
    }

    private void setWeapons(boolean check) {
        bow.setVisible(check);
        crossbow.setVisible(check);
        leatherArmour.setVisible(check);
        mace.setVisible(check);
        metalArmour.setVisible(check);
        pike.setVisible(check);
        spear.setVisible(check);
        sword.setVisible(check);
        horse.setVisible(check);
    }

    private void setSizeUnits(ImageView unit, String name, int x, int y) {
        hasGivenHandler.put(name, 0);
        unit.setLayoutX(x);
        unit.setLayoutY(y);
        unit.toFront();
        unit.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (hasGivenHandler.get(name) == 0) {
                Cell[][] cells = GameController.currentGame.getMap().getCells();
                if (toCreateUnitImageView == null) {
                    String address = "/images/Units/" + name + ".png";
                    toCreateUnitImageView = new ImageView(new Image(String.valueOf(getClass().getResource(address))));
                }
                String result = gameController.createUnitGraphics(name);
                if (result.equals("Unit created successfully!")) {
                    int x2 = (int) (selectedBuilding.getLocation().getX() - shownX + (float) 11 * 70 / edgeLength) * edgeLength;
                    int y2 = (int) (selectedBuilding.getLocation().getY() - shownY + (float) 5 * 70 / edgeLength) * edgeLength;
                    toCreateUnitImageView.setFitHeight(20);
                    toCreateUnitImageView.setFitWidth(20);
                    toCreateUnitImageView.setLayoutX(x2);
                    toCreateUnitImageView.setLayoutY(y2);
                    gameController.dropUnit(x2 / edgeLength, y2 / edgeLength, cells[x2 / edgeLength][y2 / edgeLength]
                            , name, "1");
                    toCreateUnitImageView.toFront();
                    pane.getChildren().add(toCreateUnitImageView);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Create Unit Error");
                    alert.setContentText(result);
                    alert.show();
                }
                toCreateUnitImageView = null;
                hasGivenHandler.put(name, 1);
            }
        });
    }

    private void setArabianUnitsSize() {
        setSizeMenuIcons(arabianUnitsMenu, 0, 670);
        setSizeUnits(archerBow, "ArcherBow", 502, 750);
        setSizeUnits(assassin, "Assassins", 572, 750);
        setSizeUnits(horseArcher, "HorseArchers", 640, 750);
        setSizeUnits(arabianSwordsmen, "ArabianSwordsmen", 710, 750);
        setSizeUnits(fireThrowers, "FireThrowers", 780, 760);
        setSizeUnits(slave, "Slaves", 840, 740);
        setSizeUnits(slinger, "Slingers", 910, 750);
    }

    private void setArabianUnits(boolean check) {
        arabianUnitsMenu.setVisible(check);
        archerBow.setVisible(check);
        assassin.setVisible(check);
        horseArcher.setVisible(check);
        arabianSwordsmen.setVisible(check);
        fireThrowers.setVisible(check);
        slave.setVisible(check);
        slinger.setVisible(check);
    }

    private void setEngineerSize() {
        setSizeMenuIcons(enginnerUnitsMenu, 0, 670);
        setSizeUnits(engineer, "Engineer", 640, 750);
        setSizeUnits(ladderman, "Laddermen", 700, 750);
        setSizeUnits(tunneler, "Tunneler", 760, 750);
    }

    private void setEngineer(boolean check) {
        enginnerUnitsMenu.setVisible(check);
        engineer.setVisible(check);
        ladderman.setVisible(check);
        tunneler.setVisible(check);
    }

    private void showNaturalBlock(Pane pane, int i, int j, NaturalBlock naturalBlock) {
//        ImageView imageView = new ImageView();
//        imageView.setFitHeight(30);
//        imageView.setFitWidth(30);
//        imageView.setX();
//        imageView.setY();
//        String address = "images/" + naturalBlock + ".jpg";
//        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(address)).toExternalForm()));
//        pane.getChildren().add(imageView);
    }

    private void showPeople(Pane pane, Map map, int i, int j) {
        Cell cell = map.getCells()[i][j];
        if (cell.getPeople().size() == 0) return;
        for (Person person : cell.getPeople()) {
            String imageAddress;
            if (person instanceof MilitaryPerson) {
                imageAddress = "/images/Units/" + person.getType() + ".png";
                Label unitLabel = new Label();
                unitLabel.setBackground(new Background(MainController.setFirstPageBackground(imageAddress)));
                unitLabel.setPrefHeight(20);
                unitLabel.setPrefWidth(20);
                unitLabel.setLayoutX((int) (i - shownX + (float) 11 * 70 / edgeLength) * edgeLength);
                unitLabel.setLayoutY((int) (j - shownY + (float) 5 * 70 / edgeLength) * edgeLength);
                
                String s = "King: " + person.getKing().getUsername() + "\nType: " + person.getType()
                        + "\nMode: " + ((MilitaryPerson) person).getMode();
                
                Tooltip tooltip = new Tooltip(s);
                tooltip.setStyle("-fx-font-size: 15px;");
                unitLabel.setTooltip(tooltip);
                
                unitLabel.toFront();
                pane.getChildren().add(unitLabel);
            } else {
                //TODO: workers...
            }
        }
    }


    private void showBackgrounds(Pane pane, String material, int x, int y, int edgeLength) {
        Label pictureLabel = new Label();
        pictureLabel.setPrefWidth(edgeLength);
        pictureLabel.setPrefHeight(edgeLength);
        pictureLabel.setLayoutX(edgeLength * x);
        pictureLabel.setLayoutY(edgeLength * y);
        pictureLabel.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0.2px");

        Cell cell = map.getCells()[getXLocationByPixel(x)][getYLocationByPixel(y)];
        Tooltip tooltip = getTooltipForACell(cell);
        pictureLabel.setTooltip(tooltip);

        String address = "/images/" + material + ".jpg";
        if (material.equals("castle")) address = "/images/castle.png";
        Background background = new Background(MainController.setFirstPageBackground(address));
        pictureLabel.setBackground(background);
        pane.getChildren().add(pictureLabel);
        pane.setStyle("-fx-spacing: 0");
    }

    public Tooltip getTooltipForACell(Cell cell) {
        Tooltip tooltip = new Tooltip();
        StringBuilder tooltipText = new StringBuilder();
        if (cell.getBuilding() != null)
            tooltipText.append("Building: ").append(cell.getBuilding().getType()).append("\n");
        tooltipText.append("Texture: ").append(cell.getMaterial()).append("\n");
        if (cell.getNaturalBlocks().size() != 0) tooltipText.append("Natural Blocks: ");
        for (NaturalBlock naturalBlock : cell.getNaturalBlocks()) {
            tooltipText.append(naturalBlock.getName()).append(", ");
        }
        if (cell.getNaturalBlocks().size() != 0) tooltipText.append("\n");
        tooltipText.append(unitsInformation(cell));
        tooltip.setText(tooltipText.toString());
        tooltip.setStyle("-fx-font-size: 15px;");
        tooltip.setShowDelay(new Duration(500));

//        if (cell.getPeople().size() != 0) tooltipText.append("People: ");
//        for (Person person : cell.getPeople()) {
//            tooltipText.append(person.getType()).append(", ");
//        }
//        if (cell.getPeople().size() != 0) tooltipText.append("\n");
        return tooltip;
    }

    private String unitsInformation(Cell cell) {
        StringBuilder result = new StringBuilder();
        HashMap<String, Integer> numberOfPeople = new HashMap<>();
        if (cell.getPeople().size() != 0) result.append("People:\n");
        for (Person person : cell.getPeople()) {
            numberOfPeople.put(person.getType(), 0);
        }
        for (Person person : cell.getPeople()) {
            numberOfPeople.put(person.getType(), numberOfPeople.get(person.getType()) + 1);
        }
        for (java.util.Map.Entry<String, Integer> entry : numberOfPeople.entrySet()) {
            MilitaryPerson unit = FileController.getMilitaryPersonByType(entry.getKey());
            assert unit != null;
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(", Speed: ").append(unit.getSpeed())
                    .append(", Fire power: ").append(unit.getFirePower()).append(", Defend power: ").append(unit.getDefendPower())
                    .append(", Mode: ").append(unit.getMode()).append(", Moving range: ").append(unit.getMovingRange())
                    .append(", Shooting range: ").append(unit.getShootingRange()).append("\n");
        }
        return result.toString();
    }

    private void showBuilding(Pane pane, int i, int j, Building building) {
        if (building == null) return;
//        System.out.println("show building x=" + i + " y=" + j);
        String imageAddress;
        if (building.getType().equals("castle")) imageAddress = "/images/castle.png";
        else imageAddress = "/images/Buildings/" + FileController.getBuildingCategoryByType(building.getType()) +
                "/" + building.getType() + ".png";

        Label buildingLabel = new Label();
        buildingLabel.setBackground(new Background(MainController.setFirstPageBackground(imageAddress)));
        buildingLabel.setPrefHeight(edgeLength - 5);
        buildingLabel.setPrefWidth(edgeLength - 5);
        buildingLabel.setLayoutX((int) (i - shownX + (float) 11 * 70 / edgeLength) * edgeLength);
        buildingLabel.setLayoutY((int) (j - shownY + (float) 5 * 70 / edgeLength) * edgeLength);
        
        String s = "King: " + building.getKing().getUsername() + "\nType: " + building.getType()
                + "\nCategory: " + building.getCategory() + "\nHitPoint: " + building.getHitPoint()
                + "\nNumber of workers: " + building.getWorkerCounter();
        Tooltip tooltip = new Tooltip(s);
        tooltip.setStyle("-fx-font-size: 15px;");
        buildingLabel.setTooltip(tooltip);

        EventHandler<MouseEvent> selectBuildingEventHandler = mouseEvent -> {
            selectedBuilding = map.getCells()[i][j].getBuilding();
            setBuildingMenu(pane);
        };
        buildingLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, selectBuildingEventHandler);
        buildingLabel.toFront();
        pane.getChildren().add(buildingLabel);
    }

    public int getXLocationByPixel(double x) {
        return (int) (x + shownX - (float) 11 * 70 / edgeLength);
    }

    public int getYLocationByPixel(double y) {
        return (int) (y + shownY - (float) 5 * 70 / edgeLength);
    }

}
