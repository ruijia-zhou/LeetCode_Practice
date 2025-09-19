import java.util.ArrayDeque;
import java.util.Deque;

// LeetCode 739
public class DailyTemperatures {
    private int[] temperatures;
    private int[] waitingDays;
    private Deque<Integer> colderDays;

    public int[] dailyTemperatures(int[] temperatures) {
        int numDays = temperatures.length;
        this.temperatures = temperatures;
        this.waitingDays = new int[numDays];
        this.colderDays = new ArrayDeque<>();
        for (int currDay = 0; currDay < numDays; currDay++) updateOldDays(currDay);
        return waitingDays;
    }

    private void updateOldDays(int currDay){
        int currentTemp = this.temperatures[currDay];
        while (!this.colderDays.isEmpty() && this.temperatures[this.colderDays.peek()] < currentTemp) {
            int prevDay = this.colderDays.pop();
            this.waitingDays[prevDay] = currDay - prevDay;
        }
        this.colderDays.push(currDay);
    }
}
