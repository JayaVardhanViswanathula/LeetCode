import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] pos = new int[n];
        int[] sorted = new int[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = nums[order[i]];
            pos[order[i]] = i;
        }

        int LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[LOG][n];

        // Two pointers
        int r = 0;

        for (int i = 0; i < n; i++) {

            r = Math.max(r, i);

            while (r + 1 < n &&
                   (long) sorted[r + 1] - sorted[i] <= maxDiff) {
                r++;
            }

            up[0][i] = r;
        }

        // Binary lifting
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int u = pos[queries[q][0]];
            int v = pos[queries[q][1]];

            if (u == v) {
                ans[q] = 0;
                continue;
            }

            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }

            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {

                if (up[k][u] < v) {
                    u = up[k][u];
                    steps += (1 << k);
                }
            }

            // Check whether destination is reachable
            if (up[0][u] < v) {
                ans[q] = -1;
            } else {
                ans[q] = steps + 1;
            }
        }

        return ans;
    }
}