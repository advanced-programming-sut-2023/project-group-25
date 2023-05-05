package Model;

import java.util.ArrayList;

public class Cell {
    private String material;
    private Building building = null;
    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<NaturalBlock> naturalBlocks = new ArrayList<NaturalBlock>();
    private boolean hasOil = false;
    private boolean hasTunnel = false;
    private boolean hasMoat = false;
    public boolean hasMoat() {
        return hasMoat;
    }
    public void setHasMoat(boolean hasMoat) {
        this.hasMoat = hasMoat;
    }
    
    public boolean hasOil() {
        return hasOil;
    }
    
    public void setHasOil(boolean hasOil) {
        this.hasOil = hasOil;
    }
    
    public boolean hasTunnel() {
        return hasTunnel;
    }
    
    public void setHasTunnel(boolean hasTunnel) {
        this.hasTunnel = hasTunnel;
    }
    
    public Cell(String material) {
        this.material = material;
    }

    public void addPerson(Person person) {
        people.add(person);
    }
    
    public void removePerson(Person person) {
        people.remove(person);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public ArrayList<NaturalBlock> getNaturalBlocks() {
        return naturalBlocks;
    }

    public boolean isHasTunnel() {
        return hasTunnel;
    }

}