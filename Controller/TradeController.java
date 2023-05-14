package Controller;

import Model.Game;
import Model.Trade;

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

    public String createTradeRequest(ArrayList<String> allOptions, Matcher matcher, String username) throws NoSuchAlgorithmException {
        if(!MainController.checkAllOptionsExist(matcher, allOptions))
            return "Invalid format for request; please try again:";
        String resourceType = MainController.getOptionsFromMatcher(matcher,"t",4);
        String resourceAmount = MainController.getOptionsFromMatcher(matcher,"a",4);
        String price = MainController.getOptionsFromMatcher(matcher,"p",4);
        String message = MainController.getOptionsFromMatcher(matcher,"m",4);
        Trade trade = new Trade(username,FileController.generateTradeId(),"request",resourceType,Integer.parseInt(resourceAmount),Integer.parseInt(price),message);
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
            resultMessage += ("Resource Type: " + allTrades.get(i).getResourceType() + "\n");
            resultMessage += ("Resource Amount: " + allTrades.get(i).getResourceAmount() + "\n");
            resultMessage += ("Resource Price: " + allTrades.get(i).getPrice() + "\n");
            resultMessage += ("Message: " + allTrades.get(i).getMessage() + "\n");
            resultMessage += ("___________________________________________\n");
        }
        return resultMessage;
    }

}
