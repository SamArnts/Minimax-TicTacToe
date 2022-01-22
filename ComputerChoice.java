import java.util.Random;

public class ComputerChoice {
    public static int team;
    private int [][] boardState;

    //constructor
    public ComputerChoice (int [][] boardState) {
        this.boardState = boardState;
    }

    //method that allows computer to make selection (right now its just random)
    public int makeSelection(int[][] boardState) {
        int x;
        do {
            Random rand = new Random();
             x = rand.nextInt(boardState.length);
        }while(occupied(boardState, x));
        return x;
    }

    //checks to see if a column is occupied
    public boolean occupied(int[][] boardState, int x) {

        int fillCount = 0;
        for (int i = 0; i < boardState.length; i++) {
            if (boardState[i][x] != 0) {
                fillCount++;
             }
        }   if (fillCount == boardState.length) {
                return true;
        }
        return false;
    }
}
