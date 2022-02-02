public class Tree {
    
    public static class Node {
        int [][] boardState;
        int score;
        Node child;

        public Node(int [][]boardState, int score) {
            this.boardState = boardState;
            this.score = score;
            this.child = null;
        }

    }
    public Node root;
    public Tree (Node root) {
        this.root = root;
    }

}
