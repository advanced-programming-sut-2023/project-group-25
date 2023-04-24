package Model;

import java.util.ArrayList;

public class Trade {
    private static ArrayList<Trade> allTrades = new ArrayList<>();
    private int id;
    private String tradeType;
    private String resourceType;
    private String resourceAmount;
    private int price;
    private String message;
    
    public Trade(int id, String tradeType, String resourceType, String resourceAmount, int price, String message) {
        this.id = id;
        this.tradeType = tradeType;
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.message = message;
    }
}