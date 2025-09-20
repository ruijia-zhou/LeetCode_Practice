// LeetCode 256
public class PaintHouse {
    public int minCost(int[][] costs) {
        int preCostR = 0, preCostG = 0, preCostB = 0;
        for (int[] cost : costs) {
            int newCostR = Math.min(preCostG, preCostB) + cost[0];
            int newCostG = Math.min(preCostB, preCostR) + cost[1];
            int newCostB = Math.min(preCostR, preCostG) + cost[2];
            preCostR = newCostR;
            preCostG = newCostG;
            preCostB = newCostB;
        }

        return Math.min(preCostR, Math.min(preCostG, preCostB));
    }
}
