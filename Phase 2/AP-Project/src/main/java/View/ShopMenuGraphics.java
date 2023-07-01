package View;

import Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class ShopMenuGraphics extends Application implements Initializable {

    public Button back;
    public Button meat;
    public Button bread;
    public Button cheese;
    public Button apple;
    public Button wheat;
    public Button flour;
    public Button wood;
    public Button stone;
    public Button iron;
    public Button spear;
    public Button bow;
    public Button mace;
    public Button crossbow;
    public Button pike;
    public Button sword;
    public Button leatherArmor;
    public Button ale;
    public Button hop;
    public Button metalArmor;

    public static void main(String[] args) throws Exception {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {

            GridPane firstPage = FXMLLoader.load(new URL(ShopMenuGraphics.class.getResource("/fxml/ShopMenu.fxml").toExternalForm()));
            Background background = new Background(MainController.setFirstPageBackground("/images/shopMenu.png"));
            firstPage.setBackground(background);
            Scene scene = new Scene(firstPage);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setText("\n\nBack");
    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new GameGraphics(FirstPage.changeMenuController).start(FirstPage.stage);
    }
}