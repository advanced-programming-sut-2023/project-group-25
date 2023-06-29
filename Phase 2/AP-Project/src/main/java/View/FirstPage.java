package View;

import Controller.ChangeMenuController;
import Controller.FileController;
import Controller.MainController;
import Controller.RegisterLoginController;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class FirstPage extends Application implements Initializable {
    public final static ChangeMenuController changeMenuController = new ChangeMenuController();
    public static Stage stage;
    public javafx.scene.control.Button registerBtn;
    public Button loginBtn;
    
    public static void main(String[] args) throws Exception {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FirstPage.stage = stage;
        User current = FileController.getFirstStayLoggedIn();
        if (current != null) {
            RegisterLoginController.setCurrentUser(current);
            new MainMenuGraphics().start(FirstPage.stage);
        } else {
            GridPane firstPage = FXMLLoader.load(new URL(FirstPage.class.getResource("/fxml/FirstPage.fxml").toExternalForm()));
            Background background = new Background(MainController.setFirstPageBackground("/images/FirstPage.png"));
            firstPage.setBackground(background);
            Scene scene = new Scene(firstPage);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }
    }
    
    public void register() throws Exception {
        new RegisterMenu().start(FirstPage.stage);
    }
    
    public void login() throws Exception {
        new LoginMenu().start(FirstPage.stage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Background register = new Background(MainController.setFirstPageBackground("/images/registerBtn.png"));
        registerBtn.setBackground(register);
        Background login = new Background(MainController.setFirstPageBackground("/images/loginBtn.png"));
        loginBtn.setBackground(login);
    }
}