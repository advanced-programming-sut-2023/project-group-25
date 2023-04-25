package Model;

import java.util.ArrayList;

public class Cell {
    private String material;
    private Building building;
    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<NaturalBlock> naturalBlocks = new ArrayList<NaturalBlock>();
    private boolean hasTunnel = false;
    
    public Cell(String material) {
        this.material = material;
    }
    
    public void addPerson(Person person) {
        people.add(person);
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
