package Model;

public class PopularityFactor {
    private String name;
    private int rate;
    private int popularityAmount;
    public PopularityFactor(int rate,String name) {
        this.rate = rate;
        this.name=name;
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
