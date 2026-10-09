class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openNeeded = 0; // Tracks the number of ')' needed to balance currently open '('

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If we need an odd number of ')', it means a single ')' was seen previously.
                // We must complete that pair with a ')' before opening a new '('
                if (openNeeded % 2 != 0) {
                    insertions++; // Insert ')'
                    openNeeded--; // Balance the previous '('
                }
                openNeeded += 2; // Each '(' expects two ')'
            } else { // c == ')'
                openNeeded--;
                // If we encounter a ')' without a matching '(', insert '('
                if (openNeeded < 0) {
                    insertions++; // Insert '('
                    openNeeded += 2; // Inserting '(' gives us 2 needed ')' (and 1 is satisfied by current ')')
                }
            }
        }

        // Add remaining missing ')' characters
        return insertions + openNeeded;
    }
}