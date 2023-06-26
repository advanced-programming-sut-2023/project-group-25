package View;

import Controller.MainController;
import Controller.RegisterLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterConfirmMenu extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    public ListView<String> listView;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane firstPage = FXMLLoader.load(new URL(FirstPage.class.getResource("/fxml/RegisterConfirmMenu.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/RegisterConfirmMenu.png"));
        firstPage.setBackground(background);
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().add("1- What city were you born in?");
        listView.getItems().add("2- What is your oldest sibling’s middle name?");
        listView.getItems().add("3- In what city or town did your parents meet?");
        listView.getItems().add("4- What is your mother’s last name?");
        listView.getItems().add("5- What was your first pet’s name?");
    }
}
