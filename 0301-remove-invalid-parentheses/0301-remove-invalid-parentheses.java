
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') leftRem++;
            else if (c == ')') {
                if (leftRem > 0) leftRem--;
                else rightRem++;
            }
        }

        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String s, int index, int leftCount, int rightCount,
                           int leftRem, int rightRem, StringBuilder current, List<String> result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = current.length();

        // Choice 1: Remove current character
        // Avoid duplicates by only removing the first bracket in a sequence of consecutive identical brackets
        if ((c == '(' && leftRem > 0) || (c == ')' && rightRem > 0)) {
            if (index == 0 || s.charAt(index - 1) != c) {
                for (int i = index; i < s.length() && s.charAt(i) == c; i++) {
                    int remCount = i - index + 1;
                    if (c == '(' && leftRem >= remCount) {
                        backtrack(s, i + 1, leftCount, rightCount, leftRem - remCount, rightRem, current, result);
                    } else if (c == ')' && rightRem >= remCount) {
                        backtrack(s, i + 1, leftCount, rightCount, leftRem, rightRem - remCount, current, result);
                    }
                }
            }
        }

        // Choice 2: Keep current character
        current.append(c);
        if (c != '(' && c != ')') {
            backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem, current, result);
        } else if (c == '(') {
            backtrack(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, current, result);
        } else if (c == ')' && leftCount > rightCount) {
            backtrack(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, current, result);
        }
        current.setLength(len);
    }
}