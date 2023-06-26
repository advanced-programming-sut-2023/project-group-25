package View;

import Controller.ChangeMenuController;
import Controller.FileController;
import Controller.GameController;
import Controller.MapController2;
import Model.Building;
import Model.Cell;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.security.Key;

import static Controller.MapController2.clickedBuildingToDrop;

public class GameGraphics extends Application {
    public static ImageView toBeDroppedBuildingImageView = null;
    public static Building selectedBuilding = null;
    private final GameController gameController;
    private Clipboard clipboard;
    private int edgeLength = 70;
    private int shownX = 11;
    private int shownY = 5;
    private TradeMenu tradeMenu;
    private MouseEvent previousClick;
    private String pressedKeyName = null;
    public static Cell selectedCell = null; //Selected cell is the cell in map. The place on screen must be calculated.
    
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
        
        EventHandler<MouseEvent> scrollingMouseEventHandler1 = mouseEvent -> previousClick = mouseEvent;
        
        EventHandler<MouseEvent> scrollingMouseEventHandler2 = mouseEvent -> {
            if (clickedBuildingToDrop == null) {
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
                    ImageView droppedBuildingImageView = new ImageView(new Image(String.valueOf(getClass().getResource(address))));
                    droppedBuildingImageView.setFitHeight(edgeLength);
                    droppedBuildingImageView.setFitWidth(edgeLength);
//                    System.out.println("x=" + mouseEvent.getX() + " y=" + mouseEvent.getY());
                    gamePane.getChildren().add(droppedBuildingImageView);
                    int x = (int) mouseEvent.getX() + (shownX - (11 * 70 / edgeLength)) * edgeLength;
                    int y = (int) mouseEvent.getY() + (shownY - (5 * 70 / edgeLength)) * edgeLength;
                    Cell cell = gameController.getCurrentGame().getMap().getCells()[x / edgeLength][y / edgeLength];
                    droppedBuildingImageView.setLayoutX(cell.getX() * edgeLength);
                    droppedBuildingImageView.setLayoutY(cell.getY() * edgeLength);
                    String category = FileController.getBuildingCategoryByType(clickedBuildingToDrop);
                    assert category != null;
                    Building savedBuilding = gameController.getBuilding(clickedBuildingToDrop, category);
                    Building sampleBuilding = new Building(savedBuilding);
                    Building toBeDroppedBuilding = new Building(sampleBuilding.getType(), sampleBuilding.getCategory(),
                            sampleBuilding.getBuildingNeededProducts(), sampleBuilding.getWorkerCounter(),
                            sampleBuilding.getHitPoint());
                    cell.setBuilding(toBeDroppedBuilding);
                    //TODO: samin -> use dropBuilding method to build buildings
                    toBeDroppedBuilding.setLocation(cell);
                    droppedBuildingImageView.toFront();
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
                    && clipboard.getContentTypes() != null && selectedCell != null) {
                String category = FileController.getBuildingCategoryByType(selectedBuilding.getType());
                assert category != null;
                Building savedBuilding = gameController.getBuilding(selectedBuilding.getType(), category);
                Building sampleBuilding = new Building(savedBuilding);
                Building toBeDroppedBuilding = new Building(sampleBuilding.getType(), sampleBuilding.getCategory(),
                        sampleBuilding.getBuildingNeededProducts(), sampleBuilding.getWorkerCounter(),
                        sampleBuilding.getHitPoint());
                selectedCell.setBuilding(toBeDroppedBuilding);
                //TODO: samin -> use dropBuilding method to build buildings
                toBeDroppedBuilding.setLocation(selectedCell);
                mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
            }
        };
        
        
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, scrollingMouseEventHandler1);
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, scrollingMouseEventHandler2);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, zoomingEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_MOVED, moveClickedBuildingToDropEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, dropOrCancelBuildingEventHandler);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, copyOrPasteBuildingEventHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> pressedKeyName = null);
        
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    
}
