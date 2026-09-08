class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        // Base case: prefix sum 0 has occurred once (handles subarrays starting from index 0)
        map.put(0, 1);
        
        for (int num : nums) {
            currentSum += num;
            
            // If (currentSum - k) exists, add its frequency to total count
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            
            // Record/update the frequency of currentSum in the map
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}