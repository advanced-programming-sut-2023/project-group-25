package View;

import Controller.*;
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
import java.util.ResourceBundle;


public class MainMenuGraphics extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    private final GameController gameController = FirstPage.changeMenuController.getgameController();
    public static Stage stage;

    public Button back;
    public VBox newGameContainer;
    public ListView allUsers;
    public Button start;
    public Button enterProfile;
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
        back.setText("\n\nLogout");
        allUsers.setMaxWidth(260);
        start.setMaxWidth(260);
        setUsernames();
        Background newGame = new Background(MainController.setFirstPageBackground("/images/newGameContainer.png"));
        newGameContainer.setBackground(newGame);
        Background enterProf = new Background(MainController.setFirstPageBackground("/images/enterProfile.png"));
        enterProfile.setBackground(enterProf);
        allUsers.setEditable(false);
    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        FileController.addStayLoggedInForUser(RegisterLoginController.getCurrentUser().getUsername(),false);
        new FirstPage().start(FirstPage.stage);
    }

    public void setUsernames() {
        ArrayList<User> users = FileController.getAllUsersWithoutOwner("src/main/java/Database/Users.txt",RegisterLoginController.getCurrentUser().getUsername());
        for(int i = 0; i<users.size(); i++) {
                listViewCheckBoxes.add(new CheckBox(users.get(i).getUsername()));
                allUsers.getItems().add(listViewCheckBoxes.get(i));
        }
    }

    public void start(MouseEvent mouseEvent) throws Exception {
        if(getSelectedUsernames().size() == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("New Game Creation Failed");
            alert.setContentText("Please choose at least one user");
            alert.showAndWait();
        }
        else {
            gameController.newGameGraphics(getSelectedUsernames());
            new MapMenuGraphics().start(FirstPage.stage);
        }
    }

    public ArrayList<String> getSelectedUsernames() {
        ArrayList<String> selectedUsernames = new ArrayList<>();
        for(int i = 0; i<listViewCheckBoxes.size(); i++) {
            if(listViewCheckBoxes.get(i).isSelected()) {
                selectedUsernames.add(listViewCheckBoxes.get(i).getText());
            }
        }
        selectedUsernames.add(RegisterLoginController.getCurrentUser().getUsername());
        return selectedUsernames;
    }

    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception {
        new FirstPage().start(FirstPage.stage);
    }

    public void profileHover(MouseEvent mouseDragEvent) {
        Background enterProf = new Background(MainController.setFirstPageBackground("/images/enterProfile2.png"));
        enterProfile.setBackground(enterProf);
    }

    public void profileUnhove(MouseEvent mouseEvent) {
        Background enterProf = new Background(MainController.setFirstPageBackground("/images/enterProfile.png"));
        enterProfile.setBackground(enterProf);
    }
}