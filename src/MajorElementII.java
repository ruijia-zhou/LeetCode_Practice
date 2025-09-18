import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 229
class MajorElementII {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        List<Integer> majorElements = new ArrayList<>();
        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }
        for (int num : frequencies.keySet()) {
            if (frequencies.get(num) > nums.length / 3) {
                majorElements.add(num);
            }
        }
        return majorElements;
    }
}