import java.util.ArrayList;
import java.util.Random;

public class ComputerChoice {
    public static int team;
    private int [][] boardState;

    //constructor
    public ComputerChoice (int [][] boardState) {
        this.boardState = boardState;
    }

    //method that allows computer to make selection (right now its just random)
    public int makeSelection(int[][] board) {
        /*int x;
        do {
            Random rand = new Random();
             x = rand.nextInt(boardState.length);
        }while(State.occupied(boardState, x));
        return x;*/

        int score = 0;
        int best_score = 0;
        Random rand = new Random();
        int best_col =  rand.nextInt(board.length);

        ArrayList<Integer> valid_moves = new ArrayList<>();
        valid_moves = State.get_valid_moves(board);
        
        for (int i = 0; i < valid_moves.size(); i++) {
            int [][] board_copy = clone(board);
            State.place(board_copy, valid_moves.get(i));
            score = Board.move_rating(board_copy, team);
            if (score > best_score) {
                best_score = score;
                best_col = i;
            }
        }
        return best_col;
    }

    //creates a seperate copy in memeory of a given 2D array
    public int [][] clone (int [][] twod_array) {
        int [][] copy = new int [twod_array.length][twod_array[0].length];

        for (int i = 0; i < twod_array.length; i++) {
            for (int j = 0; j < twod_array[0].length; j++) {
                copy[i][j] = twod_array[i][j];
            }
        }
        return copy;

    }
}
