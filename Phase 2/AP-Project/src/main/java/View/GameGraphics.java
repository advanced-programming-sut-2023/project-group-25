package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController2;
import Controller.RegisterLoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;

public class GameGraphics extends Application {
    private final RegisterLoginController registerLoginController;
    private final GameController gameController;
    private int edgeLength = 50;
    private int shownX = 15;
    private int shownY = 7;
    private TradeMenu tradeMenu;
    private MouseEvent previousClick;
    
    public GameGraphics(ChangeMenuController changeMenuController) {
        this.gameController = changeMenuController.getgameController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
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
    
        EventHandler<MouseEvent> scrollingMouseEventHandler2 = new EventHandler<>() {
            //TODO for samin: scrolling smoothly using MOUSE_DRAGGED event
            double x = shownX * edgeLength;
            double y = shownY * edgeLength;
    
            @Override
            public void handle(MouseEvent mouseEvent) {
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
            if (keyEvent.getCode().getName().equals("Add") || keyEvent.getCode().getName().equals("Equals")) {
                if (edgeLength <= 90) edgeLength += 10;
                mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
            } else if (keyEvent.getCode().getName().equals("Subtract") || keyEvent.getCode().getName().equals("Minus")) {
                if (edgeLength >= 0) edgeLength -= 10;
                mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), shownX, shownY, edgeLength);
            }
        };
        
   
        
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, scrollingMouseEventHandler1);
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, scrollingMouseEventHandler2);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, zoomingEventHandler);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
