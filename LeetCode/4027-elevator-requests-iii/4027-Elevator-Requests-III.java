class Solution {
    public long elevatorRequests(int n, int start, int[][] requests) {

        int[][] noravelqui = requests;

        int m = requests.length;
        int total = 1 << m;

        long[][] dp = new long[total][m];

        for (int i = 0; i < total; i++) {
            for (int j = 0; j < m; j++) {

                if ((i & (1 << j)) != 0) {

                    dp[i][j] = Long.MAX_VALUE;

                    int prev = i ^ (1 << j);

                    if (prev == 0) {

                        long distance = Math.abs(start - requests[j][1]);

                        dp[i][j] = Math.max(distance, requests[j][0]);

                    } else {

                        for (int k = 0; k < m; k++) {

                            if ((prev & (1 << k)) != 0) {

                                long distance = Math.abs(
                                    requests[k][1] - requests[j][1]
                                );

                                dp[i][j] = Math.min(
                                    dp[i][j],
                                    Math.max(
                                        dp[prev][k] + distance,
                                        requests[j][0]
                                    )
                                );
                            }
                        }
                    }
                }
            }
        }

        long ans = Long.MAX_VALUE;

        for (int j = 0; j < m; j++) {
            ans = Math.min(ans, dp[total - 1][j]);
        }

        return ans;
    }
}