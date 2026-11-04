import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Hash map to store the value and its index
        Map<Integer, Integer> seen = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // If complement is found, return the pair of indices
            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }
            
            // Store the index of the current number
            seen.put(nums[i], i);
        }
        
        return new int[] {}; // Fallback if no solution is found
    }
}
