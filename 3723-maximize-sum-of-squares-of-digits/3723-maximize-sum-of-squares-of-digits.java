class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the total sum is greater than what 9s can provide, or less than needed
        if (sum > 9 * num || sum < 1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            sb.append(digit);
            sum -= digit;
        }
        
        return sb.toString();
    }
}