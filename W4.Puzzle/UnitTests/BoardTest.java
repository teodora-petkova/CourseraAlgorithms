import junit.framework.TestCase;

public class BoardTest extends TestCase {


    public void test1() {
        
        int[][] blocks = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(blocks);
        assertEquals("", board.manhattan(), 4);
    }
    
     /*
         5  1  8 
         2  7  3 
         4  0  6  
     */

    public void test2() {
        
        int[][] blocks = new int[][] {{5, 1, 8}, {2, 7, 3}, {4, 0, 6}};
        Board board = new Board(blocks);
        assertEquals("", board.manhattan(), 13);
    }
    
    /*
         5  8  7 
         1  4  6 
         3  0  2 
     */
    public void test3() {
        int[][] blocks = new int[][] {{5, 8, 7}, {1, 4, 6}, {3, 0, 2}};
        Board board = new Board(blocks);
        assertEquals("", board.manhattan(), 17);        
    }
    
    /*
         1  0 
         2  3  
    */
    
    public void test4() {
        int[][] blocks = new int[][] {{1, 0}, {2, 3}};
        Board board = new Board(blocks);
        assertEquals("", board.manhattan(), 3);        
    }
    
    /*
         1  0  3 
         4  2  5 
         7  8  6 
     */
    
    public void test5()
    {
        int[][] blocks = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] blocksTwin = new int[][] {{0, 3, 1}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(blocks);
        Board twin = new Board(blocksTwin);
        assertEquals("", board.twin().equals(twin), true);       
    }
    
    /*
         2  0  3  4 
         1 10  6  8 
         5  9  7 12 
        13 14 11 15 
     */
    
    public void test6()
    {
        int[][] blocks = new int[][] {{2, 0, 3, 4}, {1, 10, 6, 8}, {5, 9, 7, 12}, {13, 14, 11, 15}};
        int[][] blocksTwin = new int[][] {{3, 0, 2, 4}, {1, 10, 6, 8}, {5, 9, 7, 12}, {13, 14, 11, 15}};
        Board board = new Board(blocks);
        Board twin = new Board(blocksTwin);
        assertEquals("", board.twin().equals(twin), true);       
    }

    /*
         1  0 
         2  3 
    */
    
    public void test7()
    {
        int[][] blocks = new int[][] {{1, 0}, {2, 3}};
        int[][] blocksTwin = new int[][] {{2, 0}, {1, 3}};
        Board board = new Board(blocks);
        Board twin = new Board(blocksTwin);
        assertEquals("", board.twin().equals(twin), true);       
    }
    
    public void test8()
    {    
        int[][] blocks1 = new int[][] {{1, 0}, {2, 3}};
        int[][] blocks2 = new int[][] {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board1 = new Board(blocks1);
        Board board2 = new Board(blocks2);
        assertEquals("", board2.equals(board1), false);       
    }
    
    public void test9()
    {
        int[][] blocks = new int[][] {{1, 2, 0}, {7, 5, 4}, {8, 6, 3}};
        Board board = new Board(blocks);
        assertEquals("", board.manhattan(), 8);       
    }
    
    public void test10()
    {
        int[][] blocks = new int[][] {{1, 5, 2}, {7, 0, 4}, {8, 6, 3}};
        Board board = new Board(blocks);
        assertEquals("", board.manhattan(), 10);       
    }

}