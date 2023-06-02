package Model;

public class Turn {
    private static int turnCounter;
    private User currentKing;

    public Turn(User currentKing) {
        this.currentKing = currentKing;
    }

    public static void setTurnCounter(int turnCounter) {
        Turn.turnCounter = turnCounter;
    }

    public void setCurrentKing(User currentKing) {
        this.currentKing = currentKing;
    }

    public static int getTurnCounter() {
        return turnCounter;
    }

    public User getCurrentKing() {
        return currentKing;
    }
}
