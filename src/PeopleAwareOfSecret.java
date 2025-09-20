// LeetCode 2327
public class PeopleAwareOfSecret {
    private static final int FIXED_MODULAR = 1000000007;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[] secretBuffer = new int[forget + 1];
        secretBuffer[1] = 1;
        for (int i = 2; i <= n; i++) {
            shareSecret(secretBuffer, delay, forget);
        }

        return countNumPeopleAwareOfSecretsOnFinalDay(secretBuffer);
    }

    private void shareSecret(int[] secretBuffer, int delay, int forget) {
        int dayBefore = 0;
        for (int j = 1; j <= forget; j++) {
            int temp = secretBuffer[j];
            secretBuffer[j] = dayBefore;
            dayBefore = temp;
            if (j >= delay && j < forget) {
                secretBuffer[1] += temp;
                secretBuffer[1] %= FIXED_MODULAR;
            }
        }
    }

    private int countNumPeopleAwareOfSecretsOnFinalDay(int[] secretBuffer) {
        int numPeopleKnowingSecret  = 0;
        // sum up all people knowing the secret
        for (int numPerDay : secretBuffer) {
            numPeopleKnowingSecret  += numPerDay;
            numPeopleKnowingSecret  %= FIXED_MODULAR;
        }

        return numPeopleKnowingSecret ;
    }
}