import java.util.Scanner;

public class runner {

    public static void main(String [] args) {

        Scanner scnr = new Scanner(System.in);

        //selecting board type
        System.out.println("Would you like to play: \n a) 3X3 connect four \n b) 6X7 connect four ");
        System.out.println("(respond with 'a' or 'b)");
        String boardType = scnr.next();
        while (!(boardType.equals("a") || boardType.equals("b"))) {
            System.out.println("Please respond with either 'a' or 'b' to make your selection");
            boardType = scnr.next();
        }
        
        //player chooses whether to be X or O
        System.out.println("Would you like to be Red/X or Yellow/O? \n"
                            + "(Please respond with either X or O)");
        String playerNum = scnr.next();                   
        while (!(playerNum.equals("X") || playerNum.equals("O"))) {
            System.out.println("Please respond with either 'X' or 'O' to make your selection");
             playerNum = scnr.next();
        }
        //creates a new state
        State state = new State(boardType, playerNum);
        Board.printBoard(State.getBoardState());;
        if (playerNum.equals("X")) {
            System.out.println("Alright X, you go first. The CPU will be O");
            System.out.println("Choose what column you want to place your piece by responding with the corresponding number");
            State.humanTurn = true;
            ComputerChoice.team = -1;
        
        }else {
            System.out.println("Alright CPU, you go first. Player2 go next");
            ComputerChoice.team = 1;
            ComputerChoice compChoice = new ComputerChoice(State.getBoardState());
            State.XTurn = true;
            State.humanTurn = false;
            int comp = compChoice.makeSelection(State.getBoardState());
            state.setBoardState(Integer.toString(comp));
        }

        //keeps the game running in correct conditions
        while (!State.won && !State.filled) {
            String choice = scnr.next();
            //checks if the entry is a number in the correct range
            while (!State.validateInt(choice)) {
                 choice = scnr.next();
            }//checks if the row is already occupied
            while(State.occupied(State.boardState, Integer.parseInt(choice))) {
                choice = scnr.next();
            }
            state.setBoardState(choice);
            if (State.won){ break;}
            ComputerChoice compChoice = new ComputerChoice(State.getBoardState());
            int comp = compChoice.makeSelection(State.getBoardState());
            state.setBoardState(Integer.toString(comp));
        }

        //prints results
        if(State.won && !State.XTurn) {
            System.out.println("Game over, Red/X wins");
        }else if(State.won && State.XTurn) {
            System.out.println("Game over, Yellow/O wins");
        }else{
            System.out.println("Cat game");
        }

    }

}