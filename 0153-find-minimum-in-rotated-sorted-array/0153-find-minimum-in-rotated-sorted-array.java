class Solution {
    public int findMin(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int ans = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If the search space is already sorted
            if (arr[low] <= arr[high]) {
                ans = Math.min(ans, arr[low]);
                break;
            }

            // Left half is sorted
            if (arr[low] <= arr[mid]) {
                ans = Math.min(ans, arr[low]);
                low = mid + 1;
            }
            // Right half is sorted
            else {
                ans = Math.min(ans, arr[mid]);
                high = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        int[] arr = {4, 5, 6, 7, 0, 1, 2};

        System.out.println("Minimum element: " + obj.findMin(arr));
    }
}