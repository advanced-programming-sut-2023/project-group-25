package Model;

public class Turn {
    private static int TurnCounter;
    private User currentKing;

    public Turn(User currentKing) {
        this.currentKing = currentKing;
    }

    public static int getTurnCounter() {
        return TurnCounter;
    }

    public User getCurrentKing() {
        return currentKing;
    }
}
