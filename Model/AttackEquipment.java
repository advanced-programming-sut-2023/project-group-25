package Model;

import java.util.ArrayList;

public class AttackEquipment {
    private String name;
    private ArrayList<Product> usedMaterials = new ArrayList<>();
    private User king;
    private MilitaryPerson engineer;
    
    public AttackEquipment(String name, ArrayList<Product> usedMaterials, User king, MilitaryPerson engineer) {
        this.name = name;
        this.usedMaterials = usedMaterials;
        this.king = king;
        this.engineer = engineer;
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Product> getUsedMaterials() {
        return usedMaterials;
    }
    
    public User getKing() {
        return king;
    }
    
    public MilitaryPerson getEngineer() {
        return engineer;
    }
}
