import java.util.*;

// LeetCode 1762
public class BuildingWithAnOceanView {
    public int[] findBuildings(int[] heights) {
        List<Integer> oceanViewBuildings = new ArrayList<>();
        int curMaxHeight = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > curMaxHeight) {
                oceanViewBuildings.add(i);
                curMaxHeight = heights[i];
            }
        }
        Collections.reverse(oceanViewBuildings);

        return oceanViewBuildings.stream().mapToInt(Integer::intValue).toArray();
    }
}
