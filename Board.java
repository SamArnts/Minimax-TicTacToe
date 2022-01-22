

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

    //determines if the token should be an X or O 
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

    public static boolean checkWon(int[][] boardState) {

        if (boardState.length == 3) {
            //checking for horizontal winners
            for (int j = 0; j < boardState.length; j++) {
                if (boardState[j][0] == boardState[j][1]
                 && boardState[j][1] == boardState[j][2]
                && boardState[j][0] != 0) {
                    return true;
                }
            }
            //checking for vertical winners
            for (int j = 0; j < boardState.length; j++) {
                if (boardState[0][j] == boardState[1][j]
                && boardState[1][j] == boardState[2][j]
                && boardState[0][j] != 0) {
                      return true;
                }
            }
            //checking for diaganols
            if(boardState[2][0] == boardState[1][1] && boardState[1][1] == boardState[0][2]
               && boardState[2][0] != 0) {
                    return true;
            }
             if(boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]
             && boardState[0][0] != 0) {
                  return true;
          }

          //for 6 X 7 boards
          if (boardState.length == 6) {
            
            //vertical winners
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 7; j++) {
                    if (boardState[j][i] == boardState[j][i+1] && boardState[j][i+1] == boardState[j][i+2]
                    && boardState[j][i+2] == boardState[j][i+3] && boardState[j][i] != 0) {
                        return true;
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 6; j++) {
                    if (boardState[i][j] == boardState[i][j+1] && boardState[i][j+1] == boardState[i][j+2]
                    && boardState[i][j+2] == boardState[i][j+3] && boardState[i][j] != 0) {
                        return true;
                    }
                }
            }

          }
              
        }
        
        return false;
    }


}
