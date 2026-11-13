class Solution {
    static final long MOD = 1000000007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1) return m;

        int size = 2 * m;

        long[][] mat = new long[size][size];

        // Build transition matrix
        for (int i = 0; i < m; i++) {

            // New UP state
            for (int j = 0; j < i; j++) {
                mat[i][m + j] = 1;
            }

            // New DOWN state
            for (int j = i + 1; j < m; j++) {
                mat[m + i][j] = 1;
            }
        }

        // Initial state
        long[] dp = new long[size];

        for (int i = 0; i < size; i++) {
            dp[i] = 1;
        }

        // Binary exponentiation
        long[][] result = power(mat, n - 1);

        // Multiply matrix with initial vector
        long[] ans = new long[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ans[i] = (ans[i] + result[i][j] * dp[j]) % MOD;
            }
        }

        long total = 0;

        for (long x : ans) {
            total = (total + x) % MOD;
        }

        return (int) total;
    }

    private long[][] power(long[][] mat, int exp) {
        int n = mat.length;

        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, mat);
            }

            mat = multiply(mat, mat);
            exp >>= 1;
        }

        return result;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;

        long[][] c = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {
                    c[i][j] = (c[i][j] +
                            a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return c;
    }
}