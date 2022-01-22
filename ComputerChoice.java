import java.util.Random;

public class ComputerChoice {
    public static int team;
    private int [][] boardState;

    public ComputerChoice (int [][] boardState) {
        this.boardState = boardState;
    }

    public int makeSelection(int[][] boardState) {
        int x;
        do {
            Random rand = new Random();
             x = rand.nextInt(boardState.length);
        }while(occupied(boardState, x));
        return x;
    }

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
