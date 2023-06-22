package View;

import Controller.FileController;
import Controller.MainController;
import Controller.RegisterLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
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
    boolean correctUsername = false;
    boolean correctPassword = false;
    private boolean hide = false;
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
        listView.getItems().add("1- What city were you born in?");
        listView.getItems().add("2- What is your oldest sibling’s middle name?");
        listView.getItems().add("3- In what city or town did your parents meet?");
        listView.getItems().add("4- What is your mother’s last name?");
        listView.getItems().add("5- What was your first pet’s name?");
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
    }

    public void famousSlogan() {
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
    }

    public void customSlogan() {
        customSlogan.setVisible(true);
        sloganComboBox.setVisible(false);
        randomSlogan.setVisible(false);
        resetFields();
    }

    public void noSlogan() {
        customSlogan.setVisible(false);
        sloganComboBox.setVisible(false);
        randomSlogan.setVisible(false);
        resetFields();
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
            passwordText.setPrefHeight(0);
            confirmText.setPrefHeight(0);
            passwordPass.toFront();
            confirmPass.toFront();
        }
        else {
            passwordPass.setTranslateY(0);
            confirmPass.setTranslateY(0);
            passwordText.setTranslateY(-27);
            confirmText.setTranslateY(-27);
            passwordPass.setPrefHeight(0);
            confirmPass.setPrefHeight(0);
            passwordText.toFront();
            confirmText.toFront();
        }
    }

    public void usernameCompleting() {
        if (username.getText().length() == 0) {
            usernameError.setText("Username field is empty!");
            correctUsername = false;
            usernameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else if (registerLoginController.isUsernameValid(username.getText())) {
            usernameError.setText("Username accepted!");
            correctUsername = true;
            usernameError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        } else {
            usernameError.setText("This username format is invalid");
            correctUsername = false;
            usernameError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
    }

    public void checkPassword() {
        String result = registerLoginController.isPasswordWeak(passwordText.getText());
        if (passwordText.getText().length() == 0) {
            passwordError.setText("Password field is empty!");
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (!result.equals("success")) {
            passwordError.setText(result);
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        } else if (confirmText.getText().length() == 0) {
            passwordError.setText("Confirm field is empty!");
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (!passwordText.getText().equals(confirmText.getText())) {
            passwordError.setText("Password doesn't match the confirm!");
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        } else if (result.equals("success")) {
            passwordError.setText("Password accepted!");
            correctPassword = true;
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


}
