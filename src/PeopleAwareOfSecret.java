import java.util.LinkedList;
import java.util.Queue;

// LeetCode 2327
public class PeopleAwareOfSecret {
    // fixed moduler
    private static final int MOD = 1000000007;
    // main function to find the num of people aware of secret
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // a secret buffer recording num of people knowing secret of diff days
        int[] secretBuffer = new int[forget + 1];
        secretBuffer[1] = 1;
        // simulate the secret sharing process from Day 2
        for (int i = 2; i <= n; i++) {
            shareSecret(secretBuffer, delay, forget);
        }
        return countNumPeople(secretBuffer);
    }
    // simulate the secret sharing process
    private void shareSecret(int[] secretBuffer, int delay, int forget) {
        int dayBefore = 0;
        // update num of secret-aware people of diff days
        for (int j = 1; j <= forget; j++) {
            int temp = secretBuffer[j];
            secretBuffer[j] = dayBefore;
            dayBefore = temp;
            if (j >= delay && j < forget) {
                secretBuffer[1] += temp;
                secretBuffer[1] %= MOD;
            }
        }
    }
    // count num of people aware of a secret on final day
    private int countNumPeople(int[] secretBuffer) {
        int numPeople = 0;
        // sum up all people knowing the secret
        for (int numPerDay : secretBuffer) {
            numPeople += numPerDay;
            numPeople %= MOD;
        }
        return numPeople;
    }
}