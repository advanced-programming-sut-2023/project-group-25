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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainMenuGraphics extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    public static Stage stage;

    public Button back;
    public VBox newGameContainer;
    public ListView allUsers;
    public Button start;
    private ArrayList<CheckBox> listViewCheckBoxes = new ArrayList<>();
    @Override
    public void start(Stage stage) throws Exception {
        MainMenuGraphics.stage = stage;
        GridPane firstPage = FXMLLoader.load(new URL(MainMenuGraphics.class.getResource("/fxml/MainMenu.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/mainMenu.png"));
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
        back.setText("\n\nFirst Page");
        allUsers.setMaxWidth(260);
        start.setMaxWidth(260);
        setUsernames();
        Background newGame = new Background(MainController.setFirstPageBackground("/images/newGameContainer.png"));
        newGameContainer.setBackground(newGame);
        allUsers.setEditable(false);
    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new FirstPage().start(FirstPage.stage);
    }

    public void setUsernames() {
        ArrayList<User> users = FileController.getAllUsers("src/main/java/Database/Users.txt");
        for(int i = 0; i<users.size(); i++) {
                listViewCheckBoxes.add(new CheckBox(users.get(i).getUsername()));
                allUsers.getItems().add(listViewCheckBoxes.get(i));
        }
    }

    public void start(MouseEvent mouseEvent) throws Exception {
        new MapMenuGraphics().start(FirstPage.stage);
    }
}