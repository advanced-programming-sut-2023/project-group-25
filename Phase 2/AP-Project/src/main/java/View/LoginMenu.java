package View;

import Controller.MainController;
import Controller.RegisterLoginController;
import Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginMenu extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    public ListView<String> listView;
    public ComboBox<String> sloganComboBox;
    public TextField customSlogan;
    public TextField randomSlogan;
    public TextField username;
    public Label usernameError;
    public Label passwordError;
    public TextField passwordText;
    public PasswordField passwordPass;
    public TextField confirmText;
    public PasswordField confirmPass;
    public CheckBox Hide;
    public TextField nickname;
    public TextField email;
    public Label nicknameError;
    public Label emailError;
    public RadioButton famousSloganR;
    public RadioButton customSloganR;
    public RadioButton randomSloganR;
    public RadioButton noSloganR;
    public Label sloganError;
    boolean correctUsername = false;
    private boolean hide = false;
    private boolean randomPassword = false;
    private static String userSlogan;
    private static String userUsername;
    private static String userPassword;
    private static String userNickname;
    private static String userEmail;
    private static User registeringUser;
    @Override
    public void start(Stage stage) throws Exception {
        GridPane firstPage = FXMLLoader.load(new URL(FirstPage.class.getResource("/fxml/RegisterMenu.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/RegisterMenu.png"));
        firstPage.setBackground(background);
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        listView.getItems().add("1- What city were you born in?");
//        listView.getItems().add("2- What is your oldest sibling’s middle name?");
//        listView.getItems().add("3- In what city or town did your parents meet?");
//        listView.getItems().add("4- What is your mother’s last name?");
//        listView.getItems().add("5- What was your first pet’s name?");
        sloganComboBox.getItems().add("Through adversity comes strength!");
        sloganComboBox.getItems().add("Don’t be afraid to fail!");
        sloganComboBox.getItems().add("Compete with yourself!");
        sloganComboBox.getItems().add("Build and Explore!");
        sloganComboBox.getItems().add("Keep calm and check mate the king!");
        sloganComboBox.getItems().add("Hone your skills!");
        passwordPass.setTranslateY(-27);
        confirmPass.setTranslateY(-27);
        Hide.setSelected(true);
        hide = true;
        randomSlogan.setEditable(false);
    }
}
