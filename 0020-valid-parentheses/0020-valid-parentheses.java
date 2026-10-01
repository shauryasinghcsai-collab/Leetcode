class Solution {
    public boolean isValid(String s) {
       if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Push corresponding closing bracket when an open bracket is met
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // If stack is empty OR top of stack doesn't match the closing bracket
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        // String is valid if no unmatched open brackets remain
        return stack.isEmpty();
    }
    }