package trivia;

public final class Player {
    private String name;
    private int coins = 0;
    private int place = 0;
    private boolean isInPrison = false;
    private boolean isEligibleToGetOutOfPrison = false;

    public Player(String name) {
        this.name = name;
    }

    public void addCoin() {
        coins++;
    }

    public void sendToPrison() {
        isInPrison = true;
    }

    public void setIsEligibleToGetOutOfPrison(boolean isEligibleToGetOutOfPrison) {
        this.isEligibleToGetOutOfPrison = isEligibleToGetOutOfPrison;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isInPrison() {
        return isInPrison;
    }

    public boolean isEligibleToGetOutOfPrison() {
        return isEligibleToGetOutOfPrison;
    }
}
