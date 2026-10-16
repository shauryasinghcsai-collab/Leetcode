class Solution {
    private static final int MOD = 1_000_000_007;

    long[] sumSq;
    long[] sum;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sum = new long[4 * n];
        lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> lastOccurrence = new java.util.HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int prev = lastOccurrence.getOrDefault(x, -1);

            // Update range [prev + 1, j] by adding 1
            if (prev + 1 <= j) {
                update(1, 0, n - 1, prev + 1, j, 1);
            }

            // Accumulate the sum of squares of all subarrays ending at index j
            totalSum = (totalSum + sumSq[1]) % MOD;
            lastOccurrence.put(x, j);
        }

        return (int) (totalSum % MOD);
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // Identity: (c + val)^2 = c^2 + 2*c*val + val^2
        sumSq[node] = (sumSq[node] + 2 * sum[node] * val + val * val * len) % MOD;
        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
    }
}