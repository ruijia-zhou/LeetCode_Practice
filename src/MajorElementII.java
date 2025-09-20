import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 229
class MajorElementII {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> numToFrequency = getNumToFrequency(nums);

        List<Integer> majorElements = new ArrayList<>();
        for (int num : numToFrequency.keySet()) {
            if (numToFrequency.get(num) > nums.length / 3) {
                majorElements.add(num);
            }
        }

        return majorElements;
    }

    private Map<Integer, Integer> getNumToFrequency(int[] nums){
        Map<Integer, Integer> numToFrequency = new HashMap<>();
        for (int num : nums) {
            numToFrequency.put(num, numToFrequency.getOrDefault(num, 0) + 1);
        }

        return numToFrequency;
    }
}