class Solution {
    public int minEatingSpeed(int[] nums, int h) {
        int low = 1;
        int high = 0;
        
        // Find the maximum element in nums to set the upper bound
        for (int i = 0; i < nums.length; i++) {
            high = Math.max(high, nums[i]);
        }
        
        int ans = high;
        
        // Binary search for the minimum valid eating speed
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canFinish(nums, mid, h)) {
                ans = mid;         // Try to find a smaller speed on the left
                high = mid - 1;
            } else {
                low = mid + 1;     // Too slow, need a higher speed
            }
        }
        
        return ans;
    }
    
    // Helper method to check if Koko can eat all bananas at speed k within h hours
    private boolean canFinish(int[] nums, int k, int h) {
        long totalHours = 0;
        for (int i = 0; i < nums.length; i++) {
            totalHours += (nums[i] + k - 1) / k;
        }
        return totalHours <= h;
    }
}