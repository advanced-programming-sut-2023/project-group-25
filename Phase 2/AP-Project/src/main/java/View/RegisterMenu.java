package View;

import Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        String pass = password.getText();
       password = new PasswordField();
       password.setText(pass);
    }
}
