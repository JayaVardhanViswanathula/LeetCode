
class Solution {
    public int zigZagArrays(int n, int l, int r) {

        int MOD = 1_000_000_007;
        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        for (int i = 0; i < m; i++) {
            up[i] = 1;
            down[i] = 1;
        }

        for (int len = 1; len < n; len++) {

            long[] nextUp = new long[m];
            long[] nextDown = new long[m];

            long sum = 0;

            // Prefix sum for increasing transitions
            for (int i = 0; i < m; i++) {
                nextUp[i] = sum;
                sum = (sum + down[i]) % MOD;
            }

            sum = 0;

            // Suffix sum for decreasing transitions
            for (int i = m - 1; i >= 0; i--) {
                nextDown[i] = sum;
                sum = (sum + up[i]) % MOD;
            }

            up = nextUp;
            down = nextDown;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}
