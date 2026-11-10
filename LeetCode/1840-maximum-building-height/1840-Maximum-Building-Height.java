
import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {

        List<int[]> list = new ArrayList<>();

        list.add(new int[]{1, 0});

        for (int[] r : restrictions) {
            list.add(r);
        }

        if (restrictions.length == 0 ||
            restrictions[restrictions.length - 1][0] != n) {
            list.add(new int[]{n, n - 1});
        }

        list.sort((a, b) -> a[0] - b[0]);

        int m = list.size();

        // Left to Right
        for (int i = 1; i < m; i++) {
            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);

            curr[1] = Math.min(curr[1],
                    prev[1] + curr[0] - prev[0]);
        }

        // Right to Left
        for (int i = m - 2; i >= 0; i--) {
            int[] curr = list.get(i);
            int[] next = list.get(i + 1);

            curr[1] = Math.min(curr[1],
                    next[1] + next[0] - curr[0]);
        }

        int ans = 0;

        // Find maximum possible height
        for (int i = 1; i < m; i++) {
            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);

            int distance = curr[0] - prev[0];

            int height = (prev[1] + curr[1] + distance) / 2;

            ans = Math.max(ans, height);
        }

        return ans;
    }
}
