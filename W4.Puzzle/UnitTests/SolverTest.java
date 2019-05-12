import junit.framework.TestCase;

public class SolverTest extends TestCase {
    
    public void test1() {
        
        int[][] blocks = new int[][] {{1, 2, 3}, {0, 7, 6}, {5, 4, 8}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals("", solver.moves(), 7);
    }
        
    public void test2() {
        
        int[][] blocks = new int[][] {{2, 3, 5}, {1, 0, 4}, {7, 8, 6}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals("", solver.moves(), 8);
        assertEquals("", solver.isSolvable(), true);
    }
    
    public void test3() {
        
        int[][] blocks = new int[][] {{3, 2, 5}, {1, 0, 4}, {7, 8, 6}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals("", solver.moves(), -1);
        assertEquals("", solver.isSolvable(), false);
    }
    
    public void test4() {
        
        int[][] blocks = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals("", solver.moves(), 4);
        assertEquals("", solver.isSolvable(), true);
    }

    public void test5() {
        
        int[][] blocks = new int[][] {{1, 0, 2}, {7, 5, 4}, {8, 6, 3}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals("", solver.moves(), 11);
        assertEquals("", solver.isSolvable(), true);
    }

    public void test6() {
        
        int[][] blocks = {
                {2, 6, 3},
                {4, 5, 1},
                {8, 7, 0}
        };
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        
        assertEquals("", solver.moves(), -1);
        assertEquals("", solver.isSolvable(), false);
    }
}