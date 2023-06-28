package View;

import Controller.*;
import Model.Map;
import Model.Turn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

import static View.FirstPage.stage;

public class MapMenuGraphics extends Application {
    private final ChangeMenuController changeMenuController;
    private final GameController gameController;
    private final MapController mapController;
    public TextField mapWidth;
    public TextField mapLength;

    public MapMenuGraphics() {
        this.changeMenuController = FirstPage.changeMenuController;
        this.gameController = changeMenuController.getgameController();
        this.mapController = changeMenuController.getMapController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane firstPage = FXMLLoader.load(new URL(Objects.requireNonNull(FirstPage.class.getResource
                ("/fxml/mapMenu.fxml")).toExternalForm()));
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
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
                Map map = Map.getTemplateMaps()[1];
                gameController.getCurrentGame().setMap(map);
                gameController.getCurrentGame().setMapTemplateNumber(1);
                Turn.setTurnCounter(0);
                gameController.getCurrentGame().turn= new Turn(RegisterLoginController.getCurrentUser());
                gameController.initializeTrees();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText("Success Message!");
                alert1.setContentText("Your map is ready!");
                alert1.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        try {
                            changeMenuController.getGameGraphics().start(stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
    }
    
    private void initializeTemplateMaps(int length, int width) {
        MapController2.initializeMapTemplate(length, width);
    }
}
