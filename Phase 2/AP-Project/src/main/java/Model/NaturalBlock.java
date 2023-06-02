package Model;

public class NaturalBlock {
    private String name;
    private String category;
    private String direction;
    
    public NaturalBlock(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
}
