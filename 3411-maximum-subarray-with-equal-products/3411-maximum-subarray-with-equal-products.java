class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1L;
            long gcdVal = 0L;
            long lcmVal = 1L;

            for (int j = i; j < n; j++) {
                long cur = nums[j];
                gcdVal = gcd(gcdVal, cur);
                lcmVal = lcm(lcmVal, cur);

                if (prod > Long.MAX_VALUE / cur) {
                    break;
                }
                prod *= cur;

                if (prod > 25200L) {
                    break;
                }

                if (prod == lcmVal * gcdVal) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0L) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0L || b == 0L) {
            return 0L;
        }
        return (a / gcd(a, b)) * b;
    }
}