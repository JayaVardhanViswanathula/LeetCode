import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = queries.length;
        int[] ans = new int[m];
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0;

        for (int[] query : q) {
            int start = query[0] - x;
            int end = query[0];

            while (right < logs.length && logs[right][1] <= end) {
                map.put(logs[right][0],
                    map.getOrDefault(logs[right][0], 0) + 1);
                right++;
            }

            while (left < right && logs[left][1] < start) {
                int id = logs[left][0];
                map.put(id, map.get(id) - 1);

                if (map.get(id) == 0)
                    map.remove(id);

                left++;
            }

            ans[query[1]] = n - map.size();
        }

        return ans;
    }
}