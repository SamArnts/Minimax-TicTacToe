import java.util.ArrayList;

public class ComputerChoice {
    public static int team;
    public static boolean CPU_won = false;
    public static boolean human_won;
    private int [][] boardState;

    //constructor
    public ComputerChoice (int [][] boardState) {
        this.boardState = boardState;
    }

    //method that allows computer to make selection 
    public int makeSelection(int[][] board) {

        int score = 0;
        int best_score = 0;
        int depth = 0;

        /*ArrayList<Integer> valid_moves = new ArrayList<>();
        valid_moves = State.get_valid_moves(board);
        int best_col =  valid_moves.get(0);
        
        for (int i = 0; i < valid_moves.size(); i++) {
            int [][] board_copy = clone(board);
            State.place(board_copy, valid_moves.get(i), 999);
            score = Board.move_rating(board_copy, team);
            if (score > best_score) {
                best_score = score;
                best_col = valid_moves.get(i);
            }
        }*/

        if (board.length == 3) {
            depth = Integer.MAX_VALUE;
        }

        ArrayList col_and_score = new ArrayList<>();
        col_and_score = mini_max(board, depth, true);
        return (int) col_and_score.get(0);
    }

    //minimax
    public ArrayList mini_max(int [][] board, int depth, boolean maximizing_team) {

        ArrayList col_and_score = new ArrayList<>();
        ArrayList<Integer> valid_moves = new ArrayList<>();
        valid_moves = State.get_valid_moves(board);
        int value = 0; 
        int minmax_score = 0;

        //base case, checks for terminal nodes 
        if (depth == 0 || (Board.checkWon(board, team)|| Board.checkFilled(board))) {
            //game won
            if (Board.checkWon(board, team)|| Board.checkFilled(board)) {
                if (CPU_won == true) {
                    col_and_score.add(null);
                    col_and_score.add(999999);
                    return col_and_score;
                }else if (human_won == true) {
                    col_and_score.add(null);
                    col_and_score.add(-999999);
                    return col_and_score;
                }else {
                    col_and_score.add(null);
                    col_and_score.add(0);
                }
            }
            //depth is 0
            else {
                col_and_score.add(null);
                col_and_score.add(Board.move_rating(board, team));
                return col_and_score;
            }
        }
        if (maximizing_team) {
            int best_col = valid_moves.get(0);
            value = -Integer.MAX_VALUE;
            for (int i = 0; i < valid_moves.size(); i++) {
                int [][] board_copy = clone(board);
                State.place(board_copy, valid_moves.get(i), team);
                ArrayList score = mini_max(board_copy, depth-1, false);
                minmax_score =  (int) score.get(1);
                if (minmax_score > value) {
                    value = minmax_score;
                    best_col = valid_moves.get(i);
                }
            }
            col_and_score.add(best_col);
            col_and_score.add(value);
            return col_and_score;
        }else {
            int best_col = valid_moves.get(0);
            value = Integer.MAX_VALUE;
            for (int i = 0; i < valid_moves.size(); i++) {
                int [][] board_copy = clone(board);
                State.place(board, valid_moves.get(i), -team);
                ArrayList score = mini_max(board_copy, depth-1, true);
                minmax_score =  (int) score.get(1);
                if (minmax_score < value) {
                    value = minmax_score;
                    best_col = valid_moves.get(i);
                }
            }
            col_and_score.add(best_col);
            col_and_score.add(value);
            return col_and_score;
        }

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
