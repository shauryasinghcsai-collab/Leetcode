class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }
        
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int d = 1, load = 0;
            
            for (int w : weights) {
               
                if (load + w > mid) {
                    d++;
                    load = w;
                } else {
                    load += w;
                }
            }
            
            if (d <= days) {
                ans = mid;
                high = mid - 1; 
            } else {
                low = mid + 1;  
            }
        }
        return ans;
    }
}