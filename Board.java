

public class Board {
    
    public static void printBoard(int boardState[][]) {
        char [] alphabet = {'a', 'b', 'c', 'd', 'e', 'f'};
        System.out.print(" ");
        for (int i = 0; i < boardState[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < boardState.length; i++) {
            System.out.print(alphabet[i]);
            for (int j = 0; j < boardState[0].length; j++) {
                String symb = assertSymbol(i, j, boardState);
                System.out.print("|" + symb);
                
            }System.out.println();
        }

    }

    public static String assertSymbol(int i, int j, int[][] boardState) {
        String symb;
        if(boardState[i][j] == 0) {
            symb = " ";
        }else if (boardState[i][j] == 1) {
            symb = "X";
        }else{
            symb = "O";
        }
        return symb;
    }
}
