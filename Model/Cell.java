package Model;

import java.util.ArrayList;

public class Cell {
    private String material;
    private Building building = null;
    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<NaturalBlock> naturalBlocks = new ArrayList<NaturalBlock>();
    private ArrayList<Kingdom> kingdomsWithArrowsHere = new ArrayList<>();
    private boolean hasOil = false;
    private boolean hasTunnel = false;
    private int x;
    private int y;

    public Cell(int x, int y,String material) {
        this.x = x;
        this.y = y;
        this.material = material;
    }

    public Cell(String material) {
        this.material = material;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void addNaturalBlocks(NaturalBlock naturalBlock) {
        naturalBlocks.add(naturalBlock);
    }

    public boolean hasCellTunnel() {
        return hasTunnel;
    }
    
    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
    
    public void setNaturalBlocks(ArrayList<NaturalBlock> naturalBlocks) {
        this.naturalBlocks = naturalBlocks;
    }
    
    public ArrayList<Kingdom> getKingdomsWithArrowsHere() {
        return kingdomsWithArrowsHere;
    }
    
    public void setKingdomsWithArrowsHere(ArrayList<Kingdom> kingdomsWithArrowsHere) {
        this.kingdomsWithArrowsHere = kingdomsWithArrowsHere;
    }
    
    public boolean isHasOil() {
        return hasOil;
    }
    
    public boolean isHasTunnel() {
        return hasTunnel;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void addToKingdomsWithArrows(Kingdom kingdom) {
        kingdomsWithArrowsHere.add(kingdom);
    }
    
    public void destroyBuildingCompletely() {
        this.building = null;
    }
    
    public void killPeopleCompletely() {
        this.people = new ArrayList<>();
    }
}