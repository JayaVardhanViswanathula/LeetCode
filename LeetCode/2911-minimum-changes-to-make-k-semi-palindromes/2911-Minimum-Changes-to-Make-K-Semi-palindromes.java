class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // Step 1: Precompute the cost of every substring
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                cost[i][j] = len;

                // Try every valid divisor
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Compare characters in each group
                    for (int offset = 0; offset < d; offset++) {

                        int left = i + offset;
                        int right = j - d + 1 + offset;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }

        // Step 2: DP for partitioning
        int INF = 100000;
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[n][0] = 0;

        // Process starting positions backwards
        for (int i = n - 1; i >= 0; i--) {

            for (int parts = 1; parts <= k; parts++) {

                // Every substring must have length >= 2
                for (int j = i + 1; j < n; j++) {

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][j] + dp[j + 1][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}