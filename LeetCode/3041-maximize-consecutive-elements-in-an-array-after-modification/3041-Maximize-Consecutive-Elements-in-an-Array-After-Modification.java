import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();

        int ans = 0;

        for (int x : nums) {

            int a = dp.getOrDefault(x, 0) + 1;
            int b = dp.getOrDefault(x + 1, 0) + 1;

            dp.put(x + 1, a);
            dp.put(x + 2, b);

            ans = Math.max(ans, Math.max(a, b));
        }

        return ans;
    }
}