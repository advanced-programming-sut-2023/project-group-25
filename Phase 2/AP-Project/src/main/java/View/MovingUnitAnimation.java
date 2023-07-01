package View;

import Controller.MapController2;
import Model.Cell;
import Model.Map;
import Model.Person;
import javafx.animation.Transition;
import javafx.scene.Scene;
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
    public MovingUnitAnimation(Person person, Cell destination, Scene scene, Stage stage, Map map, double shownX, double shownY
            , Pane pane, int edgeLength) {
        this.setCycleDuration(Duration.millis(1000));
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
    }
    
    @Override
    protected void interpolate(double v) {
        System.out.println("1");
        Cell home = person.getLocation();
        home.removePerson(person);
//        int dx = destination.getX() - home.getX(), dy = destination.getY() - home.getY();
        person.setLocation(destination);
        destination.addPerson(person);
        mapController.loadMapToShow(scene, stage, pane, map, (int) shownX, (int) shownY, edgeLength);
    }
}