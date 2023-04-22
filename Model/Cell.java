package Model;

import java.util.ArrayList;

public class Cell {
    private String Material;
    private Building building;
    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<NaturalBlock> naturalBlocks = new ArrayList<NaturalBlock>();
    private boolean hasTunnel;


    public void addPerson(Person person) {
        people.add(person);
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
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
