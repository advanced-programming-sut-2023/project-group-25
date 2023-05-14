package Model;

import java.util.ArrayList;

public class Trade {
    private static ArrayList<Trade> allTrades = new ArrayList<>();
    private int id;
    private String ownerKingUsername;
    private String tradeType;
    private String resourceType;
    private int resourceAmount;
    private int price;
    private String message;
    
    public Trade(String kingUsername,int id, String tradeType, String resourceType, int resourceAmount, int price, String message) {
        this.ownerKingUsername = kingUsername;
        this.id = id;
        this.tradeType = tradeType;
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.message = message;
    }

    public static ArrayList<Trade> getAllTrades() {
        return allTrades;
    }

    public int getId() {
        return id;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public String getKingUsername() {
        return ownerKingUsername;
    }
}