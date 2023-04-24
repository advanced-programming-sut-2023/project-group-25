package Model;

import java.util.ArrayList;

public class Product {
    public static ArrayList<Product> allProducts = new ArrayList<>();
    private String name;
    private int cost;
    private int price;
    private int count = 0;
    private String category;
    private ArrayList<Product> usedMaterials;
    
    
    public Product(String name, int cost, int price, String category, ArrayList<Product> usedMaterials) {
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.category = category;
        this.usedMaterials = usedMaterials;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getCategory() {
        return category;
    }
    
    public ArrayList<Product> getUsedMaterials() {
        return usedMaterials;
    }
    
    public void changeProductCount(int count) {
    
    }
    
    public static Product getProductByName(String name) {
        for (Product product : allProducts) {
            if (product.getName().equals(name)) return product;
        }
        return null;
    }
}
