package View;

import Model.Cell;
import Model.Person;
import javafx.animation.Transition;

public class MovingUnitAnimation extends Transition {
    private Person person;
    private Cell destination;
    public MovingUnitAnimation(Person person, Cell destination) {
        this.person = person;
        this.destination = destination;
    }
    
    @Override
    protected void interpolate(double v) {
    
    }
}