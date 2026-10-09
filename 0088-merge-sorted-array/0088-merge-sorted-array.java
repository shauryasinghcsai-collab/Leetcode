class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;     // Pointer for nums1's valid elements
        int j = n - 1;     // Pointer for nums2
        int k = m + n - 1; // Pointer for filling nums1 from the back

        // Compare from back and place the larger element at index k
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Copy remaining elements from nums2 if any exist
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}