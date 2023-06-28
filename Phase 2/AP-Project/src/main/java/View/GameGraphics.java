package View;

import Controller.*;
import Model.Building;
import Model.Cell;
import Model.Map;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.regex.Pattern;

import static Controller.MapController2.clickedBuildingToDrop;

public class GameGraphics extends Application {
    public static ImageView toBeDroppedBuildingImageView = null;
    public static Building selectedBuilding = null;
    public static Cell selectedCell = null; //Selected cell is the cell on map. The place on screen must be calculated.
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
        this.gameController = changeMenuController.getgameController();
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
        mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
        
        EventHandler<MouseEvent> previousClickEventHandler = mouseEvent -> {
            previousClick = mouseEvent;
            previousMouseEvent = mouseEvent;
            if (mouseEvent.isSecondaryButtonDown())
                mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
        };
        
        EventHandler<MouseEvent> scrollingMouseEventHandler = mouseEvent -> {
            if (clickedBuildingToDrop == null && previousClick.isPrimaryButtonDown()) {
                double x = shownX * edgeLength;
                double y = shownY * edgeLength;
                int dx = (int) (mouseEvent.getX() - previousClick.getX());
                int dy = (int) (mouseEvent.getY() - previousClick.getY());
                if (mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(),
                        (int) (x - dx) / edgeLength, (int) (y - dy) / edgeLength, edgeLength).equals("success")) {
                    x = x - dx;
                    y = y - dy;
                    shownX = (int) x / edgeLength;
                    shownY = (int) y / edgeLength;
                }
            }
        };
        
        EventHandler<KeyEvent> zoomingEventHandler = keyEvent -> {
            if (clickedBuildingToDrop == null) {
                if (keyEvent.getCode().getName().equals("Add") || keyEvent.getCode().getName().equals("Equals")) {
                    if (edgeLength <= 90) edgeLength += 10;
                    mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(),
                            shownX, shownY, edgeLength);
                } else if (keyEvent.getCode().getName().equals("Subtract") || keyEvent.getCode().getName().equals("Minus")) {
                    if (edgeLength > 40) edgeLength -= 10;
                    mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(),
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
                    System.out.println("x=" + x + " y=" + y);
                    Cell cell = gameController.getCurrentGame().getMap().getCells()[x / edgeLength][y / edgeLength];
                    String result = gameController.dropBuildingGraphics(cell.getX(), cell.getY(), cell, clickedBuildingToDrop);
                    if (Pattern.compile("success").matcher(result).find()) {
                        System.out.println("yes");
                        ImageView droppedBuildingImageView = new ImageView(new Image(String.valueOf(getClass().getResource(address))));
                        droppedBuildingImageView.setFitHeight(edgeLength);
                        droppedBuildingImageView.setFitWidth(edgeLength);
                        gamePane.getChildren().add(droppedBuildingImageView);
                        droppedBuildingImageView.setLayoutX(cell.getX() * edgeLength);
                        droppedBuildingImageView.setLayoutY(cell.getY() * edgeLength);
                        droppedBuildingImageView.toFront();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Drop Building Error");
                        alert.setContentText(result);
                        alert.show();
                    }
                } else if (mouseEvent.isSecondaryButtonDown()) {
                    gamePane.getChildren().remove(toBeDroppedBuildingImageView);
                    toBeDroppedBuildingImageView = null;
                    clickedBuildingToDrop = null;
                }
            }
        };
        
        
        EventHandler<KeyEvent> copyOrPasteBuildingEventHandler = keyEvent -> {
            if (keyEvent.getCode().getName().equals("Ctrl") && pressedKeyName == null) pressedKeyName = "Ctrl";
            else if (keyEvent.getCode().getName().equals("C") && pressedKeyName.equals("Ctrl") && selectedBuilding != null) {
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(selectedBuilding.getType());
                clipboard.setContent(clipboardContent);
            } else if (keyEvent.getCode().getName().equals("V") && pressedKeyName.equals("Ctrl")
                    && clipboard.getContent(DataFormat.PLAIN_TEXT) != null && selectedCell != null && selectedBuilding != null) {
                
                mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
                
                /*final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                VBox dialogVbox = new VBox(20);
                Text text = new Text("This is a dialog");
                dialogVbox.getChildren().add(text);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();*/
            }
        };
        
        Button nextTurn = new Button("Next Turn");
        nextTurn.setStyle("-fx-background-color:#FC9303;-fx-text-fill: black;-fx-border-color: black;");
        nextTurn.setLayoutX(1460);
        nextTurn.setLayoutY(700);
        EventHandler<MouseEvent> changeTurnHandler = event -> {
        
        };
        
        EventHandler<MouseEvent> rectangleSelectionEventHandler = mouseEvent -> {
            if (previousMouseEvent.isSecondaryButtonDown()) {
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
                for (int i = (int) minX / edgeLength; i < maxX / edgeLength; i++) {
                    for (int j = (int) minY / edgeLength; j < maxY / edgeLength; j++) {
                        GameGraphics.selectedCell = null;
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
                        else
                            frontLabel.setTooltip(mapController.getTooltipForACell(cells[mapController.getXLocationByPixel(i)]
                                    [mapController.getYLocationByPixel(j)]));
                        totalNumberOfPeople += cells[mapController.getXLocationByPixel(i)][mapController.getYLocationByPixel(j)]
                                .getPeople().size();
                    }
                }
                
                if (s != null) {
                    s += totalNumberOfPeople;
                    tooltip.setText(s);
                }
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
        
        gamePane.getChildren().add(nextTurn);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    
}