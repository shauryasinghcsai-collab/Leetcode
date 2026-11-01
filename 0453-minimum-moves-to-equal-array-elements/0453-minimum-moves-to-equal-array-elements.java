class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        int sum = 0;
        
        for (int i : nums) {
            mini = Math.min(mini, i); 
            sum += i;
        }
        
        return sum - n * mini;
    }
}