
class Solution {
    public char processStr(String s, long k) {

        int n = s.length();
        long[] len = new long[n + 1];

        // Step 1: Calculate length after each operation
        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = len[i] + 1;
            }
            else if (ch == '*') {
                len[i + 1] = Math.max(0, len[i] - 1);
            }
            else if (ch == '#') {
                len[i + 1] = len[i] * 2;
            }
            else if (ch == '%') {
                len[i + 1] = len[i];
            }
        }

        // Step 2: Check whether k is valid
        if (k >= len[n]) {
            return '.';
        }

        // Step 3: Traverse backwards
        for (int i = n - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {

                if (k == len[i]) {
                    return ch;
                }
            }

            else if (ch == '*') {
                // Deleted last character
                // Position remains unchanged
            }

            else if (ch == '#') {

                if (k >= len[i]) {
                    k -= len[i];
                }
            }

            else if (ch == '%') {
                k = len[i] - 1 - k;
            }
        }

        return '.';
    }
}
