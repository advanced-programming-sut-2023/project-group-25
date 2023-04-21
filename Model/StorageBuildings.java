package Model;

import java.util.ArrayList;

public class StorageBuildings extends Building {
    private ArrayList<Product> storedProducts = new ArrayList<>();
    private int capacity;
    private String storageCategory;

    public StorageBuildings(Building building, Cell location, User king, int capacity, String storageCategory) {
        super(building, location, king);
        this.capacity = capacity;
        this.storageCategory = storageCategory;
    }

    public void addProduct(Product product) {
        storedProducts.add(product);
    }

    public int countFreeSpace() {
        return (this.capacity - this.storedProducts.size());
    }

    public ArrayList<Product> getStoredProducts() {
        return storedProducts;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStorageCategory() {
        return storageCategory;
    }

}
