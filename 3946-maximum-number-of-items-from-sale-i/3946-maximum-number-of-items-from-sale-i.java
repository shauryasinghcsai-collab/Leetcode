class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxVal = 1500;
        int[] factorCount = new int[maxVal + 1];

        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        int[] multiplesCount = new int[maxVal + 1];
        for (int f = 1; f <= maxVal; f++) {
            for (int m = f; m <= maxVal; m += f) {
                multiplesCount[f] += factorCount[m];
            }
        }

        int n = items.length;
        int[] bonus = new int[n];
        for (int i = 0; i < n; i++) {
            bonus[i] = multiplesCount[items[i][0]] - 1;
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];
            int freeItems = bonus[i];

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1 + freeItems);
            }

            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b]);
        }
        return ans;
    }
}