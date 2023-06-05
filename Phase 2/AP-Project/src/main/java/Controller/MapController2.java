package Controller;

import Model.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MapController2 {
    public void loadMapToShow(Stage stage, Pane pane, Map map, int x, int y, int edgeLength) {
        //capacity: 31 x 16 (x50 pixels)
        
        if (edgeLength < 10 || edgeLength >= 100 || !isLocationAppropriateToShow(x, y, map, edgeLength)) {
            return;
        }
        
        int xCounter = 0, yCounter = 0;
        
        for (int i = x - 15 * 50 / edgeLength; i < x + 16 * 50 / edgeLength; i++) {
            for (int j = y - 7 * 50 / edgeLength; j < y + 9 * 50 / edgeLength + 2; j++) {
                Cell cell = map.getCells()[i][j];
                
                showBackground(pane, cell.getMaterial(), xCounter, yCounter, edgeLength);
                
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
    
    private boolean isLocationAppropriateToShow(int x, int y, Map map, int edgeLength) {
        return x - 15 * 50 / edgeLength >= 0 && y - 7 * 50 / edgeLength >= 0 && x + 16 * 50 / edgeLength <= map.getWidth() && y + 8 * 50 / edgeLength <= map.getLength();
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
    
    private void showBackground(Pane pane, String material, int x, int y, int edgeLength) {
        Label imageView = new Label();
        imageView.setPrefWidth(edgeLength);
        imageView.setPrefHeight(edgeLength);
        imageView.setLayoutX(edgeLength * x);
        imageView.setLayoutY(edgeLength * y);
        imageView.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0.2px; -fx-border-style: dash");
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
