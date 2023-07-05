package View;

import Controller.FileController;
import Controller.GameController;
import Controller.MainController;
import Controller.TradeController;
import Model.Product;
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


public class TradeHistory extends Application implements Initializable {

    public Button back;
    public ListView list;
    public TextField id;
    public TextField msg;

    public static void main(String[] args) throws Exception {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        GridPane firstPage = FXMLLoader.load(new URL(TradeHistory.class.getResource("/fxml/TradeHistory.fxml").toExternalForm()));
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
        list.getItems().add("No data to show!");

    }

    public void backToFirstPage(MouseEvent mouseEvent) throws Exception {
        new TradeMenuGraphics().start(FirstPage.stage);
    }

    public void showAllTrades() {
        ArrayList<String> allTrades = TradeController.showAllTrades3();
        for (int i = 0; i < allTrades.size(); i++)
            list.getItems().add(allTrades.get(i));
    }

    public void showSomeTrades() {
        ArrayList<String> allTrades = TradeController.showAllTrades2();
        for (int i = 0; i < allTrades.size(); i++)
            list.getItems().add(allTrades.get(i));
    }

    public void all(MouseEvent mouseEvent) {
        list.getItems().removeAll(list.getItems());
        showAllTrades();
        if (list.getItems().size() == 0)
            list.getItems().add("No data to show!");
    }

    public void receiver(MouseEvent mouseEvent) {
        list.getItems().removeAll(list.getItems());
        showSomeTrades();
        if (list.getItems().size() == 0)
            list.getItems().add("No data to show!");
    }

    public void accept(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        if (id.getText().equals("") || msg.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Accept Failed");
            alert.setContentText("Please fill the accept form properly!");
            alert.showAndWait();
        } else {
            String result = TradeController.acceptTrade2(id.getText(), msg.getText());
            if (result.equals("success")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setHeaderText("Accept Succeeded");
                alert.setContentText("trade accepted successfully!");
                alert.showAndWait();
                id.clear();
                msg.clear();
                list.getItems().removeAll(list.getItems());
                list.getItems().add("No data to show!");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Accept Failed");
                alert.setContentText(result);
                alert.showAndWait();
            }
        }
    }
}