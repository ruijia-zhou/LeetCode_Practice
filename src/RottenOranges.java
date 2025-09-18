// LeetCode 994
import com.google.common.collect.ImmutableList;
import java.util.LinkedList;
import java.util.Queue;

class RottenOranges {
    private int[][] grid;
    private int numRows;
    private int numCols;
    private int numOranges = 0;
    private int numRottenOranges = 0;
    private final Queue<Coordinate> rottenOranges = new LinkedList<>();
    private static final int EMPTY_GRID = 0;
    private static final int GOOD_ORANGE = 1;
    private static final int ROTTEN_ORANGE = 2;
    private static final ImmutableList<Integer> DELTA_X = ImmutableList.of(0, 1, 0, -1);
    private static final ImmutableList<Integer> DELTA_Y = ImmutableList.of(1, 0, -1, 0);

    public int orangesRotting(int[][] grid) {
        this.grid = grid;
        this.numRows = grid.length;
        this.numCols = grid[0].length;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == ROTTEN_ORANGE) this.rottenOranges.add(new Coordinate(i, j));
                if (grid[i][j] != EMPTY_GRID) this.numOranges++;
            }
        }
        return BFS();
    }

    private int BFS() {
        int numMinutes = 0;
        while (!this.rottenOranges.isEmpty()) {
            numMinutes++;
            int curRottenOranges = this.rottenOranges.size();
            for (int i = 0; i < curRottenOranges; i++) {
                Coordinate curOrange = this.rottenOranges.poll();
                this.numRottenOranges++;
                int curX = curOrange.x();
                int curY = curOrange.y();
                for (int j = 0; j < DELTA_X.size(); j++) {
                    infectAdjacent(curX + DELTA_X.get(j), curY + DELTA_Y.get(j));
                }
            }
        }
        numMinutes = Math.max(0, numMinutes - 1);
        return this.numRottenOranges == this.numOranges ? numMinutes : -1;
    }

    private void infectAdjacent(int newX, int newY) {
        if (newX < 0 || newX >= this.numRows ||
                newY < 0 || newY >= this.numCols ||
                this.grid[newX][newY] != GOOD_ORANGE) return;
        this.grid[newX][newY] = ROTTEN_ORANGE;
        this.rottenOranges.add(new Coordinate(newX, newY));
    }

    private record Coordinate(int x, int y){}
}