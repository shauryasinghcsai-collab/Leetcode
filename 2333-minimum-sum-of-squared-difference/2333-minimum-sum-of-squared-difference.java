

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + (long) k2;
        
        // Find maximum difference to size our frequency array
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }
        
        // Frequency array to count occurrences of each absolute difference
        long[] freq = new long[maxDiff + 1];
        for (int d : diffs) {
            freq[d]++;
        }
        
        // Greedily reduce the largest differences from maxDiff down to 1
        for (int d = maxDiff; d > 0 && totalOps > 0; d--) {
            if (freq[d] == 0) continue;
            
            // Determine how many we can reduce from current difference d to d - 1
            long count = freq[d];
            long operationsNeeded = Math.min(totalOps, count);
            
            freq[d] -= operationsNeeded;
            freq[d - 1] += operationsNeeded;
            totalOps -= operationsNeeded;
        }
        
        // If we still have operations left over and all differences are 0, we can just waste them
        if (totalOps > 0) {
            return 0;
        }
        
        // Calculate the final minimum sum of squared differences
        long minSum = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (freq[d] > 0) {
                minSum += freq[d] * (long) d * d;
            }
        }
        
        return minSum;
    }
}