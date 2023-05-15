package Controller;

import Model.*;

import java.beans.PropertyEditorSupport;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class TradeController {
    private static GameController gameController = null;

    public TradeController(GameController gameController) {
        this.gameController = gameController;
    }


    public String showAllKingsUsernames() {
        String resultMessage = "";
        Game game = gameController.getCurrentGame();
        for(int i = 0; i<game.getKingdoms().size(); i++) {
            if(!gameController.getCurrentGame().turn.getCurrentKing().getUsername().equals(
                    game.getKingdoms().get(i).getKing().getUsername()))
            resultMessage += ((i+1) + "- " + game.getKingdoms().get(i).getKing().getUsername() + "\n");
        }
        return resultMessage;
    }

    public String createTradeRequest(ArrayList<String> allOptions, Matcher matcher, String receiverUsername) throws NoSuchAlgorithmException {
        if(!MainController.checkAllOptionsExist(matcher, allOptions))
            return "Invalid format for request; please try again: 2";
        String resourceType = MainController.getOptionsFromMatcher(matcher,"t",4);
        String resourceAmount = MainController.getOptionsFromMatcher(matcher,"a",4);
        String price = MainController.getOptionsFromMatcher(matcher,"p",4);
        String message = MainController.getOptionsFromMatcher(matcher,"m",4);
        Trade trade = new Trade(gameController.getCurrentGame().turn.getCurrentKing().getUsername(),receiverUsername,
                FileController.generateTradeId(),"request",resourceType,Integer.parseInt(resourceAmount),Integer.parseInt(price),message);
        FileController.addTradeToFile(trade, gameController.getCurrentGame());
        return "success";
    }

    public String showAllTrades() {
        String resultMessage = "";
        ArrayList<Trade> allTrades = FileController.getAllTradesByKing(gameController.getCurrentGame().turn.getCurrentKing().getUsername(),
                gameController.getCurrentGame());
        for(int i = 0; i<allTrades.size(); i++) {
            resultMessage += ("Trade Type: <<" + allTrades.get(i).getTradeType() + ">>\n");
            resultMessage += ("Trade ID: " + allTrades.get(i).getId() + "\n");
            resultMessage += ("Sender Username: " + allTrades.get(i).getSenderUsername() + "\n");
            resultMessage += ("Resource Type: " + allTrades.get(i).getResourceType() + "\n");
            resultMessage += ("Resource Amount: " + allTrades.get(i).getResourceAmount() + "\n");
            resultMessage += ("Resource Price: " + allTrades.get(i).getPrice() + "\n");
            resultMessage += ("Message: " + allTrades.get(i).getMessage() + "\n");
            resultMessage += ("___________________________________________\n");
        }
        return resultMessage;
    }

    public String acceptTrade(Matcher matcher, ArrayList<String> allOptions) {
        if(!MainController.checkAllOptionsExist(matcher, allOptions))
            return "Invalid format for request; please try again:";
        String tradeId = MainController.getOptionsFromMatcher(matcher,"i",2);
        String message = MainController.getOptionsFromMatcher(matcher,"m",2);
        Trade trade = FileController.getTradeByID(Integer.parseInt(tradeId),gameController.getCurrentGame());
        if(trade == null)
            return "Trade accept failed; no trade with this id exists!";
        Kingdom senderKingdom = gameController.getCurrentGame().getKingdomByKing(trade.getSenderUsername());
        Kingdom receiverKingdom = gameController.getCurrentGame().getKingdomByKing(trade.getReceiverUsername());
        Product product = receiverKingdom.getKingProductByName(trade.getResourceType());
        if(product == null)
            return "Trade accept failed; You don't have this resource!";
        if(product.getCount() < trade.getResourceAmount())
            return "Trade accept failed; You don't have enough amount of this resource!";
        if(product.getCount() * product.getPrice() > senderKingdom.getInventory())
            return "Trade accept failed; Sender doesn't have this much money right now!";
        receiverKingdom.getKingProductByName(product.getName()).setCount(product.getCount() - trade.getResourceAmount());
        Product senderProduct = senderKingdom.getKingProductByName(product.getName());
        if(senderProduct == null) {
            senderProduct = product;
            product.setCount(trade.getResourceAmount());
            senderKingdom.addKingProduct(senderProduct);
        } else {
            senderProduct.setCount(senderProduct.getCount() + trade.getResourceAmount());
        }
        senderKingdom.setInventory(senderKingdom.getInventory() - (trade.getPrice() * trade.getResourceAmount()));
        receiverKingdom.setInventory(receiverKingdom.getInventory() + (trade.getPrice() * trade.getResourceAmount()));
        return "Trade accepted successfully!";
    }

}
