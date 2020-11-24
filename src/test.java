public class test {

    public static void main (String[] args){
        gameBoard board = new gameBoard("My game");

        gamePlayer player1 = new gamePlayer("Konrad");
        gamePlayer player2 = new gamePlayer("Leah");

        gameSquare sq1 = new gameSquare("Square 1", "Blue", 100);
        gameSquare sq2 = new gameSquare("Square 2", "Blue", 320);
        gameSquare sq3 = new gameSquare("Square 3", "Blue", 380);
        gameSquare sq4 = new gameSquare("Square 4", "Blue", 130);
        gameSquare sq5 = new gameSquare("Square 5", "Blue", 360);
        gameSquare sq6 = new gameSquare("Square 6", "Blue", 140);
        gameSquare sq7 = new gameSquare("Square 7", "Blue", 300);
        gameSquare sq8 = new gameSquare("Square 8", "Blue", 230);
        gameSquare sq9 = new gameSquare("Square 9", "Blue", 270);
        gameSquare sq10 = new gameSquare("Square 10", "Blue", 110);
        gameSquare sq11 = new gameSquare("Square 11", "Blue", 190);
        gameSquare sq12 = new gameSquare("Square 12", "Blue", 230);
        gameSquare sq13 = new gameSquare("Square 13", "Blue", 220);
        gameSquare sq14 = new gameSquare("Square 14", "Blue", 430);
        gameSquare sq15 = new gameSquare("Square 15", "Blue", 311);

        board.addPlayer(player1);
        board.addPlayer(player2);

        board.addSquare(sq1);
        board.addSquare(sq2);
        board.addSquare(sq3);
        board.addSquare(sq4);
        board.addSquare(sq5);
        board.addSquare(sq6);
        board.addSquare(sq7);
        board.addSquare(sq8);
        board.addSquare(sq9);
        board.addSquare(sq10);
        board.addSquare(sq11);
        board.addSquare(sq12);
        board.addSquare(sq13);
        board.addSquare(sq14);
        board.addSquare(sq15);

        board.start();



    }

}
