import java.util.ArrayDeque;
import java.util.Deque;

// LeetCode 739
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int numDays = temperatures.length;
        int[] waitingDays = new int[numDays];
        Deque<Integer> colderDays = new ArrayDeque<>();
        for (int currDay = 0; currDay < numDays; currDay++) {
            int currentTemp = temperatures[currDay];
            while (!colderDays.isEmpty() && temperatures[colderDays.peek()] < currentTemp) {
                int prevDay = colderDays.pop();
                waitingDays[prevDay] = currDay - prevDay;
            }
            colderDays.push(currDay);
        }
        return waitingDays;
    }
}
