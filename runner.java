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
        
        System.out.println("Would you like to be player1/X or player2/O? \n"
                            + "(Please respond with either X or O)");
        String playerNum = scnr.next();                   
        while (!(playerNum.equals("X") || playerNum.equals("O"))) {
            System.out.println("Please respond with either 'X' or 'O' to make your selection");
             playerNum = scnr.next();
        }
        State state = new State(boardType, playerNum);
        Board.printBoard(State.getBoardState());;
        if (playerNum.equals("X")) {
            System.out.println("Alright player1, you go first. The CPU will be player2");
            System.out.println("Choose what column you want to place your piece by responding with the corresponding number");
            ComputerChoice.team = -1;
        
        }else {
            System.out.println("Alright CPU, you go first. Player2 go next");
            ComputerChoice.team = 1;
            ComputerChoice compChoice = new ComputerChoice(State.getBoardState());
            State.XTurn = true;
            int comp = compChoice.makeSelection(State.getBoardState());
            state.setBoardState(Integer.toString(comp));
        }

        while (!State.won && !State.filled) {
            String choice = scnr.next();
            state.setBoardState(choice);
            if (State.won){ break;}
            ComputerChoice compChoice = new ComputerChoice(State.getBoardState());
            int comp = compChoice.makeSelection(State.getBoardState());
            state.setBoardState(Integer.toString(comp));
        }

        if(State.won && !State.XTurn) {
            System.out.println("Game over, player1/X wins");
        }else if(State.won && State.XTurn) {
            System.out.println("Game over, player2/O wins");
        }else{
            System.out.println("Cat game");
        }

    }

}