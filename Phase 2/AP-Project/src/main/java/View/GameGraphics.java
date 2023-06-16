package View;

import Controller.ChangeMenuController;
import Controller.FileController;
import Controller.GameController;
import Controller.MapController2;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static Controller.MapController2.clickedBuildingToDrop;

public class GameGraphics extends Application {
    public static ImageView toBeDroppedBuildingImageView = null;
    private final GameController gameController;
    private int edgeLength = 50;
    private int shownX = 15;
    private int shownY = 7;
    private TradeMenu tradeMenu;
    private MouseEvent previousClick;
    
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
    
    public void setEdgeLength(int edgeLength) {
        this.edgeLength = edgeLength;
    }
    
    public TradeMenu getTradeMenu() {
        return tradeMenu;
    }
    
    public void setTradeMenu(TradeMenu tradeMenu) {
        this.tradeMenu = tradeMenu;
    }
    
    @Override
    public void start(Stage stage) {
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
                x = x - dx;
                y = y - dy;
                shownX = (int) x / edgeLength;
                shownY = (int) y / edgeLength;
                mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
            }
        };
        
        EventHandler<KeyEvent> zoomingEventHandler = keyEvent -> {
            if (clickedBuildingToDrop == null) {
                if (keyEvent.getCode().getName().equals("Add") || keyEvent.getCode().getName().equals("Equals")) {
                    if (edgeLength <= 90) edgeLength += 10;
                    mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
                } else if (keyEvent.getCode().getName().equals("Subtract") || keyEvent.getCode().getName().equals("Minus")) {
                    if (edgeLength >= 0) edgeLength -= 10;
                    mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
                }
            }
        };
        
        EventHandler<MouseEvent> moveClickedBuildingToDropEventHandler = mouseEvent -> {
            if (clickedBuildingToDrop != null) {
                if (toBeDroppedBuildingImageView == null) {
                    String buildingNameWithoutNumber = (clickedBuildingToDrop.matches("[a-zA-Z ]+1")) ?
                            clickedBuildingToDrop.replace("1", "") : clickedBuildingToDrop;
                    String address = "/images/Buildings/" + FileController.getBuildingCategoryByType(buildingNameWithoutNumber)
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
                    String buildingNameWithoutNumber = (clickedBuildingToDrop.matches("[a-zA-Z ]+1")) ?
                            clickedBuildingToDrop.replace("1", "") : clickedBuildingToDrop;
                    String address = "/images/Buildings/" + FileController.getBuildingCategoryByType(buildingNameWithoutNumber)
                            + "/" + clickedBuildingToDrop + ".png";
                    ImageView droppedBuilding = new ImageView(new Image(String.valueOf(getClass().getResource(address))));
                    droppedBuilding.setFitHeight(edgeLength);
                    droppedBuilding.setFitWidth(edgeLength);
                    gamePane.getChildren().add(droppedBuilding);
                    droppedBuilding.setLayoutX(mouseEvent.getX());
                    droppedBuilding.setLayoutY(mouseEvent.getY());
                    droppedBuilding.toFront();
                } else if (mouseEvent.isSecondaryButtonDown()) {
                    gamePane.getChildren().remove(toBeDroppedBuildingImageView);
                    toBeDroppedBuildingImageView = null;
                    clickedBuildingToDrop = null;
                }
            }
        };
        
        
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, scrollingMouseEventHandler1);
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, scrollingMouseEventHandler2);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, zoomingEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_MOVED, moveClickedBuildingToDropEventHandler);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, dropOrCancelBuildingEventHandler);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}