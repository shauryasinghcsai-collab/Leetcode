

class Solution {
    public int numberOfPermutations(int n, int[][] requirements) {
        int MOD = 1_000_000_007;

        // Map requirements by end index
        int[] req = new int[n];
        Arrays.fill(req, -1);
        int maxCnt = 0;
        for (int[] r : requirements) {
            req[r[0]] = r[1];
            maxCnt = Math.max(maxCnt, r[1]);
        }

        // Base case: length 1 (index 0)
        // If index 0 has a requirement != 0, no valid permutation exists.
        if (req[0] != -1 && req[0] != 0) {
            return 0;
        }

        // dp[j] holds number of permutations of current prefix with j inversions
        int[] dp = new int[maxCnt + 1];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int[] nextDp = new int[maxCnt + 1];

            // Prefix sum approach to transition in O(1) per state
            long windowSum = 0;
            for (int j = 0; j <= maxCnt; j++) {
                windowSum = (windowSum + dp[j]) % MOD;
                if (j > i) {
                    windowSum = (windowSum - dp[j - i - 1] + MOD) % MOD;
                }
                nextDp[j] = (int) windowSum;
            }

            // Apply requirement constraint at index i if present
            if (req[i] != -1) {
                int requiredCnt = req[i];
                for (int j = 0; j <= maxCnt; j++) {
                    if (j != requiredCnt) {
                        nextDp[j] = 0;
                    }
                }
            }

            dp = nextDp;
        }

        return dp[req[n - 1]];
    }
}