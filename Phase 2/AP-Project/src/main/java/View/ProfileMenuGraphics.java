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
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ProfileMenuGraphics extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    public static Stage stage;
    public Button back;
    public Button username;
    public VBox changeUsername;
    public Button nickname;
    public VBox changeNickname;
    public Button email;
    public VBox changeEmail;
    public Button slogan;
    public VBox changeSlogan;
    public VBox avatar;
    public HBox changeAvatar;
    public Button avatarImg;
    public Button defAv1;
    public Button defAv2;
    public Button defAv3;
    public Button defAv4;
    public Button defAv5;
    public Button defAv6;
    public RadioButton r1;
    public RadioButton r2;
    public RadioButton r3;
    public RadioButton r4;
    public RadioButton r5;
    public RadioButton r6;
    public TextField newUsername;
    public Label usernameError;
    public TextField newNickname;
    public Label nicknameError;
    public TextField newEmail;
    public Label emailError;
    public TextField newSlogan;

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
        Background usernameBack = new Background(MainController.setFirstPageBackground("/images/profileInfo.png"));
        username.setBackground(usernameBack);
        username.setText("USERNAME: " + RegisterLoginController.getCurrentUser().getUsername());
        Background usernameChangeBack = new Background(MainController.setFirstPageBackground("/images/changeInfo.png"));
        changeUsername.setBackground(usernameChangeBack);
        nickname.setBackground(usernameBack);
        nickname.setText("NICKNAME: " + RegisterLoginController.getCurrentUser().getNickname());
        changeNickname.setBackground(usernameChangeBack);
        email.setBackground(usernameBack);
        email.setText("EMAIL: " + RegisterLoginController.getCurrentUser().getEmail());
        changeEmail.setBackground(usernameChangeBack);
        slogan.setBackground(usernameBack);
        if (RegisterLoginController.getCurrentUser().getSlogan().equals(""))
            slogan.setText("SLOGAN: Slogan is empty!");
        else
            slogan.setText("SLOGAN: " + RegisterLoginController.getCurrentUser().getSlogan());
        changeSlogan.setBackground(usernameChangeBack);
        Background avatarBack = new Background(MainController.setFirstPageBackground("/images/avatarContainer.png"));
        Background avatarChangeBack = new Background(MainController.setFirstPageBackground("/images/changeAvatar.png"));
        Background img = new Background(MainController.setFirstPageBackground(RegisterLoginController.getCurrentUser().getAvatarPath()));
        avatarImg.setBackground(img);
        avatar.setBackground(avatarBack);
        changeAvatar.setBackground(avatarChangeBack);
        Background av1 = new Background(MainController.setFirstPageBackground("/images/avatar/2.png"));
        Background av2 = new Background(MainController.setFirstPageBackground("/images/avatar/3.png"));
        Background av3 = new Background(MainController.setFirstPageBackground("/images/avatar/4.png"));
        Background av4 = new Background(MainController.setFirstPageBackground("/images/avatar/5.png"));
        Background av5 = new Background(MainController.setFirstPageBackground("/images/avatar/6.png"));
        Background av6 = new Background(MainController.setFirstPageBackground("/images/avatar/1.png"));
        defAv1.setBackground(av1);
        defAv2.setBackground(av2);
        defAv3.setBackground(av3);
        defAv4.setBackground(av4);
        defAv5.setBackground(av5);
        defAv6.setBackground(av6);
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenuGraphics().start(FirstPage.stage);
    }

    public void showUsernameChange(MouseEvent mouseEvent) {
        changeUsername.setVisible(true);
    }

    public void hideUsernameChange(MouseEvent mouseEvent) {
        changeUsername.setVisible(false);
    }

    public void hideNicknameChange(MouseEvent mouseEvent) {
        changeNickname.setVisible(false);
    }

    public void showNicknameChange(MouseEvent mouseEvent) {
        changeNickname.setVisible(true);
    }

    public void hideEmailChange(MouseEvent mouseEvent) {
        changeEmail.setVisible(false);
    }

    public void showEmailChange(MouseEvent mouseEvent) {
        changeEmail.setVisible(true);
    }


    public void hideSloganChange(MouseEvent mouseEvent) {
        changeSlogan.setVisible(false);
    }

    public void showSloganChange(MouseEvent mouseEvent) {
        changeSlogan.setVisible(true);
    }

    public void hideAvaterChange(MouseEvent mouseEvent) {
        changeAvatar.setVisible(false);
    }

    public void showAvtarChange(MouseEvent mouseEvent) {
        changeAvatar.setVisible(true);
    }

    public void changeAvatar(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        String path = "/images/avatar/";
        if (r1.isSelected())
            path += "2.png";
        else if (r2.isSelected())
            path += "3.png";
        else if (r3.isSelected())
            path += "4.png";
        else if (r4.isSelected())
            path += "5.png";
        else if (r5.isSelected())
            path += "6.png";
        else if (r6.isSelected())
            path += "1.png";
        FileController.changeAvatar(RegisterLoginController.getCurrentUser().getUsername(), path);
        RegisterLoginController.getCurrentUser().setAvatarPath(path);
        Background img = new Background(MainController.setFirstPageBackground(path));
        avatarImg.setBackground(img);
    }

    public void usernameCompleting(KeyEvent keyEvent) {
        if (newUsername.getText().length() == 0) {
            usernameError.setText("Username field is empty!");
            usernameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (FileController.getUserByUsername(newUsername.getText()) != null) {
            usernameError.setText("This username already exists!");
            usernameError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        } else if (registerLoginController.isUsernameValid(newUsername.getText())) {
            usernameError.setText("Username accepted!");
            usernameError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        } else {
            usernameError.setText("This username format is invalid!");
            usernameError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
    }

    public void changeUsername(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        if (usernameError.getText().equals("Username accepted!")) {
            FileController.changeUsername(RegisterLoginController.getCurrentUser().getUsername(), newUsername.getText());
            RegisterLoginController.getCurrentUser().setUsername(newUsername.getText());
            username.setText("USERNAME: " + newUsername.getText());
            newUsername.clear();
            usernameError.setText("New username field is empty!");
            usernameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Change Info Failed");
            alert.setContentText("Please fill the text field properly!");
            alert.showAndWait();
        }
    }

    public void changeNickname(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        if (nicknameError.getText().equals("Nickname accepted!")) {
            FileController.changeNickname(RegisterLoginController.getCurrentUser().getUsername(), newNickname.getText());
            RegisterLoginController.getCurrentUser().setNickname(newNickname.getText());
            nickname.setText("NICKNAME: " + newNickname.getText());
            newNickname.clear();
            nicknameError.setText("New nickname field is empty!");
            nicknameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Change Info Failed");
            alert.setContentText("Please fill the text field properly!");
            alert.showAndWait();
        }
    }

    public void nicknameCompleting(KeyEvent keyEvent) {
        if (newNickname.getText().length() == 0) {
            nicknameError.setText("Nickname field is empty!");
            nicknameError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else {
            nicknameError.setText("Nickname accepted!");
            nicknameError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        }
    }

    public void emailCompleting(KeyEvent keyEvent) {
        System.out.println(newEmail.getText());
        if (newEmail.getText().length() == 0) {
            emailError.setText("New email field is empty!");
            emailError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else if (registerLoginController.isEmailValid(newEmail.getText())) {
            emailError.setText("Email accepted!");
            emailError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        } else {
            emailError.setText("This Email format is invalid");
            emailError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
    }

    public void changeEmail(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        if (emailError.getText().equals("Email accepted!")) {
            FileController.changeEmail(RegisterLoginController.getCurrentUser().getUsername(), newEmail.getText());
            RegisterLoginController.getCurrentUser().setEmail(newEmail.getText());
            email.setText("EMAIL: " + newEmail.getText());
            newEmail.clear();
            emailError.setText("New Email field is empty!");
            emailError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Change Info Failed");
            alert.setContentText("Please fill the text field properly!");
            alert.showAndWait();
        }
    }

    public void changeSlogan(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        String newSlog = newSlogan.getText();
        if (newSlogan.getText().equals(""))
            newSlog = "Slogan is empty!";
        FileController.changeSlogan(RegisterLoginController.getCurrentUser().getUsername(), newSlogan.getText());
        RegisterLoginController.getCurrentUser().setSlogan(newSlogan.getText());
        slogan.setText("SLOGAN: " + newSlog);
        newSlogan.clear();
    }

    public void deleteSlogan(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        FileController.changeSlogan(RegisterLoginController.getCurrentUser().getUsername(), "");
        RegisterLoginController.getCurrentUser().setSlogan("");
        slogan.setText("SLOGAN: Slogan is empty!");
        newSlogan.clear();
    }
}