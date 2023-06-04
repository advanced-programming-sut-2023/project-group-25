package Controller;

import Model.*;
import View.FirstPage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MapController2 {
    public void loadMapToShow(Stage stage, Pane pane, Map map, int x, int y) {
        //capacity: 31 x 15
        
        if (!isLocationAppropriateToShow(x, y, map)) {
            System.out.println("1");
            return;
        }
        
        int xCounter = 0, yCounter = 0;
        
        for (int i = x - 15; i < x + 16; i++) {
            for (int j = y - 7; j < y + 8 ; j++) {
                Cell cell = map.getCells()[i][j];
                
                showBackground(pane, cell.getMaterial(), xCounter, yCounter);
                
                for (NaturalBlock naturalBlock : cell.getNaturalBlocks()) {
                    showNaturalBlock(pane, i, j, naturalBlock);
                }
                
                showBuilding(pane, map, i, j, cell.getBuilding());
    
                for (Person person : cell.getPeople()) {
                    showPerson(pane, map, i, j, person);
                }
                yCounter++;
            }
            xCounter++;
            yCounter = 0;
        }
        
        //TODO: bar below the page
        
        stage.show();
    }
    
    private boolean isLocationAppropriateToShow(int x, int y, Map map) {
        return x - 15 >= 0 && y - 7 >= 0 && x + 16 <= map.getWidth() && y + 8 <= map.getLength();
    }
    
    private void showNaturalBlock(Pane pane, int i, int j, NaturalBlock naturalBlock) {
//        ImageView imageView = new ImageView();
//        imageView.setFitHeight(30);
//        imageView.setFitWidth(30);
//        imageView.setX(i);
//        imageView.setY(100*j);
//        String address = "images/" + naturalBlock + ".jpg";
//        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(address)).toExternalForm()));
//        pane.getChildren().add(imageView);
    }
    
    private void showPerson(Pane pane, Map map, int i, int j, Person person) {
        //TODO
    }
    
    private void showBackground(Pane pane, String material, int x, int y) {
        Label imageView = new Label();
        imageView.setPrefWidth(50);
        imageView.setPrefHeight(50);
        imageView.setLayoutX(50*x);
        imageView.setLayoutY(50*y);
        imageView.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0.5px; -fx-border-style: dash");
        String address = "/images/" + material + ".jpg";
        Background background = new Background(MainController.setFirstPageBackground(address));
        imageView.setBackground(background);
        pane.getChildren().add(imageView);
        pane.setStyle("-fx-spacing: 0");
    }
    
    private void showBuilding(Pane pane, Map map, int i, int j, Building building) {
        //TODO
    }
}
