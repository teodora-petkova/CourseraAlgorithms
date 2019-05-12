import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
  
    private SearchNode lastNodeFromSolution;
    
    private static class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int manhattan;
        private final int moves;
        private final SearchNode previous;
        
        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.manhattan = board.manhattan();
            this.moves = moves + 1;
            this.previous = previous;
        }
        
        @Override
        public int compareTo(SearchNode other) {
            int c = Integer.compare(this.manhattan + this.moves, other.manhattan + other.moves);
            if (c == 0) {
                return Integer.compare(this.moves, other.moves);
            }
            return c;
        }
        
        public Board getBoard() {
            return this.board;
        }
        
        public SearchNode getPreviousSearchNode() {
            return previous;
        }
    }
    
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        
        MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
        MinPQ<SearchNode> queueTwin = new MinPQ<SearchNode>();
        
        SearchNode current = new SearchNode(initial, 0, null);
        queue.insert(current);
  
        SearchNode currentTwin = new SearchNode(initial.twin(), 0, null);
        queueTwin.insert(currentTwin);
        
        while (!current.getBoard().isGoal() &&
               !currentTwin.getBoard().isGoal()) {
            current = queue.delMin();
            currentTwin = queueTwin.delMin();
        
            for (Board neighbour : current.board.neighbors()) {
                if (current.previous == null || 
                    !current.previous.board.equals(neighbour)) {
                    queue.insert(new SearchNode(neighbour, current.moves, current));
                }
            }
            
            for (Board neighbourTwin : currentTwin.board.neighbors()) {
                if (currentTwin.previous == null || 
                    !currentTwin.previous.board.equals(neighbourTwin)) {
                    queueTwin.insert(new SearchNode(neighbourTwin, currentTwin.moves, currentTwin));
                }
            }
        }  
        
        if (current.getBoard().isGoal()) {
            this.lastNodeFromSolution = current;
        }
        else {
            this.lastNodeFromSolution = null;
        }
    }
  
    public boolean isSolvable() {
        return this.lastNodeFromSolution != null;
    }
    
    public int moves() {
        if (isSolvable())
            return this.lastNodeFromSolution.moves - 1;
        else
            return -1;
    }
    
    public Iterable<Board> solution() {
        if (isSolvable()) {
            Stack<Board> stack = new Stack<Board>();
            SearchNode node = this.lastNodeFromSolution;
            stack.push(node.getBoard());
            while (node.getPreviousSearchNode() != null) {
                node = node.getPreviousSearchNode();
                stack.push(node.getBoard());
            }
            return stack;
        }
        return null;
    }
 }