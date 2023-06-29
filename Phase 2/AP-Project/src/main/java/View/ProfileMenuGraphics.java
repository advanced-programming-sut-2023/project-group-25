package View;

import Controller.FileController;
import Controller.GameController;
import Controller.MainController;
import Controller.RegisterLoginController;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ProfileMenuGraphics extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    public static Stage stage;
    public Button back;
    @Override
    public void start(Stage stage) throws Exception {
        ProfileMenuGraphics.stage = stage;
        GridPane firstPage = FXMLLoader.load(new URL(ProfileMenuGraphics.class.getResource("/fxml/ProfileMenu.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/profileMenu.png"));
        firstPage.setBackground(background);
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setText("\n\nMain Menu");
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenuGraphics().start(FirstPage.stage);
    }
}