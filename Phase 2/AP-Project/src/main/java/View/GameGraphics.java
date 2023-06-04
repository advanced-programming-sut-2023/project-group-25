package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController2;
import Controller.RegisterLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    public void start(Stage stage) {
//        GridPane gamePane = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/gameGraphic.fxml"))));
        Pane gamePane = new Pane();
        Scene scene = new Scene(gamePane,750,1200);
        new MapController2().loadMapToShow(stage, gamePane, gameController.getCurrentGame().getMap(), 20, 20);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
