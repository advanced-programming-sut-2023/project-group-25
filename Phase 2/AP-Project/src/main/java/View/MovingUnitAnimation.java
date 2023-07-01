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
    
    public MovingUnitAnimation(Person person, Cell destination, Scene scene, Stage stage, Map map, double shownX, double shownY
            , Pane pane, int edgeLength) {
        this.setCycleDuration(Duration.millis(100));
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
        System.out.println("1");
        Cell home = person.getLocation();
        int dx = destination.getX() - home.getX();
        int dy = destination.getY() - home.getY();
        signDx = (dx > 0) ? 1 : -1;
        if (dx == 0) signDx = 0;
        signDy = (dy > 0) ? 1 : -1;
        if (dy == 0) signDy = 0;
    }
    
    @Override
    protected void interpolate(double v) {
        if (person.getLocation().getX() == destination.getX() && person.getLocation().getY() != destination.getY())
            signDx = 0;
        if (person.getLocation().getY() == destination.getY() && person.getLocation().getX() != destination.getX())
            signDy = 0;
        else if ((signDx == 0 && signDy == 0) || (person.getLocation().getX() == destination.getX()
                || person.getLocation().getY() == destination.getY())) this.stop();
        Cell cell = map.getCells()[person.getLocation().getX() + signDx][person.getLocation().getY() + signDy];
        person.getLocation().removePerson(person);
        person.setLocation(cell);
        cell.addPerson(person);
//        mapController.loadMapToShow(scene, stage, pane, map, (int) shownX, (int) shownY, edgeLength);
        String address = "/images/Units/" + person.getType() + ".png";
        ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource(address))));
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setLayoutX();
        if (person.getLocation().getX() == destination.getX() && person.getLocation().getY() != destination.getY())
            signDx = 0;
        if (person.getLocation().getY() == destination.getY() && person.getLocation().getX() != destination.getX())
            signDy = 0;
        else if ((signDx == 0 && signDy == 0) || (person.getLocation().getX() == destination.getX()
                || person.getLocation().getY() == destination.getY())) this.stop();
    }
}