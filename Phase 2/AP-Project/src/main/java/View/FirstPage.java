package View;

import Controller.ChangeMenuController;
import Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class FirstPage extends Application {
    public static Stage stage;
    public final static ChangeMenuController changeMenuController =new ChangeMenuController();
    @Override
    public void start(Stage stage) throws Exception {
        FirstPage.stage = stage;
        GridPane firstPage = FXMLLoader.load(new URL(FirstPage.class.getResource("/fxml/FirstPage.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/FirstPage.png"));
        firstPage.setBackground(background);
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
        /*canner scanner = new Scanner(System.in);
        ChangeMenuController controller = new ChangeMenuController();
        controller.run(scanner);*/
    }


    public void register() throws Exception {
        new RegisterMenu().start(FirstPage.stage);
    }
    
    public void login() throws Exception {
        new MapMenuGraphics().start(FirstPage.stage);
    }
}