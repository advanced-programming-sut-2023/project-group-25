package Controller;

import Model.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MapController2 {
    private final ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("/images/menu.png"))));
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
    private final ImageView stockpile = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/StorageBuildings/stockpile.png"))));
    private final ImageView woodCutter = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/woodcutter.png"))));
    private final ImageView quarry = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/quarry.gif"))));
    private final ImageView oxTether = new ImageView(new Image(String.valueOf(getClass().getResource("/images/Buildings/ProductionBuildings/ox tether.gif"))));
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
    public static String clickedBuildingToDrop = null;
    private boolean isTheFirstTime = false;
    
    public void loadMapToShow(Stage stage, Pane pane, Map map, int x, int y, int edgeLength) {
        
        
        //capacity: 31 x 16 (x50 pixels)
        if (edgeLength < 10 || edgeLength >= 100 || !isLocationAppropriateToShow(x, y, map, edgeLength)) {
            return;
        }
        int xCounter = 0, yCounter = 0;
        
        for (int i = x - 15 * 50 / edgeLength; i < x + 16 * 50 / edgeLength; i++) {
            for (int j = y - 7 * 50 / edgeLength; j < y + 9 * 50 / edgeLength + 2; j++) {
                Cell cell = map.getCells()[i][j];
                
                showBackground(pane, cell.getMaterial(), xCounter, yCounter, edgeLength);
                
                for (NaturalBlock naturalBlock : cell.getNaturalBlocks()) {
                    showNaturalBlock(pane, i, j, naturalBlock);
                }
                
                showBuilding(pane, map, i, j, cell.getBuilding());
                
                for (Person person : cell.getPeople()) {
                    showPerson(pane, map, i, j, person);
                }
                yCounter++;
            }
            xCounter++;
            yCounter = 0;
        }
        
        
        imageView.setLayoutX(0);
        imageView.setLayoutY(685);
        imageView.toFront();
        
        imageIcon1.setLayoutX(500);
        imageIcon1.setLayoutY(815);
        imageIcon1.setFitHeight(50);
        imageIcon1.setFitWidth(50);
        imageIcon1.toFront();
        setMenuIcon1();
        imageIcon1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(true);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            event.consume();
        });
        
        imageIcon2.setLayoutX(550);
        imageIcon2.setLayoutY(825);
        imageIcon2.setFitHeight(40);
        imageIcon2.setFitWidth(40);
        imageIcon2.toFront();
        setMenuIcon2();
        imageIcon2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(true);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            event.consume();
        });
        
        imageIcon3.setLayoutX(600);
        imageIcon3.setLayoutY(822.5);
        imageIcon3.setFitHeight(45);
        imageIcon3.setFitWidth(40);
        imageIcon3.toFront();
        setMenuIcon3();
        imageIcon3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(true);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            event.consume();
        });
        
        imageIcon4.setLayoutX(650);
        imageIcon4.setLayoutY(822.5);
        imageIcon4.setFitHeight(45);
        imageIcon4.setFitWidth(40);
        imageIcon4.toFront();
        setMenuIcon4();
        imageIcon4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(true);
            setImagesIcons5(false);
            setImagesIcons6(false);
            event.consume();
        });
        
        imageIcon5.setLayoutX(700);
        imageIcon5.setLayoutY(825);
        imageIcon5.setFitHeight(40);
        imageIcon5.setFitWidth(40);
        imageIcon5.toFront();
        setMenuIcon5();
        imageIcon5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(true);
            setImagesIcons6(false);
            event.consume();
        });
        
        imageIcon6.setLayoutX(750);
        imageIcon6.setLayoutY(825);
        imageIcon6.setFitHeight(40);
        imageIcon6.setFitWidth(40);
        imageIcon6.toFront();
        setMenuIcon6();
        imageIcon6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setImagesIcons1(false);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(true);
            event.consume();
        });
        
        if (!isTheFirstTime) {
            setImagesIcons1(true);
            setImagesIcons2(false);
            setImagesIcons3(false);
            setImagesIcons4(false);
            setImagesIcons5(false);
            setImagesIcons6(false);
            pane.getChildren().addAll(imageView, imageIcon1, imageIcon2, imageIcon3, imageIcon4, imageIcon5, imageIcon6
                    , barracks, mercenary, armoury, stairs, shortWall, highWall
                    , stockpile, woodCutter, quarry, ironMine, oxTether, market
                    , appleOrchard, wheatFarmer, hopsFarmer, dairyFarmer
                    , hovel, church, catheral
                    , poleturner, armourer, blacksmith, fletcher
                    , granary, bakery, brewer, mill, inn);
            isTheFirstTime = true;
        }
        stage.show();
    }
    
    private boolean isLocationAppropriateToShow(int x, int y, Map map, int edgeLength) {
        return x - 15 * 50 / edgeLength >= 0 && y - 7 * 50 / edgeLength >= 0 && x + 16 * 50 / edgeLength <= map.getWidth() && y + 8 * 50 / edgeLength <= map.getLength();
    }
    
    private void setMenuIcon6() {
        granary.setLayoutX(500);
        granary.setLayoutY(750);
        granary.setFitHeight(70);
        granary.setFitWidth(70);
        granary.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "granary1");
        granary.toFront();
        //TODO: select, move, drop buildings
