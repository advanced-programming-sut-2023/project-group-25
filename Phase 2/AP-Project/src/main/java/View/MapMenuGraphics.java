package View;

import Controller.ChangeMenuController;
import Controller.GameController;
import Controller.MapController;
import Model.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MapMenuGraphics extends Application implements Initializable {
    private final GameController gameController;
    private final MapController mapController;
    public TextField mapWidth;
    public TextField mapLength;
    private Stage stage;
    
    public MapMenuGraphics(ChangeMenuController changeMenuController) {
        this.gameController = changeMenuController.getgameController();
        this.mapController = changeMenuController.getMapController();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = new Stage();
        //this.stage = stage;
        URL url = new URL(Objects.requireNonNull(getClass().getResource("/fxml/mapMenu.fxml")).toExternalForm());
        AnchorPane mapPane = FXMLLoader.load(url);
        Scene scene = new Scene(mapPane);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
    }
    
    public void submitMapSize() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error!");
        
        if (!mapLength.getText().matches("\\d+") || !mapWidth.getText().matches("\\d+")) {
            alert.setContentText("You have to enter the number!");
            alert.show();
        } else {
            int length = Integer.parseInt(mapLength.getText());
            int width = Integer.parseInt(mapWidth.getText());
            if (length <= 0 || width <= 0) {
                alert.setContentText("You have entered invalid numbers!");
                alert.show();
            }
            else {
                initializeTemplateMaps(length, width);
                //TODO: start the game with the built map.
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText("Success Message!");
                alert1.setContentText("Your map is ready!");
                alert1.show();
            }
        }
    }
    
    private void initializeTemplateMaps(int length, int width) {
        MapController.initializeMapTemplate1(length, width);
        MapController.initializeMapTemplate2(length, width);
        MapController.initializeMapTemplate3(length, width);
    }
}
