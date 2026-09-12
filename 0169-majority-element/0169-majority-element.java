public class Solution {
    public static int majorityElement(int[] v) {
        int n = v.length;
        int count = 0;
        int candidate = -1;

        // Step 1: Find the majority candidate using Moore's Voting Algorithm
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                count = 1;
                candidate = v[i];
            } else if (v[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate; // Guaranteed to be the majority element
    }
}