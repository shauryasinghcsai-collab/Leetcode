class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if(n==0) return 0;
        int mini=prices[0];
        int maxprofit=0;
        for(int i=1;i<n;i++){
            int cost=prices[i]-mini;
            maxprofit=Math.max(maxprofit,cost);
            mini=Math.min(mini,prices[i]);
        }
        return maxprofit;
    }
}