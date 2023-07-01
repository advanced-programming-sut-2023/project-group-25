package View;

import Controller.ChangeMenuController;
import Controller.FileController;
import Controller.GameController;
import Controller.MapController2;
import Model.*;
import Model.Cell;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.plaf.multi.MultiListUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

import static Controller.MapController2.clickedBuildingToDrop;

public class GameGraphics extends Application {
    public static ImageView toBeDroppedBuildingImageView = null;
    public static ImageView toCreateUnitImageView = null;
    public static ImageView lastBuildingImage;
    public static Building selectedBuilding = null;
    public static Cell selectedCell = null;
    public static Building lastBuildingDropped;
    public static ArrayList<MilitaryPerson> selectedUnits = new ArrayList<>();
    private final GameController gameController;
    private Clipboard clipboard;
    private int edgeLength = 70;
    private int shownX = 11;
    private int shownY = 5;
    private TradeMenu tradeMenu;
    private MouseEvent previousClick;
    private String pressedKeyName = null;
    private MouseEvent previousMouseEvent = null;
    
    public GameGraphics(ChangeMenuController changeMenuController) {
        this.gameController = changeMenuController.getGameController();
        this.tradeMenu = new TradeMenu(changeMenuController);
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
    
    public int getEdgeLength() {
        return edgeLength;
    }
    
    public TradeMenu getTradeMenu() {
        return tradeMenu;
    }
    
    @Override
    public void start(Stage stage) {
        clipboard = Clipboard.getSystemClipboard();
        Pane gamePane = new Pane();
        Scene scene = new Scene(gamePane, 750, 1200);
        MapController2 mapController = new MapController2();
        mapController.loadMapToShow(scene, stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
        
        EventHandler<MouseEvent> previousClickEventHandler = mouseEvent -> {
            previousClick = mouseEvent;
            previousMouseEvent = mouseEvent;
            if (mouseEvent.isSecondaryButtonDown())
                mapController.loadMapToShow(scene, stage, gamePane, gameController.getCurrentGame().getMap()
                        , shownX, shownY, edgeLength);
        };
        
        EventHandler<MouseEvent> scrollingMouseEventHandler = mouseEvent -> {
            if (clickedBuildingToDrop == null && previousClick.isPrimaryButtonDown()) {
                double x = shownX * edgeLength;
                double y = shownY * edgeLength;
                int dx = (int) (mouseEvent.getX() - previousClick.getX());
                int dy = (int) (mouseEvent.getY() - previousClick.getY());
                if (mapController.loadMapToShow(scene, stage, gamePane, gameController.getCurrentGame().getMap(),
                        (int) (x - dx) / edgeLength, (int) (y - dy) / edgeLength, edgeLength).equals("success")) {
                    x = x - dx;
                    y = y - dy;
                    shownX = (int) x / edgeLength;
                    shownY = (int) y / edgeLength;
                }
                Map map = gameController.getCurrentGame().getMap();
                mapController.setMiniMapShowingX((shownX - 11 + 1411 * (map.getLength() - 22) / 115) * 115 / (map.getLength() - 22));
                mapController.setMiniMapShowingY((shownY - 5 + 729 * (map.getWidth() - 10) / 115) * 115 / (map.getWidth() - 10));
            }
        };
        
        EventHandler<KeyEvent> zoomingEventHandler = keyEvent -> {
            if (clickedBuildingToDrop == null) {
                if (keyEvent.getCode().getName().equals("Add") || keyEvent.getCode().getName().equals("Equals")) {
                    if (edgeLength <= 90) edgeLength += 10;
                    mapController.loadMapToShow(scene, stage, gamePane, gameController.getCurrentGame().getMap(),
                            shownX, shownY, edgeLength);
                } else if (keyEvent.getCode().getName().equals("Subtract") || keyEvent.getCode().getName().equals("Minus")) {
                    if (edgeLength > 40) edgeLength -= 10;
                    mapController.loadMapToShow(scene, stage, gamePane, gameController.getCurrentGame().getMap(),
                            shownX, shownY, edgeLength);
                }
            }
        };
        
        
        EventHandler<MouseEvent> moveClickedBuildingToDropEventHandler = mouseEvent -> {
            if (clickedBuildingToDrop != null) {
                if (toBeDroppedBuildingImageView == null) {
                    String address = "/images/Buildings/" + FileController.getBuildingCategoryByType(clickedBuildingToDrop)
                            + "/" + clickedBuildingToDrop + ".png";
                    toBeDroppedBuildingImageView = new ImageView(new Image(String.valueOf(getClass().getResource(address))));
                    toBeDroppedBuildingImageView.toFront();
                    gamePane.getChildren().add(toBeDroppedBuildingImageView);
                }
                toBeDroppedBuildingImageView.setFitHeight(edgeLength);
                toBeDroppedBuildingImageView.setFitWidth(edgeLength);
                toBeDroppedBuildingImageView.setLayoutX(mouseEvent.getX());
                toBeDroppedBuildingImageView.setLayoutY(mouseEvent.getY());
            }
        };
        
        EventHandler<MouseEvent> dropOrCancelBuildingEventHandler = mouseEvent -> {
            if (clickedBuildingToDrop != null) {
                if (mouseEvent.isPrimaryButtonDown()) {
                    String address = "/images/Buildings/" + FileController.getBuildingCategoryByType(clickedBuildingToDrop)
                            + "/" + clickedBuildingToDrop + ".png";
                    int x = mapController.getXLocationByPixel(mouseEvent.getX() / edgeLength) * edgeLength;
                    int y = mapController.getYLocationByPixel(mouseEvent.getY() / edgeLength) * edgeLength;
                    Cell cell = gameController.getCurrentGame().getMap().getCells()[x / edgeLength][y / edgeLength];
                    buildBuildingAndShowMessage(gamePane, address, cell, clickedBuildingToDrop);
                } else if (mouseEvent.isSecondaryButtonDown()) {
                    gamePane.getChildren().remove(toBeDroppedBuildingImageView);
                    toBeDroppedBuildingImageView = null;
                    clickedBuildingToDrop = null;
                }
            }
        };
        
        
        EventHandler<KeyEvent> copyOrPasteBuildingEventHandler = keyEvent -> {
            if (keyEvent.getCode().getName().equals("Ctrl")) pressedKeyName = "Ctrl";
            else if (keyEvent.getCode().getName().equals("C") && pressedKeyName.equals("Ctrl") && selectedBuilding != null) {
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(selectedBuilding.getType());
                clipboard.setContent(clipboardContent);
            } else if (keyEvent.getCode().getName().equals("V") && pressedKeyName.equals("Ctrl")
                    && clipboard.getContentTypes() != null && selectedCell != null) {
                String address = "/images/Buildings/" + FileController.getBuildingCategoryByType(clipboard.getString())
                        + "/" + selectedBuilding.getType() + ".png";
                buildBuildingAndShowMessage(gamePane, address, selectedCell, selectedBuilding.getType());
            }
        };
        
        
        EventHandler<MouseEvent> rectangleSelectionEventHandler = mouseEvent -> {
            if (previousMouseEvent.isSecondaryButtonDown() && clickedBuildingToDrop == null) {
                double minX = Math.min(previousClick.getX(), mouseEvent.getX());
                double maxX = Math.max(previousClick.getX(), mouseEvent.getX());
                double minY = Math.min(previousClick.getY(), mouseEvent.getY());
                double maxY = Math.max(previousClick.getY(), mouseEvent.getY());
                Cell[][] cells = gameController.getCurrentGame().getMap().getCells();
                Tooltip tooltip = new Tooltip();
                tooltip.setStyle("-fx-font-size: 15px;");
                String s = null;
                int totalNumberOfPeople = 0;
                boolean isGroup = false;
                if ((Math.abs(minX - maxX) / edgeLength > 1) || (Math.abs(minY - maxY) / edgeLength > 1)) {
                    isGroup = true;
                    s = "Total number of people: ";
                }
                
                selectedUnits.clear();
                for (int i = (int) minX / edgeLength; i < maxX / edgeLength; i++) {
                    for (int j = (int) minY / edgeLength; j < maxY / edgeLength; j++) {
                        selectedCell = null;
                        selectedUnits.clear();
                        Cell cell = cells[mapController.getXLocationByPixel(i)][mapController.getYLocationByPixel(j)];
                        selectUnits(cell);
//
//                        TextInputDialog dialog = new TextInputDialog("0");
//                        dialog.setTitle("Selecting Units");
//                        dialog.setHeaderText("Look, a Text Input Dialog");
//                        dialog.setContentText("Please enter your name:");
//
//                        Optional<String> result = dialog.showAndWait();
//                        result.ifPresent(name -> System.out.println("Your name: " + name));
//
//
//                        for (Person person : cell.getPeople()) {
//                            if (person instanceof MilitaryPerson) selectedUnits.add((MilitaryPerson) person);
//                        }
                        Label frontLabel = new Label();
                        frontLabel.setPrefWidth(edgeLength);
                        frontLabel.setPrefHeight(edgeLength);
                        frontLabel.setLayoutX(i * edgeLength);
                        frontLabel.setLayoutY(j * edgeLength);
                        frontLabel.toFront();
                        frontLabel.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: #174D8AFF;" +
                                " -fx-opacity: 0.3; -fx-border-style: solid;");
                        
                        gamePane.getChildren().add(frontLabel);
                        if (isGroup) frontLabel.setTooltip(tooltip);
                        else {
                            frontLabel.setTooltip(mapController.getTooltipForACell(cells[mapController.getXLocationByPixel(i)]
                                    [mapController.getYLocationByPixel(j)]));
                            totalNumberOfPeople += cells[mapController.getXLocationByPixel(i)][mapController.getYLocationByPixel(j)]
                                    .getPeople().size();
                            selectedCell = cells[mapController.getXLocationByPixel(i)][mapController.getYLocationByPixel(j)];
//                            for (Person person : selectedCell.getPeople()) {
//                                if (person instanceof MilitaryPerson) selectedUnits.add((MilitaryPerson) person);
//                            }
                        }
                    }
                }
                
                if (s != null) {
                    s += totalNumberOfPeople;
                    tooltip.setText(s);
                }
            } else if (mouseEvent.isPrimaryButtonDown()) {
                selectedCell = null;
                selectedUnits.clear();
            }
        };
        
        EventHandler<KeyEvent> showClipBoardEventHandler = keyEvent -> {
            if (keyEvent.getCode().getName().equals("Windows")) pressedKeyName = "Windows";
            else if (keyEvent.getCode().getName().equals("V") && pressedKeyName.equals("Windows")) {
                Dialog<Image> dialog = new Dialog<>();
                dialog.setGraphic(new ImageView(clipboard.getImage()));
                dialog.show();
            }
        };
        
        EventHandler<MouseEvent> moveOnMiniMapeEventHandler = mouseEvent -> {
            Map map = gameController.getCurrentGame().getMap();
            if (mapController.isLocationAppropriateToShow(
                    (int) (((map.getLength() - 22) * (mouseEvent.getX() - 10)) / 115 + 11 - (1411 * (map.getLength() - 22) / 115))
                    , (int) (((map.getWidth() - 10) * (mouseEvent.getY() - 10)) / 115 + 5 - (739 * (map.getWidth() - 10) / 115))
                    , map, edgeLength)) {
                if (mouseEvent.getX() >= 1411 && mouseEvent.getX() <= 1526 && mouseEvent.getY() >= 739 && mouseEvent.getY() <= 854) {
                    mapController.setMiniMapShowingX((int) mouseEvent.getX() - 10);
                    mapController.setMiniMapShowingY((int) mouseEvent.getY() - 10);
                    shownX = (int) (((map.getLength() - 22) * (mouseEvent.getX() - 10)) / 115 + 11 - (1411 * (map.getLength() - 22) / 115));
                    shownY = (int) (((map.getWidth() - 10) * (mouseEvent.getY() - 10)) / 115 + 5 - (739 * (map.getWidth() - 10) / 115));
                    mapController.loadMapToShow(scene, stage, gamePane, map, shownX, shownY, edgeLength);
                }
            }
        };
        
        EventHandler<MouseEvent> moveUnitEventHandler = mouseEvent -> {
            
            if (previousMouseEvent.isPrimaryButtonDown() && selectedUnits.size() != 0) {
                Cell cell = GameController.currentGame.getMap().getCells()
                        [mapController.getXLocationByPixel(mouseEvent.getX() / edgeLength)]
                        [mapController.getYLocationByPixel(mouseEvent.getY() / edgeLength)];
                //TODO: samin --> path finder
                for (MilitaryPerson selectedUnit : selectedUnits) {
//                    selectedUnit.getLocation().removePerson(selectedUnit);
//                    selectedUnit.setLocation(cell);
//                    cell.addPerson(selectedUnit);
                    
                    mapController.loadMapToShow(scene, stage, gamePane, gameController.getCurrentGame().getMap()
                            , shownX, shownY, edgeLength);
                }
                selectedUnits.clear();
            }
        };
        
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, previousClickEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, scrollingMouseEventHandler);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, zoomingEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_MOVED, moveClickedBuildingToDropEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, dropOrCancelBuildingEventHandler);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, copyOrPasteBuildingEventHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> pressedKeyName = null);
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, rectangleSelectionEventHandler);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, showClipBoardEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, moveOnMiniMapeEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, moveUnitEventHandler);
        
        
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    
    private void selectUnits(Cell cell) {
//        if (cell.getPeople().size() == 0) return;
//        HashMap<String, Integer> numberOfPeople = new HashMap<>();
//        for (Person person : cell.getPeople()) {
//            numberOfPeople.put(person.getType(), 0);
//        }
//        for (Person person : cell.getPeople()) {
//            numberOfPeople.put(person.getType(), numberOfPeople.get(person.getType()) + 1);
//        }
//
//        HashMap<String, Integer> wantedNumbers = new HashMap<>();
//        TextField[] numbers = new TextField[numberOfPeople.size()];
//        Dialog<Pair<String, String>> dialog = new Dialog<>();
//        dialog.setTitle("Selecting Units");
//        dialog.setHeaderText("Choose how many of each unit you want to select.");
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        for (int i = 0; i < cell.getPeople().size(); i++) {
//            Person person = cell.getPeople().get(i);
//            TextField number = new TextField("0");
//            grid.add(new Label(person.getType() + ":"), 0, i);
//            grid.add(number, 1, i);
//            dialog.getDialogPane().setContent(grid);
//        }
//        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
//        Optional<Pair<String, String>> result = dialog.showAndWait();
//        if (result.isPresent()) {
//            //TODO
//        }
        for (Person person : cell.getPeople()) {
            if (person instanceof MilitaryPerson) selectedUnits.add((MilitaryPerson) person);
        }
    }
    
    private void buildBuildingAndShowMessage(Pane gamePane, String address, Cell cell, String type) {
        String result = gameController.dropBuildingGraphics(cell.getX(), cell.getY(), cell, type);
        if (Pattern.compile("success").matcher(result).find()) {
            lastBuildingDropped=gameController.lastBuilding;
            lastBuildingImage=new ImageView(new Image(String.valueOf(getClass().getResource(address))));
            ImageView droppedBuildingImageView = new ImageView
                    (new Image(String.valueOf(getClass().getResource(address))));
            droppedBuildingImageView.setFitHeight(edgeLength);
            droppedBuildingImageView.setFitWidth(edgeLength);
            gamePane.getChildren().add(droppedBuildingImageView);
            droppedBuildingImageView.setLayoutX
                    ((int) (cell.getX() - shownX + (float) 11 * 70 / edgeLength) * edgeLength);
            droppedBuildingImageView.setLayoutY
                    ((int) (cell.getY() - shownY + (float) 5 * 70 / edgeLength) * edgeLength);
            droppedBuildingImageView.toFront();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Drop Building Error");
            alert.setContentText(result);
            alert.show();
        }
    }
    
}