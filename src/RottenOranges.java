import java.util.LinkedList;
import java.util.Queue;

// LeetCode 994
public class RottenOranges {
    // global field for storing the grids
    private int[][] grid;
    private int numRows;
    private int numCols;

    // global field for BFS
    private final Queue<Coordinate> rottenOranges = new LinkedList<>();
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {1, 0, -1, 0};
    private int numOranges = 0;
    private int numRottenOranges = 0;

    public int orangesRotting(int[][] grid) {
        // store parameters
        this.grid = grid;
        this.numRows = grid.length;
        this.numCols = grid[0].length;

        // traverse the grids to count num of oranges
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == 2) this.rottenOranges.add(new Coordinate(i, j));
                if (grid[i][j] != 0) this.numOranges++;
            }
        }

        // BFS to iteratively simulate the infection process
        return BFS();

    }

    private int BFS() {
        int numMinites = 0;
        while (!this.rottenOranges.isEmpty()) {
            numMinites++;
            // iterate through rotten oranges in current minute
            int curRottenOranges = this.rottenOranges.size();
            for (int i = 0; i < curRottenOranges; i++) {
                Coordinate curOrange = this.rottenOranges.poll();
                this.numRottenOranges++;
                int curX = curOrange.x;
                int curY = curOrange.y;
                // infect adjacent oranges if applicable
                for (int j = 0; j < 4; j++) {
                    infectAdjacent(curX + dx[j], curY + dy[j]);
                }
            }
        }
        // deduct one from num of minutes as the last round doesn't add any new rotten oranges
        numMinites = Math.max(0, numMinites - 1);
        return this.numRottenOranges == this.numOranges ? numMinites : -1;
    }

    private void infectAdjacent(int newX, int newY) {
        // check for boundaries and existence of oranges
        if (newX < 0 || newX >= this.numRows ||
                newY < 0 || newY >= numCols ||
                grid[newX][newY] != 1) return;
        this.grid[newX][newY] = 2;
        this.rottenOranges.add(new Coordinate(newX, newY));

    }

    private static class Coordinate{
        int x;
        int y;
        Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
