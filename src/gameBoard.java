import java.util.ArrayList;
import java.util.Scanner;

public class gameBoard {

    private ArrayList<gamePlayer> players = new ArrayList<>();
    private ArrayList<gameSquare> squares = new ArrayList<>();
    private String gameName;
    private final int maxPlayers = 9;
    private final int maxSquares = 40;
    private gamePlayer currentPlayer;

    public Scanner scanner = new Scanner(System.in);


    gameBoard(String gameName){
        this.gameName = gameName;
    }

    //method to add players to the board
    boolean addPlayer(gamePlayer player){
        //check if player limit hasn't been reached
        if(this.players.size() < this.maxPlayers){
            //checking if there are no name duplications to avoid confusion
            for(gamePlayer p : players){
                if(p.getPlayerName().equals(player.getPlayerName())){
                    System.out.println("Two players can't have the same name.");
                    return false;
                }
            }
            //add player to array list
            this.players.add(player);
            return true;
        }
        else return false;
    }


    //method to add squares to the game
    boolean addSquare(gameSquare square){
        //check if squares are within the limit
        if(this.squares.size() < this.maxSquares){
            //add square to the board
            this.squares.add(square);
            return true;
        }
        else return false;
    }

    //deleting a player from game
    void bankruptPlayer(gamePlayer player){
        System.out.println("Player: " + player.getPlayerName() + " has gone bankrupt!");
        //remove player from list
        players.remove(player);
        //delete all of that players properties
        //TODO >> could add a system to allow the player to sell their properties before they go bankrupt
        for(gameSquare g : squares){
            if (g.getSquareOwner() == player){
                g.setSquareOwner(null);
            }
        }
        //clear object
        player = null;
    }

    boolean travel(int steps){
        if(steps > 0){
            while(steps != 0) {
                //progressing to the next square (so the square they are starting from does not get counted)
                currentPlayer.setCurrentSquare(squares.get(currentPlayer.getCurrentSquare().getSquareID() + 1));
                steps--;
                //checking the player passes go
                if (currentPlayer.getCurrentSquare() == squares.get(0)) {
                    System.out.println("You landed on the Starting Square, here is £200");
                    //add money to the players current balance
                    currentPlayer.setPlayerMoney(currentPlayer.getPlayerMoney() + 200);
                }

                //if()

            }
            return true;
        }
        else {
            System.out.println("Could not travel.");
            return false;
        }
    }

    //when player lands on this square
    //TODO >> fix loop for game squares
    boolean land(gameSquare square){

        while (currentPlayer.getCurrentSquare().getSquareID() != square.getSquareID()){

            if (currentPlayer.getCurrentSquare().getSquareID() == square.getSquareID() -1) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("You landed on: ");
                System.out.println(currentPlayer.getCurrentSquare().toString());
                //checking if its free to buy
                if (currentPlayer.getCurrentSquare().getSquareOwner() == null) {
                    System.out.println("Would you like to buy this space for: £" + currentPlayer.getCurrentSquare().getSquarePrice() + "? ");
                    String option = "";
                    do {
                        System.out.println("Please enter 'yes' or 'no' ");
                        option = scanner.next();
                    } while (!option.equals("yes") && !option.equals("no"));
                    //updating ownership
                    if (option.equals("yes")) {
                        currentPlayer.getCurrentSquare().buySquare(currentPlayer);
                    }
                }
                //when the square has an owner the current player must donate money
                else {
                    if (currentPlayer.getCurrentSquare().donateMoney(currentPlayer)) {
                        System.out.println("Player: " + currentPlayer.getPlayerName() + " has bought square: " + currentPlayer.getCurrentSquare().getSquareID());
                    } else {
                        bankruptPlayer(currentPlayer);
                    }

                }

            }
            //if the player is on the last square on the board, make it loop instead of going past the limit
            if(currentPlayer.getCurrentSquare().getSquareID() == maxSquares){
            currentPlayer.setCurrentSquare(squares.get(0));
            }
            else{
                currentPlayer.setCurrentSquare(squares.get(currentPlayer.getCurrentSquare().getSquareID()));
            }

        }

        //landing on every square till destination
            /*
        for(int i = currentPlayer.getCurrentSquare().getSquareID(); i <= square.getSquareID(); i++){
            currentPlayer.setCurrentSquare(squares.get(i));
            //testing - prints all squares crossed for debugging
            System.out.println(currentPlayer.getCurrentSquare().toString());

            if (i == square.getSquareID() -1){
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("You landed on: ");
                System.out.println(currentPlayer.getCurrentSquare().toString());
                //checking if its free to buy
                if(currentPlayer.getCurrentSquare().getSquareOwner() == null){
                    System.out.println("Would you like to buy this space for: £" + currentPlayer.getCurrentSquare().getSquarePrice() + "? ");
                    String option = "";
                    do {
                        System.out.println("Please enter 'yes' or 'no' " );
                        option = scanner.next();
                    }while (!option.equals("yes") && !option.equals("no"));
                    //updating ownership
                    if(option.equals("yes")){
                        currentPlayer.getCurrentSquare().buySquare(currentPlayer);
                    }
                }
                //when the square has an owner the current player must donate money
                else {
                    if(currentPlayer.getCurrentSquare().donateMoney(currentPlayer)){
                        System.out.println("Player: " + currentPlayer.getPlayerName() + " has bought square: " + currentPlayer.getCurrentSquare().getSquareID());
                    }

                    else {
                        bankruptPlayer(currentPlayer);
                    }

                }
                break;
            }
            //triggered when the player passes a "go" square, they get money
            if (currentPlayer.getCurrentSquare().getGoSquareStatus()){
                currentPlayer.setPlayerMoney(currentPlayer.getPlayerMoney() + 200);
                System.out.println("Player: " + currentPlayer + " passed go!");
                return true;
            }
        }

      */

        return false;
    }

    //method to start the game
    boolean start(){
        //checking if there are at least 2 players and at least 20 squares
        if (this.players.size() >= 2 && this.squares.size() > 10) {
            //setting current square to "start/go" for all the players
            for(gamePlayer p : players){
                p.setCurrentSquare(squares.get(0));
            }

            //while here are more than 1 players then continue the game
            while (players.size() != 1){
                //loop though all the players in the game continuously
                for(gamePlayer player : players){
                    currentPlayer = player;

                    //asking the user if they are ready to roll
                    String option = "";
                    do {
                        System.out.println("--------------------------------------------------------------------------");
                        System.out.println(currentPlayer.getPlayerName() + ", are you ready to roll? (yes/no)");
                        option = scanner.next();
                    }while (!option.equals("yes"));

                    //randomise a dice throw
                    int die1 = (int) (Math.random() * (6 - 1) + 1);
                    int die2 = (int) (Math.random() * (6 - 1) + 1);
                    //land on that square
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println(currentPlayer.getPlayerName() + " has rolled: " + (die1+die2));
                    land(squares.get((die1 + die2)));
                }

            }

        }
        return true;

    }

    //prints all squares on the board, used for testing
    public void printBoard(){
        for(gameSquare g : squares){
            System.out.println(g.toString());
        }

        System.out.println();
    }

}
