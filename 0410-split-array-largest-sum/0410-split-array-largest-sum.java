class Solution {
    public int splitArray(int[] nums, int k) {
        int low = 0;
        int high = 0;
        
        // Step 1: Find the search range for binary search
        for (int num : nums) {
            low = Math.max(low, num); // low is the maximum element in nums
            high += num;              // high is the total sum of all elements
        }
        
        int ans = high;
        
        // Step 2: Apply binary search on the answer
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canSplit(nums, k, mid)) {
                ans = mid;         // Record the valid answer
                high = mid - 1;    // Try to find a smaller maximum sum
            } else {
                low = mid + 1;     // Increase the allowed sum limit
            }
        }
        
        return ans;
    }
    
    // Helper function to check if we can split nums into at most k subarrays
    // where each subarray sum does not exceed 'allowedSum'
    private boolean canSplit(int[] nums, int k, int allowedSum) {
        int subarrays = 1;
        int currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num <= allowedSum) {
                currentSum += num; // Add to the current subarray
            } else {
                subarrays++;       // Start a new subarray
                currentSum = num;
            }
        }
        
        return subarrays <= k;
    }
}