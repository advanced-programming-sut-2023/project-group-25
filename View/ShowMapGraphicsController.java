package View;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class ShowMapGraphicsController extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = new URL(Objects.requireNonNull(getClass().getResource("/fxml/mapMenu.fxml")).toExternalForm());
        BorderPane mapPane = FXMLLoader.load(url);
        Scene scene = new Scene(mapPane);
        
    }
}
