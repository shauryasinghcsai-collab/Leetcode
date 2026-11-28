import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int[] factorFreq = new int[n + 1];

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
            factorFreq[item[0]]++;
        }

        int[] multiplesCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (factorFreq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multiplesCount[f] += factorFreq[m];
            }
        }

        List<int[]> bonusUnits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int p = items[i][1];
            int gain = multiplesCount[f] - 1;
            if (gain > 0 && (long) p < 2L * minPrice) {
                bonusUnits.add(new int[]{p, gain});
            }
        }

        bonusUnits.sort(Comparator.comparingInt(a -> a[0]));

        long remBudget = budget;
        long itemsFromBonus = 0;

        for (int[] unit : bonusUnits) {
            long p = unit[0];
            long gain = unit[1];
            if (remBudget < p) break;
            long buy = Math.min((long) gain, remBudget / p);
            itemsFromBonus += buy * 2;
            remBudget -= buy * p;
        }

        long ans = itemsFromBonus + (remBudget / minPrice);
        long allCheapest = budget / minPrice;

        return (int) Math.max(ans, allCheapest);
    }
}