
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Check if nums is a rotation of [0, 1, ..., n-1]
        int idx0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }

        boolean isIncRotation = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 + i) % n] != i) {
                isIncRotation = false;
                break;
            }
        }

        if (isIncRotation) {
            // Option 1: Rotate left idx0 times
            // Option 2: Reverse (1 op), then rotate left (n - idx0) times, then reverse (1 op) -> total idx0 + 2 (if idx0 > 0)
            int ans1 = idx0;
            int ans2 = (idx0 == 0) ? 0 : (n - idx0 + 2);
            return Math.min(ans1, ans2);
        }

        // Check if nums is a rotation of decreasing order [n-1, n-2, ..., 0]
        int idxMax = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == n - 1) {
                idxMax = i;
                break;
            }
        }

        boolean isDecRotation = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idxMax + i) % n] != n - 1 - i) {
                isDecRotation = false;
                break;
            }
        }

        if (isDecRotation) {
            // Option 1: Reverse first (1 op), then rotate left
            // Option 2: Rotate left first, then reverse (1 op)
            int ans1 = 1 + ((n - idxMax) % n);
            int ans2 = idxMax + 1;
            return Math.min(ans1, ans2);
        }

        return -1;
    }
}