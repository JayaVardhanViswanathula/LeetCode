import java.util.*;

class Solution {
    TreeMap<Integer, Integer> lower = new TreeMap<>();
    TreeMap<Integer, Integer> upper = new TreeMap<>();

    int lowerSize = 0, upperSize = 0;
    long lowerSum = 0, upperSum = 0;

    void add(int val) {
        if (lower.isEmpty() || val <= lower.lastKey()) {
            lower.merge(val, 1, Integer::sum);
            lowerSize++;
            lowerSum += val;
        } else {
            upper.merge(val, 1, Integer::sum);
            upperSize++;
            upperSum += val;
        }
        balance();
    }

    void remove(int val) {
        if (lower.containsKey(val)) {
            lower.merge(val, -1, Integer::sum);
            if (lower.get(val) == 0)
                lower.remove(val);
            lowerSize--;
            lowerSum -= val;
        } else {
            upper.merge(val, -1, Integer::sum);
            if (upper.get(val) == 0)
                upper.remove(val);
            upperSize--;
            upperSum -= val;
        }
        balance();
    }

    void balance() {
        while (lowerSize > upperSize + 1) {
            int val = lower.lastKey();
            removeFromMap(lower, val);
            lowerSize--;
            lowerSum -= val;

            upper.merge(val, 1, Integer::sum);
            upperSize++;
            upperSum += val;
        }

        while (lowerSize < upperSize) {
            int val = upper.firstKey();
            removeFromMap(upper, val);
            upperSize--;
            upperSum -= val;

            lower.merge(val, 1, Integer::sum);
            lowerSize++;
            lowerSum += val;
        }
    }

    void removeFromMap(TreeMap<Integer, Integer> map, int val) {
        map.merge(val, -1, Integer::sum);
        if (map.get(val) == 0)
            map.remove(val);
    }

    long getCost() {
        long median = lower.lastKey();

        return median * lowerSize - lowerSum
                + upperSum - median * upperSize;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        lower.clear();
        upper.clear();
        lowerSize = upperSize = 0;
        lowerSum = upperSum = 0;

        for (int i = 0; i < n; i++) {
            add(nums[i]);

            if (i >= x)
                remove(nums[i - x]);

            if (i >= x - 1)
                cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}