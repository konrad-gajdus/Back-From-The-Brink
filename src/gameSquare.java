import java.util.concurrent.atomic.AtomicInteger;

public class gameSquare {

    private int squareID;
    private static final AtomicInteger count = new AtomicInteger(0);
    private String squareName;
    private String squareColour;
    private gamePlayer squareOwner = null;
    private double squarePrice;
    private int vetClinicCount = 0;
    private int rescueCentreCount = 0;
    private boolean goSquare = false;

    // constructor for normal squares
    gameSquare(String squareName, String squareColour, double squarePrice){
        this.squareID = count.incrementAndGet();
        this.squareName = squareName;
        this.squareColour = squareColour;
        this.squarePrice = squarePrice;
    }

    //constructors for special squares (chance/community/go etc.)
    //TODO >> fix special square, have one for go,community,chance,jail etc.
    gameSquare(String squareName){
        this.squareName = squareName;
    }

    //method to allow players to buy squares
    boolean buySquare(gamePlayer player){
        //checking if player has enough money to purchase
        if(player.getPlayerMoney() > this.squarePrice && this.squareOwner == null){
            //update player money
            player.setPlayerMoney(player.getPlayerMoney() - this.squarePrice);
            //update ownership
            this.squareOwner = player;
            return true;
        }

        else return false;
    }

    //method to allow player to buy vet clinics
    boolean buyVetClinic(gamePlayer player){
        //checking if player owns the square before they can build, also assuming clinics will cost 1.5x the price of the square, this can change
        if(this.squareOwner == player && player.getPlayerMoney() > (squarePrice * 1.5)){
            //checking if its within the clinic limit
            if(this.vetClinicCount < 5){
                //update ownership and money
                this.vetClinicCount += 1;
                player.setPlayerMoney(player.getPlayerMoney() - (this.squarePrice * 1.5));
                return true;
            }

            else return false;
        }

        else return false;
    }


    //donating is the equivalent of landing on someones square in monopoly
    boolean donateMoney(gamePlayer payee){
        if(this.squareOwner != null && payee.getPlayerMoney() > this.squarePrice){
            //assuming rent will be squarePrice * 0.8, this can change
            this.squareOwner.setPlayerMoney(this.squarePrice * 0.8);
            payee.setPlayerMoney(this.squarePrice * 0.8);
            return true;
        }
        else return false;
    }

    //getters/setters
    boolean getGoSquareStatus(){
        if(this.goSquare){
            return true;
        }
        else return false;
    }

    public gamePlayer getSquareOwner(){
        return this.squareOwner;
    }

    public String getSquareName(){
        return this.squareName;
    }

    public double getSquarePrice() {
        return squarePrice;
    }

    public int getSquareID() {
        return squareID;
    }

    void setSquareOwner(gamePlayer player){
        this.squareOwner = player;
    }

    @Override //printing the square data
    public String toString(){
        String str = "ID: " + getSquareID() + ", Name: " + getSquareName() + ", Owner: " + getSquareOwner() + ", Price: " + getSquarePrice();
        return str;
    }
}
