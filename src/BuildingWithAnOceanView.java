import java.util.Stack;

// LeetCode 1762
public class BuildingWithAnOceanView {
    private final Stack<Integer> oceanViewBuildings = new Stack<>();

    public int[] findBuildings(int[] heights) {
        int curMaxHeight = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > curMaxHeight) {
                this.oceanViewBuildings.push(i);
                curMaxHeight = heights[i];
            }
        }
        return reverseBuildingIndices();
    }

    private int[] reverseBuildingIndices() {
        int[] reversedOceanViewBuildings = new int[this.oceanViewBuildings.size()];
        for (int i = 0; i < reversedOceanViewBuildings.length; i++) {
            reversedOceanViewBuildings[i] = this.oceanViewBuildings.pop();
        }
        return reversedOceanViewBuildings;
    }
}
