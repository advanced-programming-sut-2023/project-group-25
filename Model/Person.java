package Model;

public class Person {
    private User king;
    private String type;
    private Cell location;


    public Person(User king, String type) {
        this.king = king;
        this.type = type;
    }
    
    public void setKing(User king) {
        this.king = king;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setLocation(Cell location) {
        this.location = location;
    }
    
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
