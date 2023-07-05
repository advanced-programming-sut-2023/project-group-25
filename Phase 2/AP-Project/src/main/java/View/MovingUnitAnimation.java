package View;

import Controller.MapController2;
import Model.Cell;
import Model.Map;
import Model.Person;
import javafx.animation.Transition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovingUnitAnimation extends Transition {
    private MapController2 mapController = new MapController2();
    private Person person;
    private Cell destination;
    private Scene scene;
    private Stage stage;
    private Map map;
    private double shownX;
    private double shownY;
    private Pane pane;
    private int edgeLength;
    private int signDx;
    private int signDy;
    private ImageView imageView;
    private int counter = 1;
    
    public MovingUnitAnimation(Person person, Cell destination, Scene scene, Stage stage, Map map, double shownX, double shownY
            , Pane pane, int edgeLength) {
        this.setCycleDuration(Duration.millis(3000));
        this.setCycleCount(-1);
        this.person = person;
        this.destination = destination;
        this.scene = scene;
        this.stage = stage;
        this.map = map;
        this.shownX = shownX;
        this.shownY = shownY;
        this.pane = pane;
        this.edgeLength = edgeLength;
        Cell home = person.getLocation();
        int dx = destination.getX() - home.getX();
        int dy = destination.getY() - home.getY();
        signDx = (dx > 0) ? 1 : -1;
        if (dx == 0) signDx = 0;
        signDy = (dy > 0) ? 1 : -1;
        if (dy == 0) signDy = 0;
        String address = "/images/Units/" + person.getType() + ".png";
        this.imageView = new ImageView(new Image(String.valueOf(getClass().getResource(address))));
        this.imageView.setFitWidth((float) edgeLength * 3 / 5);
        this.imageView.setFitHeight((float) edgeLength * 3 / 5);
        this.imageView.setLayoutX(person.getLocation().getX() * edgeLength);
        this.imageView.setLayoutY(person.getLocation().getY() * edgeLength);
        pane.getChildren().add(imageView);
    }
    
    @Override
    protected void interpolate(double v) {
        if (person.getLocation().getX() == destination.getX() && person.getLocation().getY() != destination.getY())
            signDx = 0;
        if (person.getLocation().getY() == destination.getY() && person.getLocation().getX() != destination.getX())
            signDy = 0;
        else if ((signDx == 0 && signDy == 0) || (person.getLocation().getX() == destination.getX()
                || person.getLocation().getY() == destination.getY())) {
            Cell cell = map.getCells()[person.getLocation().getX() + signDx][person.getLocation().getY() + signDy];
            person.getLocation().removePerson(person);
            person.setLocation(cell);
            cell.addPerson(person);
            pane.getChildren().remove(imageView);
            mapController.loadMapToShow(scene, stage, pane, map, (int) shownX, (int) shownY, edgeLength);
            this.stop();
        }
        if (person.getLocation().getX() == destination.getX() && person.getLocation().getY() != destination.getY())
            signDx = 0;
        if (person.getLocation().getY() == destination.getY() && person.getLocation().getX() != destination.getX())
            signDy = 0;
        else if ((signDx == 0 && signDy == 0) || (person.getLocation().getX() == destination.getX()
                || person.getLocation().getY() == destination.getY())) this.stop();
        imageView.setLayoutX(imageView.getLayoutX() + signDx * counter);
        imageView.setLayoutY(imageView.getLayoutY() + signDy * counter);
        imageView.toFront();
        if ((int) imageView.getLayoutX() / edgeLength == destination.getX()
                && (int) imageView.getLayoutY() / edgeLength != destination.getY()) signDx = 0;
        else if ((int) imageView.getLayoutY() / edgeLength == destination.getY()
                && (int) imageView.getLayoutX() / edgeLength != destination.getX()) signDy = 0;
        else if ((signDx == 0 && signDy == 0) || ((int) imageView.getLayoutX() / edgeLength == destination.getX()
                || (int) imageView.getLayoutY() / edgeLength == destination.getY())) {
            Cell cell = map.getCells()[(int) imageView.getLayoutX() / edgeLength][(int) imageView.getLayoutY() / edgeLength];
            person.getLocation().removePerson(person);
            person.setLocation(cell);
            cell.addPerson(person);
            pane.getChildren().remove(imageView);
            mapController.loadMapToShow(scene, stage, pane, map, (int) shownX, (int) shownY, edgeLength);
            this.stop();
        }
        counter++;
    }
}