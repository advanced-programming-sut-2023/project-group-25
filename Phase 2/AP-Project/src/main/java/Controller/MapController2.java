package Controller;

import Model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MapController2 {
    private final int CELL_LENGTH = 50;
    public void loadMapToShow(Stage stage, Pane pane, Map map, int x, int y) {
        for (int i = x - 15; i < x + 5; i++) {
            for (int j = y - 10; j < y + 5 ; j++) {
                
                
                
                Cell cell = map.getCells()[i][j];
                
                showBackground(pane,i,j, cell.getMaterial());
                
                for (NaturalBlock naturalBlock : cell.getNaturalBlocks()) {
                    showNaturalBlock(pane, map, i, j, naturalBlock);
                }
                
                showBuilding(pane, map, i, j, cell.getBuilding());
    
                for (Person person : cell.getPeople()) {
                    showPerson(pane, map, i, j, person);
                }
                
            }
        }
        stage.show();
    }
    
    private void showNaturalBlock(Pane pane, Map map, int i, int j, NaturalBlock naturalBlock) {
//        ImageView imageView = new ImageView();
//        imageView.setFitHeight(30);
//        imageView.setFitWidth(30);
//        imageView.setX(100*i);
//        imageView.setY(100*j);
//        String address = "images/" + naturalBlock + ".jpg";
//        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(address)).toExternalForm()));
//        pane.getChildren().add(imageView);
    }
    
    private void showPerson(Pane pane, Map map, int i, int j, Person person) {
        //TODO
    }
    
    private void showBackground(Pane pane, int i, int j, String material) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setLayoutX(50*i);
        imageView.setLayoutY(50*j);
        String address = "/images/" + material + ".jpg";
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(address)).toExternalForm()));
        pane.getChildren().add(imageView);
    }
    
    private void showBuilding(Pane pane, Map map, int i, int j, Building building) {
        //TODO
    }
}
