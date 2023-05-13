package Controller;

import Model.Game;

import java.beans.PropertyEditorSupport;

public class TradeController {
    private GameController gameController = new GameController();
    public String showAllKingsUsernames() {
        String resultMessage = "";
        Game game = gameController.getCurrentGame();
        for(int i = 0; i<game.getKingdoms().size(); i++) {
            resultMessage += ((i+1) + "- " + game.getKingdoms().get(i).getKing().getUsername() + "\n");
        }
        return resultMessage;
    }

}