//        granary.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
//            System.out.println("sssssss");
//            e.consume();
//        });
        bakery.setLayoutX(580);
        bakery.setLayoutY(750);
        bakery.setFitHeight(70);
        bakery.setFitWidth(70);
        bakery.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "bakery");
        bakery.toFront();
        brewer.setLayoutX(665);
        brewer.setLayoutY(750);
        brewer.setFitHeight(70);
        brewer.setFitWidth(70);
        brewer.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "brewer");
        brewer.toFront();
        mill.setLayoutX(750);
        mill.setLayoutY(750);
        mill.setFitHeight(70);
        mill.setFitWidth(50);
        mill.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "mill");
        mill.toFront();
        inn.setLayoutX(810);
        inn.setLayoutY(750);
        inn.setFitHeight(70);
        inn.setFitWidth(70);
        inn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "inn");
        inn.toFront();
    }
    
    private void setImagesIcons6(boolean check) {
        granary.setVisible(check);
        bakery.setVisible(check);
        brewer.setVisible(check);
        mill.setVisible(check);
        inn.setVisible(check);
    }
    
    private void setMenuIcon5() {
        fletcher.setLayoutX(500);
        fletcher.setLayoutY(750);
        fletcher.setFitHeight(70);
        fletcher.setFitWidth(70);
        fletcher.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "fletcher");
        fletcher.toFront();
        poleturner.setLayoutX(590);
        poleturner.setLayoutY(750);
        poleturner.setFitWidth(70);
        poleturner.setFitHeight(70);
        poleturner.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "poleturner");
        poleturner.toFront();
        blacksmith.setLayoutX(670);
        blacksmith.setLayoutY(750);
        blacksmith.setFitHeight(70);
        blacksmith.setFitWidth(70);
        blacksmith.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "blacksmith");
        blacksmith.toFront();
        armourer.setLayoutX(750);
        armourer.setLayoutY(750);
        armourer.setFitWidth(70);
        armourer.setFitHeight(70);
        armourer.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "armourer");
        armourer.toFront();
    }
    
    private void setImagesIcons5(boolean check) {
        poleturner.setVisible(check);
        fletcher.setVisible(check);
        blacksmith.setVisible(check);
        armourer.setVisible(check);
    }
    
    private void setMenuIcon4() {
        hovel.setLayoutX(500);
        hovel.setLayoutY(750);
        hovel.setFitHeight(70);
        hovel.setFitWidth(70);
        hovel.toFront();
        church.setLayoutX(580);
        church.setLayoutY(750);
        church.setFitHeight(70);
        church.setFitWidth(70);
        church.toFront();
        catheral.setLayoutX(660);
        catheral.setLayoutY(750);
        catheral.setFitHeight(80);
        catheral.setFitWidth(80);
        catheral.toFront();
    }
    
    private void setImagesIcons4(boolean check) {
        hovel.setVisible(check);
        church.setVisible(check);
        catheral.setVisible(check);
    }
    
    private void setMenuIcon3() {
        appleOrchard.setLayoutX(500);
        appleOrchard.setLayoutY(750);
        appleOrchard.setFitHeight(70);
        appleOrchard.setFitWidth(70);
        appleOrchard.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "apple orchard");
        appleOrchard.toFront();
        dairyFarmer.setLayoutX(580);
        dairyFarmer.setLayoutY(750);
        dairyFarmer.setFitHeight(70);
        dairyFarmer.setFitWidth(70);
        dairyFarmer.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "dairy farmer");
        dairyFarmer.toFront();
        hopsFarmer.setLayoutX(670);
        hopsFarmer.setLayoutY(750);
        hopsFarmer.setFitHeight(70);
        hopsFarmer.setFitWidth(70);
        hopsFarmer.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "hops farmer");
        hopsFarmer.toFront();
        wheatFarmer.setLayoutX(750);
        wheatFarmer.setLayoutY(750);
        wheatFarmer.setFitHeight(70);
        wheatFarmer.setFitWidth(70);
        wheatFarmer.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "wheat farmer");
        wheatFarmer.toFront();
    }
    
    private void setImagesIcons3(boolean check) {
        appleOrchard.setVisible(check);
        dairyFarmer.setVisible(check);
        hopsFarmer.setVisible(check);
        wheatFarmer.setVisible(check);
    }
    
    private void setMenuIcon2() {
        stockpile.setLayoutX(500);
        stockpile.setLayoutY(750);
        stockpile.setFitHeight(70);
        stockpile.setFitWidth(70);
        stockpile.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "stockpile");
        stockpile.toFront();
        woodCutter.setLayoutX(580);
        woodCutter.setLayoutY(750);
        woodCutter.setFitHeight(70);
        woodCutter.setFitHeight(70);
        woodCutter.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "woodcutter");
        woodCutter.toFront();
        quarry.setLayoutX(690);
        quarry.setLayoutY(750);
        quarry.setFitHeight(70);
        quarry.setFitWidth(70);
        quarry.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "quarry");
        quarry.toFront();
        oxTether.setLayoutX(750);
        oxTether.setLayoutY(740);
        oxTether.setFitHeight(50);
        oxTether.setFitWidth(50);
        oxTether.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "ox tether");
        oxTether.toFront();
        ironMine.setLayoutX(800);
        ironMine.setLayoutY(750);
        ironMine.setFitHeight(70);
        ironMine.setFitWidth(70);
        ironMine.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "iron mine");
        ironMine.toFront();
        market.setLayoutX(880);
        market.setLayoutY(750);
        market.setFitHeight(70);
        market.setFitWidth(70);
        market.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "market1");
        market.toFront();
    }
    
    private void setMenuIcon1() {
        barracks.setLayoutX(500);
        barracks.setLayoutY(750);
        barracks.setFitHeight(70);
        barracks.setFitWidth(70);
        barracks.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "barracks1");
        barracks.toFront();
        mercenary.setLayoutX(580);
        mercenary.setLayoutY(750);
        mercenary.setFitHeight(70);
        mercenary.setFitWidth(70);
        mercenary.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "mercenary post1");
        mercenary.toFront();
        armoury.setLayoutX(640);
        armoury.setLayoutY(750);
        armoury.setFitHeight(90);
        armoury.setFitWidth(100);
        armoury.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "armoury1");
        armoury.toFront();
        stairs.setLayoutX(750);
        stairs.setLayoutY(760);
        stairs.setFitHeight(60);
        stairs.setFitWidth(20);
        stairs.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "stairs");
        stairs.toFront();
        shortWall.setLayoutX(800);
        shortWall.setLayoutY(770);
        shortWall.setFitHeight(50);
        shortWall.setFitWidth(20);
        shortWall.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "short wall");
        shortWall.toFront();
        highWall.setLayoutX(850);
        highWall.setLayoutY(750);
        highWall.setFitHeight(70);
        highWall.setFitWidth(20);
        highWall.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickedBuildingToDrop = "high wall");
        highWall.toFront();
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
        Label label = new Label();
        label.setPrefWidth(edgeLength);
        label.setPrefHeight(edgeLength);
        label.setLayoutX(edgeLength * x);
        label.setLayoutY(edgeLength * y);
        label.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0.2px; -fx-border-style: dash");
        String address = "/images/" + material + ".jpg";
        Background background = new Background(MainController.setFirstPageBackground(address));
        label.setBackground(background);
        pane.getChildren().add(label);
        pane.setStyle("-fx-spacing: 0");
    }
    
    private void showBuilding(Pane pane, Map map, int i, int j, Building building) {
        //TODO
    }
}
