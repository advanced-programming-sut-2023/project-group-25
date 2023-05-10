package Model;

public class PopularityFactor {
    private String name;
    private int rate;
    private int popularityAmount;
    public PopularityFactor(String name) {
        this.rate = 0;
        this.name=name;
        this.popularityAmount = 0;
    }
    public void addPopularityAmount(int amount) {
        this.popularityAmount += amount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getPopularityAmount() {
        return popularityAmount;
    }

    public String getName() {
        return name;
    }
}
