import java.util.*;

class Solution {
    
    static class SlidingWindowMedianCost {
        private final PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Long> minHeap = new PriorityQueue<>();
        private final Map<Long, Integer> delayedRemove = new HashMap<>();
        
        private long leftSum = 0;
        private long rightSum = 0;
        private int leftSize = 0;
        private int rightSize = 0;
        
        public void add(long num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num);
                leftSum += num;
                leftSize++;
            } else {
                minHeap.add(num);
                rightSum += num;
                rightSize++;
            }
            balance();
        }
        
        public void remove(long num) {
            delayedRemove.put(num, delayedRemove.getOrDefault(num, 0) + 1);
            if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
                leftSum -= num;
                leftSize--;
            } else {
                rightSum -= num;
                rightSize--;
            }
            balance();
            prune();
        }
        
        private void balance() {
            while (leftSize > rightSize + 1) {
                long top = maxHeap.poll();
                leftSum -= top;
                leftSize--;
                minHeap.add(top);
                rightSum += top;
                rightSize++;
                prune();
            }
            while (rightSize > leftSize) {
                long top = minHeap.poll();
                rightSum -= top;
                rightSize--;
                maxHeap.add(top);
                leftSum += top;
                leftSize++;
                prune();
            }
        }
        
        private void prune() {
            while (!maxHeap.isEmpty() && delayedRemove.getOrDefault(maxHeap.peek(), 0) > 0) {
                long top = maxHeap.poll();
                delayedRemove.put(top, delayedRemove.get(top) - 1);
                if (delayedRemove.get(top) == 0) delayedRemove.remove(top);
            }
            while (!minHeap.isEmpty() && delayedRemove.getOrDefault(minHeap.peek(), 0) > 0) {
                long top = minHeap.poll();
                delayedRemove.put(top, delayedRemove.get(top) - 1);
                if (delayedRemove.get(top) == 0) delayedRemove.remove(top);
            }
        }
        
        public long getCost() {
            prune();
            long median = maxHeap.peek();
            return (median * leftSize - leftSum) + (rightSum - median * rightSize);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCost = new long[n - x + 1];
        
        SlidingWindowMedianCost sw = new SlidingWindowMedianCost();
        for (int i = 0; i < x; i++) {
            sw.add(nums[i]);
        }
        windowCost[0] = sw.getCost();
        
        for (int i = x; i < n; i++) {
            sw.remove(nums[i - x]);
            sw.add(nums[i]);
            windowCost[i - x + 1] = sw.getCost();
        }
        
        // DP to pick k non-overlapping windows of size x
        long[] prev = new long[n + 1];
        Arrays.fill(prev, Long.MAX_VALUE / 2);
        
        for (int i = 0; i <= n; i++) {
            prev[i] = 0; 
        }
        
        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, Long.MAX_VALUE / 2);
            
            long minPrev = Long.MAX_VALUE / 2;
            
            for (int i = 1; i <= n; i++) {
                if (i >= x) {
                    minPrev = Math.min(minPrev, prev[i - x]);
                    curr[i] = Math.min(curr[i - 1], minPrev + windowCost[i - x]);
                } else {
                    curr[i] = curr[i - 1];
                }
            }
            prev = curr;
        }
        
        return prev[n];
    }
}