public class gamePlayer {

    private double playerMoney  = 1500;
    private String playerName;
    private gameSquare currentSquare;

    gamePlayer(String playerName){
        this.playerName = playerName;
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public String getPlayerName(){
        return playerName;
    }

    void setPlayerMoney(double money){
        this.playerMoney = money;
    }

    public gameSquare getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(gameSquare currentSquare) {
        this.currentSquare = currentSquare;
    }
}
