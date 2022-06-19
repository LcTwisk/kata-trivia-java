package trivia;

public class Player {
    private String name;
    private int coins = 0;
    private int place = 0;
    private boolean isInPrison = false;

    public Player(String name) {
        this.name = name;
    }

    public void addCoin() {
        coins++;
    }

    public void sendToPrison() {
        isInPrison = true;
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
}
