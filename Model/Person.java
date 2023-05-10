package Model;

public class Person {
    private User king;
    private String type;
    private Cell location;


    public Person(String type) {
        this.type = type;
    }

    public User getKing() {
        return king;
    }

    public String getType() {
        return type;
    }

    public Cell getLocation() {
        return location;
    }

}
