// LeetCode 1143
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() < text2.length()) return longestCommonSubsequence(text2, text1);

        int[] dp = new int[text2.length() + 1];
        int predecessor = 0;
        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                int temp = dp[j];
                if (i == 0 || j == 0) dp[j] = 0;
                else if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[j] = predecessor + 1;
                else dp[j] = Math.max(dp[j], dp[j - 1]);
                predecessor = temp;
            }
        }

        return dp[text2.length()];
    }
}
