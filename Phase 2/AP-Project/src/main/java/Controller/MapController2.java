package Controller;

import Model.*;
import View.GameGraphics;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static Controller.MapController.*;

import java.util.Timer;
import java.util.TimerTask;

public class MapController2 {
    public static String clickedBuildingToDrop = null;
    private static int stableCount = 0;
    private static int horseCount = 0;
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
    private final ImageView foodMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/food.png"))));
    private final ImageView sourcesMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/sources.png"))));
    private final ImageView weaponsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/weapns.png"))));
    private final ImageView totalMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/t.png"))));
    private final ImageView tradeFoodMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/trade food.png"))));
    private final ImageView tradeResourcesMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/trade weapons.png"))));
    private final ImageView tradeWeaponsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/trade materials.png"))));
    private final ImageView allMarketMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/all.png"))));
    private final ImageView sellBuyMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/market/buy sell panel.png"))));

    private final ImageView bowMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/bow.png"))));
    private final ImageView crossbowMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/crossbow.png"))));
    private final ImageView leatherMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/leather.png"))));
    private final ImageView maceMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/mace.png"))));
    private final ImageView metalMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/metal.png"))));
    private final ImageView pikeMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/pike.png"))));
    private final ImageView spearMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/spear.png"))));
    private final ImageView swordMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/weapons/sword.png"))));
    private final ImageView monk = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/monk.png"))));
    private final ImageView ArabianUnitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
    private final ImageView archerBow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/ArcherBow.png"))));
    private final ImageView assassin = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/Assassins.png"))));
    private final ImageView horseArcher = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/HorseArchers.png"))));
    private final ImageView arabianSwordsmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/ArabianSwordsmen.png"))));
    private final ImageView fireThrowers = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/FireThrowers.png"))));
    private final ImageView slave = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/Slaves.png"))));
    private final ImageView slinger = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/Slingers.png"))));
    private final ImageView enginnerUnitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
    private final ImageView engineer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Engineer/Engineer.png"))));
    private final ImageView ladderman = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Engineer/Laddermen.png"))));
    private final ImageView tunneler = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Engineer/Tunneler.png"))));
    private final ImageView europeanUnitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/unitMenu.png"))));
    private final ImageView archer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Archer.png"))));
    private final ImageView crossbowmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Crossbowmen.png"))));
    private final ImageView knight = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Knight.png"))));
    private final ImageView macemen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Macemen.png"))));
    private final ImageView pikemen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Pikemen.png"))));
    private final ImageView spearmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Spearmen.png"))));
    private final ImageView swordsmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Swordsmen.png"))));
    private final ImageView bow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/bow.png"))));
    private final ImageView crossbow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/crossbow.png"))));
    private final ImageView leatherArmour = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/leatherArmour.png"))));
    private final ImageView mace = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/mace.png"))));
    private final ImageView metalArmour = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/metalarmour.png"))));
    private final ImageView pike = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/pike.png"))));
    private final ImageView spear = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/spear.png"))));
    private final ImageView sword = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/sword.png"))));
    private final ImageView horse = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/horse.png"))));
    Text foodNumber = new Text();
    private GameController gameController;
    private boolean isTheFirstTime = false;
    private int edgeLength = 70;
    private int shownX;
    private int shownY;
    private Map map;
<<<<<<< HEAD
    
=======
    private Button first = new Button("-2");
    private Button second = new Button("-1");
    private Button third = new Button("0");
    private Button forth = new Button("1");
    private Button fifth = new Button("2");

>>>>>>> Melika
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
<<<<<<< HEAD

