package Controller;

import Model.*;
import View.GameGraphics;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapController2 {
    public static String clickedBuildingToDrop = null;
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
    private final ImageView innMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/inn.png"))));
    private final ImageView millMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/mill.png"))));
    private final ImageView fletcherMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/fletcher.png"))));
    private final ImageView armorerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/armorer.png"))));
    private final ImageView blacksmithMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/blacksmith.png"))));
    private final ImageView poleturnerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Menu/poleturner.png"))));
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
    private GameController gameController;
    private boolean isTheFirstTime = false;
    private int edgeLength = 70;
    private int shownX;
    private int shownY;
    private Map map;

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

        int xCounter = 0, yCounter = 0;

        for (int i = x - 11 * 70 / edgeLength; i < x + 12 * 70 / edgeLength + 1; i++) {
            for (int j = y - 5 * 70 / edgeLength; j < y + 7 * 70 / edgeLength + 2; j++) {
                if (i >= map.getLength() || j >= map.getWidth()) continue;
                Cell cell = map.getCells()[i][j];

                showBackground(pane, cell.getMaterial(), xCounter, yCounter, edgeLength);

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

        EventHandler<MouseEvent> ccc = ev -> {
            double xScene = ev.getX();
            double yScene = ev.getY();
            if (xScene >= 0 && xScene <= 1528.8 && yScene >= 2.4 && yScene <= 692) {
                setArabianUnits(false);
                setEngineer(false);
                monk.setVisible(false);
                catheralMenu.setVisible(false);
                setEuropeanUnits(false);
                setWeapons(false);
            }
        };
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, ccc);

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
        setAllMenus(catheralMenu, pane);
        setSizeUnits(monk, 750, 720);
        setEuropeanUnitsSize();
        addWeapons();

        if (!isTheFirstTime) {
            setArabianUnits(false);
            setEngineer(false);
            monk.setVisible(false);
            catheralMenu.setVisible(false);
            setEuropeanUnits(false);
            setWeapons(false);
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
                    , bow, crossbow, leatherArmour, mace, metalArmour, pike, spear, sword, horse);
            isTheFirstTime = true;
        }


        stage.show();
        return "success";
    }

    private void setBuildingMenu(Pane pane) {
        ImageView towerMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/towerMenu.png"))));
        ImageView gatehouseMenu = new ImageView(new Image(String.valueOf(getClass().getResource("/images/gatehouseMenu.png"))));
        setSizeUnits(towerMenu, 0, 670);
        setSizeUnits(gatehouseMenu, 0, 670);
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
            Text text = new Text(GameGraphics.selectedBuilding.getType());
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
        else if (GameGraphics.selectedBuilding.getType().equals("church")) setAllMenus(churchMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("catheral")) {
            catheralMenu.setVisible(true);
            monk.setVisible(true);
        } else if (GameGraphics.selectedBuilding.getType().equals("armoury")) setAllMenus(armouryMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("hovel")) setAllMenus(hovelMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("market")) ; //TODO: Hoora
        else if (GameGraphics.selectedBuilding.getType().equals("woodcutter")) setAllMenus(woodcutterMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("caged war dogs")) setAllMenus(cageMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("pits")) setAllMenus(killingPitMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("stable")) setAllMenus(stableMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("stockpile")) setAllMenus(stockpileMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("apple orchard")) setAllMenus(appleOrchardMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("dairy farmer")) setAllMenus(dairyFarmMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("hops farmer")) setAllMenus(hopsFarmMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("wheat farmer")) setAllMenus(wheatFarmMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("quarry")) setAllMenus(quarryMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("iron mine")) setAllMenus(ironMineMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("ox tether")) setAllMenus(oxTetherMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("inn")) setAllMenus(innMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("mill")) setAllMenus(millMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("bakery")) setAllMenus(bakeryMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("brewer")) setAllMenus(breweryMenu, pane);
        else if (GameGraphics.selectedBuilding.getType().equals("poleturner")) {
            setAllMenus(poleturnerMenu, pane);
            setSizeUnits(pikeMenu, 750, 760);
            setSizeUnits(spearMenu, 800, 760);
            pane.getChildren().addAll(pikeMenu, spearMenu);
        } else if (GameGraphics.selectedBuilding.getType().equals("armourer")) {
            setAllMenus(armorerMenu, pane);
            setSizeUnits(metalMenu, 750, 760);
            setSizeUnits(leatherMenu, 800, 760);
            pane.getChildren().addAll(metalMenu, leatherMenu);
        } else if (GameGraphics.selectedBuilding.getType().equals("blacksmith")) {
            setAllMenus(blacksmithMenu, pane);
            setSizeUnits(swordMenu, 750, 760);
            setSizeUnits(maceMenu, 800, 760);
            pane.getChildren().addAll(maceMenu, swordMenu);
        } else if (GameGraphics.selectedBuilding.getType().equals("fletcher")) {
            setAllMenus(fletcherMenu, pane);
            setSizeUnits(bowMenu, 750, 760);
            setSizeUnits(crossbowMenu, 800, 760);
            pane.getChildren().addAll(bowMenu, crossbowMenu);
        }
        repair.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            //TODO: popup alert for ok,repaired,error
        });
    }

    private void setAllMenus(ImageView menu, Pane pane) {
        setSizeUnits(menu, 0, 670);
    }

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

    private void setEuropeanUnitsSize() {
        setSizeUnits(europeanUnitsMenu, 0, 670);
        setSizeUnits(archer, 502, 750);
        setSizeUnits(crossbowmen, 572, 750);
        setSizeUnits(knight, 640, 750);
        setSizeUnits(macemen, 710, 750);
        setSizeUnits(pikemen, 780, 750);
        setSizeUnits(spearmen, 840, 750);
        setSizeUnits(swordsmen, 910, 760);
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
    private void setWeapons(boolean check){
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

    private void setSizeUnits(ImageView unit, int x, int y) {
        unit.setLayoutX(x);
        unit.setLayoutY(y);
        unit.toFront();
    }

    private void setArabianUnitsSize() {
        setSizeUnits(ArabianUnitsMenu, 0, 670);
        setSizeUnits(archerBow, 502, 750);
        setSizeUnits(assassin, 572, 750);
        setSizeUnits(horseArcher, 640, 750);
        setSizeUnits(arabianSwordsmen, 710, 750);
        setSizeUnits(fireThrowers, 780, 760);
        setSizeUnits(slave, 840, 740);
        setSizeUnits(slinger, 910, 750);
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
    }

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

    private void showBackground(Pane pane, String material, int x, int y, int edgeLength) {
        Label pictureLabel = new Label();
        pictureLabel.setPrefWidth(edgeLength);
        pictureLabel.setPrefHeight(edgeLength);
        pictureLabel.setLayoutX(edgeLength * x);
        pictureLabel.setLayoutY(edgeLength * y);
        pictureLabel.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0.2px");
        String address = "/images/" + material + ".jpg";
        Background background = new Background(MainController.setFirstPageBackground(address));
        pictureLabel.setBackground(background);
        EventHandler<MouseEvent> selectCell = mouseEvent -> GameGraphics.selectedCell = map.getCells()[x][y];
        pictureLabel.setOnMouseClicked(selectCell);
        pane.getChildren().add(pictureLabel);
        pane.setStyle("-fx-spacing: 0");
    }

    private void showBuilding(Pane pane, int i, int j, Building building) {
        if (building == null) return;
//        System.out.println("loading building in cell");
        String imageAddress = "/images/Buildings/" + FileController.getBuildingCategoryByType(building.getType()) +
                "/" + building.getType() + ".png";
        ImageView buildingImageView = new ImageView(String.valueOf(getClass().getResource(imageAddress)));
        buildingImageView.setFitHeight(edgeLength);
        buildingImageView.setFitWidth(edgeLength);
        buildingImageView.setLayoutX((int) (i - shownX + (float) 11 * 70 / edgeLength) * edgeLength);
        buildingImageView.setLayoutY((int) (j - shownY + (float) 5 * 70 / edgeLength) * edgeLength);

        EventHandler<MouseEvent> showBuildingPanelEventHandler = mouseEvent -> {
            //TODO
        };

        EventHandler<MouseEvent> selectBuildingEventHandler = mouseEvent -> {
            GameGraphics.selectedBuilding = map.getCells()[i][j].getBuilding();
            setBuildingMenu(pane);
        };

        buildingImageView.addEventFilter(MouseEvent.MOUSE_CLICKED, showBuildingPanelEventHandler);
        buildingImageView.addEventFilter(MouseEvent.MOUSE_CLICKED, selectBuildingEventHandler);
        buildingImageView.toFront();
        pane.getChildren().add(buildingImageView);
    }
}
