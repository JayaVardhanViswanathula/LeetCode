class Solution {
    public int subsequencePairCount(int[] nums) {
        int mod = 1000000007;
        int n = nums.length;
        int m = 0;

        for (int x : nums)
            m = Math.max(m, x);

        int[][] dp = new int[m + 1][m + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] ndp = new int[m + 1][m + 1];

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= m; j++) {
                    if (dp[i][j] == 0) continue;

                    ndp[i][j] = (ndp[i][j] + dp[i][j]) % mod;

                    int a = gcd(i, x);
                    int b = gcd(j, x);

                    ndp[a][j] = (ndp[a][j] + dp[i][j]) % mod;
                    ndp[i][b] = (ndp[i][b] + dp[i][j]) % mod;
                }
            }

            dp = ndp;
        }

        int ans = 0;

        for (int i = 1; i <= m; i++)
            ans = (ans + dp[i][i]) % mod;

        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}