class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Step 1: Sort the array
        Arrays.sort(nums);

        int longest = 1;
        int countCurrent = 0;
        int lastSmaller = Integer.MIN_VALUE;

        // Step 2: Iterate through the sorted array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 == lastSmaller) {
                // If it is consecutive, increment count and update lastSmaller
                countCurrent += 1;
                lastSmaller = nums[i];
            } else if (nums[i] != lastSmaller) {
                // If it's a new element and not a duplicate, start a fresh sequence
                countCurrent = 1;
                lastSmaller = nums[i];
            }
            // Update the maximum length found so far
            longest = Math.max(longest, countCurrent);
        }

        return longest;
    }
}