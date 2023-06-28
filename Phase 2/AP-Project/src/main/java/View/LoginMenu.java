package View;

import Controller.FileController;
import Controller.GameController;
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
    private final GameController gameController  = FirstPage.changeMenuController.getgameController();
    public TextField username;
    public TextField passwordText;
    public PasswordField passwordPass;
    public CheckBox Hide;
    public TextField captchaText;
    public Label captcha;
    public Button back;
    public Label reset;
    public CheckBox stayLoggedIn;
    private String captchaValue;

    private boolean hide = false;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane firstPage = FXMLLoader.load(new URL(FirstPage.class.getResource("/fxml/LoginMenu.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/login.png"));
        firstPage.setBackground(background);
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCaptcha();
        passwordPass.setTranslateY(-27);
        Hide.setSelected(true);
        hide = true;
        Background background = new Background(MainController.setFirstPageBackground("/images/captcha/reset.png"));
        reset.setBackground(background);
        back.setText("\n\nFirst Page");
    }

    public void hidePassword(MouseEvent mouseEvent) {
        hide = !hide;
        if (hide) {
            passwordPass.setTranslateY(-27);
            passwordText.setTranslateY(0);
            passwordPass.toFront();
        } else {
            passwordPass.setTranslateY(0);
            passwordText.setTranslateY(-27);
            passwordText.toFront();
        }
    }

    public void clear(MouseEvent mouseEvent) {
        username.setText("");
        passwordPass.setText("");
        passwordText.setText("");
        captchaText.setText("");
        setCaptcha();
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        String result = registerLoginController.graphicLogin(username.getText(), RegisterLoginController.passwordToSHA(passwordText.getText()));
        if (username.getText().equals("") || passwordText.getText().equals("") || captchaText.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Login Failed");
            alert.setContentText("Please fill the security form completely!");
            alert.showAndWait();
            setCaptcha();
        }
        else if(!captchaText.getText().equals(captchaValue)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Incorrect Captcha");
            alert.setContentText("Please type the captcha correctly!");
            alert.showAndWait();
            setCaptcha();
        }
        else if (result.equals("success")) {
            if(stayLoggedIn.isSelected())
                FileController.addStayLoggedInForUser(RegisterLoginController.getCurrentUser().getUsername(),true);
            RegisterLoginController.setCurrentUser(FileController.getUserByUsername(username.getText()));
            new MainMenuGraphics().start(FirstPage.stage);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Login Failed");
            alert.setContentText(result);
            alert.showAndWait();
            setCaptcha();
        }

    }
    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new FirstPage().start(FirstPage.stage);
    }

    public void reset(MouseEvent mouseEvent) {
        setCaptcha();
    }

    public void setCaptcha() {
        String path = registerLoginController.getRandomCaptcha();
        String[] values = path.split("-");
        captchaValue = values[1];
        Background background = new Background(MainController.setFirstPageBackground(values[0]));
        captcha.setBackground(background);
    }

    public void passwordTextCompleting() {
        passwordPass.setText(passwordText.getText());
    }

    public void passwordPassCompleting() {
        passwordText.setText(passwordPass.getText());
    }
}
