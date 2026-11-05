import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Step 1: Sort arr2 so we can use binary search
        Arrays.sort(arr2);
        int count = 0;
        
        for (int x : arr1) {
            // Step 2: Find where x would fit in arr2 using binary search
            int idx = Arrays.binarySearch(arr2, x);
            
            // If exact match is found, distance is 0, which is <= d. So it's invalid.
            if (idx >= 0) {
                continue; 
            }
            
            // If not found, binarySearch returns -(insertion point) - 1
            int insertionPoint = -idx - 1;
            boolean isValid = true;
            
            // Check the element just greater than x (if it exists)
            if (insertionPoint < arr2.length) {
                if (Math.abs(arr2[insertionPoint] - x) <= d) {
                    isValid = false;
                }
            }
            
            // Check the element just smaller than x (if it exists)
            if (insertionPoint > 0) {
                if (Math.abs(arr2[insertionPoint - 1] - x) <= d) {
                    isValid = false;
                }
            }
            
            // If it's far enough from all elements in arr2, count it
            if (isValid) {
                count++;
            }
        }
        
        return count;
    }
}