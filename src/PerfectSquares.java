// LeetCode 279
public class PerfectSquares {
    public int numSquares(int n) {
        int[] minNumSquares = new int[n + 1];
        for (int i = 1; i <= n; i++) minNumSquares[i] = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                minNumSquares[i] = Math.min(minNumSquares[i], minNumSquares[i - j * j] + 1);
            }
        }
        return minNumSquares[n];
    }
}
