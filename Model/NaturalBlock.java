package Model;

public class NaturalBlock {
    private String name;
    private String category;
    
    public NaturalBlock(String name, String category) {
        this.name = name;
        this.category = category;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
}
