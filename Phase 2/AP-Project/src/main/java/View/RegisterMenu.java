package View;

import Controller.FileController;
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

public class RegisterMenu extends Application implements Initializable {
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
    public Button back;
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
        back.setText("\n\nFirst Page");
    }

    public void famousSlogan() {
        checkFamousSlogan();
        sloganComboBox.setVisible(true);
        randomSlogan.setVisible(false);
        customSlogan.setVisible(false);
        resetFields();
    }

    public void randomSlogan() {
        randomSlogan.setVisible(true);
        sloganComboBox.setVisible(false);
        customSlogan.setVisible(false);
        resetFields();
        sloganError.setText("Slogan accepted!");
        sloganError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        userSlogan = registerLoginController.generateRandomSlogan();
        randomSlogan.setText(userSlogan);
    }

    public void customSlogan() {
        customSlogan.setVisible(true);
        sloganComboBox.setVisible(false);
        randomSlogan.setVisible(false);
        checkCustomSlogan();
        resetFields();
    }

    public void checkCustomSlogan() {
        if(customSlogan.getText().equals("")) {
            sloganError.setText("Slogan field is empty!");
            sloganError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else {
            userSlogan = customSlogan.getText();
            sloganError.setText("Slogan accepted!");
            sloganError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        }
    }

    public void checkFamousSlogan() {
        if(sloganComboBox.getSelectionModel().getSelectedItem() == null) {
            sloganError.setText("Slogan field is empty!");
            sloganError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else {
            userSlogan = sloganComboBox.getSelectionModel().getSelectedItem();
            System.out.println(userSlogan);
            sloganError.setText("Slogan accepted!");
            sloganError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        }
    }
    public void selectFamousSlogan(ActionEvent mouseEvent) {
       checkFamousSlogan();
    }

    public void noSlogan() {
        customSlogan.setVisible(false);
        sloganComboBox.setVisible(false);
        randomSlogan.setVisible(false);
        resetFields();
        userSlogan = "";
        sloganError.setText("Slogan accepted!");
        sloganError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
    }

    public void resetFields() {
        randomSlogan.setText("");
        sloganComboBox.setPromptText("choose a slogan:");
        customSlogan.setText("");
    }

    public void hidePassword() {
        hide = !hide;
        if(hide) {
            passwordPass.setTranslateY(-27);
            confirmPass.setTranslateY(-27);
            passwordText.setTranslateY(0);
            confirmText.setTranslateY(0);
            passwordPass.toFront();
            confirmPass.toFront();
        }
        else {
            confirmPass.setTranslateY(0);
            passwordPass.setTranslateY(0);
            passwordText.setTranslateY(-27);
            confirmText.setTranslateY(-27);
            passwordText.toFront();
            confirmText.toFront();
        }
    }

    public void usernameCompleting() {
        if (username.getText().length() == 0) {
            usernameError.setText("Username field is empty!");
            correctUsername = false;
            usernameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if(FileController.getUserByUsername(username.getText()) != null) {
            usernameError.setText("This username already exists!");
            correctUsername = false;
            usernameError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
        else if (registerLoginController.isUsernameValid(username.getText())) {
            usernameError.setText("Username accepted!");
            correctUsername = true;
            usernameError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        } else {
            usernameError.setText("This username format is invalid!");
            correctUsername = false;
            usernameError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
    }

    public void checkPassword() {
        String result = registerLoginController.isPasswordWeak(passwordText.getText());
        if (passwordText.getText().length() == 0) {
            passwordError.setText("Password field is empty!");
            passwordError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (!result.equals("success")) {
            passwordError.setText(result);
            passwordError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        } else if (confirmText.getText().length() == 0) {
            passwordError.setText("Confirm field is empty!");
            passwordError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (!passwordText.getText().equals(confirmText.getText())) {
            passwordError.setText("Password doesn't match the confirm!");
            passwordError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        } else if (result.equals("success")) {
            passwordError.setText("Password accepted!");
            passwordError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        }
    }

    public void passwordTextCompleting() {
        passwordPass.setText(passwordText.getText());
        checkPassword();
    }

    public void passwordPassCompleting() {
        passwordText.setText(passwordPass.getText());
        checkPassword();
    }

    public void confirmTextCompleting() {
        confirmPass.setText(confirmText.getText());
        checkPassword();
    }

    public void confirmPassCompleting() {
        confirmText.setText(confirmPass.getText());
        checkPassword();
    }


    public void randomPassword() {
        randomPassword = !randomPassword;
        if(randomPassword) {
           String password = registerLoginController.generateRandomPassword();
            passwordText.setText(password);
            passwordPass.setText(password);
        }
        else {
            passwordText.setText("");
            passwordPass.setText("");
        }
        checkPassword();
    }

    public void nicknameCompleting() {
        if (nickname.getText().length() == 0) {
            nicknameError.setText("Nickname field is empty!");
            nicknameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else {
            nicknameError.setText("Nickname accepted!");
            nicknameError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        }
    }

    public void emailCompleting() {
        System.out.println(email.getText());
        if (email.getText().length() == 0) {
            emailError.setText("Email field is empty!");
            emailError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else if (registerLoginController.isEmailValid(email.getText())) {
            emailError.setText("Email accepted!");
            emailError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        } else {
            emailError.setText("This Email format is invalid");
            emailError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
    }

    public void submit(MouseEvent mouseEvent) throws Exception {
        if(passwordError.getText().equals("Password accepted!") && usernameError.getText().equals("Username accepted!") &&
                nicknameError.getText().equals("Nickname accepted!") && emailError.getText().equals("Email accepted!")
                && sloganError.getText().equals("Slogan accepted!")) {
            userUsername = username.getText();
            userPassword = passwordText.getText();
            userNickname = nickname.getText();
            userEmail = email.getText();
            setRegisteringUser();
            new RegisterConfirmMenu().start(FirstPage.stage);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Register Failed");
            alert.setContentText("Please fill the register form properly!");
            alert.showAndWait();
        }
    }

    public void clear(MouseEvent mouseEvent) {
        username.setText("");
        passwordPass.setText("");
        passwordText.setText("");
        confirmText.setText("");
        confirmPass.setText("");
        email.setText("");
        nickname.setText("");
        noSloganR.setSelected(true);
    }

    public void customSloganTyped(KeyEvent keyEvent) {
        checkCustomSlogan();
    }

    public static String getUserNickname() {
        return userNickname;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public RegisterLoginController getRegisterLoginController() {
        return registerLoginController;
    }

    public static String getUserSlogan() {
        return userSlogan;
    }

    public static String getUserUsername() {
        return userUsername;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public void setRegisteringUser() throws NoSuchAlgorithmException {
        userPassword = RegisterLoginController.passwordToSHA(userPassword);
        registeringUser = new User(userUsername,userPassword,userPassword,userNickname,userEmail,userSlogan);
    }

    public static User getRegisteringUser() {
        return registeringUser;
    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new FirstPage().start(FirstPage.stage);
    }
}
