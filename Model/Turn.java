package Model;

public class Turn {
    private static int TurnCounter;
    private User currentKing;

    public Turn(User currentKing) {
        this.currentKing = currentKing;
    }
}
