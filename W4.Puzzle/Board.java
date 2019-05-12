import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] blocks;
    private int zeroI;
    private int zeroJ;
    
    public Board(int[][] inputBlocks) {
        int n = inputBlocks.length;
      
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            this.blocks[i] = new int[n];
            for (int j = 0; j < n; j++) {
                this.blocks[i][j] = inputBlocks[i][j];
                if (inputBlocks[i][j] == 0) {
                    this.zeroI = i;
                    this.zeroJ = j;
                }
            }
        }
    }
        
    public int dimension() {
        return blocks.length;
    }
    
    public int hamming() {
        int numberOfBlocksOutOfPosition = 0;
        int n = blocks.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != (j+n*i+1) && blocks[i][j] != 0) {
                    numberOfBlocksOutOfPosition++;
                }
            }
        }
        
        return numberOfBlocksOutOfPosition;
    }
    
    public int manhattan() {
        int sumOfManhattanDistances = 0;
        int n = blocks.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != (j+n*i+1) && blocks[i][j] != 0) {
                    int number = blocks[i][j];
             
                    int wantedI = (number - 1)/n;
                    int wantedJ = number - (n * wantedI + 1);
                  
                    int sum = Math.abs(i-wantedI) + Math.abs(j-wantedJ);
                    sumOfManhattanDistances += sum;
                }
            }
        }
        
        return sumOfManhattanDistances;
    }
    
    public boolean isGoal() {
        return hamming() == 0 && manhattan() == 0;
    }
    
    public Board twin() {
        int[][] twinBoardBlocks = getCopiedBlocks(this.blocks);
        int n = twinBoardBlocks.length;
        
        int i1 = 0; 
        int j1 = 0;
        int i2 = 0; 
        int j2 = 0;
        
        boolean foundFirst = false;
        boolean foundSecond = false;
        
        for (int i = 0; i < n && !foundSecond; i++) {
            for (int j = 0; j < n && !foundSecond; j++) {
              
                if (twinBoardBlocks[i][j] != 0) {
                    if (!foundFirst) {
                        i1 = i;
                        j1 = j;
                        foundFirst = true;
                    }
                    else {
                        i2 = i;
                        j2 = j;
                        foundSecond = true;
                    }
                }
            }
        }
        
        int temp = twinBoardBlocks[i1][j1];
        twinBoardBlocks[i1][j1] = twinBoardBlocks[i2][j2];
        twinBoardBlocks[i2][j2] = temp;
        
        return new Board(twinBoardBlocks);
    }
    
    public boolean equals(Object y) {
       
        if (y == null || !(y.getClass() == Board.class)) {
            return false;
        }
        
        int n = this.dimension();
        Board board2 = (Board) y;
        int m = board2.dimension();
        
        if (m == n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {  
                    if (blocks[i][j] != board2.blocks[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    private int[][] getCopiedBlocks(int[][] inputBlocks) {
        int n = inputBlocks.length;
        
        int [][] copiedBlocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            copiedBlocks[i] = new int[n];
            for (int j = 0; j < n; j++) {
                copiedBlocks[i][j] = inputBlocks[i][j];
            }
        }
        
        return copiedBlocks;
    }

    private Board getNeighbour(int newZeroI, int newZeroJ) {

        int[][] neighbourBlocks = getCopiedBlocks(this.blocks);
        neighbourBlocks[zeroI][zeroJ] = neighbourBlocks[newZeroI][newZeroJ];
        neighbourBlocks[newZeroI][newZeroJ] = 0; 
        
        return new Board(neighbourBlocks);
    }
    
    public Iterable<Board> neighbors() {
      
        int n = blocks.length;
        List<Board> neighbours = new ArrayList<Board>();
       
        if (zeroJ > 0) {
            neighbours.add(getNeighbour(zeroI, zeroJ-1));
        }
       
        if (zeroJ < n-1) {
            neighbours.add(getNeighbour(zeroI, zeroJ+1));
        }
       
        if (zeroI > 0) {
            neighbours.add(getNeighbour(zeroI-1, zeroJ));
        }
       
        if (zeroI < n-1) {
            neighbours.add(getNeighbour(zeroI+1, zeroJ));
        }
       
        return neighbours;
    }
      
    public String toString() {
        
        int n = blocks.length;
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    } 
}
