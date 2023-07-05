package View;

import Controller.FileController;
import Controller.GameController;
import Controller.MainController;
import Controller.TradeController;
import Model.Kingdom;
import Model.Product;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SendRequest extends Application implements Initializable {

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
    public VBox board;
    public Label img;
    public TextField amount;
    public ComboBox usernames;
    public TextField message;
    Product product;
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

    public static void main(String[] args) throws Exception {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {

        GridPane firstPage = FXMLLoader.load(new URL(ShopMenuGraphics.class.getResource("/fxml/SendRequest.fxml").toExternalForm()));
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
        initializeUsers();
        Tooltips();
    }

    public void initializeUsers() {
        ArrayList<Kingdom> users = GameController.currentGame.getKingdoms();
        for(int i=0; i<users.size();i++) {
            if(!users.get(i).getKing().getUsername().equals(GameController.currentGame.turn.getCurrentKing().getUsername())) {
                usernames.getItems().add(users.get(i).getKing().getUsername());
            }
        }
    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new TradeMenuGraphics().start(FirstPage.stage);
    }

    public void Tooltips() {
        Tooltip woodT = new Tooltip(GameController.shopTooltip("wood"));
        Tooltip meatT = new Tooltip(GameController.shopTooltip("meat"));
        Tooltip breadT = new Tooltip(GameController.shopTooltip("bread"));
        Tooltip cheeseT = new Tooltip(GameController.shopTooltip("cheese"));
        Tooltip appleT = new Tooltip(GameController.shopTooltip("apple"));
        Tooltip wheatT = new Tooltip(GameController.shopTooltip("wheat"));
        Tooltip flourT = new Tooltip(GameController.shopTooltip("flour"));
        Tooltip stoneT = new Tooltip(GameController.shopTooltip("stone"));
        Tooltip ironT = new Tooltip(GameController.shopTooltip("iron"));
        Tooltip spearT = new Tooltip(GameController.shopTooltip("spear"));
        Tooltip bowT = new Tooltip(GameController.shopTooltip("bow"));
        Tooltip maceT = new Tooltip(GameController.shopTooltip("mace"));
        Tooltip crossbowT = new Tooltip(GameController.shopTooltip("crossbow"));
        Tooltip pikeT = new Tooltip(GameController.shopTooltip("pike"));
        Tooltip swordT = new Tooltip(GameController.shopTooltip("sword"));
        Tooltip leatherArmorT = new Tooltip(GameController.shopTooltip("leatherArmor"));
        Tooltip metalArmorT = new Tooltip(GameController.shopTooltip("metalArmor"));
        Tooltip aleT = new Tooltip(GameController.shopTooltip("ale"));
        Tooltip hopT = new Tooltip(GameController.shopTooltip("hop"));
        wood.setTooltip(woodT);
        meat.setTooltip(meatT);
        bread.setTooltip(breadT);
        cheese.setTooltip(cheeseT);
        apple.setTooltip(appleT);
        wheat.setTooltip(wheatT);
        flour.setTooltip(flourT);
        wood.setTooltip(woodT);
        stone.setTooltip(stoneT);
        iron.setTooltip(ironT);
        spear.setTooltip(spearT);
        bow.setTooltip(bowT);
        mace.setTooltip(maceT);
        crossbow.setTooltip(crossbowT);
        pike.setTooltip(pikeT);
        sword.setTooltip(swordT);
        leatherArmor.setTooltip(leatherArmorT);
        ale.setTooltip(aleT);
        hop.setTooltip(hopT);
        metalArmor.setTooltip(metalArmorT);
    }

    public void meat(MouseEvent mouseEvent) {
        product = FileController.getProductByName("meat");
        board.setVisible(true);
        img.setBackground(meat1);
    }


    public void stone(MouseEvent mouseEvent) {
        product = FileController.getProductByName("stone");
        board.setVisible(true);
        img.setBackground(rock1);
    }

    public void buy(MouseEvent mouseEvent) {
        if(amount.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Buy Failed");
            alert.setContentText("empty amount!");
            alert.showAndWait();
        } else {
            String result = GameController.buyFromShop2(product,Integer.parseInt(amount.getText()));
            if(result.equals("success")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setHeaderText("Buy succeeded");
                alert.setContentText("product bought!");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Buy Failed");
                alert.setContentText(result);
                alert.showAndWait();
            }
        }
    }

    public void bread(MouseEvent mouseEvent) {
        product = FileController.getProductByName("bread");
        board.setVisible(true);
        img.setBackground(bread1);
    }

    public void cheese(MouseEvent mouseEvent) {
        product = FileController.getProductByName("cheese");
        board.setVisible(true);
        img.setBackground(cheese1);
    }

    public void apple(MouseEvent mouseEvent) {
        product = FileController.getProductByName("apple");
        board.setVisible(true);
        img.setBackground(apple1);
    }

    public void wheat(MouseEvent mouseEvent) {
        product = FileController.getProductByName("wheat");
        board.setVisible(true);
        img.setBackground(wheat1);
    }

    public void flour(MouseEvent mouseEvent) {
        product = FileController.getProductByName("flour");
        board.setVisible(true);
        img.setBackground(flower1);
    }

    public void wood(MouseEvent mouseEvent) {
        product = FileController.getProductByName("wood");
        board.setVisible(true);
        img.setBackground(wood1);
    }

    public void iron(MouseEvent mouseEvent) {
        product = FileController.getProductByName("iron");
        board.setVisible(true);
        img.setBackground(iron1);
    }

    public void spear(MouseEvent mouseEvent) {
        product = FileController.getProductByName("spear");
        board.setVisible(true);
        img.setBackground(spear1);
    }

    public void bow(MouseEvent mouseEvent) {
        product = FileController.getProductByName("bow");
        board.setVisible(true);
        img.setBackground(bow1);
    }

    public void mace(MouseEvent mouseEvent) {
        product = FileController.getProductByName("mace");
        board.setVisible(true);
        img.setBackground(mace1);
    }

    public void crossbow(MouseEvent mouseEvent) {
        product = FileController.getProductByName("crossbow");
        board.setVisible(true);
        img.setBackground(crossbow1);
    }

    public void pike(MouseEvent mouseEvent) {
        product = FileController.getProductByName("pike");
        board.setVisible(true);
        img.setBackground(pike1);
    }

    public void sword(MouseEvent mouseEvent) {
        product = FileController.getProductByName("sword");
        board.setVisible(true);
        img.setBackground(sword1);
    }

    public void leatherArmor(MouseEvent mouseEvent) {
        product = FileController.getProductByName("leatherArmor");
        board.setVisible(true);
        img.setBackground(leatherArmour1);
    }

    public void metalArmor(MouseEvent mouseEvent) {
        product = FileController.getProductByName("metalArmor");
        board.setVisible(true);
        img.setBackground(metalarmour1);
    }

    public void ale(MouseEvent mouseEvent) {
        product = FileController.getProductByName("ale");
        board.setVisible(true);
        img.setBackground(bear1);
    }

    public void hop(MouseEvent mouseEvent) {
        product = FileController.getProductByName("hop");
        board.setVisible(true);
        img.setBackground(hop1);
    }

    public void donate(MouseEvent mouseEvent) throws Exception {
        if(message.getText().equals("") || usernames.getSelectionModel().getSelectedItem() == null || amount.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Request Failed");
            alert.setContentText("Please fill the trade form properly!");
            alert.showAndWait();
        } else {
            TradeController.createTradeRequest2(Integer.parseInt(amount.getText()),message.getText(),product.getName(),0,
                    usernames.getSelectionModel().getSelectedItem().toString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText("Request Sent");
            alert.setContentText("Trade sent successfully!");
            alert.showAndWait();
            new TradeMenuGraphics().start(FirstPage.stage);
        }
    }

    public void request(MouseEvent mouseEvent) throws Exception {
        if(message.getText().equals("") || usernames.getSelectionModel().getSelectedItem() == null || amount.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Request Failed");
            alert.setContentText("Please fill the trade form properly!");
            alert.showAndWait();
        } else if (GameController.currentGame.getKingdomByKing(GameController.currentGame.turn.getCurrentKing().getUsername()).getInventory()
            < Integer.parseInt(amount.getText()) * product.getCost()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Request Failed");
            alert.setContentText("You don't have enough money!");
            alert.showAndWait();
        } else {
            TradeController.createTradeRequest2(Integer.parseInt(amount.getText()),message.getText(),product.getName(),product.getCost(),
                    usernames.getSelectionModel().getSelectedItem().toString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText("Request Sent");
            alert.setContentText("Trade sent successfully!");
            alert.showAndWait();
            new TradeMenuGraphics().start(FirstPage.stage);
        }
    }
}