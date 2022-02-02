import java.util.ArrayList;
import java.util.Arrays;

public class State {
    
    static int boardState[][];
    static boolean won;
    static boolean XTurn;
    static boolean filled;

    //array list making it easier to print out the boards
    static ArrayList<Integer> smallAlpha = new ArrayList<Integer>(
        Arrays.asList(0, 1, 2)
    );
    static ArrayList<Integer> largeAlpha = new ArrayList<Integer>(
        Arrays.asList(0, 1, 2, 3, 4, 5, 6)
    );

    //constructor
    public State(String type, String turn) {
        if (type.equals("a")) {

            this.boardState = new int [3][3];

        }else if (type.equals("b")) {
            this.boardState = new int [6][7];
        }if (turn.equals("X")) {
            this.XTurn = true;
        }else {
            this.XTurn = false;
        }
        this.won = false;
        this.filled = false;
    }

    //constructor 
    public State (int [][] boardState) {
        this.boardState = boardState;
    }

    //getter
    public static int[][] getBoardState() {
        return boardState;
    }
    
    //setter
    public void setBoardState(String choice) {

        //checks to make sure entry is an int
        if (!validateInt(choice)) {
            return;
        }
        //checks to make sure the column is valid
        int numChoice = Integer.parseInt(choice);
        if (boardState.length == 3 && !smallAlpha.contains(numChoice)) {
            System.out.println("Please select a column using the corresponding number: ");
            return;
        }
        if (boardState.length == 6 && !largeAlpha.contains(numChoice)) {
            System.out.println("Please select a column using the corresponding number: ");
            return;
        }

        //updates board, checks if someone won, updates turn
        this.boardState = place(boardState, numChoice);
        this.filled = checkFilled(boardState);
        Board.printBoard(boardState);
        this.won = Board.checkWon(State.getBoardState());
        this.XTurn = !XTurn;
    }

    //checks to make sure entry is a integer
    public static boolean validateInt(String choice) {
        try {
            int numChoice = Integer.parseInt(choice);
            return true;
        }catch(NumberFormatException e) {
            System.out.println("Make sure your entry is a number corresponding with a column");
        }return false;
    }
    public static int[][] place(int board[][], int numChoice) {
                //places either a 1 or a -1 in the correct spot
                for (int j = board.length - 1; j >= 0; j--) {
                    if (board[j][numChoice] == 0) {
                        if  (XTurn == true) {
                            board[j][numChoice] = 1;
                        }else {
                            board[j][numChoice] = -1;
                        }
                        return board;
                    }
                 }
              return board;
     }
    
    //checks if a row is filled
    public static boolean occupied(int[][] boardState, int numChoice) {
        int fillCount = 0;
        for (int i = 0; i < boardState.length; i++) {
            if (boardState[i][numChoice] != 0) {
                fillCount ++;
             }
        }   if (fillCount == boardState.length) {
                System.out.println("Please choose a column that isn't filled");
                return true;
                }
        return false;
    }

    //checks if board is filled
    public boolean checkFilled(int [][] boardState) {
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[0].length; j++) {
                if (boardState[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //returns a list of possible moves
    public static ArrayList<Integer> get_valid_moves(int [][] boardState) {
        ArrayList<Integer> valid_moves = new ArrayList<>();

        for (int i = 0; i < boardState[0].length; i++) {
            if (!occupied(boardState, i)) {
                valid_moves.add(i);
            }
        }
        return valid_moves;
    }
}
