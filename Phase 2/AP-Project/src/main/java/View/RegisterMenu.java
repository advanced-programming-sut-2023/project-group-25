package View;

import Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterMenu extends Application implements Initializable {

    public ListView<String> listView;
    public ComboBox<String> sloganComboBox;
    public TextField customSlogan;
    public TextField randomSlogan;
    public TextField password;
    public TextField username;
    public Label usernameError;
    public Label passwordError;
    public TextField confirm;
    boolean correctUsername = false;
    boolean correctPassword = false;
    private String lastPassword;
    private String lastConfirm;
    private boolean isStar = false;
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

    public void showPassword() {
        password.setText(FirstPage.changeMenuController.getRegisterLoginController().hideShowPassword(password.getText(), lastPassword));
    }

    public void usernameCompleting() {
        if (username.getText().length() == 0) {
            usernameError.setText("Username field is empty!");
            correctUsername = false;
            usernameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else if (FirstPage.changeMenuController.getRegisterLoginController().isUsernameValid(username.getText())) {
            usernameError.setText("Username accepted!");
            correctUsername = true;
            usernameError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        } else {
            usernameError.setText("This username format is invalid");
            correctUsername = false;
            usernameError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
    }

    public void passwordCompleting() {
        lastPassword = password.getText();
        checkPassword();
    }

    public void confirmCompleting() {
        lastConfirm = confirm.getText();
        checkPassword();
    }

    public void checkPassword() {
        String result = FirstPage.changeMenuController.getRegisterLoginController().isPasswordWeak(password.getText());
        if (password.getText().length() == 0) {
            passwordError.setText("Password field is empty!");
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (!result.equals("success")) {
            passwordError.setText(result);
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        } else if (confirm.getText().length() == 0) {
            passwordError.setText("Confirm field is empty!");
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (!confirm.getText().equals(password.getText())) {
            passwordError.setText("Password doesn't match the confirm!");
            correctPassword = false;
            passwordError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        } else if (result.equals("success")) {
            passwordError.setText("Password accepted!");
            correctPassword = true;
            passwordError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        }
    }
}
