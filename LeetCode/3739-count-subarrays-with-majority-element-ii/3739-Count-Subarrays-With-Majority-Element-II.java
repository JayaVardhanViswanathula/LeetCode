class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int[] bit = new int[2 * n + 2];

        int sum = n + 1;

        update(bit, sum);

        long ans = 0;

        for (int num : nums) {

            if (num == target) {
                sum++;
            } else {
                sum--;
            }

            ans += query(bit, sum - 1);

            update(bit, sum);
        }

        return ans;
    }

    private void update(int[] bit, int i) {

        while (i < bit.length) {
            bit[i]++;
            i += i & -i;
        }
    }

    private int query(int[] bit, int i) {

        int count = 0;

        while (i > 0) {
            count += bit[i];
            i -= i & -i;
        }

        return count;
    }
}