    public String loadMapToShow(Scene scene, Stage stage, Pane pane, Map map, int x, int y, int edgeLength) {
=======
    
    public String loadMapToShow(Stage stage, Pane pane, Map map, int x, int y, int edgeLength) {
>>>>>>> 56f6f70ffe8a922f27f7745ad028bba71aaeef49
        gameController = new GameController();
        
        if (map == null) System.out.println("map is null");
        
        //capacity: 31 x 16 (x50 pixels)
        if (edgeLength < 40 || edgeLength >= 100 || !isLocationAppropriateToShow(x, y, map, edgeLength)) {
//            System.out.println("returned");
            return "bad location";
        }

        System.out.println("loading");
        
        this.edgeLength = edgeLength;
        this.shownX = x;
        this.shownY = y;
        this.map = map;
        
        int xCounter = 0, yCounter = 0;
        
        for (int i = x - 11 * 70 / edgeLength; i < x + 12 * 70 / edgeLength + 1; i++) {
            for (int j = y - 5 * 70 / edgeLength; j < y + 7 * 70 / edgeLength + 2; j++) {
                if (i >= map.getLength() || j >= map.getWidth()) continue;
                Cell cell = map.getCells()[i][j];
                
                showBackgrounds(pane, cell.getMaterial(), xCounter, yCounter, edgeLength);
                
                for (NaturalBlock naturalBlock : cell.getNaturalBlocks()) {
                    showNaturalBlock(pane, i, j, naturalBlock);
                }
                
                showBuilding(pane, i, j, cell.getBuilding());
                
                for (Person person : cell.getPeople()) {
                    showPerson(pane, map, i, j, person);
                }
                yCounter++;
            }
            xCounter++;
            yCounter = 0;
        }
<<<<<<< HEAD

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

=======
        
>>>>>>> 56f6f70ffe8a922f27f7745ad028bba71aaeef49
        setSizeUnits(imageView, 0, 670);
        setSizeIcons(imageIcon1, 500, 815, 50, 50);
        setSizeIcons(back, 500, 790, 35, 35);
        
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
<<<<<<< HEAD

        Button nextTurn = new Button("Next Turn");
        nextTurn.setStyle("-fx-background-color:#FC9303;-fx-text-fill: black;-fx-border-color: black;");
        nextTurn.setLayoutX(1460);
        nextTurn.setLayoutY(700);
        nextTurn.toFront();
        pane.getChildren().add(nextTurn);
        nextTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            gameController.nextTurn();
            event.consume();
        });

        setArabianUnitsSize();
        setEngineerSize();
        setAllMenusSize();
        setSizeUnits(monk, 750, 720);
        setEuropeanUnitsSize();
        addWeapons();
        makeHorse();
        addGranaryButton();

=======
        
>>>>>>> 56f6f70ffe8a922f27f7745ad028bba71aaeef49
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
            pane.getChildren().addAll(imageView, imageIcon1, imageIcon2, imageIcon3, imageIcon4, imageIcon5, imageIcon6
                    , barracks, mercenary, armoury, stairs, shortWall, highWall
                    , towers, militaryBuildings, gatehouse, back
                    , smallGate, largeGate, cage, pit, engineerGuild, stable, oilSmelter
                    , lookoutTower, defenceTower, perimeterTower, roundTower, squareTower
                    , stockpile, woodCutter, quarry, ironMine, oxTether, market
                    , appleOrchard, wheatFarmer, hopsFarmer, dairyFarmer
                    , hovel, church, catheral, catheralMenu, monk
                    , poleturner, armourer, blacksmith, fletcher
                    , granary, bakery, brewer, mill, inn, popularityMenu
                    , ArabianUnitsMenu, archerBow, assassin, arabianSwordsmen, fireThrowers, horseArcher, slave, slinger
                    , europeanUnitsMenu, archer, crossbowmen, knight, macemen, pikemen, spearmen, swordsmen
                    , bow, crossbow, leatherArmour, mace, metalArmour, pike, spear, sword, horse
                    , fletcherMenu, blacksmithMenu, armorerMenu, poleturnerMenu
                    , maceMenu, swordMenu, bowMenu, crossbowMenu, metalMenu, leatherMenu, pikeMenu, spearMenu
                    , churchMenu, armouryMenu, hovelMenu, woodcutterMenu, cageMenu, killingPitMenu
                    , stableMenu, stockpileMenu, appleOrchardMenu, dairyFarmMenu, hopsFarmMenu, wheatFarmMenu
                    , quarryMenu, ironMineMenu, oxTetherMenu, innMenu, millMenu, bakeryMenu, breweryMenu, granaryMenu
                    , first, second, third, forth, fifth, foodNumber);
            isTheFirstTime = true;
        }
        
        
        stage.show();
        return "success";
    }
