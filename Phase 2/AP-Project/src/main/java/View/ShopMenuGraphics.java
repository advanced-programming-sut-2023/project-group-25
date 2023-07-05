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
        Background apple1 = new Background(MainController.setFirstPageBackground("/images/Shop/apple.png"));
        Background bear1 = new Background(MainController.setFirstPageBackground("/images/Shop/bear.png"));
        Background bow1 = new Background(MainController.setFirstPageBackground("/images/Shop/bow.png"));
        Background bread1 = new Background(MainController.setFirstPageBackground("/images/Shop/bread.png"));
        Background cheese1 = new Background(MainController.setFirstPageBackground("/images/Shop/cheese.png"));
        Background crossbow1 = new Background(MainController.setFirstPageBackground("/images/Shop/crossbow.png"));
        Background flower1 = new Background(MainController.setFirstPageBackground("/images/Shop/flower.png"));
        Background hop1 = new Background(MainController.setFirstPageBackground("/images/Shop/hop.png"));
        Background iron1 = new Background(MainController.setFirstPageBackground("/images/Shop/iron.png"));
        Background leatherArmour1 = new Background(MainController.setFirstPageBackground("/images/Shop/leatherArmour.png"));
        Background mace1 = new Background(MainController.setFirstPageBackground("/images/Shop/mace.png"));
        Background meat1 = new Background(MainController.setFirstPageBackground("/images/Shop/meat.png"));
        Background metalarmour1 = new Background(MainController.setFirstPageBackground("/images/Shop/metalarmour.png"));
        Background pike1 = new Background(MainController.setFirstPageBackground("/images/Shop/pike.png"));
        Background rock1 = new Background(MainController.setFirstPageBackground("/images/Shop/rock.png"));
        Background spear1 = new Background(MainController.setFirstPageBackground("/images/Shop/spear.png"));
        Background sword1 = new Background(MainController.setFirstPageBackground("/images/Shop/sword.png"));
        Background wheat1 = new Background(MainController.setFirstPageBackground("/images/Shop/wheat.png"));
        Background wood1 = new Background(MainController.setFirstPageBackground("/images/Shop/wood.png"));
        meat.setBackground(meat1);
        bread.setBackground(bread1);
        cheese.setBackground(cheese1);
        apple.setBackground(apple1);
        wheat.setBackground(wheat1);
        flour.setBackground(flower1);
        wood.setBackground(wood1);
        stone.setBackground(rock1);
        iron.setBackground(iron1);
        spear.setBackground(spear1);
        bow.setBackground(bow1);
        mace.setBackground(mace1);
        crossbow.setBackground(crossbow1);
        pike.setBackground(pike1);
        sword.setBackground(sword1);
        leatherArmor.setBackground(leatherArmour1);
        ale.setBackground(bear1);
        hop.setBackground(hop1);
        metalArmor.setBackground(metalarmour1);
    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new GameGraphics(FirstPage.changeMenuController).start(FirstPage.stage);
    }
}