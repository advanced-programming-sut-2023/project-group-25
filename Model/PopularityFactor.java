package Model;

public class PopularityFactor {
    private int rate;
    private int popularityAmount;
    public PopularityFactor(int rate) {
        this.rate = rate;
    }
    public void addPopularityAmount(int amount) {
        this.popularityAmount += amount;
    }

    public int getRate() {
        return rate;
    }

    public int getPopularityAmount() {
        return popularityAmount;
    }
}
