package Model;

import java.util.ArrayList;

public class AttackEquipment {
    private String name;
    private ArrayList<Product> usedMaterials = new ArrayList<>();
    private User king;
    private ArrayList<MilitaryPerson> engineers = new ArrayList<>();
    
    public AttackEquipment(String name, ArrayList<Product> usedMaterials, User king, ArrayList<MilitaryPerson> engineers) {
        this.name = name;
        this.usedMaterials = usedMaterials;
        this.king = king;
        this.engineers = engineers;
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
    
    public ArrayList<MilitaryPerson> getEngineers() {
        return engineers;
    }
}
