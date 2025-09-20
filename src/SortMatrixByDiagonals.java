import java.util.Arrays;

// LeetCode 3446
public class SortMatrixByDiagonals {
    private static final boolean BOTTOM_LEFT_TRIANGLE = true;
    private static final boolean TOP_RIGHT_TRIANGLE = false;

    private int[][] grid;
    private int sideLength;

    public int[][] sortMatrix(int[][] grid) {
        this.grid = grid;
        this.sideLength = grid.length;
        sortTriangle(BOTTOM_LEFT_TRIANGLE);
        sortTriangle(TOP_RIGHT_TRIANGLE);
        return grid;
    }

    private void sortTriangle(boolean triangle){
        for (int prefix = triangle == BOTTOM_LEFT_TRIANGLE ? 0 : 1; prefix < this.sideLength; prefix++) {
            int[] diagonalValues = new int[this.sideLength - prefix];
            for (int i = 0; i + prefix < this.sideLength; i++) diagonalValues[i] = getGridValue(prefix, i, triangle);
            sortDiagonal(diagonalValues, triangle);
            updateDiagonal(diagonalValues, prefix, triangle);
        }
    }

    private int getGridValue(int prefix, int i, boolean triangle) {
        if (triangle == BOTTOM_LEFT_TRIANGLE) return this.grid[i + prefix][i];
        return this.grid[i][i + prefix];
    }

    private void updateDiagonal(int[] diagonalValues, int prefix, boolean triangle) {
        for (int i = 0; i + prefix < this.sideLength; i++) {
            if (triangle == BOTTOM_LEFT_TRIANGLE) this.grid[i + prefix][i] = diagonalValues[i];
            else this.grid[i][i + prefix] = diagonalValues[i];
        }
    }

    private void sortDiagonal(int[] diagonalValues, boolean triangle) {
        Arrays.sort(diagonalValues);
        if (triangle == BOTTOM_LEFT_TRIANGLE) reverseArray(diagonalValues);
    }

    private void reverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
