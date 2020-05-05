import java.util.*;

public class TicTacToe {

    //create required array lists
    static ArrayList<Integer> playerPositons = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositons = new ArrayList<Integer>();

    //Main Method
    public static void main(String[] args) {

        //Create a 2d array with all the symbols
        char[][] gameboard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '},};
        // call the print gameboard method and parse it the gameboard array
        printGameBoard(gameboard);

        // continously loops
        while (true) {
            //create new scanner object
            Scanner reader = new Scanner(System.in);
            // ask for a postion
            System.out.println("Enter your placement (1-9)");
            int Playerpos = reader.nextInt();
            while (playerPositons.contains(Playerpos) || cpuPositons.contains(playerPositons)) {
                System.out.println("Position taken! Please enter a correct position");
                Playerpos = reader.nextInt();
            }

            //play as the player
            placePiece(gameboard, Playerpos, "player");

            // check to see if someone won
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }


            //Play as "cpu"
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            while (playerPositons.contains(cpuPos) || cpuPositons.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
                //System.out.println("CPU GUESSING AGAIN"); // debug statement (uncomment to view when the cpu has to guess multiple times
            }
            placePiece(gameboard, cpuPos, "cpu");

            printGameBoard(gameboard);

            //Check if someone won
            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }// while true


    }// main method


    //Print gameboard method
    public static void printGameBoard(char[][] gameboard) {
        for (char[] row : gameboard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }//first for

    }// end of print gameboard method

    public static void placePiece(char[][] gameboard, int pos, String user) {
        char symbol = 'X';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositons.add(pos);
        } else if (user.equals("cpu")) {
            symbol = '0';
            cpuPositons.add(pos);
        }

        switch (pos) {
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
            default:
                break;

        }// end of switch

    }//end of place

    public static String checkWinner() {
        //Rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        //Collumns
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        //diagonals
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        //Rows
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        //Collumns
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        //diagonals
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPositons.containsAll(l)) {
                return "Congratulations you won!!";
            } else if (cpuPositons.containsAll(l)) {
                return "CPU wins! Sorry :(";
            } else if (playerPositons.size() + cpuPositons.size() == 9) {
                return "Draw! Nobody wins!";
            }
        }

        return "";
    }// end of check winner

}// end of class
