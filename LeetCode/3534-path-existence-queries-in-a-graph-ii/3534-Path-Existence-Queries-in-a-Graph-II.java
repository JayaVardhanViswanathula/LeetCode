import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[] parent = new int[n];
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[order[i]] = i;
        }

        int[] sorted = new int[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = nums[order[i]];
        }

        int LOG = 17;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            int j = i;

            while (j + 1 < n && sorted[j + 1] - sorted[i] <= maxDiff) {
                j++;
            }

            up[0][i] = j;
        }

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

            if (up[LOG - 1][u] < v) {
                ans[q] = -1;
                continue;
            }

            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][u] < v) {
                    u = up[k][u];
                    steps += (1 << k);
                }
            }

            ans[q] = steps + 1;
        }

        return ans;
    }
}