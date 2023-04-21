package Model;

public class Person {
    private User king;
    private String type;
    private Cell location;


    public Person(User king, String type, Cell location) {
        this.king = king;
        this.type = type;
        this.location = location;
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
