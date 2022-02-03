

public class Board {
    
    //prints out a graphic representation of the board
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
                //allows the correct symbol to be printed
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

    //checks to see if someone won
    public static boolean checkWon(int[][] boardState, int team) {

        //check if won for 3X3 boards
        if (boardState.length == 3) {
            //checking for horizontal winners
            for (int j = 0; j < boardState.length; j++) {
                if (boardState[j][0] == boardState[j][1]
                 && boardState[j][1] == boardState[j][2]
                && boardState[j][0] != 0) {
                    if (boardState[j][0] == ComputerChoice.team) {
                        ComputerChoice.CPU_won = true;
                    }
                    return true;
                }
            }
            //checking for vertical winners
            for (int j = 0; j < boardState.length; j++) {
                if (boardState[0][j] == boardState[1][j]
                && boardState[1][j] == boardState[2][j]
                && boardState[0][j] != 0) {
                    if (boardState[0][j] == ComputerChoice.team) {
                        ComputerChoice.CPU_won = true;
                    }else {ComputerChoice.human_won = true;}
                      return true;
                }
            }
            //checking for diaganols
            if(boardState[2][0] == boardState[1][1] && boardState[1][1] == boardState[0][2]
               && boardState[2][0] != 0) {
                if (boardState[2][0] == ComputerChoice.team) {
                    ComputerChoice.CPU_won = true;
                }else {ComputerChoice.human_won = true;}
                    return true;
            }
             if(boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]
             && boardState[0][0] != 0) {
                if (boardState[0][0] == ComputerChoice.team) {
                    ComputerChoice.CPU_won = true;
                }else {ComputerChoice.human_won = true;}
                  return true;
          }
          
        }

        //check if won for 6 X 7 boards
        if (boardState.length == 6) {
            
            //horizontal check
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 6; j++) {
                    if (boardState[j][i] == boardState[j][i+1] && boardState[j][i+1] == boardState[j][i+2]
                    && boardState[j][i+2] == boardState[j][i+3] && boardState[j][i] != 0) {
                        return true;
                    }
                }
            }
            //vertical check
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 7; j++) {
                    if (boardState[i][j] == boardState[i+1][j] && boardState[i+1][j] == boardState[i+2][j]
                    && boardState[i+2][j] == boardState[i+3][j] && boardState[i][j] != 0) {
                        return true;
                    }
                }
            }
            
            //diaganol checks
            for (int i = 3; i < boardState.length; i++) {
                for (int j = 0; j < boardState[0].length - 3; j++) {
                    if (boardState[i][j] == boardState[i-1][j+1] && boardState[i-1][j+1] == boardState[i-2][j+2]
                    && boardState[i-2][j+2] == boardState[i-3][j+3] && boardState[i][j] != 0) {
                        return true;
                    }
                }
            }
            for(int i = 3; i < boardState.length; i++) {
                for (int j = 3; j < boardState[0].length; j++) {
                    if (boardState[i][j] == boardState[i-1][j-1] && boardState[i-1][j-1] == boardState[i-2][j-2]
                    && boardState[i-2][j-2] == boardState[i-3][j-3] && boardState[i][j] != 0) {
                        return true;
                    }
                }
            }

          }
              
        return false;
    }

    //checks if board is filled
    public static boolean checkFilled(int [][] boardState) {
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[0].length; j++) {
                if (boardState[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //assigns a score to each move
    public static int move_rating(int[][] boardState, int team) {
        int score = 0;
        int team_counter = 0;
        int empty_counter = 0;
        int enemy_counter = 0;

        if (boardState.length == 3) {
            //horizontal score
            for (int i = 0; i < boardState.length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (boardState[i][j] == team) {
                        team_counter++;
                    }
                    else if (boardState[i][j] == 0) {
                        empty_counter++;
                    }
                    else if (boardState[i][j] == -team) {
                        enemy_counter++;
                    }
                }
                score += score(team_counter, empty_counter, enemy_counter);
                team_counter = 0;
                empty_counter = 0;
                enemy_counter = 0;
            }
            

            //vertical score
            for (int i = 0; i < boardState[0].length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (boardState[j][i] == team) {
                        team_counter ++;
                    }else if (boardState[j][i] == 0) {
                        empty_counter ++;
                    }else if (boardState[j][i] == -team) {
                        enemy_counter++;
                    }
                }
                score += score(team_counter, empty_counter, enemy_counter);
                team_counter = 0;
                empty_counter = 0;
                enemy_counter = 0;
            }
            
            //diaganol scores
            for (int i = 0; i < 3; i++) {
                if (boardState[i][i] == team) {
                    team_counter++;
                }else if (boardState[i][i] == 0) {
                    empty_counter++;
                }else if (boardState[i][i] == -team) {
                    enemy_counter++;
                }
            }
            score += score(team_counter, empty_counter, enemy_counter);

            team_counter = 0;
            empty_counter = 0;
            for (int i = 0; i < 3; i++) {
                if (boardState[2-i][i] == team) {
                    team_counter++;
                }else if (boardState[2-i][i] == 0) {
                    empty_counter ++; 
                }else if (boardState[i][i] == -team) {
                    enemy_counter++;
                }
            }
            score += score(team_counter, empty_counter, enemy_counter);

            team_counter = 0;
            empty_counter = 0;
            //score for center (in theory more preferable)
            for (int i = 0; i < 3; i++) {
                if (boardState[i][1] == team) {
                    team_counter++;
                }
            }
            score += team_counter * 6;

        }
        return score;
    }

    //returns a score depending on what types of pieces are in each row, col, diag
    public static int score(int team_counter, int empty_counter, int enemy_counter) {
        int score = 0;
        if (team_counter == 3) {
            score += 100;
        }else if (team_counter == 2 && empty_counter == 1) {
            score += 10;
        }else if (enemy_counter == 2 && empty_counter == 1) {
            score -=80;
        }
        return score;
    }

}
