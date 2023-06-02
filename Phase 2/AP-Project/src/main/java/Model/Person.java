package Model;

public class Person {
    private User king;
    private String type;
    private Cell location;
    protected int movingRange = 1000;
    
    public Person(User king, String type) {
        this.king = king;
        this.type = type;
    }
    
    public Person(String type) {
        this.type = type;
    }
    
    public User getKing() {
        return king;
    }
    
    public void setKing(User king) {
        this.king = king;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Cell getLocation() {
        return location;
    }
    
    public void setLocation(Cell location) {
        this.location = location;
    }
    
}
