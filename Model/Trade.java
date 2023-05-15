package Model;

import java.util.ArrayList;

public class Trade {
    private static ArrayList<Trade> allTrades = new ArrayList<>();
    private int id;
    private String senderUsername;
    private String receiverUsername;
    private String tradeType;
    private String resourceType;
    private int resourceAmount;
    private int price;
    private String message;
    
    public Trade(String senderUsername, String receiverUsername, int id, String tradeType, String resourceType, int resourceAmount, int price, String message) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
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

    public String getSenderUsername() {
        return senderUsername;
    }
    public String getReceiverUsername() {
        return receiverUsername;
    }
}