<<<<<<< HEAD
    
=======

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

>>>>>>> Melika
    private void setBuildingMenu(Pane pane) {
        ImageView towerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/towerMenu.png"))));
        ImageView gatehouseMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/gatehouseMenu.png"))));
        setSizeUnits(towerMenu, 0, 670);
        setSizeUnits(gatehouseMenu, 0, 670);
        Text text = new Text(GameGraphics.selectedBuilding.getType());
        String category = FileController.getBuildingCategoryByType(GameGraphics.selectedBuilding.getType());
        Building savedBuilding = gameController.getBuilding(GameGraphics.selectedBuilding.getType(), category);
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(GameGraphics.selectedBuilding.getHitPoint() / savedBuilding.getHitPoint());
        progressBar.setStyle("-fx-accent: green");
        progressBar.setLayoutX(890);
        progressBar.setLayoutY(760);
        Button repair = new Button("repair");
        repair.setLayoutX(820);
        repair.setLayoutY(760);
        repair.setStyle("-fx-background-color:#AB863F;-fx-text-fill: white;");
        if (GameGraphics.selectedBuilding.getType().equals("lookout tower") || GameGraphics.selectedBuilding.getType().equals("perimeter tower")
                || GameGraphics.selectedBuilding.getType().equals("square tower") || GameGraphics.selectedBuilding.getType().equals("defence turret")
                || GameGraphics.selectedBuilding.getType().equals("round tower")) {
            text.toFront();
            text.setLayoutX(650);
            text.setLayoutY(780);
            text.setFont(Font.font(20));
            pane.getChildren().addAll(towerMenu, progressBar, repair, text);
        } else if (GameGraphics.selectedBuilding.getType().equals("small stone gatehouse") || GameGraphics.selectedBuilding.getType().equals("large stone gatehouse"))
            pane.getChildren().addAll(gatehouseMenu, progressBar, repair);
        else if (GameGraphics.selectedBuilding.getType().equals("mercenary post")) setArabianUnits(true);
        else if (GameGraphics.selectedBuilding.getType().equals("barracks")) {
            setEuropeanUnits(true);
            setWeapons(true);
        } else if (GameGraphics.selectedBuilding.getType().equals("engineers guild")) setEngineer(true);
        else if (GameGraphics.selectedBuilding.getType().equals("church")) churchMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("catheral")) {
            catheralMenu.setVisible(true);
            monk.setVisible(true);
        } else if (GameGraphics.selectedBuilding.getType().equals("armoury")) setArmoury();
        else if (GameGraphics.selectedBuilding.getType().equals("hovel")) hovelMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("market")) setMarketMenu();
        else if (GameGraphics.selectedBuilding.getType().equals("granary")) setGranary();
        else if (GameGraphics.selectedBuilding.getType().equals("woodcutter")) woodcutterMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("caged war dogs")) cageMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("pits")) killingPitMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("stable")) setStable();
        else if (GameGraphics.selectedBuilding.getType().equals("stockpile")) stockpileMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("apple orchard")) appleOrchardMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("dairy farmer")) dairyFarmMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("hops farmer")) hopsFarmMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("wheat farmer")) wheatFarmMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("quarry")) quarryMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("iron mine")) ironMineMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("ox tether")) oxTetherMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("inn")) innMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("mill")) millMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("bakery")) bakeryMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("brewer")) breweryMenu.setVisible(true);
        else if (GameGraphics.selectedBuilding.getType().equals("poleturner")) {
            poleturnerMenu.setVisible(true);
            pikeMenu.setVisible(true);
            spearMenu.setVisible(true);
        } else if (GameGraphics.selectedBuilding.getType().equals("armourer")) {
            armorerMenu.setVisible(true);
            metalMenu.setVisible(true);
            leatherMenu.setVisible(true);
        } else if (GameGraphics.selectedBuilding.getType().equals("blacksmith")) {
            blacksmithMenu.setVisible(true);
            maceMenu.setVisible(true);
            swordMenu.setVisible(true);
        } else if (GameGraphics.selectedBuilding.getType().equals("fletcher")) {
            fletcherMenu.setVisible(true);
            bowMenu.setVisible(true);
            crossbowMenu.setVisible(true);
        }
        repair.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            gameController.repair(savedBuilding, GameGraphics.selectedBuilding);
        });
    }
