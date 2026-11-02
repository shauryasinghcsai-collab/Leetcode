class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = 0;

        // Find maximum element
        for (int num : nums) {
            high = Math.max(high, num);
        }

        // Binary Search
        while (low <= high) {

            int mid = low + (high - low) / 2;

            int sum = 0;

            // Calculate sum of ceil(nums[i] / mid)
            for (int num : nums) {
                sum += (num + mid - 1) / mid;
            }

            if (sum <= threshold) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    }
