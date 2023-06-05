package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController2;
import Controller.RegisterLoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;

public class GameGraphics extends Application {
    private final RegisterLoginController registerLoginController;
    private final GameController gameController;
    private TradeMenu tradeMenu;
    
    public GameGraphics(ChangeMenuController changeMenuController) {
        this.gameController = changeMenuController.getgameController();
        this.registerLoginController = changeMenuController.getRegisterLoginController();
        this.tradeMenu = new TradeMenu(changeMenuController);
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        Pane gamePane = new Pane();
        Scene scene = new Scene(gamePane, 750, 1200);
        MapController2 mapController = new MapController2();
        mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), 15, 7);
        
        EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<>() {
            double x = 15 * 50;
            double y = 7 * 50;
            double previousClickX, previousClickY;
            //MouseEvent previousMouseEvent = null;
            MouseEvent previousClick;
            
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    previousClick = mouseEvent;
                } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                    int dx = (int) (mouseEvent.getX() - previousClick.getX());
                    int dy = (int) (mouseEvent.getY() - previousClick.getY());
                    x = x - dx;
                    y = y - dy;
                    mapController.loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(),
                            (int) (x / 50), (int) y / 50);
                }
                //previousMouseEvent = mouseEvent;
            }
        };
        scene.addEventFilter(MouseEvent.ANY, mouseEventEventHandler);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
