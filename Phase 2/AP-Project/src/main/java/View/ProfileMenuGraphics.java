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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ProfileMenuGraphics extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    public static Stage stage;
    public Button back;
    public Button username;
    public Button changeUsername;
    public Button nickname;
    public Button changeNickname;
    public Button email;
    public Button changeEmail;
    public Button slogan;
    public Button changeSlogan;
    public VBox avatar;
    public HBox changeAvatar;
    public Button avatarImg;
    public Button defAv1;
    public Button defAv2;
    public Button defAv3;
    public Button defAv4;
    public Button defAv5;
    public Button defAv6;

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
        changeUsername.setText("Change Username ...");
        nickname.setBackground(usernameBack);
        nickname.setText("NICKNAME: " + RegisterLoginController.getCurrentUser().getNickname());
        changeNickname.setBackground(usernameChangeBack);
        changeNickname.setText("Change Nickname ...");
        email.setBackground(usernameBack);
        email.setText("EMAIL: " + RegisterLoginController.getCurrentUser().getEmail());
        changeEmail.setBackground(usernameChangeBack);
        changeEmail.setText("Change Email ...");
        slogan.setBackground(usernameBack);
        slogan.setText("SLOGAN: " + RegisterLoginController.getCurrentUser().getSlogan());
        changeSlogan.setBackground(usernameChangeBack);
        changeSlogan.setText("Change Slogan ...");
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
}