

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squared[i] = val * val;
        }
        
        Arrays.sort(squared);
        
        long score = 0;
        int mid = n / 2;
        
        // Subtract the smaller half (odd indices contribute negatively)
        for (int i = 0; i < mid; i++) {
            score -= squared[i];
        }
        
        // Add the larger half (even indices contribute positively)
        for (int i = mid; i < n; i++) {
            score += squared[i];
        }
        
        return score;
    }
}