<<<<<<< HEAD

    private void setMarketMenu() {
        mainMarketMenu.setVisible(true);

        tradeFoodMenu.setVisible(true);
        tradeResourcesMenu.setVisible(true);
        tradeWeaponsMenu.setVisible(true);
        allMarketMenu.setVisible(true);
        sellBuyMenu.setVisible(true);
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
        setSizeUnits(pikeMenu, 600, 760);
        setSizeUnits(spearMenu, 650, 760);
        setSizeUnits(metalMenu, 700, 760);
        setSizeUnits(leatherMenu, 750, 760);
        setSizeUnits(swordMenu, 800, 760);
        setSizeUnits(maceMenu, 850, 760);
        setSizeUnits(bowMenu, 900, 760);
        setSizeUnits(crossbowMenu, 950, 760);
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
        setSizeUnits(fletcherMenu, 0, 670);
        setSizeUnits(blacksmithMenu, 0, 670);
        setSizeUnits(armorerMenu, 0, 670);
        setSizeUnits(poleturnerMenu, 0, 670);
        setSizeUnits(catheralMenu, 0, 670);
        setSizeUnits(churchMenu, 0, 670);
        setSizeUnits(cageMenu, 0, 670);
        setSizeUnits(granaryMenu, 0, 670);
        setSizeUnits(woodcutterMenu, 0, 670);
        setSizeUnits(armouryMenu, 0, 670);
        setSizeUnits(hovelMenu, 0, 670);
        setSizeUnits(killingPitMenu, 0, 670);
        setSizeUnits(stableMenu, 0, 670);
        setSizeUnits(stockpileMenu, 0, 670);
        setSizeUnits(appleOrchardMenu, 0, 670);
        setSizeUnits(dairyFarmMenu, 0, 670);
        setSizeUnits(hopsFarmMenu, 0, 670);
        setSizeUnits(quarryMenu, 0, 670);
        setSizeUnits(wheatFarmMenu, 0, 670);
        setSizeUnits(ironMineMenu, 0, 670);
        setSizeUnits(oxTetherMenu, 0, 670);
        setSizeUnits(innMenu, 0, 670);
        setSizeUnits(millMenu, 0, 670);
        setSizeUnits(bakeryMenu, 0, 670);
        setSizeUnits(breweryMenu, 0, 670);
        setSizeUnits(mainMarketMenu, 0, 670);
        setSizeUnits(tradeFoodMenu, 0, 670);
        setSizeUnits(tradeResourcesMenu, 0, 670);
        setSizeUnits(tradeWeaponsMenu, 0, 670);
        setSizeUnits(allMarketMenu, 0, 670);
        setSizeUnits(sellBuyMenu, 0, 670);

        setSizeUnits(pikeMenu, 750, 760);
        setSizeUnits(spearMenu, 800, 760);
        setSizeUnits(metalMenu, 750, 760);
        setSizeUnits(leatherMenu, 800, 760);
        setSizeUnits(swordMenu, 750, 760);
        setSizeUnits(maceMenu, 800, 760);
        setSizeUnits(bowMenu, 750, 760);
        setSizeUnits(crossbowMenu, 800, 760);

        setSizeUnits(foodMenu,750,760);
    }

    private void setAllMenus() {
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

        pikeMenu.setVisible(false);
        spearMenu.setVisible(false);
        metalMenu.setVisible(false);
        leatherMenu.setVisible(false);
        swordMenu.setVisible(false);
        maceMenu.setVisible(false);
        bowMenu.setVisible(false);
        crossbowMenu.setVisible(false);
    }

