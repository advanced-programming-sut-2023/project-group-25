package View;

import Controller.MainController;
import Controller.RegisterLoginController;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterConfirmMenu extends Application implements Initializable {
    private final RegisterLoginController registerLoginController = FirstPage.changeMenuController.getRegisterLoginController();
    public ListView<String> listView;
    public Label captcha;
    public Label reset;
    public Label securityError;
    public TextField confirmText;
    public TextField answerText;
    public TextField captchaText;
    private String captchaValue;

    private String securityQuestion;
    private String answer;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane firstPage = FXMLLoader.load(new URL(FirstPage.class.getResource("/fxml/RegisterConfirmMenu.fxml").toExternalForm()));
        Background background = new Background(MainController.setFirstPageBackground("/images/RegisterConfirmMenu.png"));
        firstPage.setBackground(background);
        Scene scene = new Scene(firstPage);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCaptcha();
        listView.getItems().add("1- What city were you born in?");
        listView.getItems().add("2- What is your oldest sibling’s middle name?");
        listView.getItems().add("3- In what city or town did your parents meet?");
        listView.getItems().add("4- What is your mother’s last name?");
        listView.getItems().add("5- What was your first pet’s name?");
        Background background = new Background(MainController.setFirstPageBackground("/images/captcha/reset.png"));
        reset.setBackground(background);
    }

    public void setCaptcha() {
        String path = registerLoginController.getRandomCaptcha();
        String[] values = path.split("-");
        captchaValue = values[1];
        Background background = new Background(MainController.setFirstPageBackground(values[0]));
        captcha.setBackground(background);
    }

    public void reset(MouseEvent mouseEvent) {
        setCaptcha();
    }

    public void checkSecurityQuestion() {
        if (listView.getSelectionModel().getSelectedItem() == null) {
            securityError.setText("No security question has been selected!");
            securityError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else if (answerText.getText().equals("")) {
            securityError.setText("No answer has been provided!");
            securityError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else if (confirmText.getText().equals("")) {
            securityError.setText("No answer confirm has been provided!");
            securityError.setStyle("-fx-background-color: rgb(231, 227, 166); -fx-text-fill: #776605;");
        }
        else if (!confirmText.getText().equals(answerText.getText())) {
            securityError.setText("Answer doesn't match the confirm!");
            securityError.setStyle("-fx-background-color: rgba(217,150,150,0.68); -fx-text-fill: #830c0c;");
        }
        else{
            securityError.setText("Security question accepted!");
            securityError.setStyle("-fx-background-color: rgb(140,196,140); -fx-text-fill: #075407;");
        }
    }

    public void listClicked(MouseEvent mouseEvent) {
        checkSecurityQuestion();
    }

    public void answer(MouseEvent mouseEvent) {
        checkSecurityQuestion();
    }

    public void confirm(MouseEvent mouseEvent) {
        checkSecurityQuestion();
    }

    public void answerTyped(KeyEvent keyEvent) {
        checkSecurityQuestion();
    }

    public void confirmTyped(KeyEvent keyEvent) {
        checkSecurityQuestion();
    }

    public void submit(MouseEvent mouseEvent) {

    }

    public void clear(MouseEvent mouseEvent) {
        answerText.setText("");
        confirmText.setText("");
        captchaText.setText("");
    }

    public void register(MouseEvent mouseEvent) throws Exception {
        System.out.println(captchaValue);
        if(securityError.getText().equals("Security question accepted!") && captchaText.getText().equals(captchaValue)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText("Register Succeeded");
            alert.setContentText("User registered successfully!");
            alert.showAndWait();
        }
            new FirstPage().start(FirstPage.stage);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Register Failed");
            alert.setContentText("Please fill the security form properly!");
            alert.showAndWait();
        }
    }
}
