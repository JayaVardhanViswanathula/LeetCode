import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {

        HashMap<Long, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put((long) num, map.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        if (map.getOrDefault(1L, 0) % 2 == 1) {
            ans = map.get(1L);
        } else if (map.containsKey(1L)) {
            ans = map.get(1L) - 1;
        }

        for (long x : map.keySet()) {

            if (x == 1) continue;

            long curr = x;
            int count = 0;

            while (map.getOrDefault(curr, 0) >= 2) {
                count += 2;
                curr = curr * curr;
            }

            if (map.containsKey(curr)) {
                count++;
            } else {
                count--;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}