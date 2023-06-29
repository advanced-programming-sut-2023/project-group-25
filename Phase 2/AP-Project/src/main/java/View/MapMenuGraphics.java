//package View;
//
//import Controller.*;
//import Model.Map;
//import Model.Turn;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//import java.net.URL;
//import java.util.Objects;
//
//import static View.FirstPage.stage;
//
//public class MapMenuGraphics extends Application {
//    private final ChangeMenuController changeMenuController;
//    private final GameController gameController;
//    private final MapController mapController;
//    public TextField mapWidth;
//    public TextField mapLength;
//
//    public MapMenuGraphics() {
//        this.changeMenuController = FirstPage.changeMenuController;
//        this.gameController = changeMenuController.getgameController();
//        this.mapController = changeMenuController.getMapController();
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        AnchorPane firstPage = FXMLLoader.load(new URL(Objects.requireNonNull(FirstPage.class.getResource
//                ("/fxml/mapMenu.fxml")).toExternalForm()));
//        Scene scene = new Scene(firstPage);
//        stage.setScene(scene);
//        stage.setFullScreen(true);
//        stage.show();
//    }
//
//
//    public void submitMapSize() {
//
//
//    }
//}
