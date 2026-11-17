import java.util.*;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();
        int MOD = 1_000_000_007;

        int[][] dp = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Starting position
        dp[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        // Bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (board.get(i).charAt(j) == 'X') {
                    continue;
                }

                if (i == n - 1 && j == n - 1) {
                    continue;
                }

                int best = -1;
                int count = 0;

                // Down
                if (i + 1 < n) {
                    if (dp[i + 1][j] > best) {
                        best = dp[i + 1][j];
                        count = ways[i + 1][j];
                    } else if (dp[i + 1][j] == best && best != -1) {
                        count = (count + ways[i + 1][j]) % MOD;
                    }
                }

                // Right
                if (j + 1 < n) {
                    if (dp[i][j + 1] > best) {
                        best = dp[i][j + 1];
                        count = ways[i][j + 1];
                    } else if (dp[i][j + 1] == best && best != -1) {
                        count = (count + ways[i][j + 1]) % MOD;
                    }
                }

                // Diagonal
                if (i + 1 < n && j + 1 < n) {
                    if (dp[i + 1][j + 1] > best) {
                        best = dp[i + 1][j + 1];
                        count = ways[i + 1][j + 1];
                    } else if (dp[i + 1][j + 1] == best && best != -1) {
                        count = (count + ways[i + 1][j + 1]) % MOD;
                    }
                }

                if (best == -1) {
                    continue;
                }

                char ch = board.get(i).charAt(j);

                int value = Character.isDigit(ch) ? ch - '0' : 0;

                dp[i][j] = best + value;
                ways[i][j] = count;
            }
        }

        if (dp[0][0] == -1) {
            return new int[]{0, 0};
        }

        return new int[]{dp[0][0], ways[0][0]};
    }
}