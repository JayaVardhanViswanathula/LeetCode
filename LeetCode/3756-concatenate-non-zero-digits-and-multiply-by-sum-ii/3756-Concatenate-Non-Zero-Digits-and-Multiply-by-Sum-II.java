class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {

        int MOD = 1000000007;
        int n = s.length();

        long[] prefixNum = new long[n + 1];
        long[] prefixSum = new long[n + 1];

        int[] count = new int[n + 1];

        long[] pow10 = new long[n + 1];

        pow10[0] = 1;

        // Precompute powers of 10
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Build prefix arrays
        for (int i = 0; i < n; i++) {

            int digit = s.charAt(i) - '0';

            prefixSum[i + 1] = prefixSum[i] + digit;

            count[i + 1] = count[i];

            prefixNum[i + 1] = prefixNum[i];

            if (digit != 0) {

                prefixNum[i + 1] =
                    (prefixNum[i] * 10 + digit) % MOD;

                count[i + 1]++;
            }
        }

        int[] ans = new int[queries.length];

        // Process queries
        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            // Calculate digit sum
            long sum = prefixSum[r + 1] - prefixSum[l];

            // Count non-zero digits
            int k = count[r + 1] - count[l];

            // Calculate concatenated number
            long x = (
                prefixNum[r + 1]
                - prefixNum[l] * pow10[k] % MOD
                + MOD
            ) % MOD;

            // Calculate final answer
            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans;
    }
}