=======
    
>>>>>>> 56f6f70ffe8a922f27f7745ad028bba71aaeef49
    private void setPopularityMenu(Pane pane) {
        //TODO: .... popularity numbers and mask icons for each (we don't have turn yet)
//        TextField popularityAmount=new TextField(gameController.showPopularity());
        setSizeUnits(popularityMenu, 0, 670);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            double menuX = mouseEvent.getX();
            double menuY = mouseEvent.getY();
            System.out.println(menuX);
            System.out.println(menuY);
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
    
    private boolean isLocationAppropriateToShow(int x, int y, Map map, int edgeLength) {
        return x - 11 * 70 / edgeLength >= 0 && y - 7 * 50 / edgeLength >= 0 && x + 16 * 50 / edgeLength <= map.getWidth() && y + 8 * 50 / edgeLength <= map.getLength();
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
<<<<<<< HEAD
    
    private void setEuropeanUnits(Pane pane) {
        ImageView unitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/unitMenu.png"))));
        ImageView archer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Archer.png"))));
        ImageView crossbowmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Crossbowmen.png"))));
        ImageView knight = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Knight.png"))));
        ImageView macemen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Macemen.png"))));
        ImageView pikemen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Pikemen.png"))));
        ImageView spearmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Spearmen.png"))));
        ImageView swordsmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/European/Swordsmen.png"))));
        setSizeUnits(unitsMenu, 0, 670);
=======

    private void setEuropeanUnitsSize() {
        setSizeUnits(europeanUnitsMenu, 0, 670);
>>>>>>> Melika
        setSizeUnits(archer, 502, 750);
        setSizeUnits(crossbowmen, 572, 750);
        setSizeUnits(knight, 640, 750);
        setSizeUnits(macemen, 710, 750);
        setSizeUnits(pikemen, 780, 750);
        setSizeUnits(spearmen, 840, 750);
        setSizeUnits(swordsmen, 910, 760);
<<<<<<< HEAD
        
        pane.getChildren().addAll(unitsMenu, archer, crossbowmen, knight, macemen, pikemen, spearmen, swordsmen);
        addWeapons(pane);
    }
    
    private void addWeapons(Pane pane) {
        ImageView bow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/bow.png"))));
        ImageView crossbow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/crossbow.png"))));
        ImageView leatherArmour = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/leatherArmour.png"))));
        ImageView mace = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/mace.png"))));
        ImageView metalArmour = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/metalarmour.png"))));
        ImageView pike = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/pike.png"))));
        ImageView spear = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/spear.png"))));
        ImageView sword = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/sword.png"))));
        ImageView horse = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/weapons/horse.png"))));
=======
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
>>>>>>> Melika
        setSizeIcons(bow, 502, 830, 40, 50);
        setSizeIcons(crossbow, 552, 830, 40, 50);
        setSizeIcons(leatherArmour, 602, 830, 40, 50);
        setSizeIcons(mace, 652, 830, 40, 50);
        setSizeIcons(metalArmour, 702, 830, 40, 50);
        setSizeIcons(pike, 752, 830, 40, 50);
        setSizeIcons(spear, 802, 830, 40, 50);
        setSizeIcons(sword, 852, 830, 40, 50);
<<<<<<< HEAD
        setSizeIcons(horse, 902, 830, 40, 50);
    }

<<<<<<< HEAD
        pane.getChildren().addAll(bow, crossbow, leatherArmour, mace, metalArmour, pike, spear, sword, horse);
=======
        
        pane.getChildren().addAll(bow, crossbow, leatherArmour, mace, metalArmour, pike, spear, sword);
>>>>>>> 56f6f70ffe8a922f27f7745ad028bba71aaeef49
=======
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
>>>>>>> Melika
    }
    
    private void setSizeUnits(ImageView unit, int x, int y) {
        unit.setLayoutX(x);
        unit.setLayoutY(y);
        unit.toFront();
    }
<<<<<<< HEAD

    private void setArabianUnitsSize() {
        setSizeUnits(ArabianUnitsMenu, 0, 670);
=======
    
    private void setArabianUnits(Pane pane) {
        ImageView unitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
        ImageView archerBow = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/ArcherBow.png"))));
        ImageView assassin = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/Assassins.png"))));
        ImageView horseArcher = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/HorseArchers.png"))));
        ImageView arabianSwordsmen = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/ArabianSwordsmen.png"))));
        ImageView fireThrowers = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/FireThrowers.png"))));
        ImageView slave = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/Slaves.png"))));
        ImageView slinger = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Arabian/Slingers.png"))));
        setSizeUnits(unitsMenu, 0, 670);
>>>>>>> 56f6f70ffe8a922f27f7745ad028bba71aaeef49
        setSizeUnits(archerBow, 502, 750);
        setSizeUnits(assassin, 572, 750);
        setSizeUnits(horseArcher, 640, 750);
        setSizeUnits(arabianSwordsmen, 710, 750);
        setSizeUnits(fireThrowers, 780, 760);
        setSizeUnits(slave, 840, 740);
        setSizeUnits(slinger, 910, 750);
<<<<<<< HEAD
    }
    private void setArabianUnits(boolean check) {
        ArabianUnitsMenu.setVisible(check);
        archerBow.setVisible(check);
        assassin.setVisible(check);
        horseArcher.setVisible(check);
        arabianSwordsmen.setVisible(check);
        fireThrowers.setVisible(check);
        slave.setVisible(check);
        slinger.setVisible(check);
=======
        
        pane.getChildren().addAll(unitsMenu, archerBow, assassin, arabianSwordsmen, fireThrowers, horseArcher, slave, slinger);
>>>>>>> 56f6f70ffe8a922f27f7745ad028bba71aaeef49
    }
<<<<<<< HEAD
    
    private void setEngineer(Pane pane) {
        ImageView unitsMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
        ImageView engineer = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Engineer/Engineer.png"))));
        ImageView ladderman = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Engineer/Laddermen.png"))));
        ImageView tunneler = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Units/Engineer/Tunneler.png"))));
        setSizeUnits(unitsMenu, 0, 670);
        setSizeUnits(engineer, 640, 750);
        setSizeUnits(ladderman, 700, 750);
        setSizeUnits(tunneler, 760, 750);
        
        pane.getChildren().addAll(unitsMenu, engineer, tunneler, ladderman);
=======

    private void setEngineerSize() {
        setSizeUnits(enginnerUnitsMenu, 0, 670);
        setSizeUnits(engineer, 640, 750);
        setSizeUnits(ladderman, 700, 750);
        setSizeUnits(tunneler, 760, 750);
    }

    private void setEngineer(boolean check) {
        enginnerUnitsMenu.setVisible(check);
        engineer.setVisible(check);
        ladderman.setVisible(check);
        tunneler.setVisible(check);
>>>>>>> Melika
    }
    
    private void showNaturalBlock(Pane pane, int i, int j, NaturalBlock naturalBlock) {
//        ImageView imageView = new ImageView();
//        imageView.setFitHeight(30);
//        imageView.setFitWidth(30);
//        imageView.setX(i);
//        imageView.setY(100*j);
//        String address = "images/" + naturalBlock + ".jpg";
//        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(address)).toExternalForm()));
//        pane.getChildren().add(imageView);
    }
    
    private void showPerson(Pane pane, Map map, int i, int j, Person person) {
        //TODO
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
        Background background = new Background(MainController.setFirstPageBackground(address));
        pictureLabel.setBackground(background);
        EventHandler<MouseEvent> selectCell = mouseEvent -> {
//            if (!isLocationAppropriateToShow(getXLocationByPixel(x), getYLocationByPixel(y), map, edgeLength)) return;
            GameGraphics.selectedCell = map.getCells()[getXLocationByPixel(x)][getYLocationByPixel(y)];
            Label frontLabel = new Label();
            frontLabel.setPrefWidth(edgeLength);
            frontLabel.setPrefHeight(edgeLength);
            frontLabel.setLayoutX(pictureLabel.getLayoutX());
            frontLabel.setLayoutY(pictureLabel.getLayoutY());
            frontLabel.setBackground(background);
            frontLabel.toFront();
            frontLabel.setTooltip(tooltip);
            frontLabel.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: #174D8AFF;" +
                    " -fx-opacity: 0.3; -fx-border-style: solid;");
            pane.getChildren().add(frontLabel);
        };
        pictureLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, selectCell);
        pane.getChildren().add(pictureLabel);
        pane.setStyle("-fx-spacing: 0");
    }
    
    public Tooltip getTooltipForACell(Cell cell) {
        Tooltip tooltip = new Tooltip();
        StringBuilder tooltipText = new StringBuilder();
        if (cell.getBuilding() != null)
            tooltipText.append("Building: ").append(cell.getBuilding().getType()).append("\n");
        if (cell.getPeople().size() != 0) tooltipText.append("People: ");
        for (Person person : cell.getPeople()) {
            tooltipText.append(person.getType()).append(", ");
        }
        if (cell.getPeople().size() != 0) tooltipText.append("\n");
        tooltipText.append("Texture: ").append(cell.getMaterial()).append("\n");
        if (cell.getNaturalBlocks().size() != 0) tooltipText.append("Natural Blocks: ");
        for (NaturalBlock naturalBlock : cell.getNaturalBlocks()) {
            tooltipText.append(naturalBlock.getName()).append(", ");
        }
        if (cell.getNaturalBlocks().size() != 0) tooltipText.append("\n");
        tooltip.setText(tooltipText.toString());
        tooltip.setStyle("-fx-font-size: 15px;");
        tooltip.setShowDelay(new Duration(500));
        return tooltip;
    }
    
    private void showBuilding(Pane pane, int i, int j, Building building) {
        if (building == null) return;
        String imageAddress = "/images/Buildings/" + FileController.getBuildingCategoryByType(building.getType()) +
                "/" + building.getType() + ".png";
        ImageView buildingImageView = new ImageView(String.valueOf(getClass().getResource(imageAddress)));
        buildingImageView.setFitHeight(edgeLength);
        buildingImageView.setFitWidth(edgeLength);
        buildingImageView.setLayoutX(getXLocationByPixel(i));
        buildingImageView.setLayoutY(getYLocationByPixel(j));
        
        EventHandler<MouseEvent> selectBuildingEventHandler = mouseEvent -> {
            GameGraphics.selectedBuilding = map.getCells()[i][j].getBuilding();
            setBuildingMenu(pane);
        };
        
        buildingImageView.addEventFilter(MouseEvent.MOUSE_CLICKED, selectBuildingEventHandler);
        buildingImageView.toFront();
        pane.getChildren().add(buildingImageView);
    }
    
    public int getXLocationByPixel(double x) {
        return (int) (x + shownX - (float) 11 * 70 / edgeLength);
    }
    
    public int getYLocationByPixel(double y) {
        return (int) (y + shownY - (float) 5 * 70 / edgeLength);
    }
    
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
}
