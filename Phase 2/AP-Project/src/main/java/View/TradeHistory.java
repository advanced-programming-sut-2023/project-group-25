package View;

import Controller.FileController;
import Controller.GameController;
import Controller.MainController;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class TradeHistory extends Application implements Initializable {

    public Button back;
    public ListView list;

    public static void main(String[] args) throws Exception {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {

        GridPane firstPage = FXMLLoader.load(new URL(TradeHistory.class.getResource("/fxml/TradeHistory.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/shopMenu.png"));
        firstPage.setBackground(background);
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setText("\n\nBack");
    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new TradeMenuGraphics().start(FirstPage.stage);
